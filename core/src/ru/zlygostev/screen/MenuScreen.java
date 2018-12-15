package ru.zlygostev.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private Texture img;
    private Texture imgBg;
    private Texture imgBadlogic;

    private Vector2 pos;
    private Vector2 v;
    private Vector2 direct;

    public static float shipSizeHalf = (float) 0.4;
    public static float speed = (float) 0.08;

    @Override
    public void show() {
        super.show();
        img = new Texture("shuttle256.png");
        imgBadlogic = new Texture("badlogic.jpg");
        imgBg = new Texture("backgroundl1.jpg");

        pos = new Vector2(0,0);
        posDist = new Vector2(pos);
        v = new Vector2(0,0);
        direct = new Vector2(0,0);

        System.out.println("posDist touch X = " + posDist.x + " touch Y = " + posDist.y);
        System.out.println("pos touch X = " + pos.x + " touch Y = " + pos.y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.0f, 0.0f,0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (posDist.cpy().sub(pos).len() < speed) {
            posDist = pos.cpy();
            v.set(0,0);
        } else {
            direct = posDist.cpy().sub(pos);
            direct.nor();
            v = direct.cpy().scl(speed);
        }
        pos.add(v);

        batch.begin();
        batch.draw(imgBg, -2f, -1f, 4f, 2f);
        batch.draw(img, (pos.x)/glBoundsConstHalf-shipSizeHalf, (pos.y)/glBoundsConstHalf-shipSizeHalf, shipSizeHalf*2, shipSizeHalf*2);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        imgBg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
//        posDist.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touch X = " + posDist.x + " touch Y = " + posDist.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        if (keycode == Input.Keys.LEFT) {
            posDist.set(posDist.x-speed*4, posDist.y);
        }
        if (keycode == Input.Keys.RIGHT) {
            posDist.set(posDist.x+speed*4, posDist.y);
        }
        if (keycode == Input.Keys.UP) {
            posDist.set(posDist.x, posDist.y+speed*4);
        }
        if (keycode == Input.Keys.DOWN) {
            posDist.set(posDist.x, posDist.y-speed*4);
        }

        return false;
    }
}
