# Marvel Characters App

[![CircleCI](https://circleci.com/gh/rafaspita/Marvel_Characters_App.svg?style=svg)](https://circleci.com/gh/rafaspita/Marvel_Characters_App) [![codecov](https://codecov.io/gh/rafaspita/Marvel_Characters_App/branch/master/graph/badge.svg)](https://codecov.io/gh/rafaspita/Marvel_Characters_App) [![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.50-blue.svg)](http://kotlinlang.org/) 

App que lista os heróis da Marvel em uma lista infinita, consumindo a API da Marvel. Ao clicar no herói as informações sobre ele são apresentadas, mostrando também os quadrinhos que o mesmo está presente.

![app demo](https://media.giphy.com/media/h7Lo2bkeNwjzzCAvuW/giphy.gif)![app demo](https://media.giphy.com/media/Ke2wtFF5T9c2YP215i/giphy.gif)

# Libs
* [RxKotlin](https://github.com/ReactiveX/RxKotlin)
* [RxAndroid](https://github.com/ReactiveX/RxAndroid)
* [Retrofit](https://github.com/square/retrofit)
* [Koin](https://github.com/InsertKoinIO/koin)
* [Picasso](https://github.com/square/picasso)
* [Lottie](https://github.com/airbnb/lottie-android)
* [Paging](https://developer.android.com/jetpack/androidx/releases/paging)
* [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation)

# Setup
**Para consumir a API da Marvel é necessário adicionar as suas Keys no arquivo ApiModule.kt, presente no módulo Data.**
```kotlin
private const val PUBLIC_KEY = ""  
private const val PRIVATE_KEY = ""
```

# License
    Copyright 2019 Rafael Spitaliere

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
