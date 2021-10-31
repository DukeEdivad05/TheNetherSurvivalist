package thenethersurvivalist;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import com.google.common.collect.ImmutableList;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Locale;

import static carpet.settings.RuleCategory.*;

public class TheNetherSurvivalistSettings {
    public static final String THENETHERSURVIVALIST = "thenethersurvivalist";
    public static final String DATAPACK = "datapack";

    private static class anvilLimitValidator extends Validator<String> {
        private static ImmutableList<String> OPTIONS = ImmutableList.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "+0", "+1", "+2", "+3", "+4", "+5", "+6", "+7");
        @Override public String validate(ServerCommandSource source, ParsedRule<String> currentRule, String newValue, String string)
        {
            if (OPTIONS.contains(string.toLowerCase(Locale.ROOT))) {
                return string;
            }
            return null;
        }
        @Override
        public String description() { return "You must choose a value from 1 to 10 or from +0 to +7";}
    }

    @Rule(
            desc = "Set max anvil level enchantment.",
            options = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "+0", "+1", "+2", "+3", "+4", "+5", "+6", "+7"},
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST},
            validate = anvilLimitValidator.class
    )
    public static String AnvilLimit = "+0";

    @Rule(
            desc = "Allow you to bottle experience",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean BottledExp = true;

    @Rule(
            desc = "whoImT feature: Places the mined block in the player inventory when sneaking.",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean CarefulBreak = true;

    @Rule(
            desc = "Allow you to enchant shears item on enchanting table",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean EnchantableShears = true;

    @Rule(
            desc = "Anvil take only needed xp",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FairAnvil = true;

    @Rule(
            desc = "Enchantment table take only needed xp",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FairEnchanting = true;

    @Rule(
            desc = "Farmland moisture do not require water near them",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FarmlandMoisture = true;

    @Rule(
            desc = "FireBall explosions do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FireballNoGrief = true;

    @Rule(
            desc = "CreativeFly",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean FlyCommand = true;

    @Rule(
            desc = "Allow to plant golden_carrot",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean GoldenCarrotsPlant = true;

    @Rule(
            desc = "You can harden concrete powder with water bottle",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean HardenedConcretePowder = true;

    @Rule(
            desc = "Enchantment like AquaAffinity but for the lava",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean LavaAffinity = true;

    @Rule(
            desc = "Allow you to obtain mending from enchanting table",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean MendingFromEnchanting = true;

    @Rule(
            desc = "Enchantment like DepthStrider but for the lava",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean NetherDepthStrider = true;

    @Rule(
            desc = "",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean NetherrackBoneMeal = true;

    @Rule(
            desc = "Prevent to switch on Nether Portal",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean NoNetherPortal = true;

    @Rule(
            desc = "Enchantment like FrostWalker but for the lava",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean ObsidianWalker = true;

    @Rule(
            desc = "Removes all acquatic enchantment from enchanting table except for depth strider and aqua affinity",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean RemovedAcquaticEnchantments = true;

    @Rule(
            desc = "A respawn anchor with four charges does not discharge on death",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean RespawnAnchorDischarging = true;

    @Rule(
            desc = "Settings Message",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean SettingsMessage = true;

    @Rule(
            desc = "Prevent coral death on Nylium",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean UndeadCoral = true;

    @Rule(
            desc = "Wither Skull explosion do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean WitherSkullNoGrief = true;


    //------------------------------------------------------------------------------------------------------------------
    //Scripts



    @Rule(
            desc = "You can go spectator and come back in the same position.",
            appSource = "c",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean C = true;

    @Rule(
            desc = "Allow you to transform concrete to concrete powder filling a glass bottle",
            appSource = "crumbleconcrete",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean CrumbleConcrete = true;

    @Rule(
            desc = "Create personal graves when the player dies.",
            appSource = "graves",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean Graves = true;

    @Rule(
            desc = "Allow you to leash mob.",
            appSource = "mobleash",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST}
    )
    public static boolean MobLeash = true;

    @Rule(
            desc = "Allow to revive dead coral with water bottle",
            appSource = "revivecoral",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST}
    )
    public static boolean ReviveCoral = true;


    //------------------------------------------------------------------------------------------------------------------
    //DataPacks

    @Rule(
            desc = "Allow to craft black dye from flint",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean BlackDyeFromFlint = true;

    @Rule(
            desc = "Add a variant of all cobblestone craftings with blackstone",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean BlackstoneCraftings = true;

    @Rule(
            desc = "Custom Advancements from Triton_707",
            appSource = "customadvancements",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean CustomAdvancements = true;

    @Rule(
            desc = "Custom Piglin Bartering",
            category = {SURVIVAL, FEATURE, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean CustomPiglinBartering = true;

    @Rule(
            desc = "Enderman do not grief",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean EndermanNoGrief = true;

    @Rule(
            desc = "Allow to craft red sand",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean CraftableRedSand = true;

    @Rule(
            desc = "Allow some coral type to spawn on Nether Forest's biomes",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean NetherCoral = true;

    @Rule(
            desc = "Allow you to craft orange dye from red dye and glowstone dust",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean OrangeDyeFromRedGlowstone = true;

    @Rule(
            desc = "Pumpkin seed spawn in bastions",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean PumpkinSeeds = true;

    @Rule(
            desc = "Allow you to craft red dye from nether wart",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean RedDyeFromNetherWart = true;

    @Rule(
            desc = "Allow you to craft scaffolding with nether vines",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean ScaffoldingNetherVines = true;

    @Rule(
            desc = "Piglin Brute drops shulker shell like shulkers",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean ShulkerShell = true;

    @Rule(
            desc = "Allow you to craft target with wart block",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean TargetWartBlock = true;

    @Rule(
            desc = "Gnottero's vertical slabs",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, THENETHERSURVIVALIST, DATAPACK}
    )
    public static boolean VerticalSlabs = true;
}