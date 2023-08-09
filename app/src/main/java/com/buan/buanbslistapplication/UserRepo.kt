package com.buan.buanbslistapplication

import io.ktor.client.request.*

object UserRepo {

    suspend fun fetchUsers():List<Users>{

        val url = "https://63f5ac73ab76703b15af52ac.mockapi.io/api/v1/users"

        return KrotClient.httpClient.get(url)
    }
    suspend fun fetchUsers2():List<Users>{

        val url = "https://63f5ac73ab76703b15af52ac.mockapi.io/api/v1/user2"

        return KrotClient.httpClient.get(url)
    }
    suspend fun fetchUsers3():List<Users>{

        val url = "https://63f71c2de8a73b486af0cf0c.mockapi.io/api/v2/user3"

        return KrotClient.httpClient.get(url)
    }


}