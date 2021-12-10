package androidx.lifecycle

/**
 * 列表LiveData的抽象基类。不可变更其中数据。
 *
 * @constructor 构造方法，需要输入一个列表用作初始值。
 * @param originalList 初始值列表。
 */
abstract class ListLiveData<T>(originalList: List<T>) :
    LiveData<List<T>>(originalList), List<T> {

    override fun getValue(): List<T> {
        return this
    }
}