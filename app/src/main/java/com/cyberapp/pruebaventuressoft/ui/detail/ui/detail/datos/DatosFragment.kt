package com.cyberapp.pruebaventuressoft.ui.detail.ui.detail.datos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.cyberapp.pruebaventuressoft.R
import com.cyberapp.pruebaventuressoft.databinding.FragmentDatosBinding


class DatosFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_datos, container, false)
    }

}