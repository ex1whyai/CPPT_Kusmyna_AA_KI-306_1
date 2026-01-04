package KI306.Kusmyna.Lab6;

/**
 * Абстрактний базовий клас для споруд.
 *
 * <p>Містить спільні поля (назва, рік побудови) та стандартне порівняння
 * споруд за значенням {@link #getMetric()}.</p>
 */
public abstract class AbstractStructure implements Structure {

    private final String name;
    private final int yearBuilt;

    /**
     * Створює споруду з заданими параметрами.
     *
     * @param name назва споруди
     * @param yearBuilt рік побудови
     */
    protected AbstractStructure(String name, int yearBuilt) {
        this.name = name;
        this.yearBuilt = yearBuilt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Повертає рік побудови споруди.
     *
     * @return рік побудови
     */
    public int getYearBuilt() {
        return yearBuilt;
    }

    /**
     * Порівнює споруди за їх метрикою.
     *
     * @param other інша споруда
     * @return від’ємне число, 0 або додатне число (як у {@link Comparable})
     */
    @Override
    public int compareTo(Structure other) {
        return Double.compare(this.getMetric(), other.getMetric());
    }
}
