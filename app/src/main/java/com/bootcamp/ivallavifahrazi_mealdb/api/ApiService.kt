package com.bootcamp.ivallavifahrazi_mealdb.api

import com.bootcamp.ivallavifahrazi_mealdb.model.DetailMealsItem
import com.bootcamp.ivallavifahrazi_mealdb.model.ResponseDetailMeals
import com.bootcamp.ivallavifahrazi_mealdb.model.ResponseMeals
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php?c=Seafood")
    fun getMenu(): Call<ResponseMeals>

    @GET("lookup.php")
    fun getMenuDetail(
        @Query("i") idMeal: String?
    ): Call<ResponseDetailMeals>

}
