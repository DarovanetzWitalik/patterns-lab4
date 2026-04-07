package lab4.mediator;

public class Aircraft {
    private final String name;
    private final CommandCentre commandCentre;
    private boolean flying = true;
    private Runway currentRunway;
    private final StringBuilder log = new StringBuilder();

    public Aircraft(String name, CommandCentre commandCentre) {
        this.name = name;
        this.commandCentre = commandCentre;
    }

    public void requestLanding() {
        log("Літак " + name + " запитує посадку.");
        commandCentre.requestLanding(this);
    }

    public void requestTakeOff() {
        log("Літак " + name + " запитує зліт.");
        commandCentre.requestTakeOff(this);
    }

    public void log(String message) {
        log.append(message).append(System.lineSeparator());
    }

    public String flushLog() {
        String value = log.toString();
        log.setLength(0);
        return value;
    }

    public boolean isFlying() {
        return flying;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public Runway getCurrentRunway() {
        return currentRunway;
    }

    public void setCurrentRunway(Runway currentRunway) {
        this.currentRunway = currentRunway;
    }
}
