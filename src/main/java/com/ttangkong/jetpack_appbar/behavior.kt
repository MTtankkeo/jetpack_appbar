package com.ttangkong.jetpack_appbar

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

interface AppBarBehavior {
    fun handleScrollEnd(sliver: AppBarState) = Unit
    fun handleScrollFling(sliver: AppBarState) = Unit

    fun handleScrollBy(
        appbar: AppBarState,
        scroll: ScrollableState?,
        delta: Float,
        source: NestedScrollSource
    ): Float
}

class AbsoluteSliverBehavior : AppBarBehavior {
    override fun handleScrollBy(
        appbar: AppBarState,
        scroll: ScrollableState?,
        delta: Float,
        source: NestedScrollSource
    ): Float = 0f
}

class MaterialAppBarBehavior(
    val floating: Boolean = true,
    val dragOnlyExpanding: Boolean = false,
) : AppBarBehavior {
    override fun handleScrollBy(
        appbar: AppBarState,
        scroll: ScrollableState?,
        delta: Float,
        source: NestedScrollSource
    ): Float {
        // APPBAR SCROLLING CONSTRAINTS

        if (!floating) {
            assert(scroll != null) {
                "[isAlwaysScrolling] 옵션이 활성화되었으나 올바른 동작이 수행될 수 있으려면 스크롤 상태가 필수적으로 필요합니다."
            }

            // for floating
            if (scroll?.canScrollBackward == true) {
                return 0f
            } else {
                // for drag only expanding
                if (dragOnlyExpanding
                    && source != NestedScrollSource.Drag
                    && appbar.shrinkedPercent() == 1f) {
                    return 0f
                }
            }
        }

        return appbar.setOffset(appbar.offset - delta)
    }
}