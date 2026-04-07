package lab4.lighthtml;

import java.io.IOException;

public class LightImageNode extends LightNode {
    private final String href;
    private final String alt;

    public LightImageNode(String href, String alt) {
        this.href = href;
        this.alt = alt;
    }

    private ImageLoadingStrategy selectStrategy() {
        if (href.startsWith("http://") || href.startsWith("https://")) {
            return new NetworkImageStrategy();
        }
        return new FileSystemImageStrategy();
    }

    public String loadInfo() {
        try {
            return selectStrategy().load(href).describe();
        } catch (IOException ex) {
            return "Помилка завантаження з джерела '" + href + "': " + ex.getMessage();
        }
    }

    @Override
    public String outerHTML() {
        return "<img src=\"" + href + "\" alt=\"" + alt + "\"/>";
    }

    @Override
    public String innerHTML() {
        return "";
    }
}
