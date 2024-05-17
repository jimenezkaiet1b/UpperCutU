package com.example.uppercutu.api

import android.content.Context
import org.json.JSONObject
import java.io.File

object ImageUrlStorage {
    private const val FILE_NAME = "fighter_images.json"

    fun saveImageUrl(context: Context, fighterName: String, imageUrl: String) {
        val file = File(context.filesDir, FILE_NAME)
        val json = if (file.exists()) {
            JSONObject(file.readText())
        } else {
            JSONObject()
        }
        json.put(fighterName, imageUrl)
        file.writeText(json.toString())
    }

    fun getImageUrl(context: Context, fighterName: String): String? {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) {
            return null
        }
        val json = JSONObject(file.readText())
        return json.optString(fighterName, null)
    }
}