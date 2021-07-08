package net.fabricmc.thenethersurvivalist.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class NetherDepthStriderMixin extends Entity {
    public NetherDepthStriderMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow
    private float movementSpeed;

    @Shadow
    protected boolean method_29920() {
        return true;
    }

    @Shadow
    public boolean canWalkOnFluid(Fluid fluid) {
        return false;
    }

    @Shadow
    protected float getBaseMovementSpeedMultiplier() {
        return 0.8F;
    }

    @Shadow
    public float getMovementSpeed() {
        return this.movementSpeed;
    }

    @Shadow
    public boolean hasStatusEffect(StatusEffect effect) {
        return false;
    }

    @Shadow
    public boolean isClimbing() {
        return false;
    }

    @Shadow
    public Vec3d method_26317(double d, boolean bl, Vec3d vec3d) {
        return null;
    }

    @Inject(at = @At("HEAD"), method = "travel", cancellable = true)
    private void travelMixin(Vec3d movementInput, CallbackInfo ci) {
        double d = 0.08D;
        boolean bl = this.getVelocity().y <= 0.0D;
        float j;
        double e;
        FluidState fluidState = this.world.getFluidState(this.getBlockPos());
        if (this.isInLava() && this.method_29920() && !this.canWalkOnFluid(fluidState.getFluid())) {
            e = this.getY();
            j = this.isSprinting() ? 0.9F : this.getBaseMovementSpeedMultiplier();
            float g = 0.02F;
            float h = (float) EnchantmentHelper.getDepthStrider((LivingEntity) ((Object) this));
            if (h > 3.0F) {
                h = 3.0F;
            }

            if (!this.onGround) {
                h *= 0.5F;
            }

            if (h > 0.0F) {
                j += (0.54600006F - j) * h / 3.0F;
                g += (this.getMovementSpeed() - g) * h / 3.0F;
                if (this.hasStatusEffect(StatusEffects.DOLPHINS_GRACE)) {
                    j = 0.96F;
                }

                this.updateVelocity(g, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                Vec3d vec3d = this.getVelocity();
                if (this.horizontalCollision && this.isClimbing()) {
                    vec3d = new Vec3d(vec3d.x, 0.2D, vec3d.z);
                }

                this.setVelocity(vec3d.multiply((double) j, 0.800000011920929D, (double) j));
                Vec3d vec3d2 = this.method_26317(d, bl, this.getVelocity());
                this.setVelocity(vec3d2);
                if (this.horizontalCollision && this.doesNotCollide(vec3d2.x, vec3d2.y + 0.6000000238418579D - this.getY() + e, vec3d2.z)) {
                    this.setVelocity(vec3d2.x, 0.30000001192092896D, vec3d2.z);
                }
            } else {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                Vec3d vec3d4;

                if (this.getFluidHeight(FluidTags.LAVA) <= this.method_29241()) {
                    this.setVelocity(this.getVelocity().multiply(0.5D, 0.800000011920929D, 0.5D));
                    vec3d4 = this.method_26317(d, bl, this.getVelocity());
                    this.setVelocity(vec3d4);
                }

                if (!this.hasNoGravity()) {
                    this.setVelocity(this.getVelocity().add(0.0D, -d / 4.0D, 0.0D));
                }

                vec3d4 = this.getVelocity();
                if (this.horizontalCollision && this.doesNotCollide(vec3d4.x, vec3d4.y + 0.6000000238418579D - this.getY() + e, vec3d4.z)) {
                    this.setVelocity(vec3d4.x, 0.30000001192092896D, vec3d4.z);
                }
            }
        }
    }
}