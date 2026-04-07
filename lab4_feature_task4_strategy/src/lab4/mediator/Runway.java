package lab4.mediator;

public class Runway {
    private final String name;
    private final CommandCentre commandCentre;
    private boolean busy;

    public Runway(String name, CommandCentre commandCentre) {
        this.name = name;
        this.commandCentre = commandCentre;
        this.commandCentre.registerRunway(this);
    }

    public String getName() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
