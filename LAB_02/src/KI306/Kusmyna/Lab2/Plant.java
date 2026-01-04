package KI306.Kusmyna.Lab2;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Клас {@code Plant} реалізує предметну область "Рослина".
 * Містить складові: коренева система, стебло, листя, цвітіння.
 * Усі методи ведуть журнал роботи (лог) у файл.
 *
 * <p>Клас реалізує {@link AutoCloseable}, тому файл журналу коректно закривається
 * через {@link #close()} (не використовується {@code finalize()}).</p>
 *
 * @author Artur Kusmyna
 */
public class Plant implements AutoCloseable {

    /** Коренева система. */
    private RootSystem roots;
    /** Стебло. */
    private Stem stem;
    /** Листя. */
    private LeafFoliage foliage;
    /** Стан цвітіння. */
    private Flowering flowering;

    /** Назва виду/рослини. */
    private String species;
    /** Місце розташування. */
    private String location;
    /** Етап розвитку. */
    private PlantStage stage;

    /** Здоров'я 0..100. */
    private int health;
    /** Енергія 0..100. */
    private int energy;

    /** Потік для запису журналу. */
    private PrintWriter log;

    /**
     * Конструктор за замовчуванням.
     *
     * @throws FileNotFoundException якщо не вдалось створити файл журналу
     */
    public Plant() throws FileNotFoundException {
        this("Unknown plant");
    }

    /**
     * Конструктор з назвою виду.
     *
     * @param species назва виду/рослини
     * @throws FileNotFoundException якщо не вдалось створити файл журналу
     */
    public Plant(String species) throws FileNotFoundException {
        this(species, new RootSystem(), new Stem(), new LeafFoliage(), new Flowering(), "PlantLog.txt");
    }

    /**
     * Повний конструктор.
     *
     * @param species назва виду/рослини
     * @param roots коренева система (якщо {@code null} — створиться стандартна)
     * @param stem стебло (якщо {@code null} — створиться стандартне)
     * @param foliage листя (якщо {@code null} — створиться стандартне)
     * @param flowering стан цвітіння (якщо {@code null} — створиться стандартний)
     * @param logFileName назва файлу журналу
     * @throws FileNotFoundException якщо не вдалось створити файл журналу
     */
    public Plant(String species, RootSystem roots, Stem stem, LeafFoliage foliage, Flowering flowering, String logFileName)
            throws FileNotFoundException {

        this.species = (species == null || species.isBlank()) ? "Unknown plant" : species;
        this.roots = (roots == null) ? new RootSystem() : roots;
        this.stem = (stem == null) ? new Stem() : stem;
        this.foliage = (foliage == null) ? new LeafFoliage() : foliage;
        this.flowering = (flowering == null) ? new Flowering() : flowering;

        this.location = "Window";
        this.stage = PlantStage.SPROUT;
        this.health = 80;
        this.energy = 50;

        this.log = new PrintWriter(logFileName);
        log("Рослину створено: " + this.species + ", етап=" + stage);
    }

    /**
     * Записує повідомлення у файл журналу з часом.
     *
     * @param message текст повідомлення
     */
    private void log(String message) {
        if (log == null) return;
        log.println(LocalDateTime.now() + " | " + message);
        log.flush();
    }

    /**
     * Повертає назву виду/рослини.
     *
     * @return назва виду
     */
    public String getSpecies() {
        log("getSpecies()");
        return species;
    }

    /**
     * Встановлює місце розташування рослини.
     *
     * @param location нове місце (якщо порожнє/{@code null} — не змінює)
     */
    public void setLocation(String location) {
        this.location = (location == null || location.isBlank()) ? this.location : location;
        log("setLocation(" + this.location + ")");
    }

    /**
     * Повертає поточний етап розвитку.
     *
     * @return етап розвитку
     */
    public PlantStage getStage() {
        log("getStage()");
        return stage;
    }

    /**
     * Поливає рослину: корені поглинають воду, частково зростають енергія та здоров'я.
     *
     * @param ml кількість води в мл
     */
    public void water(int ml) {
        int amount = Math.max(0, ml);
        roots.absorbWater(amount);
        energy = clamp(energy + amount / 50, 0, 100);
        health = clamp(health + amount / 200, 0, 100);
        log("water(" + amount + "ml) -> зволоженість=" + roots.getHydration() + ", енергія=" + energy + ", здоров'я=" + health);
    }

    /**
     * Підживлює рослину: підвищує енергію та здоров'я (спрощено).
     *
     * @param grams кількість добрива в грамах
     */
    public void fertilize(int grams) {
        int g = Math.max(0, grams);
        energy = clamp(energy + g / 5, 0, 100);
        health = clamp(health + g / 10, 0, 100);
        log("fertilize(" + g + "g) -> енергія=" + energy + ", здоров'я=" + health);
    }

    /**
     * Дає освітлення: листя виробляє енергію (фотосинтез).
     *
     * @param hours години освітлення
     */
    public void exposeToLight(int hours) {
        int h = Math.max(0, hours);
        int gained = foliage.photosynthesize(h);
        energy = clamp(energy + gained / 10, 0, 100);
        log("exposeToLight(" + h + "h) -> енергіяФотосинтезу=" + gained + ", енергія=" + energy);
    }

    /**
     * Моделює один день росту: витрата ресурсів + можливий ріст залежно від стану.
     */
    public void growOneDay() {
        roots.consumeWater(5);
        energy = clamp(energy - 5, 0, 100);

        if (energy >= 30 && health >= 50) {
            stem.elongate(1);
            roots.growDeeper(1);
            foliage.growLeaves(1);
            foliage.increaseArea(0.2);
            log("growOneDay() -> ріст (стебло+1см, корені+1см, листя+1)");
        } else {
            health = clamp(health - 2, 0, 100);
            log("growOneDay() -> слабкий ріст, здоров'я=" + health);
        }

        updateStageAutomatically();
    }

    /**
     * Обрізання листя (умовно покращує стан).
     *
     * @param count кількість листків для обрізання
     */
    public void pruneLeaves(int count) {
        int c = Math.max(0, count);
        foliage.dropLeaves(c);
        health = clamp(health + 1, 0, 100);
        log("pruneLeaves(" + c + ") -> листків=" + foliage.getLeafCount() + ", здоров'я=" + health);
    }

    /**
     * Запускає цвітіння.
     * Якщо етап був раніший за {@link PlantStage#FLOWERING}, встановлює його на {@code FLOWERING}.
     */
    public void bloom() {
        if (stage.ordinal() < PlantStage.FLOWERING.ordinal()) {
            stage = PlantStage.FLOWERING;
        }
        flowering.startBloom(3);
        energy = clamp(energy - 10, 0, 100);
        log("bloom() -> квітів=" + flowering.getFlowerCount() + ", етап=" + stage);
    }

    /**
     * Запилює квіти. Якщо запилення успішне — етап переходить у {@link PlantStage#FRUITING}.
     */
    public void pollinate() {
        flowering.pollinate();
        log("pollinate() -> запилено=" + flowering.isPollinated());
        if (flowering.isPollinated()) {
            stage = PlantStage.FRUITING;
            log("Етап змінено на FRUITING");
        }
    }

    /**
     * Збирає урожай, якщо рослина у стадії плодоношення і квіти запилені.
     *
     * @return кількість зібраних плодів (0, якщо нема що збирати)
     */
    public int harvest() {
        int harvested = 0;
        if (stage == PlantStage.FRUITING && flowering.isPollinated()) {
            harvested = Math.max(1, flowering.getFlowerCount());
            flowering.endBloom();
            health = clamp(health - 5, 0, 100);
            energy = clamp(energy - 5, 0, 100);
            log("harvest() -> зібрано=" + harvested);
        } else {
            log("harvest() -> нічого збирати");
        }
        return harvested;
    }

    /**
     * Повертає здоров'я рослини.
     *
     * @return здоров'я 0..100
     */
    public int getHealth() {
        log("getHealth()");
        return health;
    }

    /**
     * Формує рядок зі станом рослини (для демонстрації).
     *
     * @return рядок зі станом
     */
    public String status() {
        String s = "Plant{" +
                "species='" + species + '\'' +
                ", location='" + location + '\'' +
                ", stage=" + stage +
                ", health=" + health +
                ", energy=" + energy +
                ", rootHydration=" + roots.getHydration() +
                ", stem=" + stem.getHeightCm() + "cm/" + stem.getDiameterMm() + "mm" +
                ", leaves=" + foliage.getLeafCount() +
                ", flowers=" + flowering.getFlowerCount() +
                '}';
        log("status() -> " + s);
        return s;
    }

    /**
     * Ручна зміна етапу розвитку.
     *
     * @param stage новий етап (якщо {@code null} — не змінює)
     */
    public void setStage(PlantStage stage) {
        if (stage != null) {
            this.stage = stage;
            log("setStage(" + stage + ")");
        }
    }

    /**
     * Коректно завершує роботу з файлом журналу.
     * Викликається автоматично у try-with-resources або вручну.
     */
    @Override
    public void close() {
        if (log != null) {
            log("close() -> закриття файлу журналу");
            log.close();
            log = null;
        }
    }

    /**
     * Автоматично оновлює етап розвитку залежно від параметрів росту/стану.
     */
    private void updateStageAutomatically() {
        if (stage == PlantStage.SPROUT && stem.getHeightCm() >= 15) {
            stage = PlantStage.VEGETATIVE;
            log("Авто-етап -> VEGETATIVE");
        }
        if (stage == PlantStage.VEGETATIVE && foliage.getLeafCount() >= 10) {
            stage = PlantStage.FLOWERING;
            log("Авто-етап -> FLOWERING");
        }
        if (health < 20) {
            stage = PlantStage.DORMANT;
            log("Авто-етап -> DORMANT (низьке здоров'я)");
        }
    }

    /**
     * Обмежує значення у діапазоні.
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
