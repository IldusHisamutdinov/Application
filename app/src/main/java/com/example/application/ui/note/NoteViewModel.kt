package com.example.application.ui.note

import com.example.application.data.NotesRepository
import com.example.application.data.entity.Note
import com.example.application.data.model.NoteResult
import com.example.application.ui.base.BaseViewModel

class NoteViewModel : BaseViewModel<Note?, NoteViewState>() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    fun loadNote(noteId: String) {
        NotesRepository.getNoteById(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NoteViewState(note = result.data as? Note)
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NoteViewState(error = result.error)
                }
            }
        }
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}