package KI306.Kusmyna.Lab6;

/**
 * Драйвер (тестова програма) для демонстрації роботи контейнера.
 */
public final class StructureDriver {

    /**
     * Забороняє створення екземплярів драйвера.
     */
    private StructureDriver() {
        // no instances
    }

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        // Контейнер для споруд, в який додаємо об'єкти різних класів (Building, Bridge)
        StructureContainer<Structure> container = new StructureContainer<>();

        container.add(new Building("Житловий будинок", 2008, 9, 520.0));
        container.add(new Bridge("Міст через ріку", 1995, 160.0, 12.0));
        container.add(new Building("Торговий центр", 2018, 3, 2500.0));

        System.out.println("Усі елементи контейнера:");
        container.printAll();

        Structure max = container.findMax();
        System.out.println();
        if (max != null) {
            System.out.println("Максимальний елемент (за метрикою):");
            max.printInfo();
        }

        System.out.println("\nВидаляємо елемент з індексом 1 (remove):");
        Structure removed = container.remove(1);
        removed.printInfo();

        System.out.println("\nСтан контейнера після видалення:");
        container.printAll();
    }
}
