package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentCreateTodoBinding
import com.example.todoapp.model.Todo
import com.example.todoapp.viewModel.DetailTodoViewModel
import com.example.todoapp.viewModel.ListTodoViewModel

class CreateTodoFragment : Fragment() {
    private lateinit var viewModel:DetailTodoViewModel
    private lateinit var binding:FragmentCreateTodoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val radioID = binding.radioGroupPriority.checkedRadioButtonId

            val radio = view.findViewById<RadioButton>(radioID)
            val priority = radio.tag.toString().toInt()

            var todo = Todo(binding.txtTitle.text.toString(), binding.txtNotes.text.toString(), priority)

            viewModel.addTodo(todo)

            Toast.makeText(it.context, "Todo added", Toast.LENGTH_SHORT).show()

            Navigation.findNavController(it).popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(inflater,container,false)
        return binding.root
    }
}