package KI306.Kusmyna.Lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Параметризований контейнер для споруд.
 *
 * <p>Містить мінімум 4 методи опрацювання даних, включаючи розміщення та виймання.</p>
 *
 * @param <T> тип елементів контейнера (обмежений {@link Structure})
 */
public class StructureContainer<T extends Structure> {

    private final ArrayList<T> items = new ArrayList<>();

    /**
     * Створює порожній контейнер.
     */
    public StructureContainer() {
        // empty
    }

    /**
     * Додає елемент у контейнер (розміщення елемента).
     *
     * @param item елемент, який додаємо
     */
    public void add(T item) {
        items.add(item);
    }

    /**
     * Видаляє та повертає елемент за індексом (виймання елемента).
     *
     * @param index індекс елемента
     * @return видалений елемент
     * @throws IndexOutOfBoundsException якщо індекс некоректний
     */
    public T remove(int index) {
        return items.remove(index);
    }

    /**
     * Повертає елемент за індексом без видалення.
     *
     * @param index індекс елемента
     * @return елемент контейнера
     * @throws IndexOutOfBoundsException якщо індекс некоректний
     */
    public T get(int index) {
        return items.get(index);
    }

    /**
     * Повертає кількість елементів у контейнері.
     *
     * @return кількість елементів
     */
    public int size() {
        return items.size();
    }

    /**
     * Знаходить максимальний елемент у контейнері.
     * <p>Для непарних варіантів (у т.ч. варіант 9) реалізується пошук максимуму.</p>
     *
     * @return максимальний елемент або {@code null}, якщо контейнер порожній
     */
    public T findMax() {
        if (items.isEmpty()) return null;

        T max = items.get(0);
        for (int i = 1; i < items.size(); i++) {
            T cur = items.get(i);
            if (cur.compareTo(max) > 0) {
                max = cur;
            }
        }
        return max;
    }

    /**
     * Повертає незмінюваний список елементів контейнера.
     *
     * @return read-only список елементів
     */
    public List<T> asReadOnlyList() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Друкує всі елементи контейнера в консоль.
     */
    public void printAll() {
        if (items.isEmpty()) {
            System.out.println("Контейнер порожній.");
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            System.out.print("[" + i + "] ");
            items.get(i).printInfo();
        }
    }
}
