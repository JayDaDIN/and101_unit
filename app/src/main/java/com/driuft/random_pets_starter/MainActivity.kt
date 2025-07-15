package com.driuft.random_pets_starter
//import androidx.recyclerview.widget.DividerItemDecoration
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    private lateinit var petList: MutableList<String>
    private lateinit var rvPets: RecyclerView
    private lateinit var petAdapter: PetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPets = findViewById(R.id.pet_list)
        petList = mutableListOf()
        petAdapter = PetAdapter(petList)
        rvPets.adapter = petAdapter
        rvPets.layoutManager = LinearLayoutManager(this)
        getDogImageURL()
    }

    private fun getDogImageURL() {
        val client = AsyncHttpClient()

        client["https://dog.ceo/api/breeds/image/random/20", object : JsonHttpResponseHandler() {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Dog Success", "$json")
                val petImageArray = json.jsonObject.getJSONArray("message")
                for (i in 0 until petImageArray.length()) {
                    petList.add(petImageArray.getString(i))
                }
                petAdapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("Dog Error", errorResponse)
            }
        }]
    }
}