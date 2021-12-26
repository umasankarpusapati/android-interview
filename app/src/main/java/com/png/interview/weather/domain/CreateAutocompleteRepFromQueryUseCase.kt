package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import javax.inject.Inject

interface CreateAutocompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutocompleteViewRepresentation
}

class DefaultCreateAutocompleteRepFromQueryUseCase @Inject constructor(
    private val getAutocompleteDataUseCase: GetAutocompleteDataUseCase
) : CreateAutocompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String): AutocompleteViewRepresentation {
        return try {
            val result = getAutocompleteDataUseCase(query)
            (result as NetworkResponse.Success).let {
                AutocompleteViewRepresentation.AutocompleteViewRep(
                    AutocompleteViewData(
                        (result.body.indices).map {
                            result.body[it].name
                        }
                    )
                )
            }
        } catch (e: Exception) {
            AutocompleteViewRepresentation.Error
        }
    }
}