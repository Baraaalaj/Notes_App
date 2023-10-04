package com.example.notessqlite.Activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notessqlite.Activitys.DatabaseHolder.NotesDatabaseHolder
import com.example.notessqlite.Activitys.NoteAdapter.NotesAdapter
import com.example.notessqlite.R
import com.example.notessqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var db: NotesDatabaseHolder
    lateinit var noteAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inslization to class NotesDatabaseHolder
        db = NotesDatabaseHolder(this)
        // inslization to Adapter
        noteAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.noteRecyclerView.setHasFixedSize(true)
        binding.noteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.noteRecyclerView.adapter = noteAdapter


        binding.addButton.setOnClickListener {

            val i = Intent(this, AddNoteActiviity::class.java)
            startActivity(i)
        }

    }

    override fun onResume() {
        super.onResume()
        noteAdapter.refreshData(db.getAllNotes())
    }
}