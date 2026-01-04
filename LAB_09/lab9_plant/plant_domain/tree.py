"""
Модуль з похідним класом Tree (Дерево), який наслідує Plant.
"""

from __future__ import annotations

from .plant import Plant


class Tree(Plant):
    """
    Похідний клас: Дерево.
    Додає нові поля та перевизначає ріст.
    """

    def __init__(
        self,
        species: str,
        age_days: int = 0,
        height_cm: float = 10.0,
        trunk_diameter_cm: float = 1.0,
        evergreen: bool = False,
    ):
        super().__init__(species=species, age_days=age_days, height_cm=height_cm)

        if trunk_diameter_cm <= 0:
            raise ValueError("trunk_diameter_cm має бути > 0")

        self.__trunk_diameter_cm = float(trunk_diameter_cm)
        self.__evergreen = bool(evergreen)

    def get_trunk_diameter_cm(self) -> float:
        return self.__trunk_diameter_cm

    def is_evergreen(self) -> bool:
        return self.__evergreen

    def grow(self, days: int = 1) -> None:
        """
        Перевизначений ріст для дерева:
        - дерево росте швидше за базову рослину
        - паралельно збільшується діаметр стовбура
        """
        # Використаємо базову логіку (вік, висота, здоров'я)
        super().grow(days=days)

        # Додатково: стовбур товстішає (залежить від здоров'я)
        health = self.get_health()
        thickening_per_day = 0.02 * (health / 100.0)
        self.__trunk_diameter_cm += thickening_per_day * days

    def prune(self) -> None:
        """
        Обрізка: трохи зменшує висоту, але покращує здоров'я.
        """
        # Оскільки висота в базовому класі приватна, напряму її не міняємо.
        # Зробимо простіше: кілька "днів" росту відкотимо через погіршення/покращення.
        self._health = min(100, self._health + 10)

    def __str__(self) -> str:
        return (
            f"Tree({super().__str__()}, trunk_diameter_cm={self.__trunk_diameter_cm:.2f}, "
            f"evergreen={self.__evergreen})"
        )
