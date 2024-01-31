package example.com.fake

import example.com.models.ApiResponse
import example.com.models.Country
import example.com.repository.CountryRepository

class FakeCountryRepository : CountryRepository{


    override var countries: Map<Int, List<Country>> = FakeCountryDataSource().createFakeCountriesDataSource()
    override var pageCount: Int = countries.size

    override suspend fun getCountries(page: Int): ApiResponse {
        val (prevPage, nextPage) = calculatePage(page)
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = prevPage,
            nextPage = nextPage,
            countries = countries[page-1]!! // Adjusting for zero-based array index (page - 1)
        )
    }

    private fun calculatePage(page: Int): Pair<Int?, Int?> {
        val prevPage = if (page in 2..pageCount) page - 1 else null
        val nextPage = if (page in 1 until pageCount) page + 1 else null
        return Pair(prevPage, nextPage)
    }
}