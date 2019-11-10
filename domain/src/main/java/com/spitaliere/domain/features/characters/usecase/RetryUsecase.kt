package com.spitaliere.domain.features.characters.usecase

import com.spitaliere.domain.platform.usecase.CompletableInputUseCase
import io.reactivex.Completable

/**
 * Created by Rafael Spitaliere on 09/11/2019.
 **/
class RetryUsecase : CompletableInputUseCase<(() -> Unit)> {
    override fun run(input: () -> Unit): Completable = Completable.fromAction(input)
}