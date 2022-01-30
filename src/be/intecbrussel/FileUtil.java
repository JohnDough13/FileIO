package be.intecbrussel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtil {
    public static String getFileExtension(Path path) {
        String extension = "";
        int i = path.getFileName().toString().lastIndexOf('.');
        if (i >= 0) {
            extension = path.getFileName().toString().substring(i+1);
        }

        return getFileExtensionName(path, extension);
    }

    private static String getFileExtensionName(Path path, String extension) {
        if (isFileHidden(path)) {
            return "Hidden";
        } else if (extension.trim().isEmpty()) {
            return "No extension";
        } else {
            return extension;
        }
    }

    private static boolean isFileHidden(Path path) {
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            return false;
        }
    }
}