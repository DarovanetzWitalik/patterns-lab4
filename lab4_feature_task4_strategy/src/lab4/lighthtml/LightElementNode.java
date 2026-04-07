package lab4.lighthtml;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LightElementNode extends LightNode {
    private final String tagName;
    private final DisplayType displayType;
    private final ClosingType closingType;
    private final List<String> cssClasses = new ArrayList<>();
    private final List<LightNode> children = new ArrayList<>();

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
