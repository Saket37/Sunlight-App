package com.example.sunlightapp.data.sunscreen.impl

import com.example.sunlightapp.model.SunScreen

interface SunScreenRepository {
    suspend fun getAllSunScreenData(): com.example.sunlightapp.data.Result<List<SunScreen>>
}