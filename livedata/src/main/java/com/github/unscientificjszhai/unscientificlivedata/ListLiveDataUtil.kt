@file:JvmName("ListLiveDataUtil")
@file:Suppress("unused")

package com.github.unscientificjszhai.unscientificlivedata

import androidx.lifecycle.ListLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableListLiveData
import androidx.lifecycle.MutableLiveData
import com.github.unscientificjszhai.unscientificlivedata.list.ArrayListLiveData
import com.github.unscientificjszhai.unscientificlivedata.list.VectorLiveData
import java.util.*
import kotlin.collections.ArrayList
import kotlin.reflect.KProperty

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

/**
 * 将给定List转为LiveData。
 *
 * @receiver 列表对象。
 * @return 转换成的LiveData对象。
 */
fun <T> List<T>.toListLiveData(): ListLiveData<T> {
    val arrayList = ArrayList(this)
    return ArrayListLiveData(arrayList)
}

/**
 * 为LiveData启用属性委托，可随时访问最新值。
 *
 * @receiver 提供属性的LiveData。
 * @param thisRef 进行委托的对象。
 * @param property 请求的值的属性。
 * @return LiveData中存储的值。
 */
operator fun <T> LiveData<T>.getValue(thisRef: Any?, property: KProperty<*>): T? {
    return this.value
}

/**
 * 为MutableLiveData启用属性委托，可以推送新值。
 *
 * 可在子线程调用。
 *
 * @receiver 可变的LiveData。
 * @param thisRef 进行委托的对象。
 * @param property 请求的值的属性。
 * @param value 要传递的新值。
 */
operator fun <T> MutableLiveData<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
    this.postValue(value)
}