package com.spitaliere.marvelcharacters.presentation.platform.extension

import com.spitaliere.domain.platform.usecase.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

/**
 * Created by Rafael Spitaliere on 06/11/2019.
 **/
fun <Input, Output> SingleInputUseCase<Input, Output>.invokeOnBackground(request: Input): Single<Output> =
    run(request).subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun <Input> CompletableInputUseCase<Input>.invokeOnBackground(request: Input): Completable =
    run(request).subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun <Input, Output> FlowableInputUseCase<Input, Output>.invokeOnBackground(request: Input): Flowable<Output> =
    run(request).subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun <Output> SingleUseCase<Output>.invokeOnBackground(): Single<Output> =
    run().subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun CompletableUseCase.invokeOnBackground(): Completable =
    run().subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())

fun <Output> FlowableUseCase<Output>.invokeOnBackground(): Flowable<Output> =
    run().subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())