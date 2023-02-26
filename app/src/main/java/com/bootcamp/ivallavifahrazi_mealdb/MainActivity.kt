package com.bootcamp.ivallavifahrazi_mealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.ivallavifahrazi_mealdb.adapter.MealsAdapter
import com.bootcamp.ivallavifahrazi_mealdb.api.ApiConfig
import com.bootcamp.ivallavifahrazi_mealdb.databinding.ActivityMainBinding
import com.bootcamp.ivallavifahrazi_mealdb.model.MealsItem
import com.bootcamp.ivallavifahrazi_mealdb.model.ResponseMeals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Ivallavi Fahrazi"

        ApiConfig.getService().getMenu().enqueue(object : Callback<ResponseMeals>{
            override fun onResponse(call: Call<ResponseMeals>, response: Response<ResponseMeals>) {
                Log.d("RESPONSE", response.isSuccessful.toString())
                if(response.isSuccessful){
                    val responseMeals = response.body()
                    val dataMeals = responseMeals?.meals
                    val mealsAdapter = MealsAdapter()
                    mealsAdapter.setData(dataMeals as List<MealsItem>)
                    binding.rvMenu.apply {
                        layoutManager = GridLayoutManager(this@MainActivity, 2)
                        setHasFixedSize(true)
                        adapter = mealsAdapter
                    }
                    binding.recipeSize.text = dataMeals.size.toString() + " Recipes found"
                }
            }

            override fun onFailure(call: Call<ResponseMeals>, t: Throwable) {
                Log.d("gagal", "onFailure: " + t.localizedMessage)
            }

        })
    }
}