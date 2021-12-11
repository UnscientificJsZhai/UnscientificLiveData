package com.github.unscientificjszhai.unscientificlivedata.list

import androidx.lifecycle.ListLiveData
import androidx.lifecycle.MutableListLiveData
import java.util.*

/**
 * 包装Vector的LiveData。
 *
 * 可以订阅此Vector的内部数据变更消息。当列表内数据变更时，也可以通知到界面。
 *
 * @author UnscientificJsZhai
 * @see ListLiveData.observeElements
 * @see ListLiveData.observeElementsForever
 */
@Suppress("unused")
class VectorLiveData<T> : MutableListLiveData<T>, MutableList<T> {

    /**
     * 用给定列表中的数据构建一个LiveData。
     *
     * @param originalList 原始数据。会从中拷贝出所有数据，不会复用该列表对象。
     */
    constructor(originalList: Vector<T>) : super(originalList) {
        this.mVector = Vector(originalList)
    }

    /**
     * 创建一个空的列表的LiveData。
     */
    constructor() : this(Vector()) {
        this.mVector = Vector()
    }

    // LiveData实现部分

    /**
     * 向量的内部实现。
     *
     * 用于保存数据。
     */
    private var mVector: Vector<T>

    override fun setValue(value: List<T>?) {
        if (value !== mVector) {
            mVector = if (value != null) {
                Vector(value)
            } else {
                Vector()
            }
        }
        super.setValue(mVector)
    }

    override fun postValue(value: List<T>?) {
        if (value !== mVector) {
            val newList = if (value != null) {
                Vector(value)
            } else {
                Vector()
            }
            super.postValue(newList)
        } else {
            super.postValue(value)
        }
    }

    // List实现部分

    override operator fun get(index: Int): T = mVector[index]

    override fun add(element: T): Boolean {
        val result = mVector.add(element)
        postValue(mVector)
        return result
    }

    override val size: Int
        get() = mVector.size

    override fun contains(element: T) = mVector.contains(element)

    override fun containsAll(elements: Collection<T>) = mVector.containsAll(elements)

    override fun indexOf(element: T) = mVector.indexOf(element)

    override fun isEmpty() = mVector.isEmpty()

    override fun iterator(): MutableIterator<T> = ListIterator(mVector, 0)

    override fun lastIndexOf(element: T) = mVector.lastIndexOf(element)

    override fun add(index: Int, element: T) {
        mVector.add(index, element)
        postValue(mVector)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val result = mVector.addAll(index, elements)
        postValue(mVector)
        return result
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val result = mVector.addAll(elements)
        postValue(mVector)
        return result
    }

    override fun clear() {
        mVector.clear()
        postValue(mVector)
    }

    override fun listIterator(): MutableListIterator<T> =
        ListIterator(mVector, 0)

    override fun listIterator(index: Int): MutableListIterator<T> =
        ListIterator(mVector, index)

    override fun remove(element: T): Boolean {
        val result = mVector.remove(element)
        postValue(mVector)
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val result = mVector.removeAll(elements.toSet())
        postValue(mVector)
        return result
    }

    override fun removeAt(index: Int): T {
        val result = mVector.removeAt(index)
        postValue(mVector)
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val result = mVector.retainAll(elements.toSet())
        postValue(mVector)
        return result
    }

    override operator fun set(index: Int, element: T): T {
        val previous = mVector.set(index, element)
        postValue(mVector)
        return previous
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> =
        SubList(fromIndex, toIndex)

    /**
     * LiveData子列表的包装类。用来保证对子列表进行操作也能触发数据推送。
     *
     * @param fromIndex 起始索引。包括此索引。
     * @param toIndex 终止索引。不包括此索引。
     */
    private inner class SubList(
        private var fromIndex: Int,
        private var toIndex: Int
    ) : MutableList<T> {

        /**
         * 子列表的内部实现。
         */
        private val mSubList = mVector.subList(fromIndex, toIndex)

        override val size: Int
            get() = mSubList.size

        override fun contains(element: T) = mSubList.contains(element)

        override fun containsAll(elements: Collection<T>) = mSubList.containsAll(elements)

        override operator fun get(index: Int): T = mSubList[index]

        override fun indexOf(element: T) = mSubList.indexOf(element)

        override fun isEmpty() = mSubList.isEmpty()

        override fun iterator() = ListIterator(this, 0)

        override fun lastIndexOf(element: T) = mSubList.lastIndexOf(element)

        override fun add(element: T): Boolean {
            val result = mSubList.add(element)
            postValue(mVector)
            return result
        }

        override fun add(index: Int, element: T) {
            mSubList.add(index, element)
            fromIndex += 1
            postValue(mVector)
        }

        override fun addAll(index: Int, elements: Collection<T>): Boolean {
            val result = mSubList.addAll(index, elements)
            toIndex = fromIndex + mSubList.size
            postValue(mVector)
            return result
        }

        override fun addAll(elements: Collection<T>): Boolean {
            val result = mSubList.addAll(elements)
            toIndex = fromIndex + mSubList.size
            postValue(mVector)
            return result
        }

        override fun clear() {
            mSubList.clear()
            toIndex = fromIndex
            postValue(mVector)
        }

        override fun listIterator(): MutableListIterator<T> =
            ListIterator(this, 0)

        override fun listIterator(index: Int): MutableListIterator<T> =
            ListIterator(this, index)

        override fun remove(element: T): Boolean {
            val result = mSubList.remove(element)
            postValue(mVector)
            toIndex -= 1
            return result
        }

        override fun removeAll(elements: Collection<T>): Boolean {
            val result = mSubList.removeAll(elements)
            postValue(mVector)
            toIndex = fromIndex + mSubList.size
            return result
        }

        override fun removeAt(index: Int): T {
            val result = mSubList.removeAt(index)
            postValue(mVector)
            toIndex -= 1
            return result
        }

        override fun retainAll(elements: Collection<T>): Boolean {
            val result = mSubList.retainAll(elements)
            postValue(mVector)
            toIndex = fromIndex + mSubList.size
            return result
        }

        override operator fun set(index: Int, element: T): T {
            val previous = mSubList.set(index, element)
            postValue(mVector)
            return previous
        }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
            return SubList(this.fromIndex + fromIndex, this.fromIndex + toIndex)
        }
    }

    /**
     * ListIterator的包装类。用来保证对可变迭代器的操作也能触发数据推送。
     *
     * @see VectorLiveData.listIterator
     * @see VectorLiveData.iterator
     * @param parent 父List。可以为SubList。
     * @param index 起始索引。
     */
    private inner class ListIterator(parent: MutableList<T>, index: Int) : MutableListIterator<T> {

        /**
         * 迭代器的内部实现。
         */
        private val mIterator = parent.listIterator(index)

        override fun hasPrevious() = mIterator.hasPrevious()

        override fun nextIndex() = mIterator.nextIndex()

        override fun previous() = mIterator.previous()

        override fun previousIndex() = mIterator.previousIndex()

        override fun add(element: T) {
            mIterator.add(element)
            postValue(mVector)
        }

        override fun hasNext() = mIterator.hasNext()

        override fun next() = mIterator.next()

        override fun remove() {
            mIterator.remove()
            postValue(mVector)
        }

        override fun set(element: T) {
            mIterator.set(element)
            postValue(mVector)
        }
    }
}