package example.com.repository

import example.com.models.ApiResponse
import example.com.models.Country

interface CountryRepository {

    val pageCount: Int
    val countries: Map<Int, List<Country>>

    suspend fun getCountries(page: Int = 1): ApiResponse
}