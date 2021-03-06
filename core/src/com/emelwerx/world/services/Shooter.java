package com.emelwerx.world.services;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.emelwerx.world.databags.components.AnimationComponent;
import com.emelwerx.world.databags.systemstates.PlayerSystemState;

public class Shooter {

    public static void fire(PlayerSystemState playerSystemState) {
        Gdx.app.log("Shooter", "fire!!!");
        ClosestRayResultCallback resultCallback = RayCallbackFactory.create(playerSystemState);
        DamageChecker.check(resultCallback);
        animatePlayerItem(playerSystemState.getPlayerItemEntity());
    }

    private static void animatePlayerItem(Entity entity) {
        AnimationComponent animationComponent = entity.getComponent(AnimationComponent.class);
        animationComponent.getAnimationController().animate("Armature|shoot", 1, 3, null, 0);
    }
}
