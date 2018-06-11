package main.java.common;

import main.java.path.Paths;
import sun.reflect.Reflection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Queue;
import javax.swing.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LogUtils {

    private static String log_directory;
    private static Queue<String> message_buffer;
    private static Timer message_update_daemon;

    //Constant boolean that toggles messages on/off.
    private static final boolean show_debug_messages = true;
    private static final boolean show_general_messages = true;
    private static final boolean show_repaint_messages = false;

    public static void init() {

        log_directory = new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "/AirControlV3/logs/";
        message_buffer = new ConcurrentLinkedQueue<>();
        message_update_daemon = new Timer(1000, new UpdateMessageEvent());

    }

    /**
     * Prints a generic message to console, including time and the
     * originating class name.
     * @param message     message to be printed
     */
    public static void printGeneralMessage (String message) {
        if (show_general_messages) System.out.println(getDate() + " -> |" +  Reflection.getCallerClass(2) + "| " + message);
    }

    /**
     * Prints a message to console with the DEBUG tag, including time and
     * the originating class name.
     * @param message     message to be printed
     */
    public static void printDebugMessage (String message) {
        if (show_debug_messages) System.out.println(getDate() + " -> |" +  Reflection.getCallerClass(2) + "| " + " DEBUG " + message);
    }

    /**
     * Prints a message to console with the ERROR tag, including time and
     * the originating class name.
     * @param message     message to be printed
     */
    public static void printErrorMessage (String message) {
        System.out.println(getDate() + " -> |" +  Reflection.getCallerClass(2) + "| " + " ERROR " + message);
    }

    public static void printRepaintMessage () {
        if (show_repaint_messages) System.out.println(getDate() + " -> |" + Reflection.getCallerClass(2) + "|" + " REPAINT EVENT: Invoked successfully.");
    }


    /**
     * Prints a generic message to console, including time.
     * DEPRECATED CODE | USE printGeneralMessage
     * @param message     message to be printed
     */
    @Deprecated
    public static void print (String message) {
        System.out.println(getDate() + " -> " + message +
                "\nConsole.print() had been deprecated. Use Console.printGeneralMessage() instead.");
    }

    /**
     * Gets the date from system for console output.
     * @return    string representation of current time
     */
    private static String getDate () {
        DateFormat date_format = new SimpleDateFormat("HH:mm:ss:SSS");
        Date date_object = new Date();

        return date_format.format(date_object);
    }

    private static class UpdateMessageEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {



        }

        private void updateMessages()
            throws Exception {

            Path dir_path = java.nio.file.Paths.get(log_directory);
            Files.createDirectories(dir_path);

            PrintWriter writer =
                    new PrintWriter(new FileOutputStream(log_directory + "log_" + getDate() + ".AirControlLog"));

            String buffered_message;
            buffered_message = message_buffer.poll();

            while (buffered_message != null) {
                System.out.println(buffered_message);
                writer.println(buffered_message);

                buffered_message = message_buffer.poll();
            }

            writer.flush();
            writer.close();

        }

        private static String getDate () {
            DateFormat date_format = new SimpleDateFormat("yyyy-MM-dd_HH");
            Date date_object = new Date();

            return date_format.format(date_object);
        }

    }

}
