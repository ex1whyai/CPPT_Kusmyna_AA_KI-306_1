import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Лабораторна робота №1 (варіант 9).
 *
 * <p>Програма формує зубчатий масив, який містить лише елементи заштрихованої області
 * квадратної матриці n×n згідно варіанту 9: верхньо-ліва трикутна область
 * відносно побічної діагоналі (діагональ з нижнього-лівого кута у верхній-правий),
 * включно з діагоналлю.</p>
 *
 * <p>Ввід: розмір n і символ-заповнювач (один символ) з клавіатури.
 * Якщо символ не введено або введено більше одного символу — коректне завершення.</p>
 *
 * <p>Вивід: сформований зубчатий масив виводиться на екран і у файл output.txt.</p>
 *
 * @author Artur Kusmyna
 */
public class Lab1KusmynaKI306 {

    /** Назва файлу для збереження результату. */
    private static final String OUTPUT_FILE = "output.txt";

    /**
     * Конструктор за замовчуванням.
     * Створений явно, щоб Javadoc не видавав попередження.
     */
    public Lab1KusmynaKI306() {
        // Нічого не робить
    }

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть розмір квадратної матриці n: ");
        if (!sc.hasNextInt()) {
            System.out.println("Помилка: n має бути цілим числом. Завершення.");
            return;
        }
        int n = sc.nextInt();
        sc.nextLine(); // забрати кінець рядка після числа

        if (n <= 0) {
            System.out.println("Помилка: n має бути > 0. Завершення.");
            return;
        }

        System.out.print("Введіть символ-заповнювач (ОДИН символ): ");
        String fillerStr = sc.nextLine();

        // Перевірка: не введено або введено кілька символів
        if (fillerStr == null || fillerStr.length() != 1) {
            System.out.println("Помилка: потрібно ввести рівно один символ. Завершення.");
            return;
        }

        char filler = fillerStr.charAt(0);

        // Формуємо зубчатий масив для заштрихованої області
        char[][] jagged = buildVariant9Jagged(n, filler);

        // Вивід на екран
        System.out.println("\nЗубчатий масив (лише заштрихована область):");
        String outputText = formatJagged(jagged);

        System.out.print(outputText);

        // Запис у файл
        try {
            writeToFile(OUTPUT_FILE, outputText);
            System.out.println("\nРезультат записано у файл: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.out.println("\nПомилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Формує зубчатий масив для варіанту 9.
     *
     * <p>Умови належності елемента (i, j) до заштрихованої області:
     * i + j <= n - 1 (область над побічною діагоналлю, включно з діагоналлю).</p>
     *
     * <p>Тоді в рядку i кількість заштрихованих елементів: (n - i).</p>
     *
     * @param n      розмір квадратної матриці n×n
     * @param filler символ-заповнювач
     * @return зубчатий масив char[][], що містить лише заштриховані елементи
     */
    private static char[][] buildVariant9Jagged(int n, char filler) {
        char[][] arr = new char[n][];

        for (int i = 0; i < n; i++) {
            // Для i-го рядка беремо всі j, що задовольняють i + j <= n - 1
            // тобто j = 0..(n-1-i) => кількість = n - i
            int len = n - i;
            arr[i] = new char[len];

            for (int k = 0; k < len; k++) {
                arr[i][k] = filler;
            }
        }

        return arr;
    }

    /**
     * Форматує зубчатий масив у текст для виводу.
     *
     * @param arr зубчатий масив
     * @return текстове представлення масиву (кожний рядок з нового рядка)
     */
    private static String formatJagged(char[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            sb.append("row ").append(i).append(": ");
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]);
                if (j + 1 < arr[i].length) sb.append(' ');
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Записує текст у файл (перезапис).
     *
     * @param fileName назва файлу
     * @param text     текст для запису
     * @throws IOException якщо сталася помилка вводу/виводу
     */
    private static void writeToFile(String fileName, String text) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName, false))) {
            pw.print(text);
        }
    }
}
