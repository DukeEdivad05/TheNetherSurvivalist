package net.fabricmc.thenethersurvivalist.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ConcretePowderBlock.class)
public class ConcretePowderBlockMixin extends FallingBlock {

    @Shadow
    private final BlockState hardenedState;

    public ConcretePowderBlockMixin(Block hardened, Settings settings) {
        super(settings);
        this.hardenedState = hardened.getDefaultState();
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!player.isSpectator() && player.getStackInHand(Hand.MAIN_HAND).getItem() == Items.POTION && PotionUtil.getPotion(player.getStackInHand(Hand.MAIN_HAND)) == Potions.WATER) {
            world.setBlockState(pos, this.hardenedState, 3);
            player.setStackInHand(hand, new ItemStack(Items.GLASS_BOTTLE));
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.PASS;
        }
    }
}
