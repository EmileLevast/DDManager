package fr.emile.ddmanager

import android.support.annotation.NonNull

/**
 * Created by emile on 10/03/2019.
 */

//Interdit de ne donner aucun element a notre container
class Container<T>(@NonNull vararg listElmt:T) where T: keyableForMap {

    private val listKeyElmt= arrayListOf<String>()
    val listElmt= hashMapOf<String,T>()
    var index=0

    init {
        //we save the object passed in constructor in our list
        for( item in listElmt)
        {
            this.listKeyElmt.add(item.toKey())
            this.listElmt[item.toKey()] = item
        }
    }

    fun indexUp()
    {
        if(index<listKeyElmt.size-1)
        {
            index++
        }
        else
        {
            index=0
        }
    }

    fun indexDown()
    {
        if(index>0)
        {
            index--
        }
        else
        {
            index=listKeyElmt.size
        }
    }

    //return actual item and point the nextè
    fun getItemNext(): T {

        //we can assert that never return null because we take a name in a list of key
        val keyElt=listElmt[listKeyElmt[index]]!!
        indexUp()
        return keyElt
    }

    fun getEltWith(key:String) :T?
    {
        return listElmt[key]
    }

    operator fun get(key:String):T?
    {
        return listElmt[key]
    }

    operator fun get(index:Int):T?
    {
        return listElmt[listKeyElmt[index]]
    }

    // and go to next elt and return it
    fun getNextItem():T
    {
        //we can assert that never return null because we take a name in a list of key
        indexUp()
        return listElmt[listKeyElmt[index]]!!
    }


}