package com.example.uppercutu.api

import android.util.Log
import com.example.uppercutu.modelo.events.EventosItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import com.example.uppercutu.util.Constants.Companion.API_KEY_MMA
import org.json.JSONArray

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
