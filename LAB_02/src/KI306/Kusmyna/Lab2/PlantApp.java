package KI306.Kusmyna.Lab2;

import static java.lang.System.out;

/**
 * Клас-драйвер для демонстрації роботи класу {@link Plant}.
 */
public class PlantApp {

    /**
     * Конструктор за замовчуванням.
     * Доданий для того, щоб генератор Javadoc не видавав попередження
     * про відсутність коментаря для конструктора за замовчуванням.
     */
    public PlantApp() {
        // порожній конструктор (логіка не змінюється)
    }

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        // try-with-resources гарантує закриття файлу журналу через close()
        try (Plant plant = new Plant("Sunflower")) {
            out.println(plant.status());

            plant.setLocation("Balcony");
            plant.water(300);
            plant.exposeToLight(6);
            plant.fertilize(20);

            for (int i = 0; i < 5; i++) {
                plant.growOneDay();
            }

            plant.pruneLeaves(2);
            plant.bloom();
            plant.pollinate();

            int fruits = plant.harvest();
            out.println("Зібрано: " + fruits);

            out.println(plant.status());
            out.println("Здоров'я: " + plant.getHealth());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
