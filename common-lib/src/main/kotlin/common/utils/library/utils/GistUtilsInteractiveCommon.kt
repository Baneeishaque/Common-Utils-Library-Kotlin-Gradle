package common.utils.library.utils

import common.utils.library.models.Root
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

object GistUtilsInteractiveCommon {

    @JvmStatic
    fun getGistContents(

        client: HttpClient,
        gistId: String,
        isDevelopmentMode: Boolean

    ): Root {

        var gistResponse: Root

        runBlocking {

            gistResponse = client.get(urlString = "https://api.github.com/gists/$gistId") {

                onDownload { bytesSentTotal: Long, contentLength: Long ->

                    if (isDevelopmentMode) {

                        println("Received $bytesSentTotal bytes from $contentLength")
                    }
                }
            }.body()

        }
        return gistResponse
    }
}
