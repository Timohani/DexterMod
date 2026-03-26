package com.timohani.dexter.npc;

import com.timohani.dexter.entity.custom.CharacterEntity;

public class PlayAnimationAction extends NpcAction {
    private final String controllerName;
    private final String animation;
    private boolean started = false;

    public PlayAnimationAction(CharacterEntity npc, String controllerName, String animation, int duration) {
        super(npc, duration);
        this.controllerName = controllerName;
        this.animation = animation;
    }

    @Override
    public void start() {
        super.start();
        npc.triggerAnim(controllerName, animation);
        started = true;
    }

    @Override
    public boolean isFinished() {
        if (!started) return true;
        return super.isFinished();
    }
}