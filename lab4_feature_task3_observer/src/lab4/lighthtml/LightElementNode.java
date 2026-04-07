package lab4.lighthtml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LightElementNode extends LightNode {
    private final String tagName;
    private final DisplayType displayType;
    private final ClosingType closingType;
    private final List<String> cssClasses = new ArrayList<>();
    private final List<LightNode> children = new ArrayList<>();
    private final Map<String, List<EventListener>> listeners = new HashMap<>();

    public LightElementNode(String tagName, DisplayType displayType, ClosingType closingType) {
        this.tagName = tagName;
        this.displayType = displayType;
        this.closingType = closingType;
    }

    public LightElementNode addClass(String className) {
        cssClasses.add(className);
        return this;
    }

    public LightElementNode addChild(LightNode node) {
        children.add(node);
        return this;
    }

    public LightElementNode on(String eventName, EventListener listener) {
        listeners.computeIfAbsent(eventName, key -> new ArrayList<>()).add(listener);
        return this;
    }

    public String dispatchEvent(String eventName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Подія '").append(eventName).append("' на елементі <").append(tagName).append(">")
                .append(System.lineSeparator());
        List<EventListener> eventListeners = listeners.getOrDefault(eventName, List.of());
        if (eventListeners.isEmpty()) {
            sb.append("Слухачі для цієї події відсутні.").append(System.lineSeparator());
            return sb.toString();
        }
        for (EventListener listener : eventListeners) {
            RecordingEventContext.startCapture();
            listener.handle(eventName, this);
            sb.append(RecordingEventContext.stopCapture());
        }
        return sb.toString();
    }

    protected String attributesHtml() {
        StringBuilder attributes = new StringBuilder();
        if (!cssClasses.isEmpty()) {
            attributes.append(" class=\"")
                    .append(String.join(" ", cssClasses))
                    .append("\"");
        }
        attributes.append(" data-display=\"").append(displayType.name().toLowerCase()).append("\"");
        return attributes.toString();
    }

    public int childCount() {
        return children.size();
    }

    @Override
    public String outerHTML() {
        if (closingType == ClosingType.SELF_CLOSING) {
            return "<" + tagName + attributesHtml() + "/>";
        }
        return "<" + tagName + attributesHtml() + ">" + innerHTML() + "</" + tagName + ">";
    }

    @Override
    public String innerHTML() {
        return children.stream().map(LightNode::outerHTML).collect(Collectors.joining());
    }

    public String getTagName() {
        return tagName;
    }
}
