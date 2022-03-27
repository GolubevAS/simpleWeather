package com.example.simpleweather.business.repo

import com.example.simpleweather.business.ApiProvider
import io.reactivex.rxjava3.subjects.BehaviorSubject

abstract class BaseRepository<T>(val api : ApiProvider) {

     val dataEnniter : BehaviorSubject<T> = BehaviorSubject.create()

}