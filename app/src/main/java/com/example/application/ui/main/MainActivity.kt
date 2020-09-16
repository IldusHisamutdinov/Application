package com.example.application.ui.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.application.R
import com.example.application.data.entity.Note
import com.example.application.ui.base.BaseActivity
import com.example.application.ui.base.BaseViewModel
import com.example.application.ui.note.NoteActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<List<Note>?, MainViewState>() {

    override val viewModel: BaseViewModel<List<Note>?, MainViewState> by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_main
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv_notes.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

        adapter = MainAdapter{
            NoteActivity.start(this@MainActivity, it.id)
        }

       rv_notes.adapter = adapter

        fab.setOnClickListener {
            NoteActivity.start(this@MainActivity)
        }
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.notes = it
        }
    }
}