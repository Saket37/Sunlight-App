package com.example.sunlightapp.data

import android.content.Context
import com.example.sunlightapp.data.clothing.impl.FakeClothingClothingRepository
import com.example.sunlightapp.data.clothing.impl.ClothingRepository

interface AppContainer {
    val repository: ClothingRepository
}

class AppContainerImpl(private val applicationContext: Context) : AppContainer {
    override val repository: ClothingRepository by lazy { FakeClothingClothingRepository() }
}