package androidx.lifecycle

/**
 * 列表LiveData的抽象基类。不可变更其中数据。
 *
 * @author UnscientificJsZhai
 * @constructor 构造方法，需要输入一个列表用作初始值。
 * @param originalList 初始值列表。
 */
abstract class ListLiveData<T>(originalList: List<T>) :
    LiveData<List<T>>(originalList), List<T> {

    /**
     * 观察列表内元素。
     *
     * 区别于[observe]方法，使用这个方法注册的观察回调，再每次被调用时都会生成一个新的列表对象，
     * 然后将当前此LiveData中的数据拷贝到新的列表中，再使用新的列表作为参数调用观察回调。
     * 特别的，在使用RecyclerView中的ListAdapter的时候，因为ListAdapter的submitList方法会检查列表的引用，
     * 所以应该用此方法注册监听器。
     *
     * @param owner 观察此LiveData的生命周期持有者。
     * @param observer 观察回调。
     * @see LiveData.observe
     */
    @Suppress("unused")
    fun observeElements(owner: LifecycleOwner, observer: Observer<in List<T>>) {
        val elementsObserver = Observer<List<T>> {
            val newList: List<T> = ArrayList(it)
            observer.onChanged(newList)
        }

        super.observe(owner, elementsObserver)
    }

    /**
     * 一直观察列表内元素。
     *
     * 区别于[observeForever]方法，使用这个方法注册的观察回调，再每次被调用时都会生成一个新的列表对象，
     * 然后将当前此LiveData中的数据拷贝到新的列表中，再使用新的列表作为参数调用观察回调。
     * 特别的，在使用RecyclerView中的ListAdapter的时候，因为ListAdapter的submitList方法会检查列表的引用，
     * 所以应该用此方法注册监听器。
     *
     * @param observer 观察回调。
     * @see LiveData.observeForever
     */
    @Suppress("unused")
    fun observeElementsForever(observer: Observer<in List<T>>) {
        val elementsObserver = Observer<List<T>> {
            val newList: List<T> = ArrayList(it)
            observer.onChanged(newList)
        }

        super.observeForever(elementsObserver)
    }

    override fun getValue(): List<T> {
        return this
    }
}