"""
Точка входу в програму (main) — окремий модуль, як вимагається в ЛР.
"""

from plant_domain import Plant, Tree


def main() -> None:
    # Створюємо базову рослину
    p = Plant("Фікус", age_days=30, height_cm=25.0)
    print("== Базовий об'єкт ==")
    print(p)

    p.water(200)
    p.grow(5)
    print("Після догляду:", p)
    print("Plant.number =", Plant.number)
    print("Average height (cm) =", Plant.get_average_height_cm())

    # Створюємо похідний клас (дерево)
    t = Tree("Дуб", age_days=365, height_cm=120.0, trunk_diameter_cm=8.0, evergreen=False)
    print("\n== Похідний об'єкт ==")
    print(t)

    t.water(300)
    t.grow(10)
    t.prune()
    print("Після догляду:", t)


if __name__ == "__main__":
    main()
