package com.example.application.data

import com.example.application.data.entity.Note
import com.example.application.data.provider.FirestoreDataProvider
import com.example.application.data.provider.RemoteDataProvider

object NotesRepository {

    val remoteProvider: RemoteDataProvider = FirestoreDataProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
}