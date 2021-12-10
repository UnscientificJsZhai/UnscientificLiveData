@file:JvmName("ListLiveDataUtil")
@file:Suppress("unused")

package com.github.unscientificjszhai.unscientificlivedata

import androidx.lifecycle.ListLiveData
import androidx.lifecycle.MutableListLiveData
import com.github.unscientificjszhai.unscientificlivedata.list.ArrayListLiveData
import com.github.unscientificjszhai.unscientificlivedata.list.VectorLiveData
import java.util.*

/**
 * 将一个ArrayList转化为LiveData。
 *
 * @receiver 一个ArrayList对象。
 * @return 转化后的LiveData对象。
 */
fun <T> ArrayList<T>.toArrayListLiveData(): ArrayListLiveData<T> {
    return ArrayListLiveData(this)
}

/**
 * 将一个Vector转化为LiveData。
 *
 * @receiver 一个Vector对象。
 * @return 转化后的LiveData对象。
 */
fun <T> Vector<T>.toVectorLiveData(): VectorLiveData<T> {
    return VectorLiveData(this)
}

/**
 * 将一组元素转化为ArrayListLiveData。
 *
 * @param elements 元素。
 * @return 转化后的LiveData对象。
 */
fun <T> arrayListLiveDataOf(vararg elements: T): ArrayListLiveData<T> {
    return arrayListOf(*elements).toArrayListLiveData()
}

/**
 * 为ArrayListLiveData设定新的数据。
 *
 * @receiver 要设定数据的LiveData对象。
 * @param value 任意List对象。可为null。如果为null，则视为空List。
 */
fun <T> ArrayListLiveData<T>.setValue(value: List<T>?) {
    if (value == null) {
        this.setValue(arrayListOf())
    } else {
        this.setValue(ArrayList(value))
    }
}

/**
 * 为VectorLiveData设定新的数据。
 *
 * @receiver 要设定数据的LiveData对象。
 * @param value 任意List对象。可为null。如果为null，则视为空List。
 */
fun <T> VectorLiveData<T>.setValue(value: List<T>?) {
    if (value == null) {
        this.setValue(Vector())
    } else {
        this.setValue(Vector(value))
    }
}

/**
 * 返回一个可变列表的不可变形式，用于订阅。
 *
 * @receiver 可变列表LiveData。
 * @return 自身的不可变形式。
 */
fun <T> MutableListLiveData<T>.immutable(): ListLiveData<T> {
    return this
}