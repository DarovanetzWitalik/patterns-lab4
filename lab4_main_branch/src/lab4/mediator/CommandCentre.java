package lab4.mediator;

import java.util.ArrayList;
import java.util.List;

public class CommandCentre {
    private final List<Runway> runways = new ArrayList<>();

    public void registerRunway(Runway runway) {
        runways.add(runway);
    }

    public boolean requestLanding(Aircraft aircraft) {
        for (Runway runway : runways) {
            if (!runway.isBusy()) {
                runway.setBusy(true);
                aircraft.setCurrentRunway(runway);
                aircraft.setFlying(false);
                aircraft.log("Командний центр дозволив посадку на смугу " + runway.getName());
                return true;
            }
        }
        aircraft.log("Усі смуги зайняті. Очікуйте дозволу на посадку.");
        return false;
    }

    public boolean requestTakeOff(Aircraft aircraft) {
        Runway runway = aircraft.getCurrentRunway();
        if (runway == null) {
            aircraft.log("Літак не знаходиться на смузі, зліт неможливий.");
            return false;
        }
        aircraft.log("Командний центр дозволив зліт зі смуги " + runway.getName());
        runway.setBusy(false);
        aircraft.setCurrentRunway(null);
        aircraft.setFlying(true);
        return true;
    }
}
