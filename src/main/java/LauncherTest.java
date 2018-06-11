package main.java;

import main.java.common.ThreadUtils;
import main.java.logic.CloudDirector;
import main.java.logic.RefUtils;
import main.java.music.MusicUtils;
import main.java.path.MapUtils;
import main.java.path.Paths;
import main.java.resources.FontResource;
import main.java.resources.ImageResource;
import main.java.ui.RenderUtils;
import main.java.ui.Window;

public class LauncherTest {

    static {

        ImageResource.init();
        FontResource.init();

        if (!Paths.readTelemetryData()) {
            System.out.println("READ FAILED");
            MapUtils.init();
            Paths.saveTelemetryData();
        }

        ThreadUtils.init();
        MusicUtils.init();
        RefUtils.init();

        CloudDirector.init();

        System.gc();
    }

    public static void main (String... args) {

        Window window = new Window();

        RenderUtils.init();

    }

}
