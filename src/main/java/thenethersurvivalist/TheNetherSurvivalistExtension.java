package thenethersurvivalist;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import thenethersurvivalist.utils.FlyCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class TheNetherSurvivalistExtension implements CarpetExtension {
    public static final String MOD_ID = "thenethersurvivalist";
    public static final String MOD_NAME = "The Nether Survivalist";
    public static final String MOD_VERSION = "1.0.0";

    static {
        CarpetServer.manageExtension(new TheNetherSurvivalistExtension());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(TheNetherSurvivalistSettings.class);

        // SCRIPTS
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("c", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("graves", false));
        CarpetScriptServer.registerSettingsApp(thenethersurvivalistDefaultScript("mobleash", false));
    }

//    @Override
//    public void onReload(MinecraftServer server) {
//        file(TheNetherSurvivalistSettings.EndermanNoGrief,"enderman_holdable");
//
//        advancements(TheNetherSurvivalistSettings.NoNetherPortal,"nonetherportal");
//    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        FlyCommand.register(dispatcher);
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
                            BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/scripts/" + scriptName + (isLibrary ? ".scl" : ".sc")),
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

    public static void noop() {
    }


//    public void advancements(boolean ruleName, String advancementName) {
//        File advancement = BundledModule.class.getClassLoader().getResourceAsStream("data/thenethersurvivalist/advancements/thenethersurvivalist" + advancementName + ".json");
//        if(ruleName) {
//            toggleOn(advancement);
//        } else {
//            toggleOff(advancement);
//        }
//    }
//
//    public void file(boolean ruleName, String fileName) {
//        File file = BundledModule.class.getClassLoader().getResourceAsStream("data/minecraft/tags/blocks/" + fileName + ".json");
//        if(ruleName) {
//            toggleOn(file);
//        } else {
//            toggleOff(file);
//        }
//    }
//
//    public void toggleOff(File file) {
//        String name = file.getName();
//        if(!name.contains(".disabled")) {
//            file.renameTo(new File(name + ".disabled"));
//        }
//    }
//
//    public void toggleOn(File file) {
//        String name = file.getName();
//        if(name.contains(".disabled")) {
//            file.renameTo(new File(name.substring(0, name.lastIndexOf('.'))));
//        }
//    }

    //"data/thenethersurvivalist/advancements/thenethersurvivalist" + advancementName + ".json"
    //"data/minecraft/tags/blocks/" + fileName + ".json"
}