package com.sighini.fetch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sighini.fetch.adapter.DataAdapter
import com.sighini.fetch.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private var mAdapter: DataAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(activityMainBinding.toolbar)
        viewModel =  ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.vmData.observe(this, Observer { data ->
            data?.let {
                mAdapter = DataAdapter(this, ArrayList(data))
                activityMainBinding.listView.adapter = mAdapter
            }
        })
        activityMainBinding.listView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        activityMainBinding.listView.addItemDecoration(dividerItemDecoration)
    }
}