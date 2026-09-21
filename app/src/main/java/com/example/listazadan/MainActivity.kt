package com.example.listazadan

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextTask = findViewById<EditText>(R.id.editTextTask)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val listViewTasks = findViewById<ListView>(R.id.listViewTasks)

        val tasksList = ArrayList<String>()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasksList)
        listViewTasks.adapter = adapter

        buttonAdd.setOnClickListener {
            val taskText = editTextTask.text.toString()
            if (taskText.isNotEmpty()) {
                tasksList.add(taskText)
                adapter.notifyDataSetChanged()
                editTextTask.text.clear()
            }
        }

        listViewTasks.setOnItemClickListener { _, _, position, _ ->
            tasksList.removeAt(position)
            adapter.notifyDataSetChanged()
        }
    }
}