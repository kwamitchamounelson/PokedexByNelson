
package com.nelson.pokedexbynelson.extensions

import androidx.appcompat.app.AppCompatActivity
import com.skydoves.transformationlayout.onTransformationEndContainer

fun AppCompatActivity.onTransformationEndContainerApplyParams() {
  onTransformationEndContainer(intent.getParcelableExtra("com.skydoves.transformationlayout"))
}
