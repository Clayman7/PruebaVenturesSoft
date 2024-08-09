package com.cyberapp.pruebaventuressoft.ui.detail.ui.detail.empleados

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.cyberapp.pruebaventuressoft.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmpleadosFragment : Fragment() {

    private val empleadosViewModel : EmpleadosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empleados, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Llama a la función para cargar empleados
        CoroutineScope(Dispatchers.IO).launch{
            try{
                empleadosViewModel.getEmpleados()
            }catch(e: Exception){

            }
        }

    }


}