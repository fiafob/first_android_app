package com.example.projectkal;

import android.content.Context;

public class Score {
    private int currentPoints = 0;
    private MainActivity mainActivity;
    private Storage storage;
    private int level = 0;

    public Score( Context context, Storage storage ) {
        mainActivity = (MainActivity) context;
        this.storage = storage;
        Storage.init( context );
    }

    public void writeHighscore(){ Storage.update("topScore", currentPoints); }
    public int loadHighscore(){ return Storage.load("topScore"); }

    public void setCurrentPoints(int currentPoints) { this.currentPoints = currentPoints; }
    public int getCurrentPoints() { return currentPoints; }

    public void setLevel() {

        if(currentPoints>=20)  this.level = 1;
        if(currentPoints>=60) this.level = 2;
        if(currentPoints>=100) this.level = 3;
        if(currentPoints>=140) this.level = 4;
        if(currentPoints>=180) this.level = 5;
        if(currentPoints>=220) this.level = 6;
        if(currentPoints>=260) this.level = 7;

    }
    public int getLevel() { return this.level; }
}
