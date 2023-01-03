package com.example.scorefromgooglesheet


import com.google.gson.annotations.SerializedName

data class ScoreApiResponse(
    @SerializedName("score")
    val score: List<ScoreX>
)