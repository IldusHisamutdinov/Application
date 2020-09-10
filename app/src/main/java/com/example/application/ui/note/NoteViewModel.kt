package com.example.application.ui.note

import androidx.lifecycle.ViewModel
import com.example.application.data.NotesRepository
import com.example.application.data.entity.Note

class NoteViewModel: ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note){
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}