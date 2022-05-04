package com.example.fit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ExerciseAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<ExerciseAdapter.CategoryViewHolder>(){
    val mContext = context

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var categoryList = listOf<Exercise>()

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val categoryName:TextView = itemView.findViewById(R.id.exercise_name)
        val categoryImageView:ImageView = itemView.findViewById(R.id.exercise_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = inflater.inflate(R.layout.exercise_sample,parent,false)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            val current = categoryList[position]
            holder.categoryName.text = current.exercise_name
            holder.categoryImageView.setOnClickListener {
                val intent = Intent(mContext,RepeatActivity::class.java)
                intent.putExtra("id",current.exercise_id)
                mContext.startActivity(intent)
            }
            //val image = File(mContext.filesDir.toString() + "/images" ,current.exercise_image)
//            Picasso.get().load(current.exercise_image).resize(650,800).into(holder.categoryImageView)
    }

    internal fun setCategory(exerciseList:List<Exercise>){
        this.categoryList = exerciseList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }







}