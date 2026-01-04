package KI306.Kusmyna.Lab4;

/**
 * Class {@code EquationCalculator} реалізує обчислення виразу варіанту 9:
 * <pre>y = tg(x) / (3x)</pre>
 * де x задається у градусах.
 *
 * @author Artur Kusmyna
 * @version 1.0
 */
public class EquationCalculator {

    /**
     * Конструктор за замовчуванням.
     */
    public EquationCalculator() {
    }

    /** Допуск для перевірок ділення на нуль та недопустимих значень. */
    private static final double EPS = 1e-12;

    /**
     * Обчислює значення y = tg(x)/(3x).
     *
     * @param xDeg кут x у градусах
     * @return значення y
     * @throws CalcException якщо x = 0 або tg(x) не визначений, або результат некоректний
     */
    public double calculate(double xDeg) throws CalcException {
        final double rad = Math.toRadians(xDeg);

        try {
            // 3x = 0
            if (Math.abs(xDeg) < EPS) {
                throw new ArithmeticException("x is zero");
            }

            // tg(x) не існує при cos(x)=0 => x = 90 + 180*k градусів
            double cos = Math.cos(rad);
            if (Math.abs(cos) < EPS) {
                throw new ArithmeticException("tangent undefined (cos=0)");
            }

            double y = Math.tan(rad) / (3.0 * xDeg);

            if (Double.isNaN(y) || Double.isInfinite(y)) {
                throw new ArithmeticException("result is NaN/Infinity");
            }

            return y;
        } catch (ArithmeticException ex) {
            String msg;
            if (Math.abs(xDeg) < EPS) {
                msg = "Exception reason: denominator 3*x is zero (x = 0)";
            } else if (Math.abs(Math.cos(rad)) < EPS) {
                msg = "Exception reason: tg(x) is undefined for x = 90 + 180*k degrees";
            } else {
                msg = "Unknown reason of the exception during expression calculation";
            }
            throw new CalcException(msg, ex);
        }
    }
}
