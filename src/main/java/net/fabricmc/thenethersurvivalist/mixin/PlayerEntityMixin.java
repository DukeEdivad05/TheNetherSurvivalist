package net.fabricmc.thenethersurvivalist.mixin;

import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow public final PlayerInventory inventory = new PlayerInventory((PlayerEntity) ((Object) this));

    /**
     * @author DukeEdivad05
     * @reason NetherRespawn
     */
    @Overwrite
    public static Optional<Vec3d> findRespawnPosition(ServerWorld world, BlockPos pos, float f, boolean bl, boolean bl2) {
        BlockState blockState = world.getBlockState(pos);
        Block block = blockState.getBlock();
        if (block instanceof RespawnAnchorBlock && (Integer)blockState.get(RespawnAnchorBlock.CHARGES) > 0 && RespawnAnchorBlock.isNether(world)) {
            Optional<Vec3d> optional = RespawnAnchorBlock.findRespawnPosition(EntityType.PLAYER, world, pos);
            if (!bl2 && optional.isPresent() && (Integer)blockState.get(RespawnAnchorBlock.CHARGES) <= 3) {
                world.setBlockState(pos, (BlockState)blockState.with(RespawnAnchorBlock.CHARGES, (Integer)blockState.get(RespawnAnchorBlock.CHARGES) - 1), 3);
            }

            return optional;
        } else if (block instanceof BedBlock && BedBlock.isOverworld(world)) {
            return BedBlock.findWakeUpPosition(EntityType.PLAYER, world, pos, f);
        } else if (!bl) {
            return Optional.empty();
        } else {
            boolean bl3 = block.canMobSpawnInside();
            boolean bl4 = world.getBlockState(pos.up()).getBlock().canMobSpawnInside();
            return bl3 && bl4 ? Optional.of(new Vec3d((double)pos.getX() + 0.5D, (double)pos.getY() + 0.1D, (double)pos.getZ() + 0.5D)) : Optional.empty();
        }
    }

    /**
     * @author DukeEdivad05
     * @reason LavaAffinity
     */
    @Overwrite
    public float getBlockBreakingSpeed(BlockState block) {
        float f = this.inventory.getBlockBreakingSpeed(block);
        if (f > 1.0F) {
            int i = EnchantmentHelper.getEfficiency(this);
            ItemStack itemStack = this.getMainHandStack();
            if (i > 0 && !itemStack.isEmpty()) {
                f += (float)(i * i + 1);
            }
        }

        if (StatusEffectUtil.hasHaste(this)) {
            f *= 1.0F + (float)(StatusEffectUtil.getHasteAmplifier(this) + 1) * 0.2F;
        }

        if (this.hasStatusEffect(StatusEffects.MINING_FATIGUE)) {
            float k;
            switch(this.getStatusEffect(StatusEffects.MINING_FATIGUE).getAmplifier()) {
                case 0:
                    k = 0.3F;
                    break;
                case 1:
                    k = 0.09F;
                    break;
                case 2:
                    k = 0.0027F;
                    break;
                case 3:
                default:
                    k = 8.1E-4F;
            }

            f *= k;
        }

        if (this.isSubmergedIn(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(this)) {
            f /= 5.0F;
        }

        if (this.isSubmergedIn(FluidTags.LAVA) && EnchantmentHelper.hasAquaAffinity(this)) {
            f *= 5.0F;
        }

        if (!this.onGround) {
            f /= 5.0F;
        }

        return f;
    }
}
