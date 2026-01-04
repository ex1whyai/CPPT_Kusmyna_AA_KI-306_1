package KI306.Kusmyna.Lab6;

/**
 * Міст як тип споруди.
 *
 * <p>Метрика для порівняння: площа полотна = довжина × ширина.</p>
 */
public class Bridge extends AbstractStructure {

    private final double lengthM;
    private final double widthM;

    /**
     * Створює міст.
     *
     * @param name назва мосту
     * @param yearBuilt рік побудови
     * @param lengthM довжина (м)
     * @param widthM ширина (м)
     */
    public Bridge(String name, int yearBuilt, double lengthM, double widthM) {
        super(name, yearBuilt);
        this.lengthM = lengthM;
        this.widthM = widthM;
    }

    /**
     * Повертає довжину мосту в метрах.
     *
     * @return довжина (м)
     */
    public double getLengthM() {
        return lengthM;
    }

    /**
     * Повертає ширину мосту в метрах.
     *
     * @return ширина (м)
     */
    public double getWidthM() {
        return widthM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMetric() {
        return lengthM * widthM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String metricName() {
        return "Площа полотна (м^2)";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printInfo() {
        System.out.printf(
                "Міст: %s (рік: %d), довжина: %.2f м, ширина: %.2f м, %s: %.2f%n",
                getName(), getYearBuilt(), lengthM, widthM, metricName(), getMetric()
        );
    }
}
