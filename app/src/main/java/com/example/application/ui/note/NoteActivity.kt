package com.example.application.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.example.application.R
import com.example.application.data.NotesRepository.saveNote
import com.example.application.data.entity.Note
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {
 //       private val EXTRA_NOTE = NoteActivity::class.java.name + "extra.NOTE"
        private const val DATE_FORMAT = "dd.MMM.yy HH:mm"
        private val EXTRA_NOTE = "note"

        fun start(context: Context, note: Note? = null) =
            Intent(context, NoteActivity::class.java).apply {

            note?.let {
                putExtra(EXTRA_NOTE, note)
            }
            context.startActivity(this)
        }

    }

    private var note: Note? = null
    lateinit var viewModel: NoteViewModel

    val textChangeListener = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)

        note = intent.getParcelableExtra(EXTRA_NOTE)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = note?.let { note ->
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(note.lastChanged)
        } ?: getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        editTextTitle.removeTextChangedListener(textChangeListener)
        editTextText.removeTextChangedListener(textChangeListener)

        note?.let { note ->
            editTextTitle.setText(note.title)
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
//            toolbar.setBackgroundColor(ResourcesCompat.getColor(resources, color, null))
        }

        editTextTitle.addTextChangedListener(textChangeListener)
        editTextText.addTextChangedListener(textChangeListener)
    }

    private fun saveNote() {
        if (editTextTitle.text == null || editTextTitle.text!!.length < 2) return

        note = note?.copy(
            title = editTextTitle.text.toString(),
            text = editTextText.text.toString(),
            lastChanged = Date()
        ) ?: Note(UUID.randomUUID().toString(), editTextTitle.text.toString(), editTextText.text.toString())

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

