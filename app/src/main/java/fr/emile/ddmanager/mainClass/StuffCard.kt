package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.R

class StuffCard(nom:String, val type:StuffType,imgId:Int):Entity(imgId,nom)
{
    var isUsed=false
    override fun toKey()=nom

    /*public override fun clone(): StuffCard {
        return super.clone() as StuffCard
    }*/

    enum class StuffType{
        ARME,ARTEFACT, SORT,POTION
    }

    companion object {
        val CardReference=arrayOf(
                StuffCard("arc de liberte",StuffType.ARME, R.drawable.arc_de_liberte),
                StuffCard("arbalete de la foi",StuffType.ARME, R.drawable.arbalete_de_la_foi),
                StuffCard("potion de restauration partielle",StuffType.POTION, R.drawable.potion_de_restauration_partielle)
                )
    }



}
