package KI306.Kusmyna.Lab6;

/**
 * Будівля як тип споруди.
 *
 * <p>Метрика для порівняння: загальна корисна площа = поверхи × площа забудови.</p>
 */
public class Building extends AbstractStructure {

    private final int floors;
    private final double footprintAreaM2;

    /**
     * Створює будівлю.
     *
     * @param name назва будівлі
     * @param yearBuilt рік побудови
     * @param floors кількість поверхів
     * @param footprintAreaM2 площа забудови (м^2)
     */
    public Building(String name, int yearBuilt, int floors, double footprintAreaM2) {
        super(name, yearBuilt);
        this.floors = floors;
        this.footprintAreaM2 = footprintAreaM2;
    }

    /**
     * Повертає кількість поверхів.
     *
     * @return кількість поверхів
     */
    public int getFloors() {
        return floors;
    }

    /**
     * Повертає площу забудови в квадратних метрах.
     *
     * @return площа забудови (м^2)
     */
    public double getFootprintAreaM2() {
        return footprintAreaM2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMetric() {
        return floors * footprintAreaM2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String metricName() {
        return "Загальна корисна площа (м^2)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printInfo() {
        System.out.printf(
                "Будівля: %s (рік: %d), поверхів: %d, площа забудови: %.2f м^2, %s: %.2f%n",
                getName(), getYearBuilt(), floors, footprintAreaM2, metricName(), getMetric()
        );
    }
}
