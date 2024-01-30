package example.com.routes

import example.com.models.ApiResponse
import example.com.repository.CountryRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

fun Route.getAllCountries() {
    val countryRepository: CountryRepository by inject()

    get("/countries") {
        try {
            val page = call.request.queryParameters["page"]?.toInt() ?: 1
            require(page in 1..countryRepository.pageCount)

            val apiResponse = countryRepository.getCountries(page)
            call.respond(
                message = apiResponse, status = HttpStatusCode.OK
            )
        } catch (e: NumberFormatException) {
            call.respond(
                message = ApiResponse(success = false, message = "Only Numbers Allowed."),
                status = HttpStatusCode.BadRequest
            )
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(success = false, message = "Countries not Found."),
                status = HttpStatusCode.NotFound
            )
        }
    }
}


