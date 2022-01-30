package be.intecbrussel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileApp {

    public static void main(String[] args) {
        Path unsorted = Paths.get("C:\\Users\\JohnDough\\Documents\\FileIO\\unsorted");
        Path sorted = Paths.get("C:\\Users\\JohnDough\\Documents\\FileIO\\sorted");
        FileHandler fileHandler = new FileHandler(sorted);

        try {
            Files.walk(unsorted)
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(fileHandler::handleFile);

            FileWriter.fileWriter(sorted.resolve("summary").resolve("summary.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
