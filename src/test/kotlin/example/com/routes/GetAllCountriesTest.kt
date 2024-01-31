package example.com.routes

import example.com.base.BaseRoutingTest
import example.com.fake.FakeCountryRepository
import example.com.models.ApiResponse
import example.com.repository.CountryRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.junit.Before
import org.koin.dsl.module
import kotlin.test.Test
import kotlin.test.assertEquals

class GetAllCountriesTest : BaseRoutingTest() {

    private val countryRepository: CountryRepository = FakeCountryRepository()

    @Before
    fun setup() {
        testModule = module {
            single { countryRepository }
        }
        testRouting = {
            install(Routing) {
                getAllCountries()
            }
        }
    }

    @Test
    fun `access countries endpoint, with valid page query, assert correct response`() = withBaseTestApplication {
        val pageQuery = 3
        client.get("/countries?page=$pageQuery").apply {
            val actual = Json.decodeFromString<ApiResponse>(this.bodyAsText())
            val expected = countryRepository.getCountries(pageQuery)

            assertEquals(HttpStatusCode.OK, status)
            assertEquals(actual, expected)
        }
    }

    @Test
    fun `access countries endpoint, with non-numeric page query, assert error`() = withBaseTestApplication {
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
    fun `access countries endpoint, with invalid page size, assert error`() = withBaseTestApplication {
        client.get("/countries?page=${countryRepository.pageCount + 1}").apply {
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