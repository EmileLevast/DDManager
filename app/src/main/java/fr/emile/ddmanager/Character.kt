package fr.emile.ddmanager



abstract class Character(var imgId:Int, var nom:String):KeyableForMap,ToIdDrawable
{
    //must be different for each personnage in a container
    override fun toKey()=nom

    override fun getDrawableId() = imgId
}

