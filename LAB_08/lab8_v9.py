# lab8_v9.py
# Лабораторна №8: Файли та виключення у Python
# Варіант 9: y = tg(x) / (3x)

from __future__ import annotations

import math
import struct
from pathlib import Path


def calculate_y(x: float) -> float:
    """
    Обчислює значення y = tan(x) / (3*x).

    ВАЖЛИВО: x задається у радіанах.
    Кидає ZeroDivisionError, якщо x == 0 (або дуже близьке до 0).
    """
    eps = 1e-12
    if abs(x) < eps:
        # 3*x = 0 -> ділення на нуль
        raise ZeroDivisionError("x не може дорівнювати 0 (ділення на 3*x).")

    return math.tan(x) / (3.0 * x)


def write_result_txt(filename: str | Path, value: float) -> None:
    """Записує результат у текстовий файл."""
    path = Path(filename)
    with path.open("w", encoding="utf-8") as f:
        f.write(f"{value}\n")


def read_result_txt(filename: str | Path) -> float:
    """Читає результат з текстового файлу."""
    path = Path(filename)
    with path.open("r", encoding="utf-8") as f:
        text = f.read().strip()
    return float(text)


def write_result_bin(filename: str | Path, value: float) -> None:
    """
    Записує результат у двійковий файл.
    Формат: 'd' (double, 8 байт).
    """
    path = Path(filename)
    with path.open("wb") as f:
        f.write(struct.pack("d", value))


def read_result_bin(filename: str | Path) -> float:
    """
    Читає результат з двійкового файлу.
    Формат: 'd' (double, 8 байт).
    """
    path = Path(filename)
    with path.open("rb") as f:
        data = f.read(struct.calcsize("d"))
        if len(data) != struct.calcsize("d"):
            raise ValueError("Некоректний двійковий файл: недостатньо байтів для double.")
        return struct.unpack("d", data)[0]


def main() -> None:
    """Тестовий запуск модуля з консолі."""
    try:
        x_str = input("Введіть x (у радіанах): ").strip()
        x = float(x_str)

        y = calculate_y(x)
        print(f"y = tg({x}) / (3*{x}) = {y}")

        # Файли з результатом
        txt_name = "result_v9.txt"
        bin_name = "result_v9.bin"

        write_result_txt(txt_name, y)
        write_result_bin(bin_name, y)

        # Перевірка читання назад
        y_txt = read_result_txt(txt_name)
        y_bin = read_result_bin(bin_name)

        print(f"Зчитано з TXT: {y_txt}")
        print(f"Зчитано з BIN: {y_bin}")

    except ValueError:
        print("Помилка: введіть коректне число (float).")
    except ZeroDivisionError as e:
        print(f"Помилка обчислення: {e}")
    except FileNotFoundError as e:
        print(f"Файл не знайдено: {e}")
    except OSError as e:
        print(f"Помилка ОС під час роботи з файлами: {e}")
    except Exception as e:
        print(f"Непередбачена помилка: {e}")


if __name__ == "__main__":
    main()
