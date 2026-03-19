package com.timohani.dexter;

import com.timohani.dexter.entity.ModEntities;
import com.timohani.dexter.entity.custom.CharacterEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DexterMod implements ModInitializer {

    public static final String MOD_ID = "dextermod";
    public static final Logger LOGGER = LoggerFactory.getLogger("dextermod");

    @Override
    public void onInitialize() {
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.CHARACTER, CharacterEntity.createAttributes());
    }
}
