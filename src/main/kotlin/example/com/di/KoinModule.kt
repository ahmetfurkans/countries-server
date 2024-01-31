package example.com.di

import example.com.repository.CountryRepository
import example.com.repository.CountryRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<CountryRepository> {
        CountryRepositoryImpl()
    }
}
