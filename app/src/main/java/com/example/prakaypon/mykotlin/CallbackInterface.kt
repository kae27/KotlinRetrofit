package com.example.prakaypon.mykotlin

import okhttp3.ResponseBody
import retrofit2.Retrofit

interface CallbackInterface{
    fun onResponse(myhouses: ArrayList<myhouse>, retrofit: Retrofit)
    fun onBodyError(responseBodyError: ResponseBody?)
    fun onBodyErrorIsNull()
    fun onFailure(t:Throwable?)
}