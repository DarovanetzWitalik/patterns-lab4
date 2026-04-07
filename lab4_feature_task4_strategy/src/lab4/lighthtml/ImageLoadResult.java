package lab4.lighthtml;

public class ImageLoadResult {
    private final String sourceType;
    private final String source;
    private final int size;

    public ImageLoadResult(String sourceType, String source, int size) {
        this.sourceType = sourceType;
        this.source = source;
        this.size = size;
    }

    public String describe() {
        return sourceType + " -> " + source + " (" + size + " bytes)";
    }
}
