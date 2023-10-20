package com.djupbyte.newsan.pdf
import android.content.Context
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

fun downloadAndSavePdf(url: String, fileName: String, context: Context): String? {

    try {
        val directory = context.filesDir
        if (!directory.exists()) {
            directory.mkdirs();
        }
        val file = File(directory, fileName)

        if (!file.exists()) {
            val urlConnection = URL(url).openConnection() as HttpURLConnection
            urlConnection.connect()
            val inputStream = BufferedInputStream(urlConnection.inputStream)

            val outputStream = FileOutputStream(file)

            val data = ByteArray(1024)
            var total: Long = 0
            var count: Int

            while (inputStream.read(data).also { count = it } != -1) {
                total += count.toLong()
                outputStream.write(data, 0, count)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()
        }

        return file.path
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }


}