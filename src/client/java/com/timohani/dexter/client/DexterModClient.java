package com.timohani.dexter.client;

import com.timohani.dexter.client.entity.renderer.CharacterEntityRenderer;
import com.timohani.dexter.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DexterModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.CHARACTER, CharacterEntityRenderer::new);
    }
}
