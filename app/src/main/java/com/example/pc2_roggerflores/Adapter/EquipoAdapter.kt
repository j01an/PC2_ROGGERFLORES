package com.example.pc2_roggerflores.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_roggerflores.Models.EquiposModel
import com.example.pc2_roggerflores.R

class EquipoAdapter(private var lstEquipos: List<EquiposModel>): RecyclerView.Adapter<EquipoAdapter.ViewHolder>()  {

    private var listener: AdapterView.OnItemClickListener? = null

    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onItemClick(equipo: EquiposModel)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txturl: TextView = itemView.findViewById(R.id.txtUrl)
        val txtNameEquipo: TextView = itemView.findViewById(R.id.txtNameEquipo)
        val txtTituloGanados: TextView = itemView.findViewById(R.id.txtTituloGanados)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_equipo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val equipos= lstEquipos[position]
        holder.txturl.text = equipos.url
        holder.txtNameEquipo.text = equipos.nombreEquipo
        holder.txtTituloGanados.text = equipos.titulos
    }

    override fun getItemCount(): Int {
        return lstEquipos.size
    }




}