package com.example.notessqlite.Activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notessqlite.Activitys.DatabaseHolder.NotesDatabaseHolder
import com.example.notessqlite.Activitys.Model.Note
import com.example.notessqlite.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    lateinit var binding:ActivityUpdateBinding
    lateinit var db:NotesDatabaseHolder
    private var nodeId :Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // inslization to class NotesDatabaseHolder
        db = NotesDatabaseHolder(this)

        nodeId = intent.getIntExtra("note_id",-1)
        if (nodeId == -1){
            finish()
            return
        }

        val node = db.getNoteById(nodeId)

        binding.updateTitleEditText.setText(node.title)
        binding.updateContentEditText.setText(node.content)

        binding.updateSaveButton.setOnClickListener {

            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()

            val updateNode = Note(nodeId,newTitle,newContent)
            db.updateNotes(updateNode)
            finish()
            Toast.makeText(this, "Change Saved", Toast.LENGTH_SHORT).show()
            Log.e("Baraa","Change Saved")

        }
    }
}