package com.github.unscientificjszhai.unscientificlivedata.demo

import android.app.Activity
import android.widget.Toast

/**
 * 展示迭代器效果。
 *
 *
 */
class ToastThread(
    private val iterator: MutableIterator<Int>,
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