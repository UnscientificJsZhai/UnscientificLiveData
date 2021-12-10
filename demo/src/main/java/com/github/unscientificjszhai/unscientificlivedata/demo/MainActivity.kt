package com.github.unscientificjszhai.unscientificlivedata.demo

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Demo App的主页Activity。
 * 包含一个RecyclerView和一个右上角菜单项。
 * 按右上角菜单项可以添加一个新的项目，长按一个项目可以删除它。
 *
 * @author UnscientificJsZhai
 * @see MainActivityViewModel
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IntListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        this.adapter = IntListAdapter { index ->
            this.viewModel.mList.removeAt(index)
        }

        this.recyclerView = findViewById(R.id.MainActivity_RecyclerView)
        this.recyclerView.layoutManager = LinearLayoutManager(this)
        this.recyclerView.adapter = this.adapter

        viewModel.mList.observeElements(this) { currentList ->
            Log.e("MainActivity", "onCreate: $currentList")
            this.adapter.submitList(currentList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.MainActivity_AddButton) {
            val currentNumber = this.viewModel.currentNumber
            this.viewModel.mList.add(currentNumber + 1)
            this.viewModel.currentNumber = currentNumber + 1
            return true
        } else if (item.itemId == R.id.MainActivity_Iterate) {
            ToastThread(
                this.viewModel.mList.listIterator(),
                this
            ).start()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}