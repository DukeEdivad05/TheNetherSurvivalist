package net.fabricmc.thenethersurvivalist.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static net.fabricmc.thenethersurvivalist.Load.GOLDEN_CARROTS_BLOCK;

@Environment(EnvType.CLIENT)
public class ClientLoad implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(GOLDEN_CARROTS_BLOCK, RenderLayer.getCutout());
    }
}
