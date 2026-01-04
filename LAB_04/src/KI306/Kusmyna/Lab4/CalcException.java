package KI306.Kusmyna.Lab4;

/**
 * Class {@code CalcException} уточнює помилки обчислення виразу.
 * Використовується для повідомлення користувачу причини помилки.
 *
 * @author Artur Kusmyna
 * @version 1.0
 */
public class CalcException extends ArithmeticException {

    /** Конструктор за замовчуванням. */
    public CalcException() {
        super();
    }

    /**
     * Конструктор з повідомленням.
     *
     * @param message текст причини помилки
     */
    public CalcException(String message) {
        super(message);
    }

    /**
     * Конструктор з повідомленням і причиною (cause).
     *
     * @param message текст причини
     * @param cause   першопричина помилки
     */
    public CalcException(String message, Throwable cause) {
        super(message);
        if (cause != null) {
            initCause(cause);
        }
    }
}
