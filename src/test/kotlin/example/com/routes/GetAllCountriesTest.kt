package example.com.routes

import example.com.models.ApiResponse
import example.com.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllCountriesTest {

    @Test
    fun `access root endpoint, with non-numeric page query, assert error`() = testApplication {
        application {
            configureRouting()
        }
        client.get("/countries?page=").apply {
            val actual = Json.decodeFromString<ApiResponse>(this.bodyAsText())
            val expected = ApiResponse(
                success = false,
                message = "Only Numbers Allowed."
            )
            assertEquals(HttpStatusCode.BadRequest, status)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun `access root endpoint, with invalid page size, assert error`() = testApplication {
        application {
            configureRouting()
        }
        client.get("/countries?page=").apply {
            val actual = Json.decodeFromString<ApiResponse>(this.bodyAsText())
            val expected = ApiResponse(
                success = false,
                message = "Countries not Found."
            )
            assertEquals(HttpStatusCode.NotFound, status)
            assertEquals(expected, actual)
        }
    }

}