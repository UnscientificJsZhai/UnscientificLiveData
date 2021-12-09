package com.github.unscientificjszhai.livedata

import androidx.lifecycle.ListLiveData

/**
 *
 */
class ArrayListLiveData<T>(originalList: ArrayList<T>) :
    ListLiveData<T>(originalList), MutableList<T> {

    // LiveData实现部分

    private var mArrayList = ArrayList<T>()

    override fun setValue(value: List<T>?) {
        mArrayList = if (value != null) {
            ArrayList(value)
        } else {
            arrayListOf()
        }
        super.setValue(mArrayList)
    }

    override fun postValue(value: List<T>?) {
        //TODO
        super.postValue(value)
    }

    // List实现部分

    override operator fun get(index: Int) = mArrayList[index]

    override fun add(element: T): Boolean {
        val result = mArrayList.add(element)
        value = mArrayList
        return result
    }

    override val size: Int
        get() = mArrayList.size

    override fun contains(element: T) = mArrayList.contains(element)

    override fun containsAll(elements: Collection<T>) = mArrayList.containsAll(elements)

    override fun indexOf(element: T) = mArrayList.indexOf(element)

    override fun isEmpty() = mArrayList.isEmpty()

    override fun iterator() = mArrayList.iterator()
    //TODO 检查迭代器方法

    override fun lastIndexOf(element: T) = mArrayList.lastIndexOf(element)

    override fun add(index: Int, element: T) {
        mArrayList.add(index, element)
        value = mArrayList
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        val result = mArrayList.addAll(index, elements)
        value = mArrayList
        return result
    }

    override fun addAll(elements: Collection<T>): Boolean {
        val result = mArrayList.addAll(elements)
        value = mArrayList
        return result
    }

    override fun clear() {
        mArrayList.clear()
        value = mArrayList
    }

    override fun listIterator() = mArrayList.listIterator()
    //TODO 检查迭代器方法

    override fun listIterator(index: Int) = mArrayList.listIterator(index)

    override fun remove(element: T): Boolean {
        val result = mArrayList.remove(element)
        value = mArrayList
        return result
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        val result = mArrayList.removeAll(elements.toSet())
        value = mArrayList
        return result
    }

    override fun removeAt(index: Int): T {
        val result = mArrayList.removeAt(index)
        value = mArrayList
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        val result = mArrayList.retainAll(elements.toSet())
        value = mArrayList
        return result
    }

    override operator fun set(index: Int, element: T): T {
        val previous = mArrayList.set(index, element)
        value = mArrayList
        return previous
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> = SubList(fromIndex, toIndex)

    private inner class SubList(fromIndex: Int, toIndex: Int) : MutableList<T> {

        private val mSubList = mArrayList.subList(fromIndex, toIndex)

        override val size: Int
            get() = mSubList.size

        override fun contains(element: T) = mSubList.contains(element)

        override fun containsAll(elements: Collection<T>) = mSubList.containsAll(elements)

        override operator fun get(index: Int) = mSubList[index]

        override fun indexOf(element: T) = mSubList.indexOf(element)

        override fun isEmpty() = mSubList.isEmpty()

        override fun iterator() = mSubList.iterator()

        override fun lastIndexOf(element: T) = mSubList.lastIndexOf(element)

        override fun add(element: T): Boolean {
            val result = mSubList.add(element)
            value = mArrayList
            return result
        }

        override fun add(index: Int, element: T) {
            mSubList.add(index, element)
            value = mArrayList
        }

        override fun addAll(index: Int, elements: Collection<T>): Boolean {
            val result = mSubList.addAll(index, elements)
            value = mArrayList
            return result
        }

        override fun addAll(elements: Collection<T>): Boolean {
            val result = mSubList.addAll(elements)
            value = mArrayList
            return result
        }

        override fun clear() {
            mSubList.clear()
            value = mArrayList
        }

        override fun listIterator(): MutableListIterator<T> {
            TODO("Not yet implemented")
        }

        override fun listIterator(index: Int): MutableListIterator<T> {
            TODO("Not yet implemented")
        }

        override fun remove(element: T): Boolean {
            val result = mSubList.remove(element)
            value = mArrayList
            return result
        }

        override fun removeAll(elements: Collection<T>): Boolean {
            val result = mSubList.removeAll(elements)
            value = mArrayList
            return result
        }

        override fun removeAt(index: Int): T {
            val result = mSubList.removeAt(index)
            value = mArrayList
            return result
        }

        override fun retainAll(elements: Collection<T>): Boolean {
            val result = mSubList.retainAll(elements)
            value = mArrayList
            return result
        }

        override operator fun set(index: Int, element: T): T {
            val previous = mSubList.set(index, element)
            value = mArrayList
            return previous
        }

        override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
            TODO("Not yet implemented")
        }
    }
}