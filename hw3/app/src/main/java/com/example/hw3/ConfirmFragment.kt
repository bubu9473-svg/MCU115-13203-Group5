package com.example.hw3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class ConfirmFragment : Fragment(R.layout.fragment_confirm) {


    private val viewModel: OrderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val tvSummary = view.findViewById<TextView>(R.id.tvOrderSummary)
        val btnConfirm = view.findViewById<Button>(R.id.btnConfirm)


        viewModel.order.observe(viewLifecycleOwner) { order ->
            val mainMealText = order.mainMeal.ifEmpty { "Not selected" }
            val sideDishesText = order.sideDishes.joinToString(", ").ifEmpty { "Not selected" }
            val drinkText = order.drink.ifEmpty { "Not selected" }

            tvSummary.text = """
【 Your Order Summary 】

🍔 Main Meal: $mainMealText

🍟 Side Dishes: $sideDishesText

🥤 Drink: $drinkText
""".trimIndent()
        }

// 按下 Confirm 按鈕後，直接在這裡執行講義規定的訂單完整性驗證
        btnConfirm.setOnClickListener {
            val order = viewModel.order.value

            if (order?.isComplete() != true) {

                Toast.makeText(
                    requireContext(),
                    "Please select a main meal, at least one side dish, and a drink.",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                AlertDialog.Builder(requireContext())
                    .setTitle("Submit Order")
                    .setMessage("""
🍔 Main: ${order.mainMeal}
🍟 Sides: ${order.sideDishes.joinToString(", ")}
🥤 Drink: ${order.drink}

Submit this order?
""".trimIndent())
                    .setPositiveButton("Submit") { dialog, _ ->
                        dialog.dismiss()
                        Toast.makeText(requireContext(), "Order submitted! 🍔", Toast.LENGTH_SHORT).show()


                        viewModel.resetOrder()
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }
    }
}