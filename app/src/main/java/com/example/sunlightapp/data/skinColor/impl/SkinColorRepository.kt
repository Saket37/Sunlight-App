package com.example.sunlightapp.data.skinColor.impl

import com.example.sunlightapp.model.SkinColor

interface SkinColorRepository {
    suspend fun getAllSkinColorData(): com.example.sunlightapp.data.Result<List<SkinColor>>
}