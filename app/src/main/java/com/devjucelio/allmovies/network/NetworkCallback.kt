package com.devjucelio.allmovies.network

interface NetworkCallback<T>{
    fun networkCallResult(callback: Resource<T>)
}