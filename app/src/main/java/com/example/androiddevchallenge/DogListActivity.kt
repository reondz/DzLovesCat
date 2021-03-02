/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.model.DogBrief
import com.example.androiddevchallenge.model.DogBriefListProvider
import com.example.androiddevchallenge.model.getString
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp(this)
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp(context: Context) {
    Surface(color = MaterialTheme.colors.background) {
        DogBriefList(context)
    }
}

@Composable
fun DogBriefList(context: Context) {
    Column(modifier = Modifier.background(color = Color.White)) {
        Text(
            text = "Dogs Adoption",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp),
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        )
        LazyColumn(
            Modifier.padding(10.dp),
            content = {
                items(DogBriefListProvider.getDogBriefList()) { dog ->
                    DogItem(dog, context)
                }
            }

        )
    }
}

@Composable
fun DogItem(dogBrief: DogBrief, context: Context) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            val intent = Intent(context, DogDetailActivity::class.java)
            intent.putExtra("dog", dogBrief)
            context.startActivity(intent)
        }
    ) {
        GlideImage(
            data = dogBrief.photo,
            contentDescription = "",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.width(40.dp))

        Column {
            Text("Name: ${dogBrief.name}")
            Text("Breed: ${dogBrief.breed}")
            Text("Age: ${dogBrief.age} month")
            Text("Gender: ${dogBrief.gender.getString()}")
        }
    }
    Spacer(Modifier.height(24.dp))
}