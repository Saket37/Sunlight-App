package com.example.sunlightapp.data.clothing.impl

import com.example.sunlightapp.model.Clothing

interface ClothingRepository {
    suspend fun getAllClothingData(): com.example.sunlightapp.data.Result<List<Clothing>>
}