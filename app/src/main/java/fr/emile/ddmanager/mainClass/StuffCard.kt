package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.KeyableForMap
import fr.emile.ddmanager.R

class StuffCard(val nom:String, val type:StuffType, val idImg:Int):KeyableForMap
{
    override fun toKey()=nom

    enum class StuffType{
        ARME,ARTEFACT, SORT
    }

    companion object {
        val CardReference=arrayOf(StuffCard("great sword",StuffType.ARME, R.drawable.great_sword))
    }
}
