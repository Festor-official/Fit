package com.example.fit.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.R
import com.example.fit.adapters.ExerciseAdapter
import com.example.fit.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val  exerciseViewModel: ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_fragment_view) as NavHostFragment
        val navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

//        val bodyIcon = findViewById<ImageView>(R.id.body_icon)
//        bodyIcon.setOnClickListener {
//            val intent = Intent(this, BodyActivity::class.java)
//            startActivity(intent)
//
//        }
//
//        val adapter = ExerciseAdapter(this)
//        var recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//        adapter.setCategory(exerciseViewModel.allExercise)
//        recyclerView.adapter = adapter
//        recyclerView.setLayoutManager(GridLayoutManager(this, 2))

    }
}