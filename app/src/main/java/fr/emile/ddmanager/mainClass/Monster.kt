package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R


class Monster(imgId:Int,nom:String,var number:Int,var costXp:Int) : Entity(imgId,nom){


    /*override fun clone(): Monster {
        return super<Entity>.clone() as Monster
    }*/

    override fun toKey(): String {
        return super.toKey()+number
    }

    constructor():this(0,"monster_Unknown",0,0)//only for Room

    companion object {
        val containerRef= Container(
                Monster(R.drawable.gobelincard, "Gobelin", 1, 5),
                Monster(R.drawable.squelettecard, "Squelette", 1, 8),
                Monster(R.drawable.gobelourscard, "Gobelours", 1, 15),
                Monster(R.drawable.gnollcard, "Gnoll", 1, 16),
                Monster(R.drawable.vasecard, "Vase", 1, 25),
                Monster(R.drawable.ogrecard, "Ogre", 1, 26),
                Monster(R.drawable.charognardcard, "Charognard", 1, 28),
                Monster(R.drawable.necrophagecard, "Necrophage", 1, 33),
                Monster(R.drawable.spectercard, "Spectre", 1, 36),
                Monster(R.drawable.trollcard, "Troll", 1, 45),
                Monster(R.drawable.lichecard, "Liche", 1, 53))
    }
}