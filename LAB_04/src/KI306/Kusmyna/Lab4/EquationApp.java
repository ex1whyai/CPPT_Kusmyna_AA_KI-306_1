package KI306.Kusmyna.Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Class {@code EquationApp} — програма-драйвер для {@link EquationCalculator}.
 * Зчитує ім'я файлу та x, обчислює y і записує результат у файл.
 *
 * @author Artur Kusmyna
 * @version 1.0
 */
public class EquationApp {

    /**
     * Конструктор за замовчуванням.
     */
    public EquationApp() {
    }

    /**
     * Точка входу.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        // щоб крапка була роздільником дробової частини у Scanner
        Locale.setDefault(Locale.US);

        try {
            Scanner in = new Scanner(System.in);

            out.print("Enter output file name: ");
            String fName = in.nextLine();

            PrintWriter fout = new PrintWriter(new File(fName));

            try {
                try {
                    out.print("Enter X (degrees): ");
                    double x = in.nextDouble();

                    EquationCalculator calc = new EquationCalculator();
                    double y = calc.calculate(x);

                    fout.println("Variant 9: y = tg(x)/(3x)");
                    fout.println("x (deg) = " + x);
                    fout.println("y       = " + y);

                    out.println("Done. Result saved to file: " + fName);
                } finally {
                    // виконається за будь-яких обставин (як у методичці) :contentReference[oaicite:2]{index=2}
                    fout.flush();
                    fout.close();
                }
            } catch (CalcException ex) {
                out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            out.println("Exception reason: Perhaps wrong file path / cannot create file");
        }
    }
}
