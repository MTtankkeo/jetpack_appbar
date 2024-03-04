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
  // Only scrollable component of vertical diraction are available.
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
AppBarConnection {
  val other = rememberAppBarState()

  AppBar(state = other) {
    // ... skip
  }
  AppBar {
    Box(modifier = Modifier.graphicsLayer {
      alpha = other.expandedPercent()
    }) {
      // ... child
    }
  }
}
```

#### A bad example
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
