# TalkInsightApp

This is a minimal Android prototype implementing the first development phase of the TalkInsightApp described in the product requirements. The app provides skeleton code for:

- Recording audio with `MediaRecorder`.
- Uploading audio to OpenAI Whisper and ChatGPT using OkHttp.
- Text‑to‑Speech playback.
- A placeholder Room database.
- A simple Compose UI with a record button.

## Building

1. Ensure that the Android SDK and Java are installed.
2. Set an environment variable `OPENAI_API_KEY` in `gradle.properties` or at runtime.
3. Run:
   ```
   ./gradlew assembleDebug
   ```

This will produce an APK in `app/build/outputs/apk/debug/`.

This project is intentionally minimal and does not yet implement billing or face detection features.
