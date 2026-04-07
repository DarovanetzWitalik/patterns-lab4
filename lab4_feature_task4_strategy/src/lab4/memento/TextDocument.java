package lab4.memento;

public class TextDocument {
    private String content;

    public TextDocument(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Snapshot save() {
        return new Snapshot(content);
    }

    public void restore(Snapshot snapshot) {
        this.content = snapshot.content;
    }

    public static class Snapshot {
        private final String content;

        private Snapshot(String content) {
            this.content = content;
        }
    }
}
