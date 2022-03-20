package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student

class DetailViewModel(application: Application): AndroidViewModel(application) {
    private var TAG = "volleyTag"
    val studentLD = MutableLiveData<Student>()
    private var queue: RequestQueue?= null

    fun fetch(idStudent: Int) {
        var url = "http://adv.jitusolution.com/student.php?id=" + idStudent.toString()
        Log.d("showVolley", url)
        queue = Volley.newRequestQueue(getApplication())
        var stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                response ->
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<Student>(response, Student::class.java)
                studentLD.value = result
                Log.d("showVolley", response.toString())
                Log.d("showVolley", url)
            },
            {
                Log.d("showVolley", it.toString())
                Log.d("showVolley", url)
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }
}