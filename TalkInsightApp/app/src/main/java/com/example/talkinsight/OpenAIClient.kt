package com.example.talkinsight

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.MultipartBody
import org.json.JSONObject
import java.io.File

class OpenAIClient(private val apiKey: String) {
    private val client = OkHttpClient()

    fun transcribeAudio(audio: File): String? {
        val mediaType = "audio/mpeg".toMediaType()
        val body = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", audio.name, audio.asRequestBody(mediaType))
            .addFormDataPart("model", "whisper-1")
            .build()

        val request = Request.Builder()
            .url("https://api.openai.com/v1/audio/transcriptions")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            val json = JSONObject(response.body!!.string())
            return json.getString("text")
        }
    }
}
