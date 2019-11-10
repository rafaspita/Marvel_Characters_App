package com.spitaliere.domain.features.comics.usecases

import com.spitaliere.domain.features.comics.model.ComicsInfo
import com.spitaliere.domain.features.comics.repository.ComicsRepository
import com.spitaliere.domain.platform.usecase.SingleInputUseCase
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
class GetCharacterComicsByIdCase(private val comicsRepository: ComicsRepository) : SingleInputUseCase<String, List<ComicsInfo>> {

    override fun run(input: String): Single<List<ComicsInfo>> = comicsRepository.getCharacterComicsById(input)
}