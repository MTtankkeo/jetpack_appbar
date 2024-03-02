package com.ttangkong.jetpack_appbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun rememberAppBarState(): AppBarState {
    return rememberSaveable(saver = AppBarState.Saver) { AppBarState(0f) }
}

class AppBarState(
    initialOffset: Float,
) {
    var offset by mutableFloatStateOf(initialOffset)

    // Defined when composition of a target component.
    // And, this value can change whenever it is re-composition.
    var minExtent = 0;

    // Defined when layout phase or composition of a target component.
    // And, this value is mostly equal to the height of the component.
    var maxExtent = 0

    fun expandedPercent(minExtent: Int = 0) = 1 - shrinkedPercent(minExtent)
    fun shrinkedPercent(minExtent: Int = 0) = if(maxExtent == 0) offset else offset / (maxExtent - minExtent)

    fun setOffset(newOffset: Float): Float { // phase
        assert(newOffset >= minExtent) { "Given new offset has overflowed the min-extent." }
        assert(newOffset <= maxExtent) { "Given new offset has overflowed the max-extent." }

        val oldOffset = offset

        if (offset != newOffset) {
            offset = newOffset
            return oldOffset - offset
        }
        return 0f
    }

    companion object {
        val Saver = Saver<AppBarState, Float>(
            save =    { it.offset },
            restore = { AppBarState(it) }
        )
    }
}