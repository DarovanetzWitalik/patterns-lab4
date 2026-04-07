package lab4.app;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import lab4.chain.SupportMenu;
import lab4.lighthtml.LightImageNode;
import lab4.mediator.Aircraft;
import lab4.mediator.CommandCentre;
import lab4.mediator.Runway;
import lab4.memento.TextEditor;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(runChainDemo());
        System.out.println(runMediatorDemo());
        System.out.println(runStrategyDemo());
        System.out.println(runMementoDemo());
    }

    private static String runChainDemo() {
        SupportMenu menu = new SupportMenu();
        return menu.runScenario(List.of(9, 4));
    }

    private static String runMediatorDemo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Посередник: командний центр аеропорту ===").append(System.lineSeparator());

        CommandCentre centre = new CommandCentre();
        new Runway("RW-01", centre);
        new Runway("RW-02", centre);

        Aircraft boeing = new Aircraft("Boeing-737", centre);
        Aircraft airbus = new Aircraft("Airbus-A320", centre);
        Aircraft ember = new Aircraft("Embraer-190", centre);

        boeing.requestLanding();
        airbus.requestLanding();
        ember.requestLanding();
        boeing.requestTakeOff();
        ember.requestLanding();

        sb.append(boeing.flushLog());
        sb.append(airbus.flushLog());
        sb.append(ember.flushLog());
        return sb.toString();
    }

    private static String runStrategyDemo() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Стратегія: елемент Image ===").append(System.lineSeparator());

        Path localImage = Path.of("resources", "local_image.bin");
        LightImageNode localNode = new LightImageNode(localImage.toString(), "local-image");
        sb.append(localNode.outerHTML()).append(System.lineSeparator());
        sb.append(localNode.loadInfo()).append(System.lineSeparator());

        HttpServer server = startServer(Path.of("resources", "network_image.bin"));
        try {
            LightImageNode networkNode = new LightImageNode("http://127.0.0.1:8123/image", "network-image");
            sb.append(networkNode.outerHTML()).append(System.lineSeparator());
            sb.append(networkNode.loadInfo()).append(System.lineSeparator());
        } finally {
            server.stop(0);
        }
        return sb.toString();
    }

    private static HttpServer startServer(Path file) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8123), 0);
        server.createContext("/image", exchange -> serveFile(exchange, file));
        server.start();
        return server;
    }

    private static void serveFile(HttpExchange exchange, Path file) throws IOException {
        byte[] data = Files.readAllBytes(file);
        exchange.sendResponseHeaders(200, data.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(data);
        }
    }

    private static String runMementoDemo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Мементо: текстовий редактор ===").append(System.lineSeparator());

        TextEditor editor = new TextEditor("Початкова версія документа");
        sb.append("Стан 1: ").append(editor.currentText()).append(System.lineSeparator());

        editor.save();
        editor.write("Версія після першого редагування");
        sb.append("Стан 2: ").append(editor.currentText()).append(System.lineSeparator());

        editor.save();
        editor.write("Версія після другого редагування");
        sb.append("Стан 3: ").append(editor.currentText()).append(System.lineSeparator());

        editor.undo();
        sb.append("Після undo #1: ").append(editor.currentText()).append(System.lineSeparator());

        editor.undo();
        sb.append("Після undo #2: ").append(editor.currentText()).append(System.lineSeparator());
        return sb.toString();
    }
}
