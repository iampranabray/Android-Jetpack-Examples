package com.djupbyte.dependency_injection_hilt.hilt.counter_example.di

import com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface.Counter
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.counterinterface.CounterInterface

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideMyDependency(): CounterInterface {
        return Counter()
    }
}



