package lab4.lighthtml;

public class LightTextNode extends LightNode {
    private final String text;

    public LightTextNode(String text) {
        this.text = text;
    }

    @Override
    public String outerHTML() {
        return text;
    }

    @Override
    public String innerHTML() {
        return text;
    }
}
