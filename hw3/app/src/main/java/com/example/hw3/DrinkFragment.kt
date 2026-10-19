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

class DrinkFragment : Fragment(R.layout.fragment_drink) {

    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val radioGroup = view.findViewById<RadioGroup>(R.id.rgDrink)
        val btnDone = view.findViewById<Button>(R.id.btnDone)

        btnDone.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId != -1) {
                val radioButton = view.findViewById<RadioButton>(selectedId)
                val selectedDrink = radioButton.text.toString()


                viewModel.setDrink(selectedDrink)


                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please select a drink", Toast.LENGTH_SHORT).show()
            }
        }
    }
}