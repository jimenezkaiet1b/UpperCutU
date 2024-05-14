package com.example.uppercutu.api

import android.util.Log
import com.example.uppercutu.modelo.events.EventosItem
import com.example.uppercutu.modelo.fighters.CareerStats
import com.example.uppercutu.modelo.fighters.FightersItem
import com.example.uppercutu.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL



class SportApi {

    private val client = OkHttpClient()

    fun fetchBoxingData(callback: (String) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = makeRequest()
                val responseBody = response.body?.string() ?: "Empty response body"
                callback(responseBody)
            } catch (e: IOException) {
                callback("Error: ${e.message}")
            }
        }
    }



    private fun makeRequest(): Response {
        val request = Request.Builder()
            .url("https://sportapi7.p.rapidapi.com/api/v1/sport/boxing")
            .get()
            .addHeader("X-RapidAPI-Key", "6c29ff6d27msha0ef5ed8e903aa6p1293f8jsn1955f2f381e5")
            .addHeader("X-RapidAPI-Host", "sportapi7.p.rapidapi.com")
            .build()
        return client.newCall(request).execute()
    }

    fun getSchedule(league: String, year: String): String {
        Log.d("definitiva",year)
        val endpoint = "https://api.sportsdata.io/v3/mma/scores/json/Schedule/$league/$year?key=8c50528362f34136b7dd308745c72b36"
        return sendGetRequest(endpoint)
    }
    fun getFihters() : String{
        val endpoint = "https://api.sportsdata.io/v3/mma/scores/json/Fighters?key=8c50528362f34136b7dd308745c72b36"
        return sendGetRequest(endpoint)
    }

    fun parseSchedule(scheduleJson: String): List<EventosItem> {
        val eventosList = mutableListOf<EventosItem>()

        // Parsear el JSON de la respuesta y crear objetos EventosItem
        val jsonArray = JSONArray(scheduleJson)
        for (i in 0 until jsonArray.length()) {
            Log.d("Response", "Response: $scheduleJson")
            val jsonObject = jsonArray.getJSONObject(i)
            val evento = EventosItem(
                Active = jsonObject.getBoolean("Active"),
                DateTime = jsonObject.getString("DateTime"),
                Day = jsonObject.getString("Day"),
                EventId = jsonObject.getInt("EventId"),
                LeagueId = jsonObject.getInt("LeagueId"),
                Name = jsonObject.getString("Name"),
                Season = jsonObject.getInt("Season"),
                ShortName = jsonObject.getString("ShortName"),
                Status = jsonObject.getString("Status")
            )
            eventosList.add(evento)
        }

        return eventosList
    }

    fun parseFighters(fightersJson: String): List<FightersItem> {
        val fightersList = mutableListOf<FightersItem>()
        // Agregar corchetes al principio y al final de la cadena completa
        val jsonWithBrackets = "[$fightersJson]"
        val jsonArray = JSONArray(jsonWithBrackets)
        for (i in 0 until jsonArray.length()) {
            // Obtener el JSON object actual
            val jsonObject = jsonArray.getJSONObject(i)
            // Convertir el JSON object actual a una cadena
            val jsonString = jsonObject.toString()
            // Agregar corchetes al principio y al final de la cadena JSON
            val jsonStringWithBrackets = "[$jsonString]"
            // Parsear la cadena JSON con corchetes adicionales
            val jsonArrayWithBrackets = JSONArray(jsonStringWithBrackets)
            // Obtener el objeto JSON del primer (y único) elemento en el nuevo arreglo JSON
            val jsonObjectWithBrackets = jsonArrayWithBrackets.getJSONObject(0)
            // Crear el objeto FightersItem a partir del objeto JSON con corchetes adicionales
            Log.d("Response", "Response: $fightersJson")

            val fighter = FightersItem(
                FighterId = jsonObjectWithBrackets.getInt("FighterId"),
                FirstName = jsonObjectWithBrackets.getString("FirstName"),
                LastName = jsonObjectWithBrackets.getString("LastName"),
                Nickname = jsonObjectWithBrackets.getString("Nickname"),
                WeightClass = jsonObjectWithBrackets.getString("WeightClass"),
                BirthDate = jsonObjectWithBrackets.getString("BirthDate"),
                Height = jsonObjectWithBrackets.optDouble("Height", 0.0),
                Weight = jsonObjectWithBrackets.optDouble("Weight", 0.0),
                Reach = jsonObjectWithBrackets.optDouble("Reach", 0.0),
                Wins = jsonObjectWithBrackets.getInt("Wins"),
                Losses = jsonObjectWithBrackets.getInt("Losses"),
                Draws = jsonObjectWithBrackets.getInt("Draws"),
                NoContests = jsonObjectWithBrackets.getInt("NoContests"),
                TechnicalKnockouts = jsonObjectWithBrackets.getInt("TechnicalKnockouts"),
                TechnicalKnockoutLosses = jsonObjectWithBrackets.getInt("TechnicalKnockoutLosses"),
                Submissions = jsonObjectWithBrackets.getInt("Submissions"),
                SubmissionLosses = jsonObjectWithBrackets.getInt("SubmissionLosses"),
                TitleWins = jsonObjectWithBrackets.getInt("TitleWins"),
                TitleLosses = jsonObjectWithBrackets.getInt("TitleLosses"),
                TitleDraws = jsonObjectWithBrackets.getInt("TitleDraws"),
                CareerStats = parseCareerStats(jsonObjectWithBrackets.getJSONObject("CareerStats"))
            )
            fightersList.add(fighter)
        }

        return fightersList
    }





    private fun parseCareerStats(statsJson: JSONObject?): CareerStats {
        return if (statsJson != null) {
            CareerStats(
                FighterId = statsJson.getInt("FighterId"),
                FirstName = statsJson.getString("FirstName"),
                LastName = statsJson.getString("LastName"),
                SigStrikesLandedPerMinute = statsJson.optDouble("SigStrikesLandedPerMinute", 0.0),
                SigStrikeAccuracy = statsJson.optDouble("SigStrikeAccuracy", 0.0),
                TakedownAverage = statsJson.optDouble("TakedownAverage", 0.0),
                SubmissionAverage = statsJson.optDouble("SubmissionAverage", 0.0),
                KnockoutPercentage = statsJson.optDouble("KnockoutPercentage", 0.0),
                TechnicalKnockoutPercentage = statsJson.optDouble(
                    "TechnicalKnockoutPercentage",
                    0.0
                ),
                DecisionPercentage = statsJson.optDouble("DecisionPercentage", 0.0)
            )
        } else {
            CareerStats(0.0, 0, "", 0.0, "", 0.0, 0.0, 0.0, 0.0, 0.0)
        }
    }


    private fun sendGetRequest(endpoint: String): String {
        val url = URL(endpoint)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"

        val responseCode = connection.responseCode
        println("Response Code : $responseCode")

        if (responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = connection.inputStream
            val response = inputStream.bufferedReader().use { it.readText() }
            inputStream.close()
            return response
        } else {
            return "Error: $responseCode"
        }
    }


}
