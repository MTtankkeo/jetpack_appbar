# AppBar in jetpack compose
This package allows easy and light implementation of appbar in jetpack compose environment.

> Designed to implement flexible appbar behavior by utilizing only fundamental underlying behaviors and excluding unnecessary features.

## How to make appbar?
Please refer to the code below!

> See also, this code is the simplest example in this package.

```kotlin
AppBarConnection(
  appBars = {
    AppBar(behavior = MaterialAppBarBehavior()) {
      // ... child
    }
    SizedAppBar(minExtent = 50, maxExtent = 100) {
      // ... child
    }
  }
) {
  // Only scrollable component of vertical direction are available.
  // And, can wrapping to parent.
  LazyColumn {
    // ... items
  }
}
```

### How to apply effects according to appbar position?
Please refer to the code below!

> See also, this code is an example of implementing transparency effect.

#### A good example
This example is one of the ideal and simple or basic examples.

```kotlin
// Based on oneself.
AppBarConnection {
  AppBar {
    Box(modifier = Modifier.graphicsLayer {
      alpha = it.expandedPercent()
    }) {
      // ... child
    }
  }
}

// Based on others.
// See also, the second appbar depends on the state of the first appbar.
AppBarConnection {
  val first = rememberAppBarState()

  AppBar(state = other) {
    // ... skip
  }
  AppBar {
    Box(modifier = Modifier.graphicsLayer {
      alpha = first.shrinkedPercent()
    }) {
      // ... child
    }
  }
}
```

#### A bad example
This example is also one of the bad development habits that causes performance degradation.

```kotlin
AppBarConnection {
  AppBar {
    // This reduces performance, because re-composition when scrolled appbar.
    Box(modifier = Modifier.alpha(it.expandedPercent())) {
      // ... child
    }
  }
}
```
