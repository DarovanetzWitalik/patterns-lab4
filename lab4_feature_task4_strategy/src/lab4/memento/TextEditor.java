package lab4.memento;

public class TextEditor {
    private final TextDocument document;
    private final EditorHistory history = new EditorHistory();

    public TextEditor(String initialText) {
        this.document = new TextDocument(initialText);
    }

    public void save() {
        history.push(document.save());
    }

    public void write(String newText) {
        document.setContent(newText);
    }

    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }
        document.restore(history.pop());
        return true;
    }

    public String currentText() {
        return document.getContent();
    }
}
