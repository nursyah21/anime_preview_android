package com.nursyah.composeunit3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.nursyah.composeunit3.ui.theme.ComposeUnit3Theme
import com.nursyah.composeunit3.ui.theme.Pink80

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeUnit3Theme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = Color.White
        ) {
          AnimeApp()
        }
      }
    }
  }
}

@Composable
fun AnimeApp(){
  val context = LocalContext.current
  val activity = context as Activity
  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    IconButton(
      onClick = {
        activity.startActivity(Intent(context, ListActivity::class.java))
      }
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_list),
        contentDescription = null,
        tint = Pink80
      )
    }
    IconButton(
      onClick = {
        activity.startActivity(Intent(context, GridActivity::class.java))
      }
    ) {
      Icon(
        painter = painterResource(id = R.drawable.ic_grid),
        contentDescription = null,
        tint = Pink80
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
  ComposeUnit3Theme {
    AnimeApp()
  }
}