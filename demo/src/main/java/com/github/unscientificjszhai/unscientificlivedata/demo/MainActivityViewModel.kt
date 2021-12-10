package com.github.unscientificjszhai.unscientificlivedata.demo

import androidx.lifecycle.ViewModel
import com.github.unscientificjszhai.unscientificlivedata.list.ArrayListLiveData
import kotlin.reflect.KProperty

/**
 * MainActivity的ViewModel，用于存放和界面有关的数据。
 *
 * @author UnscientificJsZhai
 * @see MainActivity
 * @see ArrayListLiveData
 */
class MainActivityViewModel : ViewModel() {

    val mList = ArrayListLiveData<Int>()

    var currentNumber = 0

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = currentNumber

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
        currentNumber = newValue
    }
}