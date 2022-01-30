package be.intecbrussel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileHandler {
    private Path destinationFolder;

    public FileHandler(Path destinationFolder) {
        this.destinationFolder = destinationFolder;
    }

    public void handleFile(Path path){
        String extension = FileUtil.getFileExtension(path);
        boolean isReadable = Files.isReadable(path);
        boolean isWritable = Files.isWritable(path);

        if (fileExtensionFolderDoesNotExist(extension)) {
            createDirectory(destinationFolder.resolve(extension));
        }

        registerFileForLogging(path, extension, isReadable, isWritable);
        moveFileToDirectory(path, extension);

    }

    private void createDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void moveFileToDirectory(Path path, String extension) {
        try {
            Files.move(path, destinationFolder.resolve(extension).resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerFileForLogging(Path path, String extension, boolean isReadable, boolean isWritable) {
        FileLog fileLog = new FileLog(path, extension, isReadable, isWritable);
        FileWriter.addFileLog(fileLog);
    }

    private boolean fileExtensionFolderDoesNotExist(String extension) {
        return Files.notExists(destinationFolder.resolve(extension));
    }
}