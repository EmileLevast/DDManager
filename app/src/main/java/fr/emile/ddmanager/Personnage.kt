package fr.emile.ddmanager

/**
 * Created by emile on 10/03/2019.
 */

class Personnage private constructor(vie:Int,
                                     var mana:Int,
                                     xpNeededLevel1:Int,
                                     imgId:Int,
                                     nom:String,
                                     var listPowers:MutableList<Power>): Character(imgId,nom)
{
    var levelStat=Level(xpNeededLevel1)

    var containerKills= Container<Monster>()

    var vie=vie
    set(value)
    {
        if(value<0)field=0
        else if(value>referenceInitCara.vie)field=referenceInitCara.vie
    }

    //just null for the companion object
    lateinit var referenceInitCara:Personnage

    constructor(persoRef:Personnage):this(persoRef.vie,persoRef.mana,persoRef.levelStat.xpNeededLevel1,
            persoRef.imgId,persoRef.nom, persoRef.listPowers.toMutableList())
    {
        referenceInitCara=persoRef
    }

    //if kill monster is false the player lose xp
    fun selectMonster(monsterSelected: Monster,killMonster:Boolean)
    {
        if(killMonster)
        {
            //there is already a monster with this name we change his number
            while(containerKills.contains(monsterSelected))monsterSelected.number++

            containerKills.add(monsterSelected)

            //if the player win a level
            if(levelStat.addXp(monsterSelected.costXp)>=1)
            {
                vie+=referenceInitCara.vie/4
            }

        }else
        {
            containerKills.remove(monsterSelected)
            levelStat.addXp(monsterSelected.costXp*(-1))
        }
    }

    companion object {
        val containerRef=Container(
                Personnage(15,0,30,R.drawable.regdardescriptioncard,"Regdar",Power.listPowerLidda),
                Personnage(10,0,20,R.drawable.liddadescriptioncard,"Lidda",Power.listPowerLidda),
                Personnage(9,11,26,R.drawable.myaliedescriptioncard,"Myalië",Power.listPowerLidda),
                Personnage(11,9,24,R.drawable.jozandescriptioncard,"Jozan",Power.listPowerLidda))
    }
}

class Power (var idImg:Int,var textExplanation:String,var availableLevel:Int): ToIdDrawable
{
    override fun getDrawableId()=idImg

    companion object {
        val listPowerLidda= mutableListOf(
                Power(R.drawable.gnollcard,"Pouvoir de niveau 1 lidda tout pourrave",1)
        )
    }
}