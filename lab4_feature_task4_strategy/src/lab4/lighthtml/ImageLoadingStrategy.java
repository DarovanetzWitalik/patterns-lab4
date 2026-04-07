package lab4.lighthtml;

import java.io.IOException;

public interface ImageLoadingStrategy {
    ImageLoadResult load(String href) throws IOException;
}
