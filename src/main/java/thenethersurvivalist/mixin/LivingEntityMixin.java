package thenethersurvivalist.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thenethersurvivalist.Load;
import thenethersurvivalist.TheNetherSurvivalistSettings;
import thenethersurvivalist.enchantments.ObsidianWalkerEnchantment;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Shadow public abstract BlockState getBlockState();
    
    @Shadow protected abstract boolean method_29920();
    @Shadow protected abstract float getBaseMovementSpeedMultiplier();

    @Inject(method = "applyMovementEffects", at = @At("HEAD"))
    private void ObsidianWalker(BlockPos pos, CallbackInfo ci) {
        World world =  ((LivingEntity)(Object)this).getEntityWorld();
        int i = EnchantmentHelper.getEquipmentLevel(Load.OBSIDIAN_WALKER, (LivingEntity)(Object)this);
        if (i > 0 && TheNetherSurvivalistSettings.ObsidianWalker) {
            ObsidianWalkerEnchantment.solidificateLava((LivingEntity)(Object)this, world, pos, i);
        }
    }
    
    @Inject(method = "travel", at = @At("HEAD"))
    private void NetherDepthStrider(Vec3d movementInput, CallbackInfo ci) {
        if (((LivingEntity)(Object)this).canMoveVoluntarily() || ((LivingEntity)(Object)this).isLogicalSideForUpdatingMovement()) {
            double d = 0.08D;
            boolean bl = ((LivingEntity)(Object)this).getVelocity().y <= 0.0D;
            if (bl && ((LivingEntity)(Object)this).hasStatusEffect(StatusEffects.SLOW_FALLING)) {
                d = 0.01D;
                ((LivingEntity)(Object)this).fallDistance = 0.0F;
            }

            FluidState fluidState = ((LivingEntity)(Object)this).world.getFluidState(((LivingEntity)(Object)this).getBlockPos());
            float j;
            double e;
            if (((LivingEntity)(Object)this).isTouchingWater() && method_29920() && !((LivingEntity)(Object)this).canWalkOnFluid(fluidState.getFluid())) {
            } else if (((LivingEntity)(Object)this).isInLava() && method_29920() && !((LivingEntity)(Object)this).canWalkOnFluid(fluidState.getFluid())) {
                e = ((LivingEntity)(Object)this).getY();
                j = ((LivingEntity)(Object)this).isSprinting() ? 0.9F : getBaseMovementSpeedMultiplier();
                float g = 0.02F;
                float h = (float) EnchantmentHelper.getEquipmentLevel(Load.NETHER_DEPTH_STRIDER, ((LivingEntity)(Object)this));;
                if (h > 3.0F) {
                    h = 3.0F;
                }

                if (!((LivingEntity)(Object)this).isOnGround()) {
                    h *= 0.5F;
                }

                if (h > 0.0F && TheNetherSurvivalistSettings.NetherDepthStrider) {
                    j += (0.54600006F - j) * h / 3.0F;
                    g += (((LivingEntity)(Object)this).getMovementSpeed() - g) * h / 3.0F;
                    if (((LivingEntity)(Object)this).hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
                        j = 0.96F;
                    }

                    ((LivingEntity)(Object)this).updateVelocity(g, movementInput);
                    ((LivingEntity)(Object)this).move(MovementType.SELF, ((LivingEntity)(Object)this).getVelocity());
                    Vec3d vec3d = ((LivingEntity)(Object)this).getVelocity();
                    if (((LivingEntity)(Object)this).horizontalCollision && ((LivingEntity)(Object)this).isClimbing()) {
                        vec3d = new Vec3d(vec3d.x, 0.2D, vec3d.z);
                    }

                    ((LivingEntity)(Object)this).setVelocity(vec3d.multiply((double) j, 0.800000011920929D, (double) j));
                    Vec3d vec3d2 = ((LivingEntity)(Object)this).method_26317(d, bl, ((LivingEntity)(Object)this).getVelocity());
                    ((LivingEntity)(Object)this).setVelocity(vec3d2);
                    if (((LivingEntity)(Object)this).horizontalCollision && ((LivingEntity)(Object)this).doesNotCollide(vec3d2.x, vec3d2.y + 0.6000000238418579D - ((LivingEntity)(Object)this).getY() + e, vec3d2.z)) {
                        ((LivingEntity)(Object)this).setVelocity(vec3d2.x, 0.30000001192092896D, vec3d2.z);
                    }
                } else {
                    ((LivingEntity)(Object)this).updateVelocity(0.02F, movementInput);
                    ((LivingEntity)(Object)this).move(MovementType.SELF, ((LivingEntity)(Object)this).getVelocity());
                    Vec3d vec3d4;

                    if (((LivingEntity)(Object)this).getFluidHeight(FluidTags.LAVA) <= ((LivingEntity)(Object)this).method_29241()) {
                        ((LivingEntity)(Object)this).setVelocity(((LivingEntity)(Object)this).getVelocity().multiply(0.5D, 0.800000011920929D, 0.5D));
                        vec3d4 = ((LivingEntity)(Object)this).method_26317(d, bl, ((LivingEntity)(Object)this).getVelocity());
                        ((LivingEntity)(Object)this).setVelocity(vec3d4);
                    }

                    if (!((LivingEntity)(Object)this).hasNoGravity()) {
                        ((LivingEntity)(Object)this).setVelocity(((LivingEntity)(Object)this).getVelocity().add(0.0D, -d / 4.0D, 0.0D));
                    }

                    vec3d4 = ((LivingEntity)(Object)this).getVelocity();
                    if (((LivingEntity)(Object)this).horizontalCollision && ((LivingEntity)(Object)this).doesNotCollide(vec3d4.x, vec3d4.y + 0.6000000238418579D - ((LivingEntity)(Object)this).getY() + e, vec3d4.z)) {
                        ((LivingEntity)(Object)this).setVelocity(vec3d4.x, 0.30000001192092896D, vec3d4.z);
                    }
                }
            }
        }
    }
}
