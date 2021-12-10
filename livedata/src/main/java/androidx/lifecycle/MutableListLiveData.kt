package androidx.lifecycle

/**
 * 列表LiveData的抽象基类。可变更其中数据。
 *
 * @author UnscientificJsZhai
 * @constructor 构造方法，需要输入一个列表用作初始值。
 * @param originalList 初始值列表。
 */
abstract class MutableListLiveData<T>(originalList: List<T>) :
    ListLiveData<T>(originalList), MutableList<T>