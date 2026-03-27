public class Rocket {

    // Rocket properties
    static double dryMass = 5000;       // kg (rocket without fuel)
    static double fuelMass = 20000;     // kg (fuel)
    static double thrust = 500000;      // Newtons
    static double fuelBurnRate = 150;   // kg/s
    static double dragCoefficient = 0.5;
    static double rocketArea = 1.5;     // m^2 (cross-sectional area)
    static double airDensity = 1.225;   // kg/m^3 (sea level)

    // Constants
    static double gravity = 9.81;       // m/s^2

    public static void main(String[] args) {
        double velocity = 0;    // m/s
        double altitude = 0;    // m
        double time = 0;        // seconds
        double dt = 0.5;        // time step in seconds

        System.out.println("=== ROCKET LAUNCH SIMULATION ===");
        System.out.printf("%-8s %-12s %-12s %-12s %-10s%n", "Time(s)", "Altitude(m)", "Velocity(m/s)", "Fuel(kg)", "Phase");
        System.out.println("----------------------------------------------------------");

        while (altitude >= 0) {
            double totalMass = dryMass + fuelMass;
            String phase;

            double thrustForce;
            if (fuelMass > 0) {
                thrustForce = thrust;
                fuelMass -= fuelBurnRate * dt;
                if (fuelMass < 0) fuelMass = 0;
                phase = "POWERED";
            } else {
                thrustForce = 0;
                phase = altitude > 0 ? "COASTING" : "LANDED";
            }

            // Drag force (opposes motion)
            double drag = 0.5 * dragCoefficient * airDensity * rocketArea * velocity * velocity;
            if (velocity < 0) drag = -drag; // drag flips when falling

            // Net force = thrust - weight - drag
            double netForce = thrustForce - (totalMass * gravity) - drag;

            // Acceleration = F / m
            double acceleration = netForce / totalMass;

            // Update velocity and altitude
            velocity += acceleration * dt;
            altitude += velocity * dt;

            if (altitude < 0) altitude = 0;

            // Print every 5 seconds
            if (time % 5 == 0 || altitude == 0) {
                System.out.printf("%-8.1f %-12.1f %-13.1f %-10.1f %-10s%n",
                        time, altitude, velocity, fuelMass, phase);
            }

            time += dt;

            // Stop if landed after liftoff
            if (altitude == 0 && time > 2) break;
        }

        System.out.println("----------------------------------------------------------");
        System.out.println("Mission complete. Rocket has landed.");
    }
}
