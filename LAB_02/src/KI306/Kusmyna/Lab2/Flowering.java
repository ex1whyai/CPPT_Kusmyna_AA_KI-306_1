package KI306.Kusmyna.Lab2;

/**
 * Клас {@code Flowering} описує стан цвітіння рослини.
 */
public class Flowering {
    /** Кількість квітів. */
    private int flowerCount;
    /** Чи відбувається цвітіння. */
    private boolean blooming;
    /** Чи відбулося запилення. */
    private boolean pollinated;

    /**
     * Конструктор за замовчуванням.
     */
    public Flowering() {
        this(0, false, false);
    }

    /**
     * Конструктор.
     *
     * @param flowerCount кількість квітів
     * @param blooming стан цвітіння
     * @param pollinated стан запилення
     */
    public Flowering(int flowerCount, boolean blooming, boolean pollinated) {
        this.flowerCount = Math.max(0, flowerCount);
        this.blooming = blooming;
        this.pollinated = pollinated;
    }

    /**
     * Повертає кількість квітів.
     *
     * @return кількість квітів
     */
    public int getFlowerCount() { return flowerCount; }

    /**
     * Повертає стан цвітіння.
     *
     * @return {@code true}, якщо цвіте
     */
    public boolean isBlooming() { return blooming; }

    /**
     * Повертає стан запилення.
     *
     * @return {@code true}, якщо запилено
     */
    public boolean isPollinated() { return pollinated; }

    /**
     * Починає цвітіння та додає задану кількість квітів.
     *
     * @param flowers скільки квітів додати
     */
    public void startBloom(int flowers) {
        blooming = true;
        flowerCount = Math.max(0, flowerCount + Math.max(0, flowers));
        pollinated = false;
    }

    /**
     * Виконує запилення (лише якщо є квіти і рослина цвіте).
     */
    public void pollinate() {
        if (blooming && flowerCount > 0) pollinated = true;
    }

    /**
     * Завершує цвітіння.
     */
    public void endBloom() {
        blooming = false;
    }
}
