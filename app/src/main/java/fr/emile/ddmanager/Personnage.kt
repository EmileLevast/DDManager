package fr.emile.ddmanager

/**
 * Created by emile on 10/03/2019.
 */

class Personnage private constructor(var vie:Int,
                                     var mana:Int,
                                     imgId:Int,
                                     nom:String): Character(imgId,nom)
{
    var levelStat=Level()

    //just null for the companion object
    lateinit var referenceInitCara:Personnage

    constructor(persoRef:Personnage):this(persoRef.vie,persoRef.mana,persoRef.imgId,persoRef.nom)
    {
        referenceInitCara=persoRef
    }

    companion object {
        val containerRef=Container(
                Personnage(15,0,R.drawable.regdardescriptioncard,"Regdar"),
                Personnage(9,0,R.drawable.liddadescriptioncard,"Lidda"),
                Personnage(9,9,R.drawable.myaliedescriptioncard,"Myalië"),
                Personnage(9,9,R.drawable.jozandescriptioncard,"Jozan"))
    }
}