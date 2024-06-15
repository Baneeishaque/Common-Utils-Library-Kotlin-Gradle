package common.utils.library.utils

import common.utils.library.models.IsOkModel
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File
import java.io.FileInputStream

object JsonFileUtilsInteractive {

    @OptIn(ExperimentalSerializationApi::class)
    @JvmStatic
    inline fun <reified T> readJsonFile(fileName: String, isDevelopmentMode: Boolean = false): IsOkModel<T> {

        val jsonFile = File(fileName)
        if (isDevelopmentMode) {

            println("jsonFile = $jsonFile")
            println("jsonFile exists = ${jsonFile.exists()}")
        }
        if (jsonFile.exists()) {

            jsonFile.inputStream().use { fileInputStream: FileInputStream ->

                val result: T = Json.decodeFromStream(fileInputStream)
                if (isDevelopmentMode) {

                    println("data = $result")
                }
                return IsOkModel(isOK = true, data = result)
            }
        }
        return IsOkModel(isOK = false)
    }
}
