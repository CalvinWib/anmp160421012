package com.example.studentapp.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.databinding.StudentListItemBinding
import com.example.studentapp.model.Student
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(), ButtonDetailClickListener {
    class StudentViewHolder(val binding:StudentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.binding.student = studentList[position]
        holder.binding.listener = this

//        holder.binding.txtId.text = studentList[position].id
//        holder.binding.txtName.text = studentList[position].name
//
//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener { picasso, uri, exception -> exception.printStackTrace()
//        }
//        picasso.build().load(
//            studentList[position].photoUrl)
//            .into(holder.binding.imgStudent, object:Callback{
//                override fun onSuccess() {
//                    holder.binding.progressImage.visibility = View.INVISIBLE
//                    holder.binding.imgStudent.visibility = View.VISIBLE
//                }
//
//                override fun onError(e: Exception?) {
//                    Log.e("Picasso_error", e.toString())
//                }
//
//            })
//
//        holder.binding.btnDetail.setOnClickListener{
////            val action = StudentListFragmentDirections.actionDetailFragment()
////            Navigation.findNavController(it).navigate(action)
//
//            val context = holder.itemView.context
//            val intent = Intent(context, StudentDetailFragment::class.java)
//            intent.putExtra("student_id", studentList[position].id)
//            context.startActivity(intent)
//        }
    }

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        val id = v.tag.toString()
        val action = StudentListFragmentDirections.actionDetailFragment(id)
        Navigation.findNavController(v).navigate(action)
    }
}
