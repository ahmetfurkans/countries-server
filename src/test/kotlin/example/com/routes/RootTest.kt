package example.com.routes

import example.com.base.BaseRoutingTest
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import kotlinx.serialization.ExperimentalSerializationApi
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

class RootTest : BaseRoutingTest(){

    @Before
    fun setup() {
        testRouting = {
            install(Routing) {
                root()
            }
        }
    }
    @Test
    fun `access root endpoint assert correct information`() = withBaseTestApplication{

        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Welcome to Countries API!", bodyAsText())
        }
    }

    @ExperimentalSerializationApi
    @Test
    fun `access non existing endpoint,assert not found`() = testApplication {
            val response = client.get("/unknown")
            assertEquals(expected = HttpStatusCode.NotFound, actual = response.status)
            assertEquals(expected = "Page not Found.", actual = response.bodyAsText())
        }
}

