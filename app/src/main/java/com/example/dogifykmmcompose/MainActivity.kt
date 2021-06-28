package com.example.dogifykmmcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dogifykmmcompose.ui.theme.DogifyKmmComposeTheme
import com.example.shared.domain.DogBreed
import com.example.shared.responsemodel.DogServiceKtor
import com.google.accompanist.coil.rememberCoilPainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogifyKmmComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val viewModel: MainViewModel by viewModels { MainViewModel.Factory(DogServiceKtor()) }
                    val screenData: MainScreenData by viewModel.mainScreenData.observeAsState(
                        MainScreenData(
                            emptyList(),
                            true
                        )
                    )
                    if (screenData.showLoading) {
                        CircularProgressIndicator()
                    } else {
                        Log.d("MRF", screenData.dogBreeds.take(5).toString())
                        DogList(dogs = screenData.dogBreeds)
                    }
                }
            }
        }
    }
}

@Composable
fun DogList(dogs: List<DogBreed>) {
    LazyColumn {
        items(dogs) { dog ->
            DogRow(dog)
        }
    }
}

@Composable
fun DogRow(dog: DogBreed) {
    Row {
        Image(painter = rememberCoilPainter(request = dog.imageUrl, fadeIn =true), contentDescription = null, Modifier.size(100.dp))
        Text(dog.name, modifier= Modifier.align(CenterVertically))
    }
}

@Preview
@Composable
fun DogListPreview() {
    DogifyKmmComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            DogList(dogs = sampleData)
        }
    }
}

private val sampleData = listOf(
    DogBreed(name = "affenpinscher", imageUrl = "https://images.dog.ceo/breeds/affenpinscher/n02110627_8714.jpg"),
    DogBreed(name = "african", imageUrl = "https://images.dog.ceo/breeds/african/n02116738_9748.jpg"),
    DogBreed(name = "airedale", imageUrl = "https://images.dog.ceo/breeds/airedale/n02096051_6502.jpg"),
    DogBreed(name = "akita", imageUrl = "https://images.dog.ceo/breeds/akita/Japaneseakita.jpg"),
    DogBreed(name = "appenzeller", imageUrl = "https://images.dog.ceo/breeds/appenzeller/n02107908_2543.jpg")
)