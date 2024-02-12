package com.nyan.twtest.domain.entity.details

data class DetailsEntity(
	val types: List<String>? = null,
	val weight: Int? = null,
	val abilities: List<AbilitiesItemEntity?>? = null,
	val species: SpeciesEntity? = null,
	val name: String? = null,
	val id: Int? = null,
	val height: Int? = null,
)

data class AbilitiesItemEntity(
	val isHidden: Boolean? = null,
	val abilityName: String? = null,
)

data class SpeciesEntity(
	val name: String? = null,
)
