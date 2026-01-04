"""
Модуль з базовим класом Plant (Рослина).
"""

from __future__ import annotations


class Plant:
    """
    Базовий клас предметної області: Рослина.

    Приватні поля позначені через __ (name mangling),
    "захищені" — через _ (умовно protected, як у методичці).
    """

    # Статичні змінні-члени класу (для демонстрації)
    number = 0
    _average_height_cm = 0.0

    def __init__(self, species: str, age_days: int = 0, height_cm: float = 1.0):
        """
        :param species: вид/назва рослини (наприклад, "Фікус")
        :param age_days: вік у днях
        :param height_cm: висота у сантиметрах
        """
        if not species.strip():
            raise ValueError("species не може бути порожнім")
        if age_days < 0:
            raise ValueError("age_days має бути >= 0")
        if height_cm <= 0:
            raise ValueError("height_cm має бути > 0")

        # Приватні поля
        self.__species = species.strip()
        self.__age_days = age_days
        self.__height_cm = float(height_cm)

        # Умовно "захищене" поле (доступне для нащадків)
        self._health = 100  # 0..100

        # Оновлення статичних значень
        Plant.number += 1
        Plant._average_height_cm = (Plant._average_height_cm + self.__height_cm) / 2.0

    # --------- Гетери (публічний доступ до приватних полів) ---------

    def get_species(self) -> str:
        return self.__species

    def get_age_days(self) -> int:
        return self.__age_days

    def get_height_cm(self) -> float:
        return self.__height_cm

    def get_health(self) -> int:
        return self._health

    @staticmethod
    def get_average_height_cm() -> float:
        """Статичний метод: середня висота (умовна) по створених рослинах."""
        return Plant._average_height_cm

    # --------- Поведінка рослини ---------

    def water(self, amount_ml: int) -> None:
        """
        Полив рослини. Трошки підвищує "здоров'я" (для демонстрації).
        """
        if amount_ml <= 0:
            raise ValueError("amount_ml має бути > 0")

        # Проста модель: чим більше води (в межах), тим краще
        if amount_ml < 100:
            self._health = min(100, self._health + 1)
        elif amount_ml <= 500:
            self._health = min(100, self._health + 3)
        else:
            # перелив
            self._health = max(0, self._health - 5)

    def grow(self, days: int = 1) -> None:
        """
        Ріст рослини за N днів.
        Базова реалізація: +0.2 см/день, модифікується здоров'ям.
        Похідний клас може перевизначити.
        """
        if days <= 0:
            raise ValueError("days має бути > 0")

        self.__age_days += days

        # Модель росту залежить від здоров'я
        growth_per_day = 0.2 * (self._health / 100.0)
        self.__height_cm += growth_per_day * days

        # Легка деградація без догляду
        self._health = max(0, self._health - 1)

    def __str__(self) -> str:
        return (
            f"Plant(species='{self.__species}', age_days={self.__age_days}, "
            f"height_cm={self.__height_cm:.2f}, health={self._health})"
        )
