package id.faizalempe.carousellnews.domain.base

import id.faizalempe.carousellnews.domain.ext.OnError
import id.faizalempe.carousellnews.domain.ext.OnStart
import id.faizalempe.carousellnews.domain.ext.OnSuccess
import id.faizalempe.carousellnews.domain.ext.OnComplete
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

abstract class BaseInteractor<T: Any, P> {

    protected abstract fun build(params: P): Flow<T>

    fun observe(
        params: P,
        onStart: OnStart,
        onSuccess: OnSuccess<T>,
        onError: OnError,
        onComplete: OnComplete,
        scope: CoroutineScope
    ) {
        build(params).flowOn(Dispatchers.IO)
            .onStart { onStart.invoke() }
            .onEach { onSuccess.invoke(it) }
            .onCompletion { onComplete.invoke() }
            .catch { onError.invoke(it) }
            .launchIn(scope)
    }
}