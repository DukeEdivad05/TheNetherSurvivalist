package thenethersurvivalist;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.GameRules;
import org.apache.logging.log4j.LogManager;
import thenethersurvivalist.block.GoldenCarrotsBlock;
import thenethersurvivalist.block.ObsidianWalkerBlock;
import thenethersurvivalist.enchantments.LavaAffinityEnchantment;
import thenethersurvivalist.enchantments.NetherDepthStriderEnchantment;
import thenethersurvivalist.enchantments.ObsidianWalkerEnchantment;
import thenethersurvivalist.events.TheNetherSurvivalistEvents;

public class Load implements ModInitializer {

	public static final GoldenCarrotsBlock GOLDEN_CARROTS_BLOCK = new GoldenCarrotsBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
	public static final ObsidianWalkerBlock OBSIDIAN_WALKER_BLOCK = new ObsidianWalkerBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).ticksRandomly().requiresTool().strength(50.0F, 1200.0F));

	public static final Item LOGO = new Item(new FabricItemSettings());

	public static GameRules.Key<GameRules.BooleanRule> NETHER_SPAWN = GameRuleRegistry.register("netherSpawn", GameRules.Category.SPAWNING, GameRuleFactory.createBooleanRule(true));

	private static final EquipmentSlot[] ALL_ARMOR = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static Enchantment OBSIDIAN_WALKER = Registry.register(Registry.ENCHANTMENT, new Identifier("minecraft", "obsidian_walker"), new ObsidianWalkerEnchantment(Enchantment.Rarity.RARE, new EquipmentSlot[]{EquipmentSlot.FEET}));
	public static Enchantment NETHER_DEPTH_STRIDER = Registry.register(Registry.ENCHANTMENT, new Identifier("minecraft", "nether_depth_strider"), new NetherDepthStriderEnchantment(Enchantment.Rarity.RARE, ALL_ARMOR));
	public static Enchantment LAVA_AFFINITY = Registry.register(Registry.ENCHANTMENT, new Identifier("minecraft", "lava_affinity"), new LavaAffinityEnchantment(Enchantment.Rarity.RARE, ALL_ARMOR));

	@Override
	public void onInitialize() {
		LogManager.getLogger("TheNetherSurvivalist").info("TheNetherSurvivalist succesfully installed");
		CarpetServer.manageExtension(new TheNetherSurvivalistExtension());
		TheNetherSurvivalistEvents.noop();

		Registry.register(Registry.BLOCK, new Identifier("minecraft", "golden_carrots"), GOLDEN_CARROTS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("minecraft", "obsidian_walker"), OBSIDIAN_WALKER_BLOCK);

		Registry.register(Registry.ITEM, new Identifier("tns", "logo"), LOGO);

		Registry.ITEM.set(Registry.ITEM.getRawId(Items.GOLDEN_CARROT), RegistryKey.of(Registry.ITEM.getKey(), Registry.ITEM.getId(Items.GOLDEN_CARROT)),(Item)(new AliasedBlockItem(GOLDEN_CARROTS_BLOCK, (new Item.Settings()).group(ItemGroup.BREWING).food(FoodComponents.GOLDEN_CARROT))),Registry.ITEM.getLifecycle());
	}
}