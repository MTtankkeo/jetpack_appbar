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
      // ... child component
    }
    SizedAppBar(minExtent = 50, maxExtent = 100) {
      // ... child component
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
