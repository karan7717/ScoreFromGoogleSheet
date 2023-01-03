package com.example.scorefromgooglesheet


import com.google.gson.annotations.SerializedName

data class ScoreX(
    @SerializedName("blueScore")
    val blueScore: Int,
    @SerializedName("greenScore")
    val greenScore: Int,
    @SerializedName("id")
    val id: Int
)