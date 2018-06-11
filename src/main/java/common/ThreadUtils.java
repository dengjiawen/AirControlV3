package main.java.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadUtils {

    public static ExecutorService mouse_worker;
    public static ExecutorService frost_worker;
    public static ExecutorService position_worker;

    public static void init() {
        mouse_worker = Executors.newCachedThreadPool();
        frost_worker = Executors.newCachedThreadPool();
        position_worker = Executors.newCachedThreadPool();
    }

}
