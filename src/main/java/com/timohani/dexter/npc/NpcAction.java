package com.timohani.dexter.npc;

import com.timohani.dexter.entity.custom.CharacterEntity;

public abstract class NpcAction {
    protected CharacterEntity npc;
    protected int duration;   // -1 = бесконечно
    protected int ticks;

    public NpcAction(CharacterEntity npc, int duration) {
        this.npc = npc;
        this.duration = duration;
    }

    public void start() {
        ticks = 0;
    }

    public void tick() {
        ticks++;
    }

    public boolean isFinished() {
        return duration >= 0 && ticks >= duration;
    }
}