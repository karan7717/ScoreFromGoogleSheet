package com.example.scorefromgooglesheet

import android.app.DownloadManager.Request
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.scorefromgooglesheet.ui.theme.ScoreFromGoogleSheetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScoreFromGoogleSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val retrofit = RetrofitHelper.getInstance()
                    val scoreApi = retrofit.create(ScoreApi::class.java)
                    Greeting(scoreApi)
                }
            }
        }
    }
}

@Composable
fun Greeting(scoreApi: ScoreApi) {
    var id by remember { mutableStateOf("") }
    var greenScore by remember { mutableStateOf("") }
    var blueScore by remember { mutableStateOf("") }

    Column() {

        TextField(
            value = greenScore,
            onValueChange = { greenScore = it },
            label = { Text("Green Score") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        TextField(
            value = blueScore,
            onValueChange = { blueScore = it },
            label = { Text("Blue Score") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Button(
                enabled = if (greenScore.isNotEmpty() && blueScore.isNotEmpty()) true else false,
                onClick = {
                    val score = Score(
                        action = "insert",
                        id = 1,
                        greenScore = greenScore,
                        blueScore = blueScore
                    )

                    val call =
                        scoreApi.sendScore(
                            score.action,
                            score.id,
                            score.greenScore,
                            score.blueScore
                        )
                    call.enqueue(object : Callback<Score> {
                        override fun onResponse(
                            call: Call<Score>,
                            response: retrofit2.Response<Score>
                        ) {
                            println(response.message())
                        }

                        override fun onFailure(call: Call<Score>, t: Throwable) {
                            println(t.message)
                        }

                    })
                    greenScore = ""
                    blueScore = ""
                }) {
                Text(
                    text = "Submit Score",
                    Modifier.fillMaxWidth()

                )

            }

        }
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Button(onClick = {
                val score =
                    Score(
                        action = "update",
                        id = 16,
                        greenScore = greenScore,
                        blueScore = blueScore
                    )

                val call =
                    scoreApi.updateScore(score.action, score.id, score.greenScore, score.blueScore)
                call.enqueue(object : Callback<Score> {
                    override fun onResponse(
                        call: Call<Score>,
                        response: retrofit2.Response<Score>
                    ) {
                        println(response.code())
                    }

                    override fun onFailure(call: Call<Score>, t: Throwable) {
                        println("Failed")
                    }

                })
                greenScore = ""
                blueScore = ""
            }) {
                Text(
                    text = "Update Score",
                    Modifier.fillMaxWidth()

                )

            }

        }
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {

            Button(onClick = {

                CoroutineScope(Dispatchers.IO).launch {

                    val response = scoreApi.listScore()
            if(response != null){
                println(response.body().toString())
            }
                    // process response...

                }
            })
            {
                Text(
                    text = "Get Score",
                    Modifier.fillMaxWidth()

                )
            }
        }

    Row(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {

        Button(onClick = {
            val score =
                Score(
                    action = "delete",
                    id = 16,
                    greenScore = greenScore,
                    blueScore = blueScore
                )
            val call =
                scoreApi.deleteScore(score.action, score.id, score.greenScore, score.blueScore)
            call.enqueue(object : Callback<Score> {
                override fun onResponse(
                    call: Call<Score>,
                    response: retrofit2.Response<Score>
                ) {
                    println(response.code())
                }

                override fun onFailure(call: Call<Score>, t: Throwable) {
                    println("Failed")
                }

                // process response...

            })

        })
        {
            Text(
                text = "Delete Score",
                Modifier.fillMaxWidth()

            )
        }
    }
}
}


//
//
//
//
//           })
//
//           }

 //      }










//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ScoreFromGoogleSheetTheme {
//        Greeting()
//    }
//}