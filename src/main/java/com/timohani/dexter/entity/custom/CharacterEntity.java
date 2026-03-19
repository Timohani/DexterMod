package com.timohani.dexter.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class CharacterEntity extends AnimalEntity {
    private String characterName = "Неизвестный"; // временно

    public CharacterEntity(EntityType<? extends AnimalEntity> type, World world) {
        super(type, world);
    }

    @Override
    protected void initGoals() {
        // Минимальное поведение: плавать, смотреть на игрока, гулять
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.6D));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }

    // Метод для установки имени (позже будем брать из реестра)
    public void setCharacterName(String name) {
        this.characterName = name;
    }

    // При клике ПКМ показываем имя (позже заменим на диалог)
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            player.sendMessage(Text.literal("Это " + characterName), false);
        }
        return ActionResult.SUCCESS;
    }

    // Обязательные методы AnimalEntity (пока заглушки)
    @Override
    public CharacterEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() instanceof PlayerEntity) {
            return false;
        }
        return super.damage(source, amount);
    }
}
