package thenethersurvivalist.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(Objects.requireNonNull(Registry.SOUND_EVENT.get(new Identifier(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().toString()))), 1.0F));
    }
}
