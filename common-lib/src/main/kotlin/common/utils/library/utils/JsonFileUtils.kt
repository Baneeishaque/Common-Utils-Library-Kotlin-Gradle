package common.utils.library.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToStream
import java.io.File
import java.io.FileOutputStream

object JsonFileUtils {

    @OptIn(ExperimentalSerializationApi::class)
    @JvmStatic
    inline fun <reified T> writeJsonFile(

        fileName: String,
        data: T,
        isPrettyPrint: Boolean = true
    ) {

        FileOutputStream(File(fileName)).use { fileOutputStream: FileOutputStream ->

            val prettyJson = Json {

                prettyPrint = isPrettyPrint
            }
            prettyJson.encodeToStream(data, fileOutputStream)
        }
    }
}
