package com.example.fit

import android.app.DownloadManager
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var  exerciseViewModel: ExerciseViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bodyIcon = findViewById<ImageView>(R.id.body_icon)
        bodyIcon.setOnClickListener {
            val intent = Intent(this,BodyActivity::class.java)
            startActivity(intent)

        }



        val adapter = ExerciseAdapter(this)
        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)
        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        adapter.setCategory(exerciseViewModel.allExercise)
        recyclerView.adapter = adapter
        recyclerView.setLayoutManager(GridLayoutManager(this, 2))

    }
}