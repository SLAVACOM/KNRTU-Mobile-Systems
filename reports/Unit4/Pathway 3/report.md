# Unit 4 · Pathway 3 — Adaptive layouts

## 1. Build an adaptive app with dynamic navigation (Reply)
- **Change screens without a navigation graph** — `ReplyHomeScreen` переключает список/детали по
  `isShowingHomepage`, системная кнопка «Назад» обрабатывается `BackHandler`.
- **Make your layout adapt to different screen sizes** — `material3-window-size-class`,
  `calculateWindowSizeClass(this)` в `MainActivity`, `WindowWidthSizeClass` передаётся в `ReplyApp`.
- **Implement adaptive navigation layout** — `ReplyNavigationType`: Compact → `NavigationBar`,
  Medium → `NavigationRail`, Expanded → `PermanentNavigationDrawer`.

## 2. Build an app with an adaptive layout (Reply)
- Превью для Compact / Medium (700dp) / Expanded (1000dp).
- `ReplyContentType` (`LIST_ONLY`, `LIST_AND_DETAIL`): на Expanded список и детали письма показываются рядом,
  `ReplyDetailsScreen(isFullScreen)`, «Назад» в режиме list-detail закрывает приложение.
- Тесты `ReplyAppTest`, `ReplyAppStateRestorationTest` (`StateRestorationTester`) и аннотации
  `@TestCompactWidth` / `@TestMediumWidth` / `@TestExpandedWidth`.
  Compact и Medium тесты проходят на Pixel 10 Pro, Expanded — на том же эмуляторе с `wm size 2560x1600`, `wm density 320`.

| Compact: список | Compact: детали | Medium: navigation rail |
|---|---|---|
| ![](reply_compact_list.png) | ![](reply_compact_details.png) | ![](reply_medium_rail.png) |

Expanded: permanent navigation drawer + список и детали

![](reply_expanded_list_detail.png)
