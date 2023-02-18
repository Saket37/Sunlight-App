package com.example.sunlightapp.data.clothing.impl

import com.example.sunlightapp.data.Result
import com.example.sunlightapp.model.Clothing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeClothingClothingRepository @Inject constructor() : ClothingRepository {
    override suspend fun getAllClothingData(): Result<List<Clothing>> {
        return withContext(Dispatchers.IO) {
            com.example.sunlightapp.data.Result.Success(clothingData)
        }
    }
}