package com.example.filmow

import android.content.ContentValues
import android.content.Context
import com.example.filmow.BancoHelper


class FilmeDAO{

    var banco: BancoHelper

    constructor(context: Context){
        this.banco = BancoHelper(context)
    }

    // create
    fun insert(f: Filme){
        val cv = ContentValues()
        cv.put("titulo", f.titulo)
        cv.put("avaliacao", f.avaliacao)
        this.banco.writableDatabase.insert("filme",null, cv)
    }

    // all
    fun get(): ArrayList<Filme>{
        val colunas = arrayOf("id", "titulo", "avaliacao")
        val lista = ArrayList<Filme>()

        val c = this.banco.readableDatabase.query("filme", colunas, null, null, null, null, null)

        c.moveToFirst()

        if (c.count > 0){
            do {
                val id = c.getInt(c.getColumnIndex("id"))
                val titulo = c.getString(c.getColumnIndex("titulo"))
                val avaliacao = c.getString(c.getColumnIndex("avaliacao"))
                lista.add(Filme(id, titulo, avaliacao))
            }while(c.moveToNext())
        }

        return lista
    }


    // find
    fun get(index: Int): Filme?{
        val colunas = arrayOf("id", "titulo", "avaliacao")
        val where = "id = ?"
        val pwhere = arrayOf(index.toString())

        val c = this.banco.readableDatabase.query("filme", colunas, where, pwhere, null, null, null)

        c.moveToFirst()

        if (c.count > 0){
            val id = c.getInt(c.getColumnIndex("id"))
            val titulo = c.getString(c.getColumnIndex("titulo"))
            val avaliacao = c.getString(c.getColumnIndex("avaliacao"))
            return Filme(id, titulo, avaliacao)
        }

        return null
    }

    // update
    fun update(f: Filme){
        val where = "id = ?"
        val pwhere = arrayOf(f.id.toString())
        val cv = ContentValues()
        cv.put("id", f.id)
        cv.put("titulo", f.titulo)
        cv.put("avaliacao", f.avaliacao)

        this.banco.writableDatabase.update("filme", cv, where, pwhere)
    }

    // delete
    fun delete(id: Int){
        val where = "id = ?"
        val pwhere = arrayOf(id.toString())

        this.banco.writableDatabase.delete("filme", where, pwhere)
    }
}