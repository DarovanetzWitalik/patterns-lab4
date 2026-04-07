package lab4.lighthtml;

public final class RecordingEventContext {
    private static final ThreadLocal<StringBuilder> BUFFER = ThreadLocal.withInitial(StringBuilder::new);

    private RecordingEventContext() {
    }

    public static void startCapture() {
        BUFFER.get().setLength(0);
    }

    public static void record(String message) {
        BUFFER.get().append(message).append(System.lineSeparator());
    }

    public static String stopCapture() {
        return BUFFER.get().toString();
    }
}
