package net.fabricmc.thenethersurvivalist.mixin;

import net.fabricmc.thenethersurvivalist.TheNetherSurvivalistSettings;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireballEntity.class)
public class FireballEntityMixin {
    @Redirect(method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
    )
    )
    private boolean getBoolean(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> rule) {
        return !TheNetherSurvivalistSettings.FireballNoGrief;
    }
}
