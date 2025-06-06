package com.example.talkinsight

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject

class ChatGPTClient(private val apiKey: String) {
    private val client = OkHttpClient()

    fun analyze(text: String): String? {
        val mediaType = "application/json".toMediaType()
        val payload = JSONObject()
        payload.put("model", "gpt-3.5-turbo")
        payload.put(
            "messages",
            JSONArray().put(
                JSONObject().put("role", "user").put("content", text)
            )
        )

        val request = Request.Builder()
            .url("https://api.openai.com/v1/chat/completions")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(payload.toString().toRequestBody(mediaType))
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) return null
            val json = JSONObject(response.body!!.string())
            val choice = json.getJSONArray("choices").getJSONObject(0)
            val message = choice.getJSONObject("message")
            return message.getString("content")
        }
    }
}
