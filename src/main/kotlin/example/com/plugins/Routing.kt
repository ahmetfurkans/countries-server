package example.com.plugins

import example.com.routes.getAllCountries
import example.com.routes.root
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        root()
        getAllCountries()
    }
}

