package helpers;

import java.io.File;

public class SystemHelpers {
    public static String getCurrentDir(){
        return System.getProperty("user.dir") + File.separator;
    }
}
