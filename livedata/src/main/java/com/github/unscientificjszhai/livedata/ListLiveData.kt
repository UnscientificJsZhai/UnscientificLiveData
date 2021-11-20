package com.github.unscientificjszhai.livedata

import androidx.lifecycle.LiveData

abstract class ListLiveData<T>(originalList: List<T>) :
    LiveData<List<T>>(originalList), List<T> {

    constructor() : this(emptyList())

    override fun getValue(): List<T> {
        return this
    }
}