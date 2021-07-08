package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.*;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Random;

@Mixin(EnchantmentScreenHandler.class)
public abstract class FairEnchanting extends ScreenHandler {

    @Shadow private final Inventory inventory;
    @Shadow private final ScreenHandlerContext context;
    @Shadow private final Property seed;
    @Shadow public final int[] enchantmentPower;
    @Shadow private List<EnchantmentLevelEntry> generateEnchantments(ItemStack stack, int slot, int level) {
        return null;
    }

    protected final Random random;
    protected int enchantmentTableSeed;

    public FairEnchanting(@Nullable ScreenHandlerType<?> type, int syncId, Inventory inventory, ScreenHandlerContext context, Property seed, int[] enchantmentPower) {
        super(type, syncId);
        this.inventory = inventory;
        this.context = context;
        this.seed = Property.create();
        this.enchantmentPower = new int[3];
        this.random = new Random();
    }

    /**
     * @author DukeEdivad05
     * @reason FairEnchanting
     */
    @Overwrite
    public boolean onButtonClick(PlayerEntity player, int id) {
        ItemStack itemStack = this.inventory.getStack(0);
        ItemStack itemStack2 = this.inventory.getStack(1);
        int i = id + 1;
        if ((itemStack2.isEmpty() || itemStack2.getCount() < i) && !player.abilities.creativeMode) {
            return false;
        } else if (this.enchantmentPower[id] <= 0 || itemStack.isEmpty() || (player.experienceLevel < i || player.experienceLevel < this.enchantmentPower[id]) && !player.abilities.creativeMode) {
            return false;
        } else {
            this.context.run((world, blockPos) -> {
                ItemStack itemStack3 = itemStack;
                List<EnchantmentLevelEntry> list = this.generateEnchantments(itemStack, id, this.enchantmentPower[id]);
                if (!list.isEmpty()) {
                    if (TheNetherSurvivalistSettings.FairEnchanting) {
                        int Points = 0;
                        for (int a = (this.enchantmentPower[id] - i); a < this.enchantmentPower[id]; a++) {
                            if (a >= 0 && a <= 15) {
                                Points += 2 * a + 7;
                            } else if (a >= 16 && a <= 30) {
                                Points += 5 * a - 38;
                            } else if (a >= 31) {
                                Points += 9 * a - 158;
                            }
                        }
                        player.addExperience(-Points);
                        player.addExperienceLevels(i);
                        player.applyEnchantmentCosts(itemStack, i);
                    } else {
                        player.applyEnchantmentCosts(itemStack, i);
                    }
                    boolean bl = itemStack.getItem() == Items.BOOK;
                    if (bl) {
                        itemStack3 = new ItemStack(Items.ENCHANTED_BOOK);
                        CompoundTag compoundTag = itemStack.getTag();
                        if (compoundTag != null) {
                            itemStack3.setTag(compoundTag.copy());
                        }

                        this.inventory.setStack(0, itemStack3);
                    }

                    for(int k = 0; k < list.size(); ++k) {
                        EnchantmentLevelEntry enchantmentLevelEntry = (EnchantmentLevelEntry)list.get(k);
                        if (bl) {
                            EnchantedBookItem.addEnchantment(itemStack3, enchantmentLevelEntry);
                        } else {
                            itemStack3.addEnchantment(enchantmentLevelEntry.enchantment, enchantmentLevelEntry.level);
                        }
                    }

                    if (!player.abilities.creativeMode) {
                        itemStack2.decrement(i);
                        if (itemStack2.isEmpty()) {
                            this.inventory.setStack(1, ItemStack.EMPTY);
                        }
                    }

                    player.incrementStat(Stats.ENCHANT_ITEM);
                    if (player instanceof ServerPlayerEntity) {
                        Criteria.ENCHANTED_ITEM.trigger((ServerPlayerEntity)player, itemStack3, i);
                    }

                    this.inventory.markDirty();
                    this.seed.set(player.getEnchantmentTableSeed());
                    this.onContentChanged(this.inventory);
                    world.playSound((PlayerEntity)null, blockPos, SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F);
                }

            });
            return true;
        }
    }
}
