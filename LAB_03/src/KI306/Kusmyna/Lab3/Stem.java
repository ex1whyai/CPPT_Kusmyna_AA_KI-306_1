package KI306.Kusmyna.Lab3;

/**
 * Клас {@code Stem} описує стебло рослини.
 */
public class Stem {
    /** Висота (см). */
    private int heightCm;
    /** Діаметр (мм). */
    private int diameterMm;

    /**
     * Конструктор за замовчуванням.
     */
    public Stem() {
        this(10, 3);
    }

    /**
     * Конструктор.
     *
     * @param heightCm висота (см)
     * @param diameterMm діаметр (мм)
     */
    public Stem(int heightCm, int diameterMm) {
        this.heightCm = Math.max(0, heightCm);
        this.diameterMm = Math.max(1, diameterMm);
    }

    /**
     * Повертає висоту стебла.
     *
     * @return висота (см)
     */
    public int getHeightCm() { return heightCm; }

    /**
     * Повертає діаметр стебла.
     *
     * @return діаметр (мм)
     */
    public int getDiameterMm() { return diameterMm; }

    /**
     * Збільшує/зменшує висоту стебла.
     *
     * @param cm зміна висоти (см), може бути від’ємною
     */
    public void elongate(int cm) {
        heightCm = Math.max(0, heightCm + cm);
    }

    /**
     * Потовщує/тоншить стебло.
     *
     * @param mm зміна діаметра (мм), може бути від’ємною
     */
    public void thicken(int mm) {
        diameterMm = Math.max(1, diameterMm + mm);
    }

    /**
     * Перевіряє, чи стебло можна вважати достатньо міцним (спрощено).
     *
     * @return {@code true}, якщо стебло міцне
     */
    public boolean isSturdy() {
        return diameterMm >= 5 && heightCm <= 200;
    }
}
