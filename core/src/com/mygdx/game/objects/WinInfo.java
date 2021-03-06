package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;

public class WinInfo {
    Vector2 position;
    TextureRegion frame;
    boolean show;


    WinInfo(Assets assets){
        position = new Vector2(40, 70);
        frame = assets.wininfo;
    }

    public void render(SpriteBatch batch) {
        if(show) {
            batch.draw(frame, position.x, position.y);
        }
    }
}
