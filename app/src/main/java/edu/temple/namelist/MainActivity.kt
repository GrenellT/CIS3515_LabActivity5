package edu.temple.namelist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var names: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        names = mutableListOf("Kevin Shaply", "Stacey Lou", "Gerard Clear", "Michael Studdard", "Michelle Studdard")

        val spinner = findViewById<Spinner>(R.id.spinner)
        val nameTextView = findViewById<TextView>(R.id.textView)

        with (spinner) {
            adapter = CustomAdapter(names, this@MainActivity)
            onItemSelectedListener = object: OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    p0?.run {
                        nameTextView.text = getItemAtPosition(p2).toString()
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }

        val deleteButton = findViewById<View>(R.id.deleteButton)

        // Set OnClickListener for the deleteButton
        deleteButton.setOnClickListener {
            val selectedIndex = spinner.selectedItemPosition
            (names as MutableList).removeAt(selectedIndex)
            (spinner.adapter as BaseAdapter).notifyDataSetChanged()

            // Set selection to index 0
            val resetIndex = 0
            spinner.setSelection(resetIndex)

            // Update the nameTextView if names list is not empty
            if (names.isNotEmpty()) {
                val selectedName = names.getOrNull(resetIndex) ?: ""
                nameTextView.text = selectedName
            } else {
                // Hide the deleteButton if no names are available
                nameTextView.text = "No Names In List"
                nameTextView.textSize = 20f
                deleteButton.visibility = View.GONE
            }
        }

    }
}