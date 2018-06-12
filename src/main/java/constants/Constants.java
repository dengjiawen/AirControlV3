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
 */

package main.java.constants;

import java.util.concurrent.ConcurrentHashMap;

public class Constants {

    private final static String UI_DEFINITION_PATH = "/constants/UIDefinitions.constants";
    private final static String CORE_DEFINITION_PATH = "/constants/CoreDefinitions.constants";

    private static ConcurrentHashMap<String, Integer> integer_parameters = new ConcurrentHashMap<>();

    public static int getInt(String resource_name, Definitions type) {
        try {
            return integer_parameters.get(resource_name);
        } catch (NullPointerException e) {
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
                    return 0;
            }
            return getInt(resource_name, type);
        }
    }

    public static boolean getBoolean(String resource_name, Definitions type) {

        return getInt(resource_name, type) == 1;

    }

}
