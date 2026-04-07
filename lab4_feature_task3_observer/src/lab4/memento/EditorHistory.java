package lab4.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class EditorHistory {
    private final Deque<TextDocument.Snapshot> states = new ArrayDeque<>();

    public void push(TextDocument.Snapshot snapshot) {
        states.push(snapshot);
    }

    public TextDocument.Snapshot pop() {
        return states.pop();
    }

    public boolean isEmpty() {
        return states.isEmpty();
    }
}
