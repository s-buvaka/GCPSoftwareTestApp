package com.wispapp.gcpsoftwaretestapp.core.model.remote

import com.google.gson.Gson
import com.wispapp.gcpsoftwaretestapp.core.model.pojo.MenuResponse
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL


interface RemoteProvider {

    fun getMenu(): Result<List<MenuResponse>>
}

class RemoteProviderImpl private constructor(private val baseUrl: String) : RemoteProvider {

    companion object {

        private const val URL_GET_MENU = "fk3d5kg6cptkpr6/menu.json?dl=1"

        private var remoteProvider: RemoteProviderImpl? = null

        fun getInstance(baseUrl: String): RemoteProviderImpl {
            return remoteProvider?.let { it } ?: run {
                val provider = RemoteProviderImpl(baseUrl)
                remoteProvider = provider
                provider
            }
        }
    }

    override fun getMenu(): Result<List<MenuResponse>> {
        val url = baseUrl + URL_GET_MENU
        return getResponseText(url)
    }

    @Throws(IOException::class)
    private inline fun <reified T> getResponseText(stringUrl: String): Result<T> {
        val response = StringBuilder()
        val url = URL(stringUrl)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

        if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val data = getResponseObject<T>(urlConnection, response)
            return Result.Success(data)
        }

        return Result.Error(Exception("Network exception"))
    }

    private inline fun <reified T> getResponseObject(
        urlConnection: HttpURLConnection,
        response: StringBuilder
    ): T {
        val input = BufferedReader(InputStreamReader(urlConnection.inputStream))
        var strLine: String

        while (input.readLine().also { strLine = it } != null) {
            response.append(strLine)
        }
        input.close()

        return mapResponse(response.toString(), T::class.java)
    }

    private fun <T> mapResponse(response: String, clazz: Type): T = Gson().fromJson(response, clazz)
}