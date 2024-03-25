package com.example.studentapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.studentapp.databinding.CarListItemBinding
import com.example.studentapp.model.Car
import com.example.studentapp.model.Student

class CarListAdapter(val CarList:ArrayList<Car>)
    :RecyclerView.Adapter<CarListAdapter.CarViewHolder>()
{
    class CarViewHolder(var binding:CarListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return CarList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtId.text = "ID: " + CarList[position].id
        holder.binding.txtName.text = CarList[position].name
        var c = CarList[position].colors as ArrayList<String>

        holder.binding.txtBrand.text = CarList[position].brand?.name + ", " + CarList[position].brand?.country

        holder.binding.txtColor.text="Color: "

        for(i in 0 until c.size){
            holder.binding.txtColor.append(c[i])
            if (i < c.size - 1) {
                holder.binding.txtColor.append(", ")
            }
        }

//        holder.binding.txtColor.text = CarList[position].colors.toString()
        holder.binding.txtYear.text = CarList[position].year.toString()
//        holder.binding.imageView. = CarList[position].images

        holder.binding.btnDetail.setOnClickListener{
            //
        }
    }

    fun updateCarList(newCarList:ArrayList<Car>){
        CarList.clear()
        CarList.addAll(newCarList)
        notifyDataSetChanged()
    }
}
