package viewSugangSincheong;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileNode {
    private File file;

    public FileNode(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }

    public String readFileContents() {
        try {
            Path filePath = file.toPath();
            List<String> lines = Files.readAllLines(filePath);
            StringBuilder content = new StringBuilder();
            for (String line : lines) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file contents.";
        }
    }

    @Override
    public String toString() {
        return getFileName();
    }
}
