package com.nursyah.composeunit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nursyah.composeunit3.data.DataSource
import com.nursyah.composeunit3.model.Anime
import com.nursyah.composeunit3.ui.theme.ComposeUnit3Theme
import com.nursyah.composeunit3.ui.theme.Pink80

class ListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeUnit3Theme {

        Surface(
          modifier = Modifier.fillMaxSize(),
          color = Color.White,
        ) {
            AnimeListApp(
              animeList = DataSource().loadAllAnime()
            )
        }
      }
    }
  }
}


@Composable
fun AnimeListApp(
  animeList: List<Anime>,
) {
  LazyColumn(
    verticalArrangement = Arrangement.spacedBy(4.dp)
  ){
    items(animeList){
      AnimeCard(
        anime = it
      )
    }
  }
}

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnimeCard(
  anime: Anime,
){
  var resize by remember {
    mutableStateOf(false)
  }

  var modifierDefault = Modifier
    .fillMaxWidth()
    .height(200.dp)
  if (resize) modifierDefault = Modifier
    .fillMaxWidth()
  Card(
    onClick = { resize = !resize },
    modifier = Modifier
      .fillMaxWidth()
      .padding(4.dp),
    colors = CardDefaults.cardColors(
      containerColor = Pink80
    ),
    elevation = CardDefaults.cardElevation(4.dp)
  ) {
    Image(
      modifier = modifierDefault,
      painter = painterResource(id = anime.imageResourceId),
      contentDescription = null,
      contentScale = ContentScale.FillWidth
    )
    Text(
      text = stringResource(id = anime.titleResourceId),
      color = Color.White,
      style = MaterialTheme.typography.bodyLarge,
      modifier = Modifier.padding(horizontal = 4.dp)
    )
    Text(
      text = stringResource(id = anime.linkResourceId),
      color = Color.White,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(horizontal = 4.dp)
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ListActivityPreview() {
  ComposeUnit3Theme {
    AnimeListApp(animeList = DataSource().loadAllAnime())
  }
}