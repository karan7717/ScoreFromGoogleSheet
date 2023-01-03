package com.example.scorefromgooglesheet

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface ScoreApi {
   // https://script.google.com/macros/library/d/1YxokX6QGEb_Grc7pHY4OM9HSuOk5YZgrvMxkl5uLXAFY--_KFaW5ZJsH/1

    @POST("macros/s/AKfycbyn26PO9FC_7HApoqHzLKS3Xgdk7726L70Scf6WD-qKxvCLcS4osGki4PLBRcnaPsDJ/exec")
    @FormUrlEncoded
   fun sendScore(
       @Field("action") action: String,
       @Field("id") id : Int,
        @Field("greenScore") greenScore : String,
        @Field("blueScore") blueScore: String
    ): Call<Score>

@POST("macros/s/AKfycbyn26PO9FC_7HApoqHzLKS3Xgdk7726L70Scf6WD-qKxvCLcS4osGki4PLBRcnaPsDJ/exec")
@FormUrlEncoded
fun updateScore(
    @Field("action")action:String,
    @Field("id") id : Int,
    @Field("greenScore") greenScore : String,
    @Field("blueScore") blueScore: String
): Call<Score>

    @POST("macros/s/AKfycbyn26PO9FC_7HApoqHzLKS3Xgdk7726L70Scf6WD-qKxvCLcS4osGki4PLBRcnaPsDJ/exec")
    @FormUrlEncoded
    fun deleteScore(
        @Field("action")action:String,
        @Field("id") id : Int,
        @Field("greenScore") greenScore : String,
        @Field("blueScore") blueScore: String
    ): Call<Score>

  @GET("macros/s/AKfycbyn26PO9FC_7HApoqHzLKS3Xgdk7726L70Scf6WD-qKxvCLcS4osGki4PLBRcnaPsDJ/exec")
 suspend fun listScore() : Response<ScoreApiResponse>
}
