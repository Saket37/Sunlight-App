package com.example.sunlightapp.data.sunscreen.impl

import com.example.sunlightapp.data.Result
import com.example.sunlightapp.model.SunScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FakeSunScreenRepository @Inject constructor() : SunScreenRepository {
    override suspend fun getAllSunScreenData(): Result<List<SunScreen>> {
        return withContext(Dispatchers.IO) {
            com.example.sunlightapp.data.Result.Success(sunScreenData)
        }
    }
}