package com.example.sunlightapp.data.skinColor.impl

import com.example.sunlightapp.data.Result
import com.example.sunlightapp.model.SkinColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeSkinColorRepository @Inject constructor() : SkinColorRepository {
    override suspend fun getAllSkinColorData(): Result<List<SkinColor>> {
        return withContext(Dispatchers.IO) {
            com.example.sunlightapp.data.Result.Success(
                skinColorData
            )
        }
    }
}