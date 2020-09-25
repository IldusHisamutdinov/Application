package com.example.application.di

import com.example.application.data.NotesRepository
import com.example.application.data.provider.FirestoreDataProvider
import com.example.application.data.provider.RemoteDataProvider
import com.example.application.ui.main.MainViewModel
import com.example.application.ui.note.NoteViewModel
import com.example.application.ui.splash.SplashViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseAuth.getInstance()}
    single { FirebaseFirestore.getInstance() }
    single<RemoteDataProvider> { FirestoreDataProvider(get(), get()) }
    single { NotesRepository(get()) }
}

val splashModule = module {
    viewModel { SplashViewModel(get())}
}

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val noteModule = module {
    viewModel { NoteViewModel(get()) }
}