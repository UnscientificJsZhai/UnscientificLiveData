package com.github.unscientificjszhai.unscientificlivedata.demo

import android.app.Activity
import android.widget.Toast

/**
 * 展示迭代器效果。
 *
 * 启动此线程后，通过传入的迭代器，没500毫秒删除一个迭代器中的元素，并Toast出元素。
 * 会调用元素的[Any.toString]方法。
 *
 * @author UnscientificJsZhai
 * @param iterator 可变迭代器。
 * @param context Activity上下文。用于发送Toast。
 */
class ToastThread(
    private val iterator: MutableIterator<Any>,
    private val context: Activity
) : Thread() {

    override fun run() {
        while (iterator.hasNext()) {
            val next = iterator.next()
            context.runOnUiThread {
                Toast.makeText(
                    this.context,
                    next.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            iterator.remove()
            sleep(500)
        }
    }
}