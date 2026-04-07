package lab4.lighthtml;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NetworkImageStrategy implements ImageLoadingStrategy {
    @Override
    public ImageLoadResult load(String href) throws IOException {
        URL url = new URL(href);
        byte[] data;
        try (InputStream inputStream = url.openStream()) {
            data = inputStream.readAllBytes();
        }
        return new ImageLoadResult("Мережа", href, data.length);
    }
}
