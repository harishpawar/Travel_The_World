package utils;

import Entities.Browser;
import com.google.common.io.Resources;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FileUtils {

    private FileUtils(){

    }

    public static String readResource(String fileName){
        String result;
        URL url = Resources.getResource(fileName);
        try {
            result = Resources.toString(url, StandardCharsets.UTF_8);
            return result;
        }catch (IOException e){
            throw new RuntimeException("Not able to read file :" , e);
        }
    }

    public static String copyResourceToTempFolder(String resourceName){
        File directory = new File("target/temp/" + System.currentTimeMillis());
        directory.mkdir();

        File file = new File(directory, resourceName);
        URL url = Resources.getResource(resourceName);
        try(FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Resources.copy(url, fileOutputStream);
        }catch (IOException e){
            throw new RuntimeException("Not able to read/write file ", e);
        }
        return file.getPath();
    }

}
