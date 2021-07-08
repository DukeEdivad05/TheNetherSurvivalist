package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin {
    /**
     * @author DukeEdivad05
     * @reason BottledExp
     */
    @Overwrite
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && isBottle(itemStack) && TheNetherSurvivalistSettings.BottledExp) {
            if (player.isCreative()) {
                player.dropItem(new ItemStack(Items.EXPERIENCE_BOTTLE), false);
                return ActionResult.SUCCESS;
            } else {
                int XpRemove = new Random().nextInt(-1 - (-7) + 1) + (-7);
                if ( player.experienceLevel > 0 || (player.experienceProgress * 7) >= -XpRemove) {
                    player.addExperience(XpRemove);
                    player.dropItem(new ItemStack(Items.EXPERIENCE_BOTTLE), false);
                    return ActionResult.SUCCESS;
                } else {
                    return ActionResult.CONSUME;
                }
            }
        } else if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;

        }
    }

    private static boolean isBottle(ItemStack stack) {
        return stack.getItem() == Items.GLASS_BOTTLE;
    }
}
