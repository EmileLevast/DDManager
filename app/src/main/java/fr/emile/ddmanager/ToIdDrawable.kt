package fr.emile.ddmanager

interface ToIdDrawable {
    fun getDrawableId():Int
}

fun List<ToIdDrawable>.getOnlyIdImage():List<Int>{
    val list= mutableListOf<Int>()
    this.forEach { list.add(it.getDrawableId()) }
    return list
}