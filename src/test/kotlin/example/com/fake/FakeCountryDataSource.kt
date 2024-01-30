package example.com.fake

import example.com.models.*

class FakeCountryDataSource {
    fun createFakeCountriesDataSource(): List<Country> {
        return listOf(
            createFakeCountry("Country1", "Flag1", "SVG1", "Alt1"),
            createFakeCountry("Country2", "Flag2", "SVG2", "Alt2"),
            createFakeCountry("Country3", "Flag3", "SVG3", "Alt3"),
            createFakeCountry("Country4", "Flag4", "SVG4", "Alt4"),
            createFakeCountry("Country5", "Flag5", "SVG5", "Alt5"),
            createFakeCountry("Country6", "Flag6", "SVG6", "Alt6"),
        )
    }

    private fun createFakeCountry(
        name: String,
        flagAlt: String,
        flagSvg: String,
        flagPng: String
    ): Country {
        return Country(
            flags = Flag(
                png = flagPng,
                svg = flagSvg,
                alt = flagAlt
            ),
            name = Name(
                common = name,
                official = "Official $name",
                nativeName = mapOf(
                    "en" to NativeName(
                        official = "Official English Name $name",
                        common = "Common English Name $name"
                    )
                    // Add more language mappings as needed
                )
            ),
            currencies = mapOf(
                "USD" to Currencies(
                    name = "US Dollar",
                    symbol = "$"
                )
                // Add more currency mappings as needed
            ),
            capital = listOf("Capital $name"),
            region = "Region $name",
            subregion = "Subregion $name",
            languages = mapOf(
                "en" to "English",
                "fr" to "French"
                // Add more language mappings as needed
            ),
            population = 1000000, // Replace with the desired population value
            borders = listOf("Border1", "Border2")
            // Add more fields as needed
        )
    }
}