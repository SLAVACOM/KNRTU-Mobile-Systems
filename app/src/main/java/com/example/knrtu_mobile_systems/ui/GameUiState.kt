package com.example.knrtu_mobile_systems.ui

data class GameUiState(
   val currentScrambledWord: String = "",
   val currentWordCount: Int = 1,
   val score: Int = 0,
   val isGuessedWordWrong: Boolean = false,
   val isGameOver: Boolean = false
)