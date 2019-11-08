package com.example.filmow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_form.*
import org.w3c.dom.Text

class FormActivity : AppCompatActivity() {
    private lateinit var etTitulo: EditText
    private lateinit var sbAvaliacao: SeekBar
    private lateinit var tvAvaliacao: TextView
    private lateinit var btSalvar: Button
    private lateinit var btCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        this.etTitulo = findViewById(R.id.etFormTitulo)
        this.sbAvaliacao = findViewById(R.id.sbFormAvaliacao)
        this.tvAvaliacao = findViewById(R.id.tvFormAvaliacao)
        this.btSalvar = findViewById(R.id.btFormSalvar)
        this.btCancelar = findViewById(R.id.btFormCancelar)

        this.btSalvar.setOnClickListener(OnClickBotao())
        this.btCancelar.setOnClickListener({
            finish()
        })

        this.sbAvaliacao.setOnSeekBarChangeListener(ChangeSeekBar())
        this.tvAvaliacao.text = this.sbAvaliacao.progress.toString()

    }

    inner class OnClickBotao : View.OnClickListener{
        override fun onClick(v: View?) {
            val titulo = this@FormActivity.etTitulo.text.toString()
            val avaliacao = this@FormActivity.tvAvaliacao.text.toString()
            val filme = Filme(titulo, avaliacao)
            val itPessoa = Intent()

            itPessoa.putExtra("FILME", filme)
            setResult(Activity.RESULT_OK, itPessoa)
            finish()
        }
    }

    inner class ChangeSeekBar : SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            this@FormActivity.tvAvaliacao.text = progress.toString()
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }

    }

}