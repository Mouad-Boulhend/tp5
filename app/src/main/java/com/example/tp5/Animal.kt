package com.example.tp5

class Animal (val nom: String, val espece: String, val Image: Int, var isChecked: Boolean= false){
    companion object{
        var checkedAnimals: MutableList<Animal> = mutableListOf()
    }
}





