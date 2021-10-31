package thenethersurvivalist;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import carpet.settings.ParsedRule;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.WorldSavePath;
import org.apache.commons.io.IOUtils;
import thenethersurvivalist.utils.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class TheNetherSurvivalistExtension implements CarpetExtension {
    public static final String MOD_ID = "thenethersurvivalist";
    public static final String MOD_NAME = "The Nether Survivalist";
    public static final String MOD_VERSION = "1.2.0";

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(TheNetherSurvivalistSettings.class);

        // SCRIPTS
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("c", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("graves", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("mobleash", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("crumbleconcrete", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("revivecoral", false));

        // Advancement
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("customadvancements", false));
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        registerDatapackRule(server, "AnvilLimit");
        registerDatapackRule(server, "BlackDyeFromFlint");
        registerDatapackRule(server, "BlackstoneCraftings");
        registerDatapackRule(server, "BottledExp");
        registerDatapackRule(server, "C");
        registerDatapackRule(server, "CraftableRedSand");
        registerDatapackRule(server, "CrumbleConcrete");
        registerDatapackRule(server, "CustomAdvancements");
        registerDatapackRule(server, "CustomPiglinBartering");
        registerDatapackRule(server, "EnchantableShears");
        registerDatapackRule(server, "EndermanNoGrief");
        registerDatapackRule(server, "FairAnvil");
        registerDatapackRule(server, "FairEnchanting");
        registerDatapackRule(server, "FarmlandMoisture");
        registerDatapackRule(server, "FireballNoGrief");
        // FlyCommand
        registerDatapackRule(server, "GoldenCarrotsPlant");
        registerDatapackRule(server, "Graves");
        registerDatapackRule(server, "HardenedConcretePowder");
        registerDatapackRule(server, "LavaAffinity");
        registerDatapackRule(server, "MendingFromEnchanting");
        registerDatapackRule(server, "MobLeash");
        registerDatapackRule(server, "NetherCoral");
        registerDatapackRule(server, "NetherDepthStrider");
        registerDatapackRule(server, "NetherrackBoneMeal");
        registerDatapackRule(server, "NoNetherPortal");
        registerDatapackRule(server, "ObsidianWalker");
        registerDatapackRule(server, "OrangeDyeFromRedGlowstone");
        registerDatapackRule(server, "PumpkinSeeds");
        registerDatapackRule(server, "RedDyeFromNetherWart");
        registerDatapackRule(server, "RemovedAcquaticEnchantments");
        registerDatapackRule(server, "RespawnAnchorDischarging");
        registerDatapackRule(server, "ReviveCoral");
        registerDatapackRule(server, "ScaffoldingNetherVines");
        registerDatapackRule(server, "ShulkerShell");
        registerDatapackRule(server, "TargetWartBlock");
        registerDatapackRule(server, "VerticalSlabs");
        registerDatapackRule(server, "WitherSkullNoGrief");
        initializeDatapackRules(server);
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        FlyCommand.register(dispatcher);
        VersionCommand.register(dispatcher);
        PortalCommand.register(dispatcher);
        Portal2Command.register(dispatcher);
        Portal3Command.register(dispatcher);
    }

    @Override
    public String version() {
        return MOD_ID;
    }

    public static Map<String, String> datapackRules = new HashMap<>();

    public void initializeDatapackRules(MinecraftServer server) {
        ResourcePackManager resourcePackManager = server.getCommandSource().getMinecraftServer().getDataPackManager();
        resourcePackManager.scanPacks();
        List<ResourcePackProfile> list = Lists.newArrayList(resourcePackManager.getEnabledProfiles());
        datapackRules.forEach((ruleName, datapackName) -> {
            ResourcePackProfile resourcePackProfile = resourcePackManager.getProfile("file/" + datapackName + ".zip");
            if (resourcePackProfile != null) list.remove(resourcePackProfile);
        });
        ReloadCommand.method_29480(list.stream().map(ResourcePackProfile::getName).collect(toList()), server.getCommandSource());
        datapackRules.forEach((ruleName, datapackName) -> copyDatapackFolder(server, datapackName));
        resourcePackManager.scanPacks();
        datapackRules.forEach((ruleName, datapackName) -> {
            ParsedRule<?> rule = CarpetServer.settingsManager.getRule(ruleName);
            ResourcePackProfile resourcePackProfile = resourcePackManager.getProfile("file/" + datapackName + ".zip");
            if (rule.getBoolValue() || (rule.type == String.class && !rule.get().equals("false"))) {
                if (!list.contains(resourcePackProfile))
                    resourcePackProfile.getInitialPosition().insert(list, resourcePackProfile, (pack) -> pack, false);
            } else {
                list.remove(resourcePackProfile);
            }
        });
        ReloadCommand.method_29480(list.stream().map(ResourcePackProfile::getName).collect(toList()), server.getCommandSource());
    }

    public void registerDatapackRule(MinecraftServer server, String ruleName) {
        datapackRules.put(ruleName, ruleName);
        CarpetServer.settingsManager.addRuleObserver((source, rule, s) -> {
            if (rule.name.equals(ruleName)) {
                ResourcePackManager resourcePackManager = source.getMinecraftServer().getDataPackManager();
                ResourcePackProfile resourcePackProfile = resourcePackManager.getProfile("file/" + ruleName + ".zip");
                List<ResourcePackProfile> list = Lists.newArrayList(resourcePackManager.getEnabledProfiles());
                if (rule.getBoolValue() || (rule.type == String.class && !rule.get().equals("false"))) {
                    if (!list.contains(resourcePackProfile))
                        resourcePackProfile.getInitialPosition().insert(list, resourcePackProfile, (pack) -> pack, false);
                } else {
                    list.remove(resourcePackProfile);
                }
                ReloadCommand.method_29480(list.stream().map(ResourcePackProfile::getName).collect(toList()), source);
            }
        });
    }

    private void copyDatapackFolder(MinecraftServer server, String datapackName) {
        try {
            String datapacks = server.getSavePath(WorldSavePath.DATAPACKS).toString();
            Files.copy(
                    BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/datapacks/" + datapackName + ".zip"),
                    new File(datapacks, datapackName + ".zip").toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (IOException ignored) {
        }
    }

    private static BundledModule thenethersurvivalistDefaultScript(String scriptName, boolean isLibrary) {
        BundledModule module = new BundledModule(scriptName.toLowerCase(Locale.ROOT), null, false);
        try {
            module = new BundledModule(scriptName.toLowerCase(Locale.ROOT),
                    IOUtils.toString(
                            Objects.requireNonNull(BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/scripts/" + scriptName + (isLibrary ? ".scl" : ".sc"))),
                            StandardCharsets.UTF_8
                    ), isLibrary);
        } catch (NullPointerException | IOException ignored) {
        }
        return module;
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player) {
        if(TheNetherSurvivalistSettings.SettingsMessage && player.hasPermissionLevel(2)) {
            player.sendMessage(new LiteralText("Type /carpet for settings"), false);
        }
    }
}