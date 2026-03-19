package com.timohani.dexter.entity;

import com.timohani.dexter.DexterMod;
import com.timohani.dexter.entity.custom.CharacterEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<CharacterEntity> CHARACTER =
            Registry.register(Registries.ENTITY_TYPE, Identifier.of(DexterMod.MOD_ID, "character"),
                    EntityType.Builder.create(CharacterEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 1.8f).build()
            );

    public static void registerModEntities() {
        DexterMod.LOGGER.info("Registering Mod Entities for: " + DexterMod.MOD_ID);
    }
}