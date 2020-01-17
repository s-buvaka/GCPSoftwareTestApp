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

    protected inline fun <reified T> getResult(stringUrl: String): Result<T> {
        val response = StringBuilder()
        val url = URL(stringUrl)
        return try {
            executeResponse(url, response)
        } catch (e: IOException) {
            Result.Error(e)
        }

    }

    protected inline fun <reified T> executeResponse(
        url: URL,
        response: StringBuilder
    ): Result<T> {
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        return if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val data = writeData<T>(urlConnection, response)
            Result.Success(data)
        } else
            Result.Error(Exception("Network exception"))
    }

    protected inline fun <reified T> writeData(
        urlConnection: HttpURLConnection,
        response: StringBuilder
    ): T {
        val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
        var strLine: String?

        while (input.readLine().also { strLine = it } != null) {
            response.append(strLine)
        }
        input.close()
        return mapResponse(response.toString(), T::class.java)
    }

    protected fun <T> mapResponse(response: String, clazz: Type): T = Gson().fromJson(response, clazz)
}