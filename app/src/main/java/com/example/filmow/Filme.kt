package com.example.filmow

import java.io.Serializable

class Filme : Serializable{
    var id: Int
    var titulo: String
    var avaliacao: String

    constructor(titulo:String, avaliacao: String){
        this.id = -1
        this.titulo = titulo
        this.avaliacao = avaliacao
    }

    constructor(id: Int, titulo: String, avaliacao: String){
        this.id = id
        this.titulo = titulo
        this.avaliacao = avaliacao
    }

    override fun toString(): String {
        return "${id} - ${titulo} - ${avaliacao}"
    }
}