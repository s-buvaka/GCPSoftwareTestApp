package com.wispapp.gcpsoftwaretestapp.core.model.remote

import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL

interface RemoteProvider<T> {

    fun get(): Result<T>
}

abstract class BaseRemoteProvider<T> : RemoteProvider<T>{

    @Throws(IOException::class)
    protected inline fun <reified T> getResult(stringUrl: String): Result<T> {
        val response = StringBuilder()
        val url = URL(stringUrl)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
            var strLine: String?

            while (input.readLine().also { strLine = it } != null) {
                response.append(strLine)
            }
            input.close()
            val data = mapResponse<T>(response.toString(), T::class.java)
            return Result.Success(data)
        }

        return Result.Error(Exception("Network exception"))
    }

    protected fun <T> mapResponse(response: String, clazz: Type): T = Gson().fromJson(response, clazz)
}