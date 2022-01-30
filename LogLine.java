package be.intecbrussel;

import java.nio.file.Path;

public class LogLine {
    private Path path;
    private String extension;
    private boolean isReadable;
    private boolean isWritable;

    public LogLine(Path path, String extension, boolean isReadable, boolean isWritable) {
        this.path = path;
        this.extension = extension;
        this.isReadable = isReadable;
        this.isWritable = isWritable;
    }

    public Path getPath() {
        return path;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isReadable() {
        return isReadable;
    }

    public boolean isWritable() {
        return isWritable;
    }

    @Override
    public String toString() {
        return String.format("%-50s | %19s | %19s |",
                path.getFileName(), isReadable?"         x         ":"         /         ", isWritable?"         x         ":"         /         " );
    }
}