package lab4.app;

import lab4.chain.SupportMenu;
import lab4.mediator.Aircraft;
import lab4.mediator.CommandCentre;
import lab4.mediator.Runway;
import lab4.memento.TextEditor;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(runChainDemo());
        System.out.println(runMediatorDemo());
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
