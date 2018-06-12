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
 * Constants.java
 * -----------------------------------------------------------------------------
 * This is a specialized java class designed to handle requests from classes
 * for constant values from XML files.
 * <p>
 * This class is a part of the CoreFramework, and is essential for the
 * normal functions of this software.
 * <p>
 * This class should not be changed under any circumstances.
 * -----------------------------------------------------------------------------
 * This is a legacy class ported from the TeslaUI project. Compatibility is
 * not guaranteed.
 * -----------------------------------------------------------------------------
 */

package main.java.constants;

import main.java.common.LogUtils;

import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    /**
     * Constant strings that define the path of definition files.
     * These lines should not be altered under any circumstances.
     */
    private final static String UI_DEFINITION_PATH = "/constants/UIDefinitions.constants";
    private final static String CORE_DEFINITION_PATH = "/constants/CoreDefinitions.constants";

    /**
     * A concurrent hashmap that keeps tabs on loaded constants
     * in order to avoid repeated parsing.
     */
    private static ConcurrentHashMap<String, Integer> integer_parameters = new ConcurrentHashMap<>();

    /**
     * Method that parses XML entries into integers.
     *
     * @param resource_name XML tag
     * @param type          variable type (Core/UI)
     * @return parsed integer
     */
    public static int getInt(String resource_name, Definitions type) {

        /* try finding the variable in the hashmap first */
        try {

            LogUtils.printCoreMessage("Requesting constant " + resource_name + "!");

            return integer_parameters.get(resource_name);
        } catch (NullPointerException e) {

            LogUtils.printCoreMessage("Constant " + resource_name + " not found in " +
                    "existing constants, will try to load from configuration files.");

            /* if variable do not exist, search the XML file */
            switch (type) {
                case CORE_CONSTANTS:
                    integer_parameters.put(resource_name,
                            ParseUtils.parseInt(CORE_DEFINITION_PATH, resource_name));
                    break;
                case UI_CONSTANTS:
                    integer_parameters.put(resource_name,
                            ParseUtils.parseInt(UI_DEFINITION_PATH, resource_name));
                    break;
                default:
                    /* if resource not found, return 0 */
                    integer_parameters.put(resource_name, 0);
            }

            LogUtils.printCoreMessage("Constant " + resource_name + " found in the " +
                    "configuration files!");

            return getInt(resource_name, type);
        }
    }

    /**
     * Method that parses XML entries into booleans.
     * (0 = false, 1 = true)
     *
     * @param resource_name XML tag
     * @param type          variable type (Core/UI)
     * @return  parsed boolean
     */
    public static boolean getBoolean(String resource_name, Definitions type) {

        LogUtils.printCoreMessage("Constant " + resource_name + " requested. Will try" +
                " to convert constant into boolean value.");

        return getInt(resource_name, type) == 1;

    }

}
