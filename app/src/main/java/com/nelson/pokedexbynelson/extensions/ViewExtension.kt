
package com.nelson.pokedexbynelson.extensions

import android.view.View

fun View.gone(shouldBeGone: Boolean) {
  visibility = if (shouldBeGone) {
    View.GONE
  } else {
    View.VISIBLE
  }
}
