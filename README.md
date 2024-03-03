# AppBar in jetpack compose
This package allows easy and light implementation of appbar in jetpack compose environment.

## How to make appbar?
Please refer to the code below!

> See also, this code is the simplest example in this package.

```kotlin
AppBarConnection(
  appBars = {
    AppBar(behavior = MaterialAppBarBehavior()) {
      // ... child component
    }
  }
) {
  // Only scrollable component of vertical diraction are available.
  LazyColumn {
    // ... items
  }
}
```
