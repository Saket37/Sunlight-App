package com.example.sunlightapp.di

import android.content.Context
import com.example.sunlightapp.data.clothing.impl.ClothingRepository
import com.example.sunlightapp.data.clothing.impl.FakeClothingClothingRepository
import com.example.sunlightapp.data.skinColor.impl.FakeSkinColorRepository
import com.example.sunlightapp.data.skinColor.impl.SkinColorRepository
import com.example.sunlightapp.data.sunscreen.impl.FakeSunScreenRepository
import com.example.sunlightapp.data.sunscreen.impl.SunScreenRepository
import com.example.sunlightapp.datastore.StoreAppData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesClothingRepository(): ClothingRepository {
        return FakeClothingClothingRepository()
    }

    @Singleton
    @Provides
    fun providesSkinColorRepository(): SkinColorRepository {
        return FakeSkinColorRepository()
    }

    @Singleton
    @Provides
    fun providesSunScreenRepository(): SunScreenRepository {
        return FakeSunScreenRepository()
    }

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context): StoreAppData {
        return StoreAppData(context)
    }
}