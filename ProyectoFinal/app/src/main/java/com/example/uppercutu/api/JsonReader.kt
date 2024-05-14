package com.example.uppercutu.api


import android.content.Context
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class JsonReader(private val context: Context) {

    fun loadJsonFromFile(context: Context, fileName: String): JSONObject? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return JSONObject(jsonString)
    }

    fun filterDataByDate(jsonObject: JSONObject, targetDate: String): JSONArray? {
        val filteredArray = JSONArray()
        var year = "2019"
        if (targetDate.contains("2019")){
             year = "2019"
        }else if (targetDate.contains("2020")){
            year = "2020"
        }else if (targetDate.contains("2021")){
            year = "2021"
        }else if (targetDate.contains("2022")){
            year = "2022"
        }else if (targetDate.contains("2023")){
            year = "2023"
        }else if (targetDate.contains("2024")){
            year = "2024"
        }

        val dataArray = jsonObject.optJSONArray(year) ?: return null

        for (i in 0 until dataArray.length()) {
            val dataObject = dataArray.getJSONObject(i)
            if (dataObject.getString("date") == targetDate) {
                filteredArray.put(dataObject)
            }
        }

        return if (filteredArray.length() > 0) filteredArray else null
    }
}