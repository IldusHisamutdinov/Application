package com.example.application.ui.note

import com.example.application.data.entity.Note
import com.example.application.ui.base.BaseViewState

class NoteViewState(val note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)