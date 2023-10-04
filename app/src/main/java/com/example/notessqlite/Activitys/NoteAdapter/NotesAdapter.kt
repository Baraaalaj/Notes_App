package com.example.notessqlite.Activitys.NoteAdapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.notessqlite.Activitys.DatabaseHolder.NotesDatabaseHolder
import com.example.notessqlite.Activitys.Model.Note
import com.example.notessqlite.Activitys.UpdateActivity
import com.example.notessqlite.R
import com.example.notessqlite.databinding.NodeItemBinding

class NotesAdapter(private var notes:List<Note>,val context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val db:NotesDatabaseHolder = NotesDatabaseHolder(context)

    class NotesViewHolder(val binding :NodeItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NodeItemBinding.inflate(inflater, parent, false)
        return NotesViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {

        holder.binding.txtTitle.text = notes[position].title.toString()
        holder.binding.txtContent.text = notes[position].content.toString()
        holder.binding.updateButton.setOnClickListener {

            val i = Intent(holder.itemView.context,UpdateActivity::class.java).apply {
                putExtra("note_id",notes[position].id)
            }
            holder.itemView.context.startActivity(i)

            holder.binding.deleteButton.setOnClickListener {

                db.deleteNodes(notes[position].id)

                refreshData(db.getAllNotes())
                Toast.makeText(holder.itemView.context, "Note Delete", Toast.LENGTH_SHORT).show()
                Log.e("Baraa","Note Delete")
            }

        }
    }
// refresh All Data in Get All information
    fun refreshData(newNotes:List<Note>){

        notes = newNotes
        notifyDataSetChanged()
    }

}