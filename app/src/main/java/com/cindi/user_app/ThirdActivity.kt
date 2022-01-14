package com.cindi.user_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.ListView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cindi.user_app.api.Retrofit
import com.cindi.user_app.api.UserApi
import com.cindi.user_app.data.user_data
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThirdActivity : AppCompatActivity() {
    lateinit var adapter: ItemAdapter
    lateinit var back : ImageView
    lateinit var swipeContainer : SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        val listViewId = findViewById<ListView>(R.id.item_list)

        //back to second screen
        back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            finish()
        }

        //swipe
        iniRefreshListener()
        //next page
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {

            Retrofit.api.getSelectedNext().enqueue(object : Callback<user_data> {
                override fun onResponse(call: Call<user_data>, response: Response<user_data>) {
                    val responseBody = response.body()
                    val data = responseBody!!.data
                    println(data)
                    adapter = data?.let { ItemAdapter(nameList = it, this@ThirdActivity) }!!
                    listViewId.adapter = adapter

                    //item clickeable
                    listViewId.isClickable = true
                    listViewId.setOnItemClickListener{parent, view, position, id ->
                        val full_name = intent.getStringExtra("full_name").toString()
                        val choose_name = data[position].firstName + " " + data[position].lastName
                        val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                        intent.putExtra("choose_name", choose_name)
                        intent.putExtra("full_name", full_name)
                        startActivity(intent)
                    }
                }
                override fun onFailure(call: Call<user_data>, t: Throwable) {
                    println(t.toString())
                }
            })

        }
        //data page 2

        //call data
        Retrofit.api.getSelectedName().enqueue(object : Callback<user_data> {
            override fun onResponse(call: Call<user_data>, response: Response<user_data>) {
                val responseBody = response.body()
                val data = responseBody!!.data
                println(data)
                adapter = data?.let { ItemAdapter(nameList = it, this@ThirdActivity) }!!
                listViewId.adapter = adapter

                //item clickeable
                listViewId.isClickable = true
                listViewId.setOnItemClickListener{parent, view, position, id ->
                    val full_name = intent.getStringExtra("full_name").toString()
                    val choose_name = data[position].firstName + " " + data[position].lastName
                    val intent = Intent(this@ThirdActivity, SecondActivity::class.java)
                    intent.putExtra("choose_name", choose_name)
                    intent.putExtra("full_name", full_name)
                    startActivity(intent)
                }
            }
            override fun onFailure(call: Call<user_data>, t: Throwable) {
                println(t.toString())
            }
        })
    }

    private fun iniRefreshListener() {
        val swipeRefreshLayout=findViewById<SwipeRefreshLayout>(R.id.swipeContainer)
        swipeRefreshLayout.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener { // This method gets called when user pull for refresh,
            // You can make your API call here,
            val handler = Handler()
            handler.postDelayed(Runnable {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false)
                }
            }, 3000)
        })
    }
}