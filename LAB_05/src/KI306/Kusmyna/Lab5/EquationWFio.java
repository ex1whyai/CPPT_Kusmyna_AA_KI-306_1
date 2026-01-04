package KI306.Kusmyna.Lab5;

import KI306.Kusmyna.Lab4.CalcException;
import KI306.Kusmyna.Lab4.EquationCalculator;

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class {@code EquationWFio} обчислює вираз варіанту 9 (Lab4) і
 * вміє зберігати/відновлювати (x, y) у текстовому та двійковому файлах.
 *
 * <p>Варіант 9: y = tg(x) / (3x)</p>
 *
 * @author Кусмина А.А.
 * @version 1.0
 */
public class EquationWFio {

    private double xDeg;
    private double y;

    /**
     * Конструктор за замовчуванням.
     */
    public EquationWFio() {
    }

    /**
     * Обчислює y для заданого x (у градусах) через клас Lab4.
     *
     * @param xDeg x у градусах
     * @throws CalcException якщо обчислення неможливе (x=0, tg не існує тощо)
     */
    public void calculate(double xDeg) throws CalcException {
        this.xDeg = xDeg;
        EquationCalculator calc = new EquationCalculator();
        this.y = calc.calculate(xDeg);
    }

    /**
     * Повертає останнє збережене значення x у градусах.
     *
     * @return останнє значення x (градуси)
     */
    public double getXDeg() {
        return xDeg;
    }

    /**
     * Повертає останнє обчислене (або прочитане з файлу) значення y.
     *
     * @return останнє значення y
     */
    public double getY() {
        return y;
    }


    /**
     * Записує (x, y) у текстовий файл.
     *
     * @param fName назва файлу
     * @throws FileNotFoundException якщо файл неможливо створити/відкрити
     */
    public void writeResTxt(String fName) throws FileNotFoundException {
        // щоб десяткова частина писалась через крапку
        Locale.setDefault(Locale.US);

        try (PrintWriter out = new PrintWriter(fName)) {
            // формат: x y
            out.printf(Locale.US, "%.10f %.10f%n", xDeg, y);
        }
    }

    /**
     * Читає (x, y) з текстового файлу.
     *
     * @param fName назва файлу
     * @throws FileNotFoundException якщо файл не знайдено
     */
    public void readResTxt(String fName) throws FileNotFoundException {
        Locale.setDefault(Locale.US);

        try (Scanner sc = new Scanner(new File(fName))) {
            this.xDeg = sc.nextDouble();
            this.y = sc.nextDouble();
        }
    }

    /**
     * Записує (x, y) у двійковий файл (binary).
     *
     * @param fName назва файлу
     * @throws IOException якщо сталася помилка вводу/виводу
     */
    public void writeResBin(String fName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fName))) {
            out.writeDouble(xDeg);
            out.writeDouble(y);
        }
    }

    /**
     * Читає (x, y) з двійкового файлу (binary).
     *
     * @param fName назва файлу
     * @throws IOException якщо сталася помилка вводу/виводу
     */
    public void readResBin(String fName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fName))) {
            this.xDeg = in.readDouble();
            this.y = in.readDouble();
        }
    }
}
