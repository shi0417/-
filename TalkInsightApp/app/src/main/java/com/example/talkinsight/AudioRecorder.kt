package com.example.talkinsight

import android.media.MediaRecorder
import android.util.Log
import java.io.File

class AudioRecorder(private val outputFile: File) {
    private var recorder: MediaRecorder? = null

    fun start() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(outputFile.absolutePath)
            prepare()
            start()
        }
    }

    fun stop() {
        try {
            recorder?.apply { stop(); release() }
        } catch (e: Exception) {
            Log.e("AudioRecorder", "stop failed", e)
        }
        recorder = null
    }
}
