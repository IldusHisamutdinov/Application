package com.example.application.ui.main

import com.example.application.data.entity.Note
import com.example.application.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)

