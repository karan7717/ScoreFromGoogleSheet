package com.example.scorefromgooglesheet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper{
    val baseUrl = "https://script.google.com/"
    fun getInstance() : Retrofit{
      return Retrofit.Builder().baseUrl(baseUrl)
          .addConverterFactory(GsonConverterFactory.create()).build()
    }
}
