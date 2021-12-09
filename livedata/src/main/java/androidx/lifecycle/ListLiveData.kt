package androidx.lifecycle

abstract class ListLiveData<T>(originalList: List<T>) :
    LiveData<List<T>>(originalList), List<T> {

    override fun getValue(): List<T> {
        return this
    }
}