package example.com.base

import example.com.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.core.module.Module
import org.koin.ktor.plugin.Koin

abstract class BaseRoutingTest {
    protected var testModule: Module? = null
    protected var testRouting: Application.() -> Unit = { }


    init {
        stopKoin()
    }

    fun <R> withBaseTestApplication(test: suspend ApplicationTestBuilder.() -> R) = testApplication {
        environment {
            config = MapApplicationConfig("ktor.environment" to "test")
        }
        application {
            testModule?.let {
                install(Koin) {
                    modules(it)
                }
                configureSerialization()
            }
            testRouting()
        }
        test()
    }
}
