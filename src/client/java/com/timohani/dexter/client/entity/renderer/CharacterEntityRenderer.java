package com.timohani.dexter.client.entity.renderer;

import com.timohani.dexter.entity.custom.CharacterEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class CharacterEntityRenderer extends MobEntityRenderer<CharacterEntity, PlayerEntityModel<CharacterEntity>> {

    public CharacterEntityRenderer(EntityRendererFactory.Context context) {
        // Используем модель игрока (с limbs = false — обычный скин, true — тонкие руки)
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(CharacterEntity entity) {
        return Identifier.of("textures/entity/steve.png");
    }
}
