package com.example.application.data

import com.example.application.data.entity.Note
import com.example.application.data.provider.RemoteDataProvider

class NotesRepository(val dataProvider: RemoteDataProvider) {
    fun getCurrentUser() = dataProvider.getCurrentUser()
    fun getNotes() = dataProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = dataProvider.saveNote(note)
    fun getNoteById(id: String) = dataProvider.getNoteById(id)
    fun deleteNote(id: String) = dataProvider.deleteNote(id)
}