package com.pdm0126.repaso2.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.repaso2.models.Post

@Composable
fun PostCard(post: Post) {
  Card(
    modifier = Modifier.fillMaxWidth(),
    shape = CardDefaults.elevatedShape,
    elevation = CardDefaults.cardElevation(8.dp)
  ) {
    Column(modifier = Modifier.padding(8.dp)) {
      Text("Post id: ${post.id}, User id: ${post.userId}", fontSize = 8.sp)
      Text(post.title, fontWeight = FontWeight.ExtraBold)
      Text(
        text = post.body,
        maxLines = 4,
        fontSize = 12.sp,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.ExtraLight,
        lineHeight = 16.sp
      )
    }
  }
}