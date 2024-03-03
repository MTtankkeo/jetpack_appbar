# AppBar in jetpack compose
This package allows easy and light implementation of appbar in jetpack compose environment.

## How to make appbar?
Please refer to the code below.

```kotlin
AppBarConnection(
  appBars = {
    AppBar(behavior = MaterialAppBarBehavior()) {
      // ... child component
    }
  }
) {
  LazyColumn {
    // ... items
  }
}
```
