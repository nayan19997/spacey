package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Assets;
import com.mygdx.game.Controls;

public class Ship {

    enum State {
        IDLE, LEFT, RIGHT, SHOOT, DYING, DEAD
    }

    Vector2 position;

    State state;
    float stateTime;
    float speed = 5;
    int health = 3;

    TextureRegion frame;
    TextureRegion shield;
    TextureRegion gameover;



    Weapon weapon;

    Ship(int initialPosition){
        position = new Vector2(initialPosition, 10);
        state = State.IDLE;
        stateTime = 0;

        weapon = new Weapon();
    }


    void setFrame(Assets assets){
        switch (state){
            case IDLE:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                shield = assets.naveshield.getKeyFrame(stateTime, true);
                break;
            case LEFT:
                frame = assets.naveleft.getKeyFrame(stateTime, true);
                break;
            case RIGHT:
                frame = assets.naveright.getKeyFrame(stateTime, true);
                break;
            case SHOOT:
                frame = assets.naveshoot.getKeyFrame(stateTime, true);
                break;
            case DYING:
                frame = assets.navedead.getKeyFrame(stateTime, true);
                break;
            default:
                frame = assets.naveidle.getKeyFrame(stateTime, true);
                break;
        }
    }

    void render(SpriteBatch batch){
        batch.draw(frame, position.x, position.y);
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            batch.draw(shield, position.x-2, position.y-11);
        }

        weapon.render(batch);
    }

    boolean lifezero() {
        return health == 0;
    }

    public void update(float delta, Assets assets) {
        stateTime += delta;

        if(Controls.isLeftPressed()){
            moveLeft();
        } else if(Controls.isRightPressed()){
            moveRight();
        } else {
            idle();
        }

        if(Controls.isShootPressed()) {
            shoot();
            assets.shootSound.play();
        }




        switch (state){
            case IDLE:
            case RIGHT:
            case LEFT:
            case SHOOT:
                if (Controls.isLeftPressed()) {
                    setState(State.LEFT);
                    moveLeft();
                } else if (Controls.isRightPressed()) {
                    setState(State.RIGHT);
                    moveRight();
                } else {
                    setState(State.IDLE);
                }

                if (Controls.isShootPressed()) {
                    setState(State.SHOOT);
                    shoot();
                    assets.shootSound.play();
                }
                if (health == 0) {
                    setState(State.DYING);

                }
                break;
            case DYING:
                if(assets.navedead.isAnimationFinished(stateTime))
                {
                    setState(State.DEAD);
                }
                break;
        }






        setFrame(assets);

        weapon.update(delta, assets);
    }

    void idle(){
        state = State.IDLE;
    }

    void moveLeft(){
        position.x -= speed;
        state = State.LEFT;
    }

    void moveRight(){
        position.x += speed;
        state = State.RIGHT;
    }

    void shoot(){
        state = State.SHOOT;
        weapon.shoot(position.x +16);
    }

    public void damage() {
        health -= 1;
    }

    private void setState(State state) {
        this.state = state;
        stateTime = 0;
    }
}
