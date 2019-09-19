package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R


class Monster(imgId:Int,nom:String,var number:Int,var costXp:Int) : Character(imgId,nom){


    /*override fun clone(): Monster {
        return super<Character>.clone() as Monster
    }*/

    override fun toKey(): String {
        return super.toKey()+number
    }

    companion object {
        val containerRef= Container(
                Monster(R.drawable.gobelincard, "Gobelin", 1, 8),
                Monster(R.drawable.squelettecard, "Squelette", 1, 10),
                Monster(R.drawable.gobelourscard, "Gobelours", 1, 18),
                Monster(R.drawable.gnollcard, "Gnoll", 1, 17),
                Monster(R.drawable.vasecard, "Vase", 1, 23),
                Monster(R.drawable.ogrecard, "Ogre", 1, 24),
                Monster(R.drawable.charognardcard, "Charognard", 1, 28),
                Monster(R.drawable.necrophagecard, "Necrophage", 1, 31),
                Monster(R.drawable.spectercard, "Spectre", 1, 33),
                Monster(R.drawable.trollcard, "Troll", 1, 42),
                Monster(R.drawable.lichecard, "Liche", 1, 53))
    }
}