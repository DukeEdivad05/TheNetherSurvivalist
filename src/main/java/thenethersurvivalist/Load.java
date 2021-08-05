package thenethersurvivalist;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import thenethersurvivalist.block.GoldenCarrotsBlock;
import thenethersurvivalist.block.ObsidianWalkerBlock;
import thenethersurvivalist.events.TheNetherSurvivalistEvents;

public class Load implements ModInitializer {

	public static final GoldenCarrotsBlock GOLDEN_CARROTS_BLOCK = new GoldenCarrotsBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
	public static final ObsidianWalkerBlock OBSIDIAN_WALKER_BLOCK = new ObsidianWalkerBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).ticksRandomly().requiresTool().strength(50.0F, 1200.0F));

	public static final Item LOGO = new Item(new FabricItemSettings());

	@Override
	public void onInitialize() {
		System.out.println("TheNetherSurvivalist successfully installed");
		CarpetServer.manageExtension(new TheNetherSurvivalistExtension());
		TheNetherSurvivalistEvents.noop();

		Registry.register(Registry.BLOCK, new Identifier("minecraft", "golden_carrots"), GOLDEN_CARROTS_BLOCK);
		Registry.register(Registry.BLOCK, new Identifier("minecraft", "obsidian_walker"), OBSIDIAN_WALKER_BLOCK);

		Registry.register(Registry.ITEM, new Identifier("tns", "logo"), LOGO);
	}
}