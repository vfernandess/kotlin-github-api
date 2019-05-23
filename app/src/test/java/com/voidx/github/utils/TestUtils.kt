package com.voidx.github.utils

import com.squareup.moshi.Moshi
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

class TestUtils {

    companion object {

        private val moshi = Moshi.Builder().build()

        fun <T> getObject(fileName: String, classType: Class<T>): T? {
            val json = getJson(fileName)
            var value: T?
            try {
                value = moshi.adapter(classType).fromJson(json!!)
            } catch (e: IOException) {
                e.printStackTrace()
                value = null
            }

            return value
        }

        fun <T> getObject(fileName: String, classType: Type): T? {
            val json = getJson(fileName)
            var value: T?
            try {
                value = moshi.adapter<Any>(classType).fromJson(json!!) as T
            } catch (e: IOException) {
                e.printStackTrace()
                value = null
            }

            return value
        }

        fun getJson(fileName: String): String? {
            var json: String?
            try {
                val classLoader = ClassLoader.getSystemClassLoader()
                val inputStream = classLoader.getResourceAsStream(fileName)
                val size = inputStream.available()
                val buffer = ByteArray(size)

                inputStream.read(buffer)
                inputStream.close()

                json = String(buffer, StandardCharsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
                json = null
            }

            return json
        }

        fun <T> createConnectionErrorObservable(): Observable<T> {
            return Observable.error<T>(createGenericError(500))
        }

        fun createGenericError(errorCode: Int): HttpException {

            var error = getJson("generic_error.json")
            error = String.format(error!!, errorCode.toString())

            val responseError = Response.error<Any>(
                errorCode,
                ResponseBody.create(MediaType.parse("application/json"), error)
            )

            return HttpException(responseError)
        }

    }
}