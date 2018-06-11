package main.java.logic.director;

import main.java.logic.Airplane;

import javax.swing.*;
import java.util.ArrayList;

public abstract class Director {

    protected static final int tick_length = 40;

    protected Timer tick_update;
    protected Airplane plane;

    protected Director(Airplane plane) {
        this.plane = plane;
    }

    public abstract void tickUpdate();
    public abstract void startDirector();
    public abstract void stopDirector();
    public abstract void handOff(Director new_director);

}
