package com.example.uppercutu.modelo.events

data class EventosItem(
    val Active: Boolean,
    val DateTime: String,
    val Day: String,
    val EventId: Int,
    val LeagueId: Int,
    val Name: String,
    val Season: Int,
    val ShortName: String,
    val Status: String
)