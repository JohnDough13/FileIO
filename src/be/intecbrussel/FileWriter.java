package be.intecbrussel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWriter {
    private static Map<String, List<FileLog>> fileLogMap = new HashMap<>();

    public static void addFileLog (FileLog fileLog) {
        if (fileLogMap.containsKey(fileLog.getExtension())) {
            fileLogMap.get(fileLog.getExtension()).add(fileLog);
        } else {
            List<FileLog> list = new ArrayList<>();
            list.add(fileLog);
            fileLogMap.put(fileLog.getExtension(), list);
        }
    }

    public static void fileWriter(Path summaryPath) {
        List<String> summary = new ArrayList<>();

        summary.add(String.format("%-50s | %19s | %19s |%n%n", "Filename", "isReadable?", "isWritable?"));

        for (Map.Entry<String, List<FileLog>> stringListEntry : fileLogMap.entrySet()) {
            summary.add(stringListEntry.getKey() + ": ");

            List<FileLog> fileLogList = stringListEntry.getValue();
            fileLogList.forEach(fileLog -> summary.add(fileLog.toString()));
            summary.add("\n\n");
        }

        writeToFile(summaryPath, summary);
    }

    private static void writeToFile(Path summaryPath, List<String> summary) {
        try {
            if (Files.notExists(summaryPath.getParent())) {
                Files.createDirectories(summaryPath.getParent());
            }
            Files.write(summaryPath, summary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

