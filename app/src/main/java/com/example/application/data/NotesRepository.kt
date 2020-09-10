package com.example.application.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.application.data.entity.Note
import java.util.*

object NotesRepository {

    private val notesLiveData: MutableLiveData<List<Note>> = MutableLiveData()
    private val notes: MutableList<Note> = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Первая заметка",
            "Тучки небесные, вечные странники!\n" +
                    "Степью лазурною, цепью жемчужною\n" +
                    "М.Лермонтов",
            Note.Color.WHITE),
           // color = Color.LTGRAY ),
        Note(
            UUID.randomUUID().toString(),
            "Вторая заметка",
            "Белеет парус одинокой\n" +
                    "В тумане моря голубом!..\n" +
                    "М.Лермонов",
            Note.Color.YELLOW
        ),
        Note(
            UUID.randomUUID().toString(),
            "Третья заметка",
            "Ночевала тучка золотая\n" +
                    "На груди утеса-великана; \n" +
                    "М.Лермонов",
            Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Четвертая заметка",
            "Листья в поле пожелтели,\n" +
                    "И кружатся и летят; \n" +
                    "М.Лермонов",
            Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Пятая заметка",
            "Отделкой золотой блистает мой кинжал;\n" +
                    "Клинок надежный, без порока; \n" +
                    "М.Лермонов",
            Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Шестая заметка",
            "Люблю отчизну я, но странною любовью!\n" +
                    "М.Лермонов",
            Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Седьмая заметка",
            "Люблю отчизну я, но странною любовью!\n" +
                    "М.Лермонов",
            Note.Color.VIOLET
        )

    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> = notesLiveData
    fun saveNote(note: Note){
        addOrReplaceNote(note)
        notesLiveData.value = notes
    }
    private fun addOrReplaceNote(note: Note){
        for(i in notes.indices){
            if(notes[i].id == note.id) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

}
