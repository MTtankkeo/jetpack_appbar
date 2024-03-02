package com.ttangkong.jetpack_appbar.components

import com.ttangkong.jetpack_appbar.AppBarBehavior
import com.ttangkong.jetpack_appbar.AppBarState
import com.ttangkong.jetpack_appbar.MaterialAppBarBehavior
import com.ttangkong.jetpack_appbar.SliverController
import com.ttangkong.jetpack_appbar.rememberAppBarState
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints

enum class AppBarAlignment {
    Scroll,
    Center,
    Absolute,
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    alignment: AppBarAlignment = AppBarAlignment.Scroll,
    behavior: AppBarBehavior = MaterialAppBarBehavior(),
    state: AppBarState = rememberAppBarState(),
    content: @Composable (AppBarState) -> Unit,
) {
    val controller = SliverController.Provider.current

    // When a component is entered the composition, attaches a sliver state form a controller.
    LaunchedEffect(key1 = "Attach state to controller") {
        controller.attach(state, behavior)
    }

    // When a component is leave the composition, detaches a sliver state from a controller.
    DisposableEffect(key1 = "Detach state to controller") {
        onDispose { controller.detach(state) }
    }

    Box(
        modifier = modifier
            .clipToBounds()
            .layout() { measurable, constraints ->
                // The sliver component should not always consider the height of
                // the parent component calculations.
                val placeable = measurable.measure(
                    constraints.copy(maxHeight = Constraints.Infinity)
                )

                // The max-extent of the sliver is equal to the calculated height.
                state.maxExtent = placeable.height

                layout(constraints.maxWidth, placeable.height - state.offset.toInt()) {
                    // 최종적으로 슬라이버 요소를 인자에 해당하는 적절한 위치로 배치합니다.
                    placeable.placeRelative(
                        x = 0,
                        y = (when (alignment) {
                            AppBarAlignment.Scroll -> -state.offset
                            AppBarAlignment.Center -> -state.offset / 2
                            AppBarAlignment.Absolute -> 0f
                        }).toInt()
                    )
                }
            }
    ) {
        content(state)
    }
}