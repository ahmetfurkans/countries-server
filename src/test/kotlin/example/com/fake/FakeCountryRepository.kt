package example.com.fake

import example.com.models.ApiResponse
import example.com.models.Country
import example.com.repository.CountryRepository

class FakeCountryRepository : CountryRepository{
    override val pageCount: Int
        get() = 3
    override val countries: Map<Int, List<Country>>
        get() = TODO("Not yet implemented")

    override suspend fun getCountries(page: Int): ApiResponse {
        TODO("Not yet implemented")
    }
}