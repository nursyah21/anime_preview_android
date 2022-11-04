package com.nursyah.composeunit3.data

import com.nursyah.composeunit3.R
import com.nursyah.composeunit3.model.Anime

class DataSource {
  fun loadAllAnime(): List<Anime>{
    return listOf(
      Anime(
        R.drawable.cute_girl_after_school,
        R.string.cute_girl_after_school_title,
        R.string.cute_girl_after_school_link
      ),
      Anime(
        R.drawable.cute_and_beautiful_girl_uniform,
        R.string.cute_and_beautiful_girl_uniform_title,
        R.string.cute_and_beautiful_girl_uniform_link
      ),
      Anime(
        R.drawable.summer_girl,
        R.string.summer_girl_title,
        R.string.summer_girl_link
      ),
      Anime(
        R.drawable.lovely_girl,
        R.string.lovely_girl_title,
        R.string.lovely_girl_link
      )
    )
  }
}