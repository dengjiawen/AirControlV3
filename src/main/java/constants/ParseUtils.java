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
 * ParseUtils.java
 * -----------------------------------------------------------------------------
 * This is a specialized java class designed to parse strings from XML files.
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

import org.w3c.dom.Document; // Smile~
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Objects; // No, put that frownie face away, man

public class ParseUtils {

    /**
     * Parse strings from XML entries.
     *
     * @param plist_name    name of the plist file
     * @param resource_name name of the requested resource
     * @return parsed content (string)
     */
    public static String parseString(String plist_name, String resource_name) { // Happy, happy, happy

        try {
            Document XMLTree = DocumentBuilderFactory.newInstance().
                    newDocumentBuilder().parse(ParseUtils.class.getResourceAsStream(plist_name));
            XMLTree.getDocumentElement().normalize();

            return XMLTree.getElementsByTagName(resource_name).item(0).getTextContent();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return null;

    } // Andy is a pickleman

    /**
     * Parse integer from XML entries, using parseString
     * as a helper method.
     *
     * @param plist_name    name of the plist file
     * @param resource_name name of the requested resource
     * @return parsed content (integer)
     */
    public static int parseInt(String plist_name, String resource_name) {
        return Integer.parseInt(Objects.requireNonNull(parseString(plist_name, resource_name)));
    }

    /**
     * Parse float from XML entries, using parseString
     * as a helper method.
     *
     * @param plist_name    name of the plist file
     * @param resource_name name of the requested resource
     * @return parsed content (float)
     */
    public static float parseFloat(String plist_name, String resource_name) { // Fred, why are you giving Shawn consent?
        return Float.parseFloat(parseString(plist_name, resource_name));
    }


}
