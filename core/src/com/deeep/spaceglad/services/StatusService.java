package com.deeep.spaceglad.services;

import com.deeep.spaceglad.databags.StatusComponent;
import com.deeep.spaceglad.databags.AnimationComponent;
import com.deeep.spaceglad.databags.EnemyAnimations;

public class StatusService {
    public StatusComponent create(AnimationComponent animationComponent) {
        StatusComponent statusComponent = new StatusComponent();
        statusComponent.setAnimationComponent(animationComponent);
        statusComponent.setAlive(true);
        statusComponent.setRunning(true);
        return statusComponent;
    }

    public void update(StatusComponent statusComponent, float delta) {
        if (!statusComponent.isAlive()) {
            statusComponent.setAliveStateTime(statusComponent.getAliveStateTime() + delta);
        }
    }

    public void setAlive(StatusComponent statusComponent, AnimationComponent animationComponent, boolean alive) {
        statusComponent.setAlive(alive);
        AnimationService animationService = new AnimationService();
        animationService.animate(animationComponent,
                EnemyAnimations.id,
                EnemyAnimations.offsetDeath2,
                EnemyAnimations.durationDeath2,
                1, 3);
    }
}