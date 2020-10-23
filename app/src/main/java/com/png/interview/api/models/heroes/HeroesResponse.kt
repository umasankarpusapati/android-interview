package com.png.interview.api.models.heroes

import com.squareup.moshi.Json

data class Hero(
    val abilities: List<Ability>?,
    val attribute_id: String?,
    val c_hero_id: String?,
    val c_unit_id: String?,
    val icon_url: IconUrl?,
    val name: String?,
    val release_date: String?,
    val release_patch: String?,
    val role: HeroRole?,
    val short_name: String?,
    val talents: List<Talent>?,
    val translations: List<String>?,
    val type: HeroType?
)

data class Ability(
    val cooldown: Int?,
    val description: String?,
    val hotkey: String?,
    val icon: String?,
    val mana_cost: Int?,
    val name: String?,
    val owner: String?,
    val title: String?,
    val trait: Boolean?
)

data class Talent(
    val ability: String?,
    val cooldown: Int?,
    val description: String?,
    val icon: String?,
    val icon_url: IconUrl?,
    val level: Int?,
    val mana_cost: Int?,
    val name: String?,
    val sort: Int?,
    val title: String?
)

data class IconUrl(
    @Json(name = "92x93")
    val bigUrl: String?,

    @Json(name = "66x66")
    val smallUrl: String?
)

enum class HeroRole {
    @Json(name = "Warrior") WARRIOR,
    @Json(name = "Support") SUPPORT,
    @Json(name = "Specialist") SPECIALIST,
    @Json(name = "Assassin") ASSASSIN,
    @Json(name = "Multiclass") MULTICLASS
}

enum class HeroType {
    @Json(name = "Melee") MELEE,
    @Json(name = "Ranged") RANGED
}