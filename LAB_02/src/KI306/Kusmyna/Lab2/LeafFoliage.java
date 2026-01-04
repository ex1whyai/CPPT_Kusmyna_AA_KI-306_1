package KI306.Kusmyna.Lab2;

/**
 * Клас {@code LeafFoliage} описує листя рослини.
 */
public class LeafFoliage {
    /** Кількість листків. */
    private int leafCount;
    /** Середня площа одного листка (см^2). */
    private double avgLeafAreaCm2;

    /**
     * Конструктор за замовчуванням.
     */
    public LeafFoliage() {
        this(6, 8.0);
    }

    /**
     * Конструктор.
     *
     * @param leafCount кількість листків
     * @param avgLeafAreaCm2 середня площа листка (см^2)
     */
    public LeafFoliage(int leafCount, double avgLeafAreaCm2) {
        this.leafCount = Math.max(0, leafCount);
        this.avgLeafAreaCm2 = Math.max(0.0, avgLeafAreaCm2);
    }

    /**
     * Повертає кількість листків.
     *
     * @return кількість листків
     */
    public int getLeafCount() { return leafCount; }

    /**
     * Повертає середню площу листка.
     *
     * @return середня площа (см^2)
     */
    public double getAvgLeafAreaCm2() { return avgLeafAreaCm2; }

    /**
     * Нарощує нові листки.
     *
     * @param count кількість листків для додавання
     */
    public void growLeaves(int count) {
        leafCount = Math.max(0, leafCount + Math.max(0, count));
    }

    /**
     * Скидає частину листків.
     *
     * @param count кількість листків для видалення
     */
    public void dropLeaves(int count) {
        leafCount = Math.max(0, leafCount - Math.max(0, count));
    }

    /**
     * Змінює середню площу листка.
     *
     * @param delta зміна площі (см^2), може бути від’ємною
     */
    public void increaseArea(double delta) {
        avgLeafAreaCm2 = Math.max(0.0, avgLeafAreaCm2 + delta);
    }

    /**
     * Імітація фотосинтезу (спрощена модель).
     *
     * @param lightHours кількість годин освітлення
     * @return умовні одиниці виробленої енергії
     */
    public int photosynthesize(int lightHours) {
        int h = Math.max(0, lightHours);
        double surface = leafCount * avgLeafAreaCm2;
        return (int)Math.round(surface * h / 50.0);
    }
}
