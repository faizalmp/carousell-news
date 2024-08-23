package id.faizalempe.carousellnews.domain.ext

typealias OnStart = () -> Unit
typealias OnSuccess<T> = (T) -> Unit
typealias OnError = (Throwable) -> Unit
typealias OnComplete = () -> Unit