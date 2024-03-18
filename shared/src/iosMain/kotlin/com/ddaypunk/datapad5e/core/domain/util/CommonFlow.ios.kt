package com.ddaypunk.datapad5e.core.domain.util

import com.ddaypunk.translator.core.domain.util.DisposableHandle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual open class CommonFlow<T> actual constructor(
    private val flow: Flow<T>
) : Flow<T> by flow {
    fun subscribe(
        coroutineScope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(dispatcher) {
            flow.collect(onCollect)
        }

        return DisposableHandle { job.cancel() }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun subscribe(
        onCollect: (T) -> Unit
    ): DisposableHandle {
        return subscribe(
            coroutineScope = GlobalScope,
            dispatcher = Dispatchers.Main,
            onCollect = onCollect
        )
    }
}