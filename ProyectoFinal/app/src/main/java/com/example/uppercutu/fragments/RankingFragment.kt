package com.example.uppercutu.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.uppercutu.R
import com.example.uppercutu.api.SportApi
import com.example.uppercutu.customZoom.CustomZoomLayout
import com.example.uppercutu.data.Pugiles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RankingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }
    /**
     * Configura las vistas tras haberse creado la vista del fragmento
     *
     * @param view La vista creada
     * @param savedInstanceState Informacion del estado previamente guardado
     *
     **/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup(view)
        fetchBoxingData()

    }


    private fun setup(view: View){
//        val boxersRV= view.findViewById<RecyclerView>(R.id.boxersRv)
//
//        val layoutManager = CustomZoomLayout(view.context)
//
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        layoutManager.reverseLayout = true
//        layoutManager.stackFromEnd = true
//
//        boxersRV.layoutManager = layoutManager
//
//        val snapHelper = LinearSnapHelper()
//        snapHelper.attachToRecyclerView(boxersRV)
//        boxersRV.isNestedScrollingEnabled = false
//
//        val boxers = ArrayList<Pugiles>()
//
//        boxers.add(
//            Pugiles(
//                "Kaiet",
//                "Peso Pesado",
//                5f,
//                "https://unsplash.com/es/fotos/puente-blanco-sobre-el-rio-durante-el-dia-RWlQTUHC_R8"
//
//            )
//        )

    }
    private fun fetchBoxingData() {
        val sportApi = SportApi()
        sportApi.fetchBoxingData { response ->
            Log.d("RankingFragment", response)
            // Aquí puedes manejar la respuesta de la API, por ejemplo, actualizar tus vistas con los datos obtenidos
        }
    }
}