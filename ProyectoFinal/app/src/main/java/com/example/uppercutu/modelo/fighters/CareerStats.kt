package com.example.uppercutu.modelo.fighters

data class CareerStats(
    val DecisionPercentage: Double,
    val FighterId: Int,
    val FirstName: String,
    val KnockoutPercentage: Double,
    val LastName: String,
    val SigStrikeAccuracy: Double,
    val SigStrikesLandedPerMinute: Double,
    val SubmissionAverage: Double,
    val TakedownAverage: Double,
    val TechnicalKnockoutPercentage: Double
)