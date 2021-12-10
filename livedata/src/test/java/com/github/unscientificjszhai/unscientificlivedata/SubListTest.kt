package com.github.unscientificjszhai.unscientificlivedata

import org.junit.Test

/**
 * 测试子列表功能的单元测试。
 *
 * @see ArrayListLiveData.subList
 */
class SubListTest {

    @Test
    fun subListTest() {
        val list = ArrayList((0..10).toList())
        val subList1 = list.subList(1, 6)
        println(subList1)
        val subList2 = subList1.subList(1,2)
        println(subList2)
        subList1.add(6)
        println(subList1)
        println(list)
    }
}