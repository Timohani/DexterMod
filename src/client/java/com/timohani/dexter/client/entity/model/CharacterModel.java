package com.timohani.dexter.client.entity.model;

import com.timohani.dexter.DexterMod;
import com.timohani.dexter.entity.custom.CharacterEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CharacterModel extends GeoModel<CharacterEntity> {

    @Override
    public Identifier getModelResource(CharacterEntity object) {
        return Identifier.of(DexterMod.MOD_ID, "geo/character.geo.json");
    }

    @Override
    public Identifier getTextureResource(CharacterEntity object) {
        return Identifier.of(DexterMod.MOD_ID, "textures/entity/character.png");
    }

    @Override
    public Identifier getAnimationResource(CharacterEntity object) {
        return Identifier.of(DexterMod.MOD_ID, "animations/character.animation.json");
    }
}