package thenethersurvivalist.mixin;

import thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.minecraft.tag.BlockTags;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AnvilScreenHandler.class)
public abstract class FairAnvil extends ForgingScreenHandler {
    public FairAnvil(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
        this.levelCost = Property.create();
    }

    @Shadow @Override
    protected boolean canTakeOutput(PlayerEntity player, boolean present) {
        return false;
    }

    @Shadow @Override
    protected boolean canUse(BlockState state) {
        return false;
    }

    @Shadow @Override
    public void updateResult() {

    }

    @Shadow private final Property levelCost;
    @Shadow private int repairItemUsage;

    /**
     * @author DukeEdivad05
     * @reason FairAnvil
     */
    @Overwrite
    public ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        if (!player.abilities.creativeMode) {
            if (TheNetherSurvivalistSettings.FairEnchanting) {
                int Points = 0;
                for(int a = 0; a <= (this.levelCost.get()); a++) {
                    if( a >= 0 && a <= 16) {
                        Points += Math.pow(a, 2) + 6 * a;
                    } else if(a >= 17 && a <= 31) {
                        Points += 2.5 * Math.pow(a, 2) - 40.5 * a + 360;
                    } else if ( a >= 32) {
                        Points += 4.5 * Math.pow(a, 2) - 162.5 * a + 2220;
                    }
                    player.addExperience(-Points);
                }
            } else {
                player.addExperienceLevels(-this.levelCost.get());
            }
        }

        this.input.setStack(0, ItemStack.EMPTY);
        if (this.repairItemUsage > 0) {
            ItemStack itemStack = this.input.getStack(1);
            if (!itemStack.isEmpty() && itemStack.getCount() > this.repairItemUsage) {
                itemStack.decrement(this.repairItemUsage);
                this.input.setStack(1, itemStack);
            } else {
                this.input.setStack(1, ItemStack.EMPTY);
            }
        } else {
            this.input.setStack(1, ItemStack.EMPTY);
        }

        this.levelCost.set(0);
        this.context.run((world, blockPos) -> {
            BlockState blockState = world.getBlockState(blockPos);
            if (!player.abilities.creativeMode && blockState.isIn(BlockTags.ANVIL) && player.getRandom().nextFloat() < 0.12F) {
                BlockState blockState2 = AnvilBlock.getLandingState(blockState);
                if (blockState2 == null) {
                    world.removeBlock(blockPos, false);
                    world.syncWorldEvent(1029, blockPos, 0);
                } else {
                    world.setBlockState(blockPos, blockState2, 2);
                    world.syncWorldEvent(1030, blockPos, 0);
                }
            } else {
                world.syncWorldEvent(1030, blockPos, 0);
            }

        });
        return stack;
    }
}
