package com.example.application.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider

import com.example.application.R
import com.example.application.data.entity.Note
import com.example.application.extensions.getColorInt
import com.example.application.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*


class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {
        private const val EXTRA_NOTE = "note"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) =
            Intent(context, NoteActivity::class.java).run {
                putExtra(EXTRA_NOTE, noteId)
                context.startActivity(this)
            }
    }

    override val layoutRes = R.layout.activity_note
    private var note: Note? = null
    override val viewModel: NoteViewModel by viewModel()

    var color: Note.Color = Note.Color.WHITE

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

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted){
            finish()
            return
        }

        this.note = data.note
        initView()
    }

    private fun initView() {
        editTextTitle.removeTextChangedListener(textChangeListener)
        editTextText.removeTextChangedListener(textChangeListener)

        note?.let { note ->
            editTextTitle.setTextKeepState(note.title)
            editTextText.setText(note.text)
            toolbar.setBackgroundColor(note.color.getColorInt(this))
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(note.lastChanged)
        }

        editTextTitle.addTextChangedListener(textChangeListener)
        editTextText.addTextChangedListener(textChangeListener)

        colorPicker.onColorClickListener = {
            color = it
            toolbar.setBackgroundColor(it.getColorInt(this))
            saveNote()
        }
    }

    private fun saveNote() {
        if (editTextTitle.text == null || editTextTitle.text!!.length < 3) return

        note = note?.copy(
            title = editTextTitle.text.toString(),
            text = editTextText.text.toString(),
            lastChanged = Date(),
            color = color
        ) ?: Note(
            UUID.randomUUID().toString(),
            editTextTitle.text.toString(),
            editTextText.text.toString(),
            color
        )

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu) = menuInflater.inflate(R.menu.note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.palette -> togglePallete().let { true }
        R.id.delete -> deleteNote().let { true }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun togglePallete() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.delete))
            .setNegativeButton(getString(R.string.note_delete_cancel)) { dialog, which -> dialog.dismiss() }
            .setPositiveButton(getString(R.string.note_delete_ok)) { dialog, which -> viewModel.deleteNote() }
            .show()
    }

}