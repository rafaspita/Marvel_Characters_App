package com.spitaliere.domain.platform.usecase

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 03/11/2019.
 **/
interface BaseInputUseCase<in Input, out Output>{
    fun run(input: Input) : Output
}

interface BaseUseCase<out Output> {
    fun run() : Output
}

interface SingleInputUseCase<Input, Output> : BaseInputUseCase<Input, Single<Output>>

interface FlowableInputUseCase<Input, Output> : BaseInputUseCase<Input, Flowable<Output>>

interface ObservableInputUseCase<Input, Output> : BaseInputUseCase<Input, Observable<Output>>

interface CompletableInputUseCase<Input> : BaseInputUseCase<Input, Completable>

interface SingleUseCase<Output> : BaseUseCase<Single<Output>>

interface FlowableUseCase<Output> : BaseUseCase<Flowable<Output>>

interface ObservableUseCase<Output> : BaseUseCase<Observable<Output>>

interface CompletableUseCase : BaseUseCase<Completable>