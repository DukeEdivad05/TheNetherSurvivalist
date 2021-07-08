package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.Load;
import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(FarmlandBlock.class)
public class FarmlandBlockMixin extends Block {

    public FarmlandBlockMixin(Settings settings) {
        super(settings);
    }

    /**
     * @author DukeEdivad05
     * @reason FarmableCropBlockOnNether
     */
    @Overwrite
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        return TheNetherSurvivalistSettings.FarmlandMoisture;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.isSpectator() && player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.GOLDEN_CARROT && world.getBlockState(pos.up()).getBlock() == Blocks.AIR && hit.getSide() == Direction.UP && TheNetherSurvivalistSettings.GoldenCarrotsPlant) {
            world.setBlockState(pos.up(), Load.GOLDEN_CARROTS_BLOCK.getDefaultState(), 3);
            if(!player.abilities.creativeMode) {
                player.getStackInHand(Hand.MAIN_HAND).decrement(1);
            }
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }
}
