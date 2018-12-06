package com.example.prakaypon.mykotlin


import com.google.gson.annotations.SerializedName
import java.io.StringReader


class Result(@SerializedName("feeds") val data :ArrayList<myhouse>)


data class myhouse(   @SerializedName("field1")
                      var temperature: Double = 0.0,
                      @SerializedName("field2")
                      var light: Double = 0.0,
                      @SerializedName("created_at")
                      var date :String ="")

