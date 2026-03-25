package com.timohani.dexter.entity.custom;

import com.timohani.dexter.npc.NpcAction;
import com.timohani.dexter.npc.WalkToAction;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class CharacterEntity extends MobEntity implements GeoEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private boolean happy = false;
    public boolean dead = false;

    private final Queue<NpcAction> actionQueue = new LinkedList<>();
    private NpcAction currentAction = null;

    public CharacterEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "movementController", 5, event -> {
            if (event.isMoving()) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("walk"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("idle"));
            }
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>(this, "emotionController", 2, event -> {
            if (isHappy()) {
                event.getController().setAnimation(RawAnimation.begin().thenPlayAndHold("happy_eyes"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenPlayAndHold(""));
            }
            return PlayState.CONTINUE;
        }));

        controllers.add(new AnimationController<>(this, "blinkController", 2, event -> {
            if (!isDead()) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("blink"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenPlayAndHold(""));
            }
            return PlayState.CONTINUE;
        }));
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient) {
            // Генерируем случайную точку в радиусе 10 блоков
            BlockPos target = this.getBlockPos().add(
                    (random.nextInt(21) - 10),
                    0,
                    (random.nextInt(21) - 10)
            );
            // Добавляем действие: идти со скоростью 1.0, максимальное время 200 тиков (10 сек)
            scheduleAction(new WalkToAction(this, target, 0.7, 200));
            // Можно добавить ожидание после ходьбы, например:
            // scheduleAction(new WaitAction(this, 60));
        }
        return ActionResult.SUCCESS;
    }

    public void scheduleAction(NpcAction action) {
        actionQueue.add(action);
    }

    private void updateActions() {
        if (currentAction == null && !actionQueue.isEmpty()) {
            currentAction = actionQueue.poll();
            currentAction.start();
        }
        if (currentAction != null) {
            currentAction.tick();
            if (currentAction.isFinished()) {
                currentAction = null;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            updateActions();
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    // Атрибуты
    public static DefaultAttributeContainer.Builder createAttributes() {
        return LivingEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0);
    }

    // Полная неуязвимость
    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return Collections.emptyList(); // Возвращаем пустой список вместо null
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY; // Возвращаем пустой ItemStack вместо null
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        // Пустая реализация, так как персонаж не использует экипировку
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT; // Возвращаем правую руку по умолчанию вместо null
    }

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}