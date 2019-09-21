package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.IKeyableForMap
import fr.emile.ddmanager.IShowImage


abstract class Entity(override var imgId:Int, var nom:String): IKeyableForMap, IShowImage
{
    //must be different for each personnage in a container
    override fun toKey()=nom

    override fun equals(other: Any?): Boolean {
        return when {
            this===other -> true
            (other as? Entity)?.nom==this.nom -> true
            else -> false
        }
    }

    override fun hashCode(): Int {
        val prime=31
        val result=1
        return result*prime+nom.hashCode()
    }
}

