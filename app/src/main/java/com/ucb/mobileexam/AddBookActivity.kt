package com.ucb.mobileexam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddBookActivity : AppCompatActivity() {
    lateinit var input_title_book: EditText
    lateinit var input_body_book: EditText
    lateinit var button_book: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        input_title_book = findViewById(R.id.input_title_book)
        input_body_book = findViewById(R.id.input_body_book)
        button_book = findViewById(R.id.button_book)
        button_book.setOnClickListener {
            GlobalScope.launch {
                val bookDao = AppRoomDatabase.getDatabase(applicationContext).bookDato()
                val repository = BookRepository(bookDao)
                repository.insert(Book(input_title_book.getText().toString(), input_body_book.getText().toString()))
                val lista = repository.getListBooks()
                lista.forEach {
                    Log.d("DBTEST","Id book = ${it.id}, Title: ${it.title}, Body: ${it.body}")
                }
            }
        }
    }
}