package com.example.application.data

import android.graphics.Color
import com.example.application.data.entity.Note

object NotesRepository {

    val notes: List<Note> = listOf(
        Note(
            "Первая заметка",
            "Тучки небесные, вечные странники!\n" +
                    "Степью лазурною, цепью жемчужною\n" +
                    "М.Лермонтов",
            color = Color.LTGRAY
        ),
        Note(
            "Вторая заметка",
            "Белеет парус одинокой\n" +
                    "В тумане моря голубом!..\n" +
                    "М.Лермонов",
            color = Color.YELLOW
        ),
        Note(
            "Третья заметка",
            "Ночевала тучка золотая\n" +
                    "На груди утеса-великана; \n" +
                    "М.Лермонов",
            color = Color.WHITE
        ),
        Note(
            "Четвертая заметка",
            "Листья в поле пожелтели,\n" +
                    "И кружатся и летят; \n" +
                    "М.Лермонов",
            color = Color.LTGRAY
        ),
        Note(
            "Пятая заметка",
            "Отделкой золотой блистает мой кинжал;\n" +
                    "Клинок надежный, без порока; \n" +
                    "М.Лермонов",
            color = Color.CYAN
        ),
        Note(
            "Шестая заметка",
            "Люблю отчизну я, но странною любовью!\n" +
                    "М.Лермонов",
            color = Color.LTGRAY
        ),
        Note(
            "Седьмая заметка",
            "Люблю отчизну я, но странною любовью!\n" +
                    "М.Лермонов",
            color = Color.LTGRAY
        )

    )

}
