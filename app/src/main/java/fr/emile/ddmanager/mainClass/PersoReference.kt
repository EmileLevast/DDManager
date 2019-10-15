package fr.emile.ddmanager.mainClass

import androidx.room.Embedded
import androidx.room.Ignore
import fr.emile.ddmanager.Container
import fr.emile.ddmanager.IKeyableForMap
import fr.emile.ddmanager.R

data class PersoReference(var vie:Int=0,
                          var mana:Int=0,
                          var xpNeededLevel1:Int=0,
                          var imgId:Int=0,
                          var nom:String,
                          var listPowers:List<Power>,
                          var listEquipment:List<String>) :IKeyableForMap{

    override fun toKey()=nom

    companion object {
        val containerRef= Container(
                PersoReference(15, 0, 36, R.drawable.regdardescriptioncard, "Regdar", Power.listPowerRegdar,listOf("epee_large_a_deux_mains")),
                PersoReference(10, 0, 20, R.drawable.liddadescriptioncard, "Lidda", Power.listPowerLidda,listOf("sarbacane_empoisonnee","amulette_de_yondalla")),
                PersoReference(9, 11, 29, R.drawable.myaliedescriptioncard, "Myalië", Power.listPowerMyalie,listOf("arc_beni_des_elfes","mains_enflammees")),
                PersoReference(11, 9, 25, R.drawable.jozandescriptioncard, "Jozan", Power.listPowerJozan,listOf("arbalete_sacree_de_pelor","restauration_supreme")))
    }
}