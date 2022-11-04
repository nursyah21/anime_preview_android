package com.nursyah.composeunit3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Anime(
  @DrawableRes val imageResourceId: Int,
  @StringRes val titleResourceId: Int,
  @StringRes val linkResourceId: Int
)
