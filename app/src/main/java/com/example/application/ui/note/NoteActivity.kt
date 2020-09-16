package com.example.application.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider

import com.example.application.R
import com.example.application.data.entity.Note
import com.example.application.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : BaseActivity<Note?, NoteViewState>() {

    companion object {
        private const val EXTRA_NOTE = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) =
            Intent(context, NoteActivity::class.java).run {
                noteId?.let {
                    putExtra(EXTRA_NOTE, noteId)
                }
                context.startActivity(this)
            }
    }

    private var note: Note? = null
    override val viewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    override val layoutRes = R.layout.activity_note

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)

        noteId?.let { id ->
            viewModel.loadNote(id)
        } ?: let {
            supportActionBar?.title = getString(R.string.new_note_title)
        }
        initView()
    }

    override fun renderData(data: Note?) {
        this.note = data
        supportActionBar?.title = note?.let { note ->
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(note.lastChanged)
        } ?: let {
            getString(R.string.new_note_title)
        }
        initView()
    }

    private fun initView() {
        editTextTitle.removeTextChangedListener(textChangeListener)
        editTextText.removeTextChangedListener(textChangeListener)

        note?.let { note ->
            editTextTitle.setTextKeepState(note.title)
            editTextText.setText(note.text)

            val color = when (note.color) {
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yellow
                Note.Color.GREEN -> R.color.color_green
                Note.Color.BLUE -> R.color.color_blue
                Note.Color.RED -> R.color.color_red
                Note.Color.VIOLET -> R.color.color_violet
            }

            toolbar.setBackgroundColor(color)
        }

        editTextTitle.addTextChangedListener(textChangeListener)
        editTextText.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if (editTextTitle.text == null || editTextTitle.text!!.length < 3) return

        note = note?.copy(
            title = editTextTitle.text.toString(),
            text = editTextText.text.toString(),
            lastChanged = Date()
        ) ?: Note(
            UUID.randomUUID().toString(),
            editTextTitle.text.toString(),
            editTextText.text.toString()
        )

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}