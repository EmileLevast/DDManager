package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.IKeyableForMap
import fr.emile.ddmanager.IShowImage


abstract class Character(override var imgId:Int, var nom:String): IKeyableForMap, IShowImage
{
    //must be different for each personnage in a container
    override fun toKey()=nom
}

