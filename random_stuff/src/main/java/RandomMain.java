import random.Aircraft;
import random.Helicopter;

public class RandomMain {

    public static void main(String[] args) {

        Aircraft aircraft = new Aircraft();
        aircraft.land();

        Helicopter helicopter = new Helicopter();
        helicopter.land();

        Aircraft helicopterAsAircraft = new Helicopter();
        helicopterAsAircraft.land();

    }

}
