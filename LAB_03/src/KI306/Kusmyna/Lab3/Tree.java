package KI306.Kusmyna.Lab3;

import java.io.FileNotFoundException;

/**
 * Клас {@code Tree} — підклас для предметної області "Дерево" (варіант 9).
 * Розширює {@link Plant} та реалізує інтерфейс {@link SeasonalCare}.
 *
 * @author Kusmyna
 * @version 1.0
 */
public class Tree extends Plant implements SeasonalCare {

    /** Вік дерева (роки). */
    private int ageYears;

    /** Діаметр стовбура (см). */
    private double trunkDiameterCm;

    /** Ознака вічнозеленого дерева. */
    private boolean evergreen;

    /**
     * Конструктор за замовчуванням.
     *
     * @throws FileNotFoundException якщо не вдалось створити файл журналу
     */
    public Tree() throws FileNotFoundException {
        this("Tree", false);
    }

    /**
     * Конструктор.
     *
     * @param species назва дерева
     * @param evergreen чи вічнозелене
     * @throws FileNotFoundException якщо не вдалось створити файл журналу
     */
    public Tree(String species, boolean evergreen) throws FileNotFoundException {
        super(species);
        this.evergreen = evergreen;
        this.ageYears = 1;
        this.trunkDiameterCm = 2.0;
        log("Tree створено: evergreen=" + evergreen);
    }

    /**
     * Збільшує вік дерева на 1 рік і трохи збільшує діаметр стовбура.
     */
    public void growOneYear() {
        ageYears++;
        trunkDiameterCm += 0.6;
        log("growOneYear() -> ageYears=" + ageYears + ", trunkDiameterCm=" + trunkDiameterCm);
    }

    /**
     * Обрізання гілок (умовно) — використовує наявний механізм обрізки листя.
     */
    public void pruneBranches() {
        pruneLeaves(1);
        log("pruneBranches()");
    }

    /**
     * Сезонний догляд (вимога інтерфейсу).
     *
     * @param season пора року
     */
    @Override
    public void careForSeason(Season season) {
        if (season == null) return;

        switch (season) {
            case SPRING -> {
                fertilize(20);
                water(300);
                bloom();
                pollinate();
                log("careForSeason(SPRING)");
            }
            case SUMMER -> {
                exposeToLight(8);
                water(200);
                growOneDay();
                log("careForSeason(SUMMER)");
            }
            case AUTUMN -> {
                if (!evergreen) pruneLeaves(2);
                pruneBranches();
                log("careForSeason(AUTUMN)");
            }
            case WINTER -> {
                // Взимку: мінімальна активність (спрощено)
                log("careForSeason(WINTER)");
            }
        }
    }

    /**
     * Повертає стан дерева (розширений стан рослини).
     *
     * @return рядок зі станом
     */
    public String treeStatus() {
        String s = status() + " Tree{" +
                "ageYears=" + ageYears +
                ", trunkDiameterCm=" + trunkDiameterCm +
                ", evergreen=" + evergreen +
                '}';
        log("treeStatus()");
        return s;
    }
}
