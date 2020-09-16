package com.example.application.data.provider

import androidx.lifecycle.LiveData
import com.example.application.data.entity.Note
import com.example.application.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
}