package com.deeep.spaceglad.services;


import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.deeep.spaceglad.databags.GameWorld;
import com.deeep.spaceglad.UI.GameUI;
import com.deeep.spaceglad.databags.Scene;
import com.deeep.spaceglad.systems.PhysicsSystem;
import com.deeep.spaceglad.systems.MonsterSystem;
import com.deeep.spaceglad.systems.PlayerSystem;
import com.deeep.spaceglad.systems.RenderSystem;
import com.deeep.spaceglad.systems.StatusSystem;

public class WorldLoader {

    private GameWorld gameWorld;
    private GameUI gameUI;

    public GameWorld create(GameUI ui) {
        Bullet.init();

        gameWorld = new GameWorld();
        gameUI = ui;

        setDebug();
        addSystems();
        loadLevel();
        createPlayer(0, 6, 0);

        return gameWorld;
    }

    private void setDebug() {
        if (gameWorld.isDebug()) {
            DebugDrawer debugDrawer = new DebugDrawer();
            debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
            gameWorld.setDebugDrawer(debugDrawer);
        }
    }

    private void addSystems() {
        Engine engine = new Engine();

        RenderSystem renderSystem = new RenderSystem();
        gameWorld.setRenderSystem(renderSystem);
        engine.addSystem(renderSystem);

        PhysicsSystem physicsSystem = new PhysicsSystem();
        gameWorld.setPhysicsSystem(physicsSystem);
        engine.addSystem(physicsSystem);

        PlayerSystem playerSystem = new PlayerSystem(
                gameWorld, gameUI, renderSystem.perspectiveCamera);
        gameWorld.setPlayerSystem(playerSystem);
        engine.addSystem(playerSystem);

        MonsterSystem monsterSystem = new MonsterSystem(gameWorld);
        engine.addSystem(monsterSystem);

        StatusSystem statusSystem = new StatusSystem(gameWorld);
        engine.addSystem(statusSystem);

        gameWorld.setEngine(engine);

        if (gameWorld.isDebug()) {
            physicsSystem.collisionWorld.setDebugDrawer(gameWorld.getDebugDrawer());
        }
    }


    private void loadLevel() {
        Scene area = SceneLoader.load("area", 0, 0, 0);
        gameWorld.setCurrentScene(area);
        gameWorld.getEngine().addEntity(area.getGround());
        gameWorld.getEngine().addEntity(area.getSky());
        gameWorld.getPlayerSystem().dome = area.getSky();
    }

    private void createPlayer(float x, float y, float z) {
        PlayerFactory playerFactory = new PlayerFactory();
        Entity player = playerFactory.create(gameWorld.getPhysicsSystem(), x, y, z);
        gameWorld.setPlayer(player);
        gameWorld.getEngine().addEntity(player);

        PlayerItemFactory playerItemFactory = new PlayerItemFactory();
        Entity gun = playerItemFactory.create("GUNMODEL", 2.5f, -1.9f, -4);
        gameWorld.setGun(gun);
        gameWorld.getEngine().addEntity(gun);
        gameWorld.getPlayerSystem().gun = gun;
        gameWorld.getRenderSystem().gun = gun;
    }


    public static void remove(GameWorld gameWorld, Entity entity) {
        gameWorld.getEngine().removeEntity(entity);
        gameWorld.getPhysicsSystem().removeBody(entity);
    }
}
