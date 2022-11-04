package com.nursyah.composeunit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursyah.composeunit3.data.DataSource
import com.nursyah.composeunit3.model.Anime
import com.nursyah.composeunit3.ui.theme.ComposeUnit3Theme
import com.nursyah.composeunit3.ui.theme.Pink80

class GridActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeUnit3Theme {
        Surface(
          modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
          color = Color.White,
        ) {
          AnimeGridApp(
            animeList = DataSource().loadAllAnime()
          )
        }
      }
    }
  }
}

@Composable
fun AnimeGridApp(
  animeList: List<Anime>
){
  val modifier = Modifier.fillMaxWidth()
  var resize by remember {
    mutableStateOf(false)
  }
  var animeId by remember {
    mutableStateOf(DataSource().loadAllAnime()[0])
  }
  AnimeGridCard(
    anime = animeId,
    modifier = if(resize) modifier.alpha(1f) else modifier.alpha(0f)
  )
  Column {
    LazyVerticalGrid(
      horizontalArrangement = Arrangement.spacedBy(4.dp),
      verticalArrangement = Arrangement.spacedBy(4.dp),
      columns = GridCells.Fixed(2),
      modifier = if(resize) modifier.alpha(0f) else modifier.alpha(1f),
      userScrollEnabled = !resize
    ){
      items(animeList){
        AnimeGridCard(
          anime = it,
          onClick = {
            resize = !resize
            animeId = it
          },
          modifier = Modifier.size(68.dp)
        )
      }
    }
    Box(
      modifier = Modifier.clickable {
        if(resize) resize = false
      }.fillMaxSize(1f)
    ){}
  }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeGridCard(
  modifier: Modifier = Modifier,
  anime: Anime,
  onClick: () -> Unit = {},
){
  Card(
    onClick = onClick,
    elevation = CardDefaults.cardElevation(2.dp),
    colors = CardDefaults.cardColors(Color.White)
  ){
    Row{
      Box{
        Image(
          painter = painterResource(id = anime.imageResourceId),
          contentDescription = null,
          contentScale = ContentScale.Crop,
          modifier = modifier
        )
      }
      Column {
        Text(
          text = stringResource(id = anime.titleResourceId),
          color = Pink80,
          fontWeight = FontWeight.Bold,
          fontSize = 14.sp,
          maxLines = 1
        )
        Text(
          text = stringResource(id = anime.linkResourceId),
          color = Pink80,
          fontSize = 10.sp,
          maxLines = 1
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GridActivityPreview() {
  ComposeUnit3Theme {
    AnimeGridApp(animeList = DataSource().loadAllAnime())
  }
}