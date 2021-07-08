package net.fabricmc.thenethersurvivalist;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import net.minecraft.server.command.ServerCommandSource;

import static carpet.settings.RuleCategory.*;

public class TheNetherSurvivalistSettings {
    public static final String THENETHERSURVIVALIST = "thenethersurvivalist";

    private static class anvilLimitValidator extends Validator<Integer> {
        @Override public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string)
        {
            return newValue;
        }
        @Override
        public String description() { return "You must choose a value from 0 to 10";}


    }
    @Rule(
            desc = "Create personal graves when the player dies.",
            appSource = "graves",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean graves = true;

    @Rule(
            desc = "You can go spectator and come back in the same position.",
            appSource = "c",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean c = true;

    @Rule(
            desc = "whoImT feature: Places the mined block in the player inventory when sneaking.",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean carefulBreak = true;

    @Rule(
            desc = "Allow you to leash mob.",
            appSource = "mobleash",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean mobleash = true;

    @Rule(
            desc = "Set max anvil level enchantment.",
            options = {"0", "5", "10"},
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST},
            validate = anvilLimitValidator.class
    )
    public static int anvilLimit = 0;

    @Rule(
            desc = "Settings Message",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean settingsMessage = true;

    @Rule(
            desc = "Prevent coral death on Nylium",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean undeadCoral = true;

    @Rule(
            desc = "Take only needed xp",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FairEnchanting = true;

    @Rule(
            desc = "CreativeFly",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FlyCommand = true;

    @Rule(
            desc = "Prevent to switch on Nether Portal",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean NoNetherPortal = true;

    @Rule(
            desc = "Enderman do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean EndermanNoGrief = true;

    @Rule(
            desc = "Removes all acquatic enchantment from enchanting table except for depth strider and aqua affinity",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean RemovedAcquaticEnchantments = true;

    @Rule(
            desc = "Allow you to bottle experience",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean BottledExp = true;

    @Rule(
            desc = "Farmland moisture do not require water near them",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FarmlandMoisture = true;

    @Rule(
            desc = "Allow to plant golden_carrot",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean GoldenCarrotsPlant = true;

    @Rule(
            desc = "FireBall explosions do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FireballNoGrief = true;

    @Rule(
            desc = "Wither Skull explosion do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean WitherSkullNoGrief = true;

    @Rule(
            desc = "Allow you to obtain mending from enchanting table",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean MendingFromEnchanting = true;

    @Rule(
            desc = "Allow you to enchant shears item on enchanting table",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean EnchantableShears = true;
}