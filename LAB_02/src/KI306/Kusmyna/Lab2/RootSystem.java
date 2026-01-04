package KI306.Kusmyna.Lab2;

/**
 * Клас {@code RootSystem} описує кореневу систему рослини.
 */
public class RootSystem {
    /** Глибина коріння (см). */
    private int depthCm;
    /** Рівень розгалуження (>=1). */
    private int branchingLevel;
    /** Зволоженість 0..100. */
    private int hydration;

    /**
     * Конструктор за замовчуванням (стартові параметри коренів).
     */
    public RootSystem() {
        this(5, 1, 50);
    }

    /**
     * Конструктор.
     *
     * @param depthCm глибина коріння в сантиметрах
     * @param branchingLevel рівень розгалуження (>=1)
     * @param hydration зволоженість 0..100
     */
    public RootSystem(int depthCm, int branchingLevel, int hydration) {
        this.depthCm = Math.max(0, depthCm);
        this.branchingLevel = Math.max(1, branchingLevel);
        this.hydration = clamp(hydration, 0, 100);
    }

    /**
     * Повертає глибину коріння.
     *
     * @return глибина коріння (см)
     */
    public int getDepthCm() { return depthCm; }

    /**
     * Повертає рівень розгалуження.
     *
     * @return рівень розгалуження (>=1)
     */
    public int getBranchingLevel() { return branchingLevel; }

    /**
     * Повертає поточну зволоженість.
     *
     * @return зволоженість 0..100
     */
    public int getHydration() { return hydration; }

    /**
     * Збільшує/зменшує глибину коріння.
     *
     * @param cm зміна глибини в сантиметрах (може бути від’ємною)
     */
    public void growDeeper(int cm) {
        depthCm = Math.max(0, depthCm + cm);
    }

    /**
     * Збільшує/зменшує розгалуження коріння.
     *
     * @param delta зміна рівня розгалуження (може бути від’ємною)
     */
    public void increaseBranching(int delta) {
        branchingLevel = Math.max(1, branchingLevel + delta);
    }

    /**
     * Поглинає воду і підвищує зволоженість (спрощена модель).
     *
     * @param ml кількість води в мл
     */
    public void absorbWater(int ml) {
        int add = Math.max(0, ml) / 20;
        hydration = clamp(hydration + add, 0, 100);
    }

    /**
     * Витрачає воду і зменшує зволоженість.
     *
     * @param units умовні одиниці витрати
     */
    public void consumeWater(int units) {
        hydration = clamp(hydration - Math.max(0, units), 0, 100);
    }

    /**
     * Обмежує значення у вказаному діапазоні.
     *
     * @param v значення
     * @param lo нижня межа
     * @param hi верхня межа
     * @return значення, обрізане до [lo..hi]
     */
    private static int clamp(int v, int lo, int hi) {
        return Math.max(lo, Math.min(hi, v));
    }
}
