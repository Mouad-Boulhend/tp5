package com.example.tp5

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimaux(val lista: MutableList<Animal>):
    RecyclerView.Adapter<AdapterAnimaux.AnimalViewHolder>() {
    inner class AnimalViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image = itemView.findViewById<ImageView>(R.id.imageAnimal)
        val nom = itemView.findViewById<TextView>(R.id.NomAnimal)
        val espece = itemView.findViewById<TextView>(R.id.especeAnimal)
        val dButton = itemView.findViewById<ImageButton>(R.id.buttonDetails)
        val sButton = itemView.findViewById<ImageButton>(R.id.buttonSupprimer)
        val check = itemView.findViewById<CheckBox>(R.id.checkbox)
    }
    override fun getItemCount(): Int { return lista.size }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.animal_item, parent, false)
        return AnimalViewHolder(view) }
    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = lista[position]
        holder.image.setImageResource(animal.Image)
        holder.nom.text = animal.nom
        holder.espece.text = animal.espece
        holder.check.isChecked = animal.isChecked

        holder.dButton.setOnClickListener {
            Toast.makeText(holder.itemView.context,
                "${animal.nom} est un ${animal.espece}",
                Toast.LENGTH_SHORT).show() }

        holder.itemView.setOnClickListener {

            if(animal.isChecked){
                animal.isChecked = !animal.isChecked
                holder.check.isChecked = animal.isChecked
                Animal.checkedAnimals.remove(animal)
            }else{
                animal.isChecked = !animal.isChecked
                holder.check.isChecked = animal.isChecked
                Animal.checkedAnimals.add(animal)
            }

            //holder.itemView.findViewById<TextView>(R.id.checkedAnimals).text = ""
            //var noms = ""
            //    Animal.checkedAnimals.forEach { x ->
            //        noms += x.nom
            //    holder.itemView.findViewById<TextView>(R.id.checkedAnimals).text = noms
            //}


        }

        holder.sButton.setOnClickListener {
            val alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Alert de suppression")
            alert.setMessage("Vous etes sure tu dois supprimer ${animal.nom}?")

            alert.setPositiveButton("Oui"){dialog, which ->
                lista.removeAt(position)
                Toast.makeText(holder.itemView.context, "${animal.nom} supprimer", Toast.LENGTH_SHORT).show()
                notifyItemRemoved(position)
            }

            alert.setNegativeButton("Annuler"){dialog, which ->
                Toast.makeText(holder.itemView.context, "Annuler", Toast.LENGTH_SHORT).show()
            }
            alert.show()
        }
    }
}


