package com.example.prakaypon.mykotlin

import android.app.Dialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.*
import okhttp3.ResponseBody
import retrofit2.Retrofit
import kotlin.math.max

class MainActivity : AppCompatActivity(){


    companion object callbackInterface:CallbackInterface {
        var size: Int?=null
        var date:String =""
        var status:String =""
        lateinit var alertDialog:AlertDialog.Builder
        lateinit var dialog: Dialog
        lateinit var textResult:TextView
        lateinit var data:ArrayList<myhouse>


        override fun onResponse(myhouses: ArrayList<myhouse>, retrofit: Retrofit) {
            Log.i("MainActivity","size "+myhouses.size);

            size = myhouses.size
            date = myhouses.get(0).date
            dialog.dismiss()
            textResult.text="size "+size.toString()+"\n"+"date "+date

        }

        override fun onBodyError(responseBodyError: ResponseBody?) {
            Log.i("MainActivity","onBodyError = "+responseBodyError);
        }

        override fun onBodyErrorIsNull() {
            Log.i("MainActivity","onBodyErrorIsNull");
        }

        override fun onFailure(t: Throwable?) {
            Log.i("MainActivity","onFailure = "+t);
        }

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById<TextView>(R.id.textView2)

        val restful = Restful(callbackInterface)
        alertDialog = AlertDialog.Builder(this@MainActivity,R.style.Theme_AppCompat_Dialog)
        alertDialog.setCancelable(false)
        alertDialog.setTitle("waiting...")
        dialog= alertDialog.create()
        dialog.show()




    }


}


