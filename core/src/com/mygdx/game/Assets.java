package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets extends AssetManager {
    public TextureAtlas atlas;
    public Animation<TextureRegion> space;
    public Animation<TextureRegion> alien;
    public Animation<TextureRegion> aliendie;
    public Animation<TextureRegion> naveidle;
    public Animation<TextureRegion> naveleft;
    public Animation<TextureRegion> naveright;
    public Animation<TextureRegion> naveshoot;
    public Animation<TextureRegion> shoot;
    public Animation<TextureRegion> alienshoot;
    public Animation<TextureRegion> naveshield;
    public Animation<TextureRegion> navedead;
    public TextureAtlas.AtlasRegion gameover;
    public TextureRegion wininfo;

    public Sound shootSound = Gdx.audio.newSound(Gdx.files.internal("shootsound.wav"));
    public Sound alienSound = Gdx.audio.newSound(Gdx.files.internal("aliensound.wav"));
    public Sound aliendieSound = Gdx.audio.newSound(Gdx.files.internal("aliendie.wav"));

    public void load(){
        load("invaders.atlas", TextureAtlas.class);
    }

    @Override
    public synchronized boolean update() {
        boolean update = super.update();

        if(update){
            atlas = get("invaders.atlas", TextureAtlas.class);

            loadAssets();
        }
        return update;
    }

    void loadAssets(){
        space = new Animation<TextureRegion>(1f, atlas.findRegions("space"));

        alien = new Animation<TextureRegion>(0.4f, atlas.findRegions("alien"));
        aliendie = new Animation<TextureRegion>(0.05f, atlas.findRegions("aliendie"));
        naveidle = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveidle"));
        naveleft = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveleft"));
        naveright = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveright"));
        naveshoot = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveshoot"));
        shoot = new Animation<TextureRegion>(0.02f, atlas.findRegions("shoot"));
        alienshoot = new Animation<TextureRegion>(0.1f, atlas.findRegions("alienshoot"));
        naveshield = new Animation<TextureRegion>(0.1f, atlas.findRegions("naveidle_shield"));
        navedead = new Animation<TextureRegion>(0.1f, atlas.findRegions("navedead"));
        gameover = atlas.findRegion("gameover");




        wininfo = atlas.findRegion("win");

    }
}
