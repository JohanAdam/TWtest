package com.nyan.twtest.data.model.details

import com.google.gson.annotations.SerializedName
import com.nyan.twtest.domain.entity.details.AbilitiesItemEntity
import com.nyan.twtest.domain.entity.details.DetailsEntity
import com.nyan.twtest.domain.entity.details.SpeciesEntity

data class DetailsResp(

    @SerializedName("cries") val cries: CriesResp? = null,

    @SerializedName("location_area_encounters") val locationAreaEncounters: String? = null,

    @SerializedName("types") val types: List<TypesItemResp?>? = null,

    @SerializedName("base_experience") val baseExperience: Int? = null,

    @SerializedName("held_items") val heldItems: List<Any?>? = null,

    @SerializedName("weight") val weight: Int? = null,

    @SerializedName("is_default") val isDefault: Boolean? = null,

    @SerializedName("past_types") val pastTypes: List<Any?>? = null,

    @SerializedName("past_abilities") val pastAbilities: List<Any?>? = null,

    @SerializedName("abilities") val abilities: List<AbilitiesItemResp?>? = null,

    @SerializedName("game_indices") val gameIndices: List<GameIndicesItemResp?>? = null,

    @SerializedName("species") val species: SpeciesResp? = null,

    @SerializedName("stats") val stats: List<StatsItemResp?>? = null,

    @SerializedName("moves") val moves: List<MovesItemResp?>? = null,

    @SerializedName("name") val name: String? = null,

    @SerializedName("id") val id: Int? = null,

    @SerializedName("forms") val forms: List<FormsItemResp?>? = null,

    @SerializedName("height") val height: Int? = null,

    @SerializedName("order") val order: Int? = null
)

data class MovesItemResp(

    @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetailsItemResp?>? = null,

    @SerializedName("move") val move: MoveResp? = null
)

data class TypeResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class TypesItemResp(

    @SerializedName("slot") val slot: Int? = null,

    @SerializedName("type") val type: TypeResp? = null
)

data class AbilityResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class CriesResp(

    @SerializedName("legacy") val legacy: String? = null,

    @SerializedName("latest") val latest: String? = null
)

data class MoveResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class StatResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class VersionGroupDetailsItemResp(

    @SerializedName("level_learned_at") val levelLearnedAt: Int? = null,

    @SerializedName("version_group") val versionGroup: VersionGroupResp? = null,

    @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethodResp? = null
)

data class GameIndicesItemResp(

    @SerializedName("game_index") val gameIndex: Int? = null,

    @SerializedName("version") val version: VersionResp? = null
)

data class VersionResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class AbilitiesItemResp(

    @SerializedName("is_hidden") val isHidden: Boolean? = null,

    @SerializedName("ability") val ability: AbilityResp? = null,

    @SerializedName("slot") val slot: Int? = null
)

data class SpeciesResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class FormsItemResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class VersionGroupResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class MoveLearnMethodResp(

    @SerializedName("name") val name: String? = null,

    @SerializedName("url") val url: String? = null
)

data class StatsItemResp(

    @SerializedName("stat") val stat: StatResp? = null,

    @SerializedName("base_stat") val baseStat: Int? = null,

    @SerializedName("effort") val effort: Int? = null
)

fun DetailsResp.mapToDomain(): DetailsEntity {
    return DetailsEntity(
        types = this.types?.map {
            it?.type?.name ?: ""
        },
        weight = this.weight,
        abilities = this.abilities?.map {
            AbilitiesItemEntity(
                isHidden = it?.isHidden,
                abilityName = it?.ability?.name
            )
        },
        species = SpeciesEntity(
            name = this.species?.name,
        ),
        name = name,
        id = id,
        height = height
    )
}
