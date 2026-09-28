---
# Material Theming with Jetpack Compose
---
![img.png](Material%20Theming%20with%20Jetpack%20Compose/img.png)

![img_1.png](Material%20Theming%20with%20Jetpack%20Compose/img_1.png)

![img_2.png](Material%20Theming%20with%20Jetpack%20Compose/img_2.png)

![img_3.png](Material%20Theming%20with%20Jetpack%20Compose/img_3.png)

![img_4.png](Material%20Theming%20with%20Jetpack%20Compose/img_4.png)

---
# Simple animation with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Material Theming with Jetpack Compose
---
---
# Project: Create a 30 Days App
---

## Описание проекта

В рамках капстоун-проекта юнита («Project: Create a 30 Days App») приложение-визитку
«ветклиники» (Woof) было полностью заменено новым приложением **«30 дней рецептов»** —
витриной из 30 рецептов, по одному на каждый день месяца, с указанием категории блюда.

**Данные (30 дней):**

| День | Рецепт | Категория |
|---|---|---|
| 1 | Паста Карбонара | Паста |
| 2 | Курица терияки с рисом | Основное блюдо |
| 3 | Борщ | Суп |
| 4 | Пицца Маргарита | Выпечка |
| 5 | Цезарь с курицей | Салат |
| 6 | Блины с творогом | Завтрак |
| 7 | Плов с курицей | Основное блюдо |
| 8 | Крем-суп из шампиньонов | Суп |
| 9 | Лазанья | Паста |
| 10 | Шакшука | Завтрак |
| 11 | Котлеты с картофельным пюре | Основное блюдо |
| 12 | Том-ям | Суп |
| 13 | Греческий салат | Салат |
| 14 | Чизкейк | Десерт |
| 15 | Паста с лососем | Паста |
| 16 | Овощное рагу | Основное блюдо |
| 17 | Сырники | Завтрак |
| 18 | Гуляш с гречкой | Основное блюдо |
| 19 | Солянка | Суп |
| 20 | Домашние бургеры | Фастфуд |
| 21 | Паста Болоньезе | Паста |
| 22 | Запечённая курица с овощами | Основное блюдо |
| 23 | Оливье | Салат |
| 24 | Омлет с сыром и овощами | Завтрак |
| 25 | Рыба в сливочном соусе | Основное блюдо |
| 26 | Хачапури по-аджарски | Выпечка |
| 27 | Грибной ризотто | Основное блюдо |
| 28 | Тирамису | Десерт |
| 29 | Домашняя шаурма | Фастфуд |
| 30 | Стейк с запечённым картофелем | Основное блюдо |

**Архитектура и данные:** `model/Recipe.kt` описывает `Recipe` (день, ресурс названия,
категория) и `enum RecipeCategory` (8 категорий), каждая из которых несёт свою иконку
(`material-icons-extended`) и акцентный цвет. `data/Datasource.kt` хранит список из 30
рецептов, все текстовые строки вынесены в `strings.xml`.

**UI:** `MainActivity.kt` строит `RecipesApp` — `Scaffold` с `CenterAlignedTopAppBar` и
сеткой `LazyVerticalGrid(columns = GridCells.Fixed(2))`. Каждая карточка (`RecipeCard`)
показывает круглую иконку категории, номер дня, название рецепта и подпись категории;
при нажатии карточка плавно уменьшается (`animateFloatAsState` + `graphicsLayer`) —
лёгкая тактильная анимация вместо анимации Woof-приложения.

**Брендинг:** собственная цветовая схема на тёплой палитре (терракотовый/оливковый/
горчичный, отдельные светлая и тёмная темы в `ui/theme/Color.kt` и `Theme.kt`), три
шрифтовых стиля (`Serif` для заголовков, `SansSerif` для текста, `Monospace` для
номера дня в `Type.kt`) и собственный набор скруглений карточек (`Shapes.kt`).

Проект собран и проверен на эмуляторе (`assembleDebug` + запуск на устройстве):

![screenshot_1.png](Project%20Create%20a%2030%20Days%20App/screenshot_1.png)

![screenshot_2.png](Project%20Create%20a%2030%20Days%20App/screenshot_2.png)

---
