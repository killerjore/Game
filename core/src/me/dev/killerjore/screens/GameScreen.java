package me.dev.killerjore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.strongjoshua.console.Console;
import com.strongjoshua.console.GUIConsole;
import me.dev.killerjore.Main;
import me.dev.killerjore.audio.AudioManager;
import me.dev.killerjore.console.ConsoleCommandExecutor;
import me.dev.killerjore.entities.item.items.Weapon;
import me.dev.killerjore.event.EventManager;
import me.dev.killerjore.ui.inventory.Inventory;
import me.dev.killerjore.textureRepository.TextureManager;
import me.dev.killerjore.ui.UIManager;
import me.dev.killerjore.entities.EntityManager;
import me.dev.killerjore.entities.creature.attacker.movable.Player;
import me.dev.killerjore.entities.creature.attacker.movable.Skeleton;
import me.dev.killerjore.input.InputHandler;
import me.dev.killerjore.world.WorldManager;

public class GameScreen implements Screen {

    private Main main;

    private OrthographicCamera camera;
    private SpriteBatch uiSpriteBatch;

    private Console console;

    public GameScreen(Main main) {

        uiSpriteBatch = new SpriteBatch();

        Gdx.graphics.setWindowedMode(32 * 21, 32 * 13);

        this.main = main;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 32 * 21, 32 * 13);
        camera.position.set(0, 0, 0);
        camera.update();

        new InputHandler(this);

        /*
        * Initializing the singleton classes beforehand
         */
        TextureManager.getInstance().initTextureRepos();
        EventManager.getInstance();
        AudioManager.getInstance();
        EntityManager.getInstance();

        console = new GUIConsole();
        console.setSizePercent(100, 50);
        console.setPositionPercent(0, 50);
        console.setDisplayKeyID(Input.Keys.F12);
        console.setCommandExecutor(new ConsoleCommandExecutor(console));
    }

    @Override
    public void show() {
        Player player = new Player(23 * 32, 23 * 32, 64, 64, 25, 25, 20, 20, 20, 20, camera);
        Skeleton skeleton = new Skeleton(22 * 32, 19 * 32, 64, 64, 25, 25,20, 20, 20, 20, 70);
        Weapon weapon = new Weapon(22 * 32, 22 * 32, 32, 32, 32, 32);

        EntityManager.getInstance().getStarterWorldEntityList().add(skeleton);
        EntityManager.getInstance().getStarterWorldEntityList().add(player);
        EntityManager.getInstance().getStarterWorldEntityList().add(weapon);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if (!EntityManager.getInstance().getPlayer().isActive()) {
            main.setScreen(new GameOverScreen());
            return;
        }

        WorldManager.getInstance().getCurrentWorld().render(delta, camera);
        uiSpriteBatch.begin();
        UIManager.getInstance().render(uiSpriteBatch);
        Inventory.getInstance().render(uiSpriteBatch);
        uiSpriteBatch.end();

        EntityManager.getInstance().dispose();

        console.refresh();
        console.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        System.out.println("Dispose called");
        WorldManager.getInstance().getCurrentWorld().dispose();
        uiSpriteBatch.dispose();
        UIManager.getInstance().dispose();
        AudioManager.getInstance().dispose();
        TextureManager.getInstance().dispose();
        console.dispose();
    }
}
