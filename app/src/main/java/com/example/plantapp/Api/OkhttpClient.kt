package com.example.plantapp.Api

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


object OkhttpClient {
    fun getInstance(): OkhttpClient {
        return this
    }
    suspend fun getCategories(): String {
        return suspendCancellableCoroutine { continuation ->
            val client = OkHttpClient()

            val url = "https://dummy-api-jtg6bessta-ey.a.run.app/getCategories"

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(e)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        if (response.isSuccessful) {
                            val responseData = response.body?.string()
                            if (continuation.isActive) {
                                continuation.resume(responseData ?: "")
                            }
                        } else {
                            continuation.resumeWithException(IOException("Failed with code: ${response.code}"))
                        }
                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }
            })

            continuation.invokeOnCancellation {
                client.dispatcher.cancelAll()
            }
        }
    }

    suspend fun getQuestions(): String {
        return suspendCancellableCoroutine { continuation ->
            val client = OkHttpClient()

            val url = "https://dummy-api-jtg6bessta-ey.a.run.app/getQuestions"

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isActive) {
                        continuation.resumeWithException(e)
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    try {
                        if (response.isSuccessful) {
                            val responseData = response.body?.string()
                            if (continuation.isActive) {
                                continuation.resume(responseData ?: "")
                            }
                        } else {
                            continuation.resumeWithException(IOException("Failed with code: ${response.code}"))
                        }
                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }
            })

            continuation.invokeOnCancellation {
                client.dispatcher.cancelAll()
            }
        }
    }



}