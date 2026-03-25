package com.timohani.dexter.npc;

import com.timohani.dexter.entity.custom.CharacterEntity;
import net.minecraft.util.math.BlockPos;

public class WalkToAction extends NpcAction {
    private final BlockPos target;
    private final double speed;

    public WalkToAction(CharacterEntity npc, BlockPos target, double speed, int duration) {
        super(npc, duration);
        this.target = target;
        this.speed = speed;
    }

    @Override
    public void start() {
        super.start();
        npc.getNavigation().startMovingTo(target.getX(), target.getY(), target.getZ(), speed);
    }

    @Override
    public void tick() {
        super.tick();
        // Если навигация закончила путь раньше времени, завершаем действие
        if (npc.getNavigation().isIdle() && !isFinished()) {
            ticks = duration; // принудительное завершение
        }
    }
}