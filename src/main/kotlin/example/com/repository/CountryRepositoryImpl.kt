package example.com.repository

import example.com.models.ApiResponse
import example.com.models.Country
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import java.io.File
import java.lang.Exception
import kotlin.coroutines.coroutineContext

const val PAGE_SIZE = 10

class CountryRepositoryImpl : CountryRepository {

    override var countries: Map<Int, List<Country>>
    override var pageCount: Int

    init {
        countries =
            fetchCountriesFromFileConvertToMap(
                "/Users/ahmetfurkansevim/Desktop/ktor/countries-server/src/main/resources/countries.json"
            )
        pageCount = countries.size
    }

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

    private fun fetchCountriesFromFileConvertToMap(filePath: String): Map<Int, List<Country>> {
        try {
            val json = File(filePath).readText()
            val content = Json.decodeFromString<List<Country>>(json)
            return content.chunked(PAGE_SIZE).withIndex().associate { (index, list) -> index to list }
        } catch (e: Exception) {
            // Log the exception or handle it appropriately
            throw RuntimeException("Failed to fetch countries from file.", e)
        }
    }

    private fun calculatePage(page: Int): Pair<Int?, Int?> {
        val prevPage = if (page in 2..pageCount) page - 1 else null
        val nextPage = if (page in 1 until pageCount) page + 1 else null
        return Pair(prevPage, nextPage)
    }

}