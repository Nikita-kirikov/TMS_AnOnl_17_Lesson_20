package app.tms_lesson_19
/*
Переписать весь функционал на viewBinding
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.tms_lesson_19.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var headerAdd  : TextInputEditText
    private lateinit var textAdd : TextInputEditText
    private lateinit var buttonAdd : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var myAdapter : NoteAdapter
    private lateinit var important : CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        setContentView(binding.root)


        actionNote()
    }

    private fun initViews() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter = NoteAdapter()
        recyclerView.adapter = myAdapter
        myAdapter.notes = SingletonList.getNotes()

        headerAdd = binding.headerAdd
        textAdd = binding.textAdd
        buttonAdd = binding.buttonAdd
        important = binding.important
    }

    private fun actionNote() {
        buttonAdd.setOnClickListener {
            createNewItem()
        }

        myAdapter.onClick = {
            Toast.makeText(this, "Note ${it.header} is clicked", Toast.LENGTH_SHORT).show()
        }

        myAdapter.onGroupClick = {
            Toast.makeText(this, "Group ${it.text} is clicked", Toast.LENGTH_SHORT).show()
        }

        myAdapter.onLongClick = {
            SingletonList.importantItem(it)
            myAdapter.notifyItemChanged(myAdapter.itemCount)
        }
    }

    private fun createNewItem() {
        val header = textAdd.text.toString().trim()
        val text = textAdd.text.toString().trim()

        val newItem = if (text.isEmpty()) {
            Group(header)
        } else if (important.isChecked) {
            Note(header, text, Date().toString(), true)
        } else {
            Note(header, text, Date().toString())
        }

        SingletonList.addItem(newItem)
        myAdapter.notes = SingletonList.getNotes()

        myAdapter.notifyItemInserted(myAdapter.itemCount)

        headerAdd.text?.clear()
        textAdd.text?.clear()
        important.isChecked = false
    }
}