package com.sighini.fetch

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.google.gson.Gson
import com.sighini.fetch.util.VolleySingleton
import org.json.JSONArray

class ModelLayer(context: Context) {

    private val data = MutableLiveData<List<ModelData>>()

    init {
        VolleySingleton.getInstance(context).addToRequestQueue(getJsonRequest())
    }
    companion object {
        const val SERVICE_URL = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
        private var instance: ModelLayer? = null
        fun getInstance(context: Context) =
            instance ?: ModelLayer(context).also {
                instance = it
            }
    }

    fun getData() = data


    private fun getJsonRequest(): JsonArrayRequest {
        val request = JsonArrayRequest(SERVICE_URL,
            Response.Listener { response ->
                parseResponse(response)
            },
            Response.ErrorListener { error ->
                Log.d("LOG", error.message?: "NULL ERROR")
            })
        return request
    }

    private fun parseResponse(response: JSONArray?) {
        val list = Gson().fromJson(response.toString() , Array<ModelData>::class.java).toList()
        data.value = list.filter{!it.name.isNullOrEmpty()}?.sortedWith(
               compareBy({ it.listId }, { it.name})
           )
    }
}