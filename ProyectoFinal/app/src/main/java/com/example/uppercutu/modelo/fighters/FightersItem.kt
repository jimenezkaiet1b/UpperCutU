package com.example.uppercutu.modelo.fighters

data class FightersItem(
    val BirthDate: String,
    val CareerStats: CareerStats,
    val Draws: Int,
    val FighterId: Int,
    val FirstName: String,
    val Height: Double,
    val LastName: String,
    val Losses: Int,
    val Nickname: String,
    val NoContests: Int,
    val Reach: Double,
    val SubmissionLosses: Int,
    val Submissions: Int,
    val TechnicalKnockoutLosses: Int,
    val TechnicalKnockouts: Int,
    val TitleDraws: Int,
    val TitleLosses: Int,
    val TitleWins: Int,
    val Weight: Double,
    val WeightClass: String,
    val Wins: Int
)