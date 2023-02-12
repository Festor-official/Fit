
package com.example.fit.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.fit.viewmodel.BodyViewModel
import com.example.fit.R
import com.example.fit.data.Body
import java.lang.IllegalStateException
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class AddBodyMeasurementsFragment : DialogFragment() {

    var count = 0
    var bodyList = arrayListOf<String>()
    private lateinit var bodyViewModel: BodyViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val listBody = arrayListOf<String>("height","shoulders","chest","biceps","talia","forearm","wrest","legs","caviar","weight")
        val alertDaySkip = activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val v: View = inflater.inflate(R.layout.fragment_add_measurements_body, null)
            val next = v.findViewById<TextView>(R.id.next)

            next.setOnClickListener{

                val valueBody = v.findViewById<TextView>(R.id.body_measurements).text.toString()
                bodyList.add(valueBody)
                count+=1

                if(count==10){
                    Log.v("MainActivity","10")
                    val dateFormat: DateFormat = SimpleDateFormat("yyyy.MM.dd")
                    val dateToday = dateFormat.format(Date())
                    bodyViewModel= ViewModelProvider(this).get(BodyViewModel::class.java)
                    val id  = bodyViewModel.lastMeasurement?.id?.plus(1)
                    if(id != null){
                        val body = Body(id,bodyList[0],bodyList[1],bodyList[2],bodyList[3],bodyList[4],bodyList[5],bodyList[6],bodyList[7],bodyList[8],bodyList[9],dateToday)
                        bodyViewModel.insert(body)
                    }
                    this.activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
                }else{
                    v.findViewById<TextView>(R.id.body_part).text = listBody[count]
                    Log.v("MainActivity", bodyList.toString() + " $count")
                }
            }
            builder.setMessage("New measures:")
                .setView(v)
                .setPositiveButton("Next",
                    DialogInterface.OnClickListener { dialog, id ->
                        count = 0
                        bodyList.clear()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        count = 0
                        bodyList.clear()
                    })

            builder.create()
        }?: throw IllegalStateException("Activity cannot be null")

        return alertDaySkip
    }




}