package com.example.application.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.application.R
import com.example.application.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*

class MainAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(note: Note) = with(itemView) {
            title.text = note.title
            text.text = note.text
        //    setCardBackgroundColor(color)

            val color = when(note.color){
                Note.Color.WHITE -> R.color.color_white
                Note.Color.YELLOW -> R.color.color_yellow
                Note.Color.GREEN -> R.color.color_green
                Note.Color.BLUE -> R.color.color_blue
                Note.Color.RED -> R.color.color_red
                Note.Color.VIOLET -> R.color.color_violet
            }

            setBackgroundColor(ContextCompat.getColor(itemView.context, color))

            itemView.setOnClickListener {
                val onItemClick = onItemClick ?: return@setOnClickListener

                onItemClick.invoke(note)
            }
        }
    }
}
