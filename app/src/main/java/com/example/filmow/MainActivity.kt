package com.example.filmow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var filmeAdapter: FilmeRecyclerAdapter
    private lateinit var dao: FilmeDAO
    private lateinit var lista: ArrayList<Filme>
    val FORM_ADD = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        dao = FilmeDAO(this)
        this.lista = arrayListOf()

        //inserirFilme()
        this.lista = this.dao.get()
        initRecyclerView()
        filmeAdapter.submitList(lista)


        fab.setOnClickListener { view ->
            val itForm = Intent(this, FormActivity::class.java)
            startActivityForResult(itForm, FORM_ADD)
        }
    }

    private fun initRecyclerView(){
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            filmeAdapter = FilmeRecyclerAdapter()
            adapter = filmeAdapter
        }
    }

    fun atualizar(){
        this.lista.clear()
        this.lista.addAll(this.dao.get())
        filmeAdapter.update()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == FORM_ADD){
                val filme = data?.getSerializableExtra("FILME") as Filme
                this.dao.insert(filme)
                this.atualizar()
            }
        }
    }
}
