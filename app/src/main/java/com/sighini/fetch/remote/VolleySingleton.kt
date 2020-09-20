package com.sighini.fetch.remote

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class VolleySingleton(context: Context)  {

    companion object {
        private var instance: VolleySingleton? = null
        fun getInstance(context: Context) =
            instance ?: VolleySingleton(context).also {
                instance = it
            }
    }
    val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    fun addToRequestQueue(request: JsonArrayRequest){
        requestQueue?.add(request)
    }
}