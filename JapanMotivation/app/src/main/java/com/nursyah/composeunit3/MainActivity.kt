@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.nursyah.composeunit3

import android.content.ClipData
import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nursyah.composeunit3.ui.theme.ComposeUnit3Theme
import com.nursyah.composeunit3.ui.theme.Shapes

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeUnit3Theme {
       App()
      }
    }
  }
}

@Composable
fun TopBar() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.primary)
      .padding(8.dp)
  ){
    Text(
      text = stringResource(id = R.string.app_name)
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(){
  val quoteArr = stringArrayResource(id = R.array.quote)
  val quoteRomajiArr = stringArrayResource(id = R.array.quote_romaji)
  val quoteTranslateArr = stringArrayResource(id = R.array.quote_translate)

  Scaffold(
    topBar = { TopBar() },
  ) {
    LazyColumn(
      contentPadding = it,
      verticalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier
        .padding(horizontal = 4.dp)
    ) {
      items(quoteArr.size) { i ->
        if(i == 0) Spacer(modifier = Modifier.height(8.dp))
        CardMotivation(
          number = (i + 1).toString(),
          motivation = quoteArr[i],
          motivationRomaji = quoteRomajiArr[i],
          motivationTranslate = quoteTranslateArr[i]
        )
      }
    }
  }
}

@Composable
fun CardMotivation(
  number: String,
  motivation: String,
  motivationRomaji: String,
  motivationTranslate: String
){
  var state by remember {
    mutableStateOf(false)
  }
  val modifier = Modifier
    .fillMaxWidth()
    .animateContentSize(
      animationSpec = spring(
        dampingRatio = Spring.DampingRatioLowBouncy,
        stiffness = Spring.StiffnessLow
      )
    )

  val modifierInfo = if(state) modifier else modifier.height(0.dp)
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(0.dp))
      .clickable { state = !state },
    elevation = CardDefaults.elevatedCardElevation(2.dp)
  ){
    Row(
      modifier = Modifier.padding(4.dp),
      horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
      Text(
        text = number,
        modifier = Modifier
          .background(
            color = MaterialTheme.colorScheme.background,
            shape = Shapes.medium
          )
          .size(20.dp),
        textAlign = TextAlign.Center
      )
      Column{
        Text(
          text = motivation
        )
        MoreInformation(
          motivation = motivation,
          motivationRomaji = motivationRomaji,
          motivationTranslate = motivationTranslate,
          modifier = modifierInfo
        )
      }
    }
  }
}

@Composable
fun MoreInformation(
  motivation: String,
  motivationRomaji: String,
  motivationTranslate: String,
  modifier: Modifier = Modifier
){
  val context = LocalContext.current
  val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager

  Column(
    modifier = modifier
  ) {
    Text(
      text = motivationRomaji,
      fontSize = 12.sp,
      lineHeight = 13.sp
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
      text = motivationTranslate,
      fontSize = 12.sp,
      lineHeight = 13.sp
    )
    Button(
      contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
      onClick = {
        val clip = ClipData.newPlainText("motivation", "$motivation\n$motivationRomaji\n$motivationTranslate")
        clipboard.setPrimaryClip(clip)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
          Toast
            .makeText(context, "copy to clipboard", Toast.LENGTH_SHORT)
            .show()
      },
      modifier = Modifier.align(Alignment.End)
    ){
      Text(stringResource(R.string.copy))
    }
  }
}

@Preview(
  showBackground = true,
  showSystemUi = true,
)
@Preview(
  showBackground = true,
  showSystemUi = true,
  uiMode = UI_MODE_NIGHT_YES,
  name = "night"
)
@Composable
fun DefaultPreview() {
  ComposeUnit3Theme {
    App()
  }
}