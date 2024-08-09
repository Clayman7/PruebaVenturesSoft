package com.cyberapp.pruebaventuressoft.ui.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.cyberapp.pruebaventuressoft.R

import com.cyberapp.pruebaventuressoft.databinding.ActivityMainBinding
import com.cyberapp.pruebaventuressoft.ui.detail.ui.detail.DetailPruebaActivity
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initListeners()
    }

    private fun initListeners() {
        binding.btnLogin.setOnClickListener {
            respuestaApi()
        }
    }

    private fun respuestaApi(){

        binding.progresbar.isVisible = true

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val codigo = mainViewModel.getApi(binding.tieUser.text.toString(), binding.tiePassword.text.toString())
                    // Actualiza la interfaz de usuario o realiza otras operaciones en el contexto de Dispatchers.Main
                    withContext(Dispatchers.Main) {
                        if(codigo.toString() == "ET000"){
                            binding.progresbar.isVisible = false
                            navegateToDetail()
                        }else{
                            binding.progresbar.isVisible = false
                            elecionMensaje(codigo.toString())
                        }


                    }
                } catch (e: Exception) {
                        println("Error al obtener el código: ${e.message}")
                }
            }
       }

    private fun navegateToDetail(){
        startActivity(Intent(this, DetailPruebaActivity::class.java))
    }

    private fun elecionMensaje(codigo: String){
        when(codigo){
            "ET201" -> showDialog(getString(R.string.ET201))
            "ET202" -> showDialog(getString(R.string.ET202))
            "ET216" -> showDialog(getString(R.string.ET2016))
            "ET217" -> showDialog(getString(R.string.ET2017))
        }
    }

    private fun showDialog(mensaje: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_mensaje)

        val aviso: TextView = dialog.findViewById(R.id.tvMensaje)
        val aceptar: Button = dialog.findViewById(R.id.btnAceptar)

        aviso.text = mensaje

        //configura el boton aceptar
        aceptar.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}