package com.bootcamp.ivallavifahrazi_mealdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bootcamp.ivallavifahrazi_mealdb.api.ApiConfig
import com.bootcamp.ivallavifahrazi_mealdb.databinding.ActivityDetailMealsBinding
import com.bootcamp.ivallavifahrazi_mealdb.model.DetailMealsItem
import com.bootcamp.ivallavifahrazi_mealdb.model.MealsItem
import com.bootcamp.ivallavifahrazi_mealdb.model.ResponseDetailMeals
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailMealsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailMealsBinding

    companion object{
        const val EXTRA_MEALS = "extra_meals"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMealsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val dataMeals = intent.getParcelableExtra<MealsItem>(EXTRA_MEALS)
        val idMeals = dataMeals?.idMeal.toString()

        supportActionBar?.title = "Details"
        ApiConfig.getService().getMenuDetail(idMeals).enqueue(object : Callback<ResponseDetailMeals>{
            override fun onResponse(
                call: Call<ResponseDetailMeals>,
                response: Response<ResponseDetailMeals>
            ) {
                Log.d("RESPONSE", response.isSuccessful.toString())
                if(response.isSuccessful){
                    val responseDetail = response.body()
                    val detailMeals = responseDetail?.meals?.get(0)
                    binding.apply {
                        tvJudul.text = detailMeals?.strMeal
                        tvArea.text = detailMeals?.strArea
                        tvInstruction.text = detailMeals?.strInstructions

                        Glide.with(this@DetailMealsActivity)
                            .load(detailMeals?.strMealThumb)
                            .error(R.drawable.ic_launcher_background)
                            .circleCrop()
                            .into(imgMeals)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDetailMeals>, t: Throwable) {

            }
        })


    }
}