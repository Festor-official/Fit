 package com.example.fit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class RepeatActivity : AppCompatActivity() {
    private var total = 0
    private var weight = 0
    private lateinit var repeatMadeString:String
    private lateinit var  exerciseViewModel: ExerciseViewModel
    private lateinit var  repeatMade:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repeat)

        exerciseViewModel = ViewModelProvider(this).get(ExerciseViewModel::class.java)

        val exercise_id = intent.extras?.get("id").toString().toInt()

        val chartButton = findViewById<ImageView>(R.id.charts)
        chartButton.setOnClickListener {
            val intent = Intent(this,ChartActivity::class.java)
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

        val dateFormat: DateFormat = SimpleDateFormat("yyyy.MM.dd")
        //2021.06.02 2021.08.06
        val dateStart = "2021.08.06"
        val dateToday = dateFormat.format(Date())
        val set = days(dateToday,dateStart)


        addRepeat.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_ENTER -> {
                            repeatMade.text = repeatMade.text.toString() + "\n" + addRepeat.text.toString() + "\n"
                            repeatMadeString = repeatMade.text.toString()
                            total += addRepeat.text.toString().toInt()
                            sum.text  = "Sum:$total"
                            exerciseViewModel.addRepeat(exercise_id,set,addRepeat.text.toString().toInt(),total,weight)
                            addRepeat.text.clear()
                            return true
                        }
                        else -> {
                        }
                    }
                }
                return false
            }
        })

        val repeatAmount3 = findViewById<TextView>(R.id.repeat_amount_3)
        val repeatAmount4 = findViewById<TextView>(R.id.repeat_amount_4)
        val repeatAmount5 = findViewById<TextView>(R.id.repeat_amount_5)

        repeatAmount3.setOnClickListener {

            repeatAmount3.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.light_blue)
            repeatAmount4.backgroundTintList = null
            repeatAmount5.backgroundTintList = null
        }
        repeatAmount4.setOnClickListener {

            repeatAmount4.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.light_blue)
            repeatAmount3.backgroundTintList = null
            repeatAmount5.backgroundTintList = null
        }
        repeatAmount5.setOnClickListener {

            //repeatAmount5.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.light_blue)
            repeatAmount3.backgroundTintList = null
            repeatAmount4.backgroundTintList = null
        }


        val recyclerViewPreviousResult = findViewById<RecyclerView>(R.id.recycler_view_previous_result)
        val previousResult = exerciseViewModel.getLastRepeat(exercise_id)

        val recyclerViewBestResult = findViewById<RecyclerView>(R.id.recycler_view_best_result)
        val sumText = exerciseViewModel.maxFromAmountRepeat(exercise_id,2)
        if(sumText.amount.isNotEmpty()){
            bestSum.text = "Sum:" +  sumText.amount.subList(0,3).sum()


            weightButton.text = "Weight:$weight kg"
            val adapter =  ResultAdapter(this)
            adapter.setResult(sumText.amount.subList(0,3))
            recyclerViewBestResult.adapter = adapter
            recyclerViewBestResult.setLayoutManager(GridLayoutManager(this, 1))


            previousSum.text = "Sum:" +  previousResult.amount.subList(0,3).sum()
            val adapterPreviousResult = PreviousResultAdapter(this)
            adapterPreviousResult.setResult(previousResult.amount.subList(0,3))
            recyclerViewPreviousResult.adapter = adapterPreviousResult
            recyclerViewPreviousResult.setLayoutManager(GridLayoutManager(this, 1))
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




    fun days(dateToday:String,dateStart:String):Int{
        //Year,month,day

        val listToday = dateToday.split(".")
        val listStart = dateStart.split(".")


        val daysMonthPass = arrayListOf<Int>(0,31,59,90,120,151,181,212,243,273,304,334,365)

        val year = listToday[0].toInt() - listStart[0].toInt()
        Log.v("MainActivity",listToday.toString())
        var month = daysMonthPass[listStart[1].toInt()] - daysMonthPass[listToday[1].toInt()]
        if (month<0){
            month *= -1
        }
        var day = 0
        day = if(listToday[1].toInt() >= listStart[1].toInt()){
            listToday[2].toInt() - listStart[2].toInt()
        } else{
            listStart[2].toInt() - listToday[2].toInt()

        }

        val today  = (365 * year) + month + day
        Log.v("MainActivity","year$year,month:$month,day:$day")
        return today

    }


}