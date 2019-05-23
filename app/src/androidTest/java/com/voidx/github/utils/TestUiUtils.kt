package com.voidx.github.utils

import android.support.test.InstrumentationRegistry
import android.util.Log
import java.io.IOException


class TestUiUtils {

    companion object {

        fun readFixture(file: String): String {
            val path = "fixtures/"
            var json = ""
            try {
                val openFile = InstrumentationRegistry.getContext().assets.open(path + file)
                val size = openFile.available()
                val buffer = ByteArray(size)
                openFile.read(buffer)
                openFile.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                Log.e("TEST", "readFixture: ", e)
                return json
            }

            return json
        }

    }

}