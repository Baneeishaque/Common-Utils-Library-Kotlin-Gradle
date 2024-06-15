package common.utils.library.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.FileOutputStream

object JsonFileUtils {

    @OptIn(ExperimentalSerializationApi::class)
    @JvmStatic
    inline fun <reified T> writeJsonFile(fileName: String, data: T) {

        FileOutputStream(File(fileName)).use { fileOutputStream: FileOutputStream ->

            Json.encodeToStream(data, fileOutputStream)
        }
    }
}
