package lab4.lighthtml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemImageStrategy implements ImageLoadingStrategy {
    @Override
    public ImageLoadResult load(String href) throws IOException {
        Path path = Path.of(href);
        int size = (int) Files.size(path);
        return new ImageLoadResult("Файлова система", path.toAbsolutePath().toString(), size);
    }
}
