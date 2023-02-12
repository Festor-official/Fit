 package com.example.fit.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fit.R
import com.example.fit.adapters.PreviousResultAdapter
import com.example.fit.adapters.ResultAdapter
import com.example.fit.viewmodel.ExerciseViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


 @AndroidEntryPoint()
 class RepeatActivity : AppCompatActivity() {
    private var total = 0
    private var weight = 0
    private lateinit var repeatMadeString:String
    private val  exerciseViewModel: ExerciseViewModel by viewModels()
    private lateinit var  repeatMade:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeat)


        val exercise_id = intent.extras?.get("id").toString().toInt()

        val chartButton = findViewById<ImageView>(R.id.charts)
        chartButton.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            intent.putExtra("id",exercise_id)
            repeatMadeString = ""
            startActivity(intent)
        }

        val bestSum = findViewById<TextView>(R.id.best_sum)
        val previousSum = findViewById<TextView>(R.id.previous_result_sum)
        repeatMade = findViewById<TextView>(R.id.repeat_made)
        val addRepeat = findViewById<EditText>(R.id.add_repeat)
        val sum = findViewById<TextView>(R.id.sum)
        val weightButton = findViewById<TextView>(R.id.weight_exercise)
        val weightMinus = findViewById<TextView>(R.id.minus_weight)
        val weightPlus = findViewById<TextView>(R.id.add_weight)


        weightMinus.setOnClickListener {
            weight-=1
        }

        weightPlus.setOnClickListener {
            weight+=1
        }


//        val set = exerciseViewModel.


//        addRepeat.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
//                if (event.action == KeyEvent.ACTION_DOWN) {
//                    when (keyCode) {
//                        KeyEvent.KEYCODE_ENTER -> {
//                            repeatMade.text = repeatMade.text.toString() + "\n" + addRepeat.text.toString() + "\n"
//                            repeatMadeString = repeatMade.text.toString()
//                            total += addRepeat.text.toString().toInt()
//                            sum.text  = "Sum:$total"
//                            exerciseViewModel.addRepeat(exercise_id,set,addRepeat.text.toString().toInt(),total)
//                            addRepeat.text.clear()
//                            return true
//                        }
//                        else -> {
//                        }
//                    }
//                }
//                return false
//            }
//        })



        val recyclerViewPreviousResult = findViewById<RecyclerView>(R.id.recycler_view_previous_result)
        val previousResult = exerciseViewModel.getLastRepeat(exercise_id)

        val recyclerViewBestResult = findViewById<RecyclerView>(R.id.recycler_view_best_result)
        val sumText = exerciseViewModel.maxFromAmountRepeat(exercise_id,2,0,0)
        if(sumText.amount.isNotEmpty()){
            bestSum.text = "Sum:" +  sumText.amount.subList(0,3).sum()


            weightButton.text = "Weight:$weight kg"
            val adapter =  ResultAdapter(this)
            adapter.setResult(sumText.amount.subList(0,3))
            recyclerViewBestResult.adapter = adapter
            recyclerViewBestResult.setLayoutManager(GridLayoutManager(this, 1))

            if(previousResult!= null){
                previousSum.text = "Sum:" +  previousResult.amount.subList(0,3).sum()
                val adapterPreviousResult = PreviousResultAdapter(this)
                adapterPreviousResult.setResult(previousResult.amount.subList(0,3))
                recyclerViewPreviousResult.adapter = adapterPreviousResult
                recyclerViewPreviousResult.setLayoutManager(GridLayoutManager(this, 1))
            }
  }




//        submit.setOnClickListener{
//            repeatMade.text = repeatMade.text.toString() + "\n " +  addRepeat.text.toString()
//            total += addRepeat.text.toString().toInt()
//            sum.text = "Sum: " +  total.toString()
//        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putString("repeat", repeatMadeString)
        savedInstanceState.putInt("total", total)

        // etc.
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.
        total = savedInstanceState.getInt("total")
        repeatMade.text = savedInstanceState.getString("repeat")
    }




//    fun days(dateToday:String):Int{
//        //Year,month,day
//
//
////
////        return today.toInt()
//
//    }


}