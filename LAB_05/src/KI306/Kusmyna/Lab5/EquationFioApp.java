package KI306.Kusmyna.Lab5;

import KI306.Kusmyna.Lab4.CalcException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Driver program for {@link EquationWFio}.
 * Тестує: обчислення -> запис TXT/BIN -> читання BIN/TXT.
 *
 * @author Artur Kusmyna
 * @version 1.0
 */
public class EquationFioApp {

    /**
     * Конструктор за замовчуванням.
     */
    public EquationFioApp() {
    }

    /**
     * Точка входу програми. Тестує обчислення та читання/запис у TXT/BIN.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        final String txtName = "textRes.txt";
        final String binName = "binRes.bin";

        try (Scanner sc = new Scanner(System.in)) {

            out.print("Enter x (degrees): ");
            double x = sc.nextDouble();

            EquationWFio obj = new EquationWFio();
            obj.calculate(x);

            out.println("Calculated y = " + obj.getY());

            // запис
            obj.writeResTxt(txtName);
            obj.writeResBin(binName);

            // читання/перевірка
            EquationWFio fromBin = new EquationWFio();
            fromBin.readResBin(binName);
            out.println("[BIN] x = " + fromBin.getXDeg() + ", y = " + fromBin.getY());

            EquationWFio fromTxt = new EquationWFio();
            fromTxt.readResTxt(txtName);
            out.println("[TXT] x = " + fromTxt.getXDeg() + ", y = " + fromTxt.getY());

            // простенька перевірка
            double eps = 1e-9;
            boolean okBin = Math.abs(obj.getY() - fromBin.getY()) < eps;
            boolean okTxt = Math.abs(obj.getY() - fromTxt.getY()) < eps;
            out.println("Check BIN: " + (okBin ? "OK" : "FAIL"));
            out.println("Check TXT: " + (okTxt ? "OK" : "FAIL"));

        } catch (InputMismatchException e) {
            out.println("Input error: x must be a number.");
        } catch (CalcException e) {
            out.println("Calculation error: " + e.getMessage());
        } catch (FileNotFoundException e) {
            out.println("File error: cannot create/read file. " + e.getMessage());
        } catch (IOException e) {
            out.println("I/O error: " + e.getMessage());
        }
    }
}
