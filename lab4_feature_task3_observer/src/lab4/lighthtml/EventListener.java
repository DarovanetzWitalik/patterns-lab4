package lab4.lighthtml;

@FunctionalInterface
public interface EventListener {
    void handle(String eventName, LightElementNode source);
}
