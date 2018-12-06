package com.example.prakaypon.mykotlin

import android.telecom.CallScreeningService
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class Restful
{
    var temp: ArrayList<myhouse>? = null
    var result: Result? = null
    val retrofit = Retrofit.Builder()
            .baseUrl("https://thingspeak.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val houseapi:HouseAPi = retrofit.create(HouseAPi::class.java)


    constructor(callbackListener: CallbackInterface?)
    {

        val call = houseapi.getData(9)
        val response = call.enqueue(object :Callback<Result>
        {
            override fun onFailure(call: Call<Result>?, t: Throwable?) {
                callbackListener!!.onFailure(t)
            }

            override fun onResponse(call: Call<Result>?, response: Response<Result>?) {
                var statusCode = response!!.code()
                result = response.body()
                Log.i("statusCode",statusCode.toString());
                if (result==null)
                {
                    var responseBody:ResponseBody = response.errorBody()
                    if (responseBody!= null)
                    {
                        callbackListener?.onBodyError(responseBody)

                    }else
                    {
                        callbackListener!!.onBodyErrorIsNull()
                    }
                }else
                {
                    temp = result!!.data
                    callbackListener!!.onResponse(temp!!,retrofit)
                }

            }
        })
    }

}

interface HouseAPi
{
    @GET("channels/{id}/feed.json")
    fun getData (@Path("id") id:Int)
    : Call<Result>
}