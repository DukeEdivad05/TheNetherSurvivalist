package thenethersurvivalist;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import org.apache.commons.io.IOUtils;
import thenethersurvivalist.utils.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public class TheNetherSurvivalistExtension implements CarpetExtension {
    public static final String MOD_ID = "thenethersurvivalist";
    public static final String MOD_NAME = "The Nether Survivalist";
    public static final String MOD_VERSION = "1.1.0";

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
        CarpetScriptServer.registerBuiltInScript(thenethersurvivalistDefaultScript("advancements", false));
    }

    public static void loadScripts(MinecraftServer server) {
        CarpetServer.scriptServer.addScriptHost(server.getCommandSource(), "advancements", null, true, true, false);
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        loadScripts(server);
    }

    @Override
    public void onReload(MinecraftServer server) {
        loadScripts(server);
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
        if(TheNetherSurvivalistSettings.settingsMessage && player.hasPermissionLevel(2)) {
            player.sendMessage(new LiteralText("Type /carpet for settings"), false);
        }
    }
}