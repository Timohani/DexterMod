package com.timohani.dexter.client.entity.renderer;

import com.timohani.dexter.client.entity.model.CharacterModel;
import com.timohani.dexter.entity.custom.CharacterEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CharacterEntityRenderer extends GeoEntityRenderer<CharacterEntity> {


    public CharacterEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CharacterModel());  // Create model here
        this.shadowRadius = 0.5f;
    }
}
