package com.example.buanbslistapplication

import android.util.Log
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

object KrotClient {

    //제이슨 설정
    private val json = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
    
    //http 클라이언트
    val httpClient = HttpClient{
        //json 설정
        install(JsonFeature){
            serializer = KotlinxSerializer(json = json)
        }
        //로깅 설정
        install(Logging){
            logger = object  : Logger{
                override fun log(message: String) {
                    Log.d("TAG", "api log: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout){
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }
        //기본적인 api 호출 시 넣는 것들
            defaultRequest {
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Json)
            }
    }
}