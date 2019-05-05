package fr.emile.ddmanager



abstract class Character(var imgId:Int, var nom:String):keyableForMap
{
    //must be different for each personnage in a container
    override fun toKey()=nom
}

fun List<Character>.getOnlyIdImage():List<Int>{
    val list= mutableListOf<Int>()
    this.forEach { list.add(it.imgId) }
    return list
}