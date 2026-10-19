package com.example.hw3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.R

class MainMealFragment : Fragment(R.layout.fragment_main_meal) {


    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = view.findViewById<RadioGroup>(R.id.rgMainMeal) // 確保 XML 中 RadioGroup 的 ID 對應
        val btnDone = view.findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId != -1) {

                val radioButton = view.findViewById<RadioButton>(selectedId)
                val selectedMeal = radioButton.text.toString()


                viewModel.setMainMeal(selectedMeal)


                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please select a main meal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}