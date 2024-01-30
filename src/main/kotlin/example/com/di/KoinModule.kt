package example.com.di

import example.com.repository.CountryRepository
import example.com.repository.CountryRepositoryImpl
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

val koinModule = module {
    single<CountryRepository> {
        CountryRepositoryImpl()
    }
}
