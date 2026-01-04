package KI306.Kusmyna.Lab3;

/**
 * Клас-драйвер для демонстрації роботи класу {@link Tree}.
 */
public class TreeApp {

    /**
     * Конструктор за замовчуванням (щоб Javadoc не видавав warning про default constructor).
     */
    public TreeApp() { }

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {
        try (Tree tree = new Tree("Apple tree", false)) {
            System.out.println(tree.treeStatus());

            tree.careForSeason(Season.SPRING);
            tree.growOneYear();

            int fruits = tree.harvest();
            System.out.println("Зібрано плодів: " + fruits);

            System.out.println(tree.treeStatus());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
