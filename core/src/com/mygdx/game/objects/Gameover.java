package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class Gameover {

    Vector2 position;
    TextureRegion frame;
    boolean show;


    Gameover(Assets assets){
        position = new Vector2(40, 70);
        frame = assets.gameover;
    }

    public void render(SpriteBatch batch) {
        if(show) {
            batch.draw(frame, position.x, position.y);
        }
    }
}

