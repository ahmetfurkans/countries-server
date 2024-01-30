package example.com.models

import kotlinx.serialization.Serializable

@Serializable
data class Country(
    val flags: Flag,
    val name: Name,
    val currencies: Map<String, Currencies>,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>,
    val population: Int,
    val borders: List<String>
)

@Serializable
data class Flag(
    val png: String,
    val svg: String,
    val alt: String
)

@Serializable
data class NativeName(
    val official: String,
    val common: String
)

@Serializable
data class Name(
    val common: String,
    val official: String,
    val nativeName: Map<String, NativeName>
)

@Serializable
data class Currencies(
    val name: String,
    val symbol: String
)

@Serializable
data class Language(
    val code: String,
    val name: String
)