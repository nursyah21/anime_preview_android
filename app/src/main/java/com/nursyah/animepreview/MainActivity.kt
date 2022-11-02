package com.nursyah.animepreview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursyah.animepreview.ui.theme.AnimePreviewTheme
import com.nursyah.animepreview.ui.theme.Pink80

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AnimePreviewTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background
        ) {
          App()
        }
      }
    }
  }
}

@Composable
fun App(){
  var currentContent by remember {
    mutableStateOf(0)
  }
  var imageId by remember {
    mutableStateOf(R.drawable.cute_girl_after_school)
  }
  var titleId by remember {
    mutableStateOf(R.string.cute_girl_after_school_title)
  }
  var linkId by remember {
    mutableStateOf(R.string.cute_girl_after_school_link)
  }

  when(currentContent){
    0 -> {
      imageId = R.drawable.cute_girl_after_school
      titleId = R.string.cute_girl_after_school_title
      linkId = R.string.cute_girl_after_school_link
    }
    1 -> {
      imageId = R.drawable.school_uniform
      titleId = R.string.school_uniform_title
      linkId = R.string.school_uniform_link
    }
    2 -> {
      imageId = R.drawable.cute_and_beautiful_girl_uniform
      titleId = R.string.cute_and_beautiful_girl_title
      linkId = R.string.cute_and_beautiful_girl_link
    }
    3 -> {
      imageId = R.drawable.summer_girl
      titleId = R.string.summer_girl_title
      linkId = R.string.summer_girl_link
    }
    4 -> {
      imageId = R.drawable.lovely_girl
      titleId = R.string.lovely_girl_title
      linkId = R.string.lovely_girl_link
    }
  }
  ImageAndText(
    image = imageId,
    title = titleId,
    link = linkId
  )
  ButtonLeftRight(
    left = {
      if(currentContent == 0) currentContent = 4
      else currentContent--
    },
    right = {
      if(currentContent == 4) currentContent = 0
      else currentContent++
    }
  )
}

@Composable
fun ButtonLeftRight(
  left: () -> Unit,
  right: () -> Unit
){
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    IconButton(
      modifier = Modifier
        .background(Color(0x77FFFFFF)),
      onClick = left
    ){
      Icon(
        painter = painterResource(id = R.drawable.ic_left),
        contentDescription = null,
        tint = Pink80
      )
    }
    IconButton(
      modifier = Modifier
        .background(Color(0x77FFFFFF)),
      onClick = right
    ){
      Icon(
        painter = painterResource(id = R.drawable.ic_right),
        contentDescription = null,
        tint = Pink80
      )
    }
  }
}

@Composable
fun ImageAndText(
  image: Int,
  title: Int,
  link: Int
){
  Image(
    painter = painterResource(id = image),
    contentDescription = null,
    modifier = Modifier.fillMaxSize(),
    contentScale = ContentScale.FillWidth
  )
  Column(
    verticalArrangement = Arrangement.Bottom,
    modifier = Modifier
      .padding(8.dp)
  ) {
    Column(
      modifier = Modifier
        .background(Color(0x77FFFFFF))
        .fillMaxWidth()
        .padding(4.dp)
    ){
      Text(
        text = stringResource(id = title),
        color = Pink80,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
      )
      Text(
        text = stringResource(id = link),
        color = Pink80,
        fontSize = 14.sp
      )
    }
  }
}


@Preview(
  showBackground = true,
  showSystemUi = true
)
@Composable
fun DefaultPreview() {
  AnimePreviewTheme {
    App()
  }
}