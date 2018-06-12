/**
 * Copyright 2018 (C) Jiawen Deng. All rights reserved.
 * <p>
 * This document is the property of Jiawen Deng.
 * It is considered confidential and proprietary.
 * <p>
 * This document may not be reproduced or transmitted in any form,
 * in whole or in part, without the express written permission of
 * Jiawen Deng.
 * <p>
 * -----------------------------------------------------------------------------
 * Director.java
 * -----------------------------------------------------------------------------
 * This is an abstract data type (ADT) that defines how a director should
 * behave. A director directs the position of an Airplane object, providing
 * the Canvas object with up to date position and angle information.
 *
 * This class is a part of CoreLogic.
 * -----------------------------------------------------------------------------
 */

package main.java.logic.director;

import main.java.common.LogUtils;
import main.java.constants.Constants;
import main.java.constants.Definitions;

import main.java.logic.Airplane;

import javax.swing.*;

public abstract class Director {

    static {
        tick_length = Constants.getInt("tickLength", Definitions.ZERO_DAY_PATCH);
    }

    /* variable defining the length of director updates */
    static int tick_length;


    Timer tick_update;  // timer that updates Airplane position and angle
    Airplane plane;     // the Airplane object that the director manages

    /**
     * Default constructor (deprecated).
     */
    @ Deprecated
    private Director() {}

    /**
     * Constructor that takes a plane as a parameter.
     * @param plane Airplane object
     */
    Director(Airplane plane) {
        this.plane = plane;
    }

    /**
     * The following are abstract methods that must
     * be included in each of the daughter class.
     */
    public abstract void tickUpdate();
    public abstract void startDirector();
    public abstract void stopDirector();
    public abstract void handOff(Director new_director);

}
