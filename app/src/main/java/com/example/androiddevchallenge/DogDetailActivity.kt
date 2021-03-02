package com.example.androiddevchallenge

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.model.DogBrief
import com.example.androiddevchallenge.model.getString
import dev.chrisbanes.accompanist.glide.GlideImage

class DogDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dog: DogBrief = intent.getSerializableExtra("dog") as DogBrief

        title = dog.name

        setContent {
            Column(Modifier.padding(10.dp)) {
                GlideImage(
                    data = dog.photo,
                    contentDescription = "",
                    modifier = Modifier.size(350.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(20.dp))

                Row() {
                    Column {
                        Text("Name: ${dog.name}")
                        Text("Breed: ${dog.breed}")
                        Text("Age: ${dog.age} month")
                        Text("Gender: ${dog.gender.getString()}")
                    }

                    Spacer(Modifier.width(60.dp))

                    Button(onClick = { Toast.makeText(applicationContext, "You've adopted ${dog.name}!!", Toast.LENGTH_SHORT)}) {
                        Text("Adopt ${dog.name}")
                    }
                }

                Spacer(Modifier.height(20.dp))
                
                Text(text = "Introduce of ${dog.name}:")
                Text(text = dog.desc)
                
            }
        }
    }
}