package fr.emile.ddmanager.gestionAffichage

import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import fr.emile.ddmanager.MainAcvtivity
import fr.emile.ddmanager.R
import fr.emile.ddmanager.gestionFragment.DeckFragment
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.gestionFragment.heroPower.FragmentHeroPower
import fr.emile.ddmanager.gestionFragment.monsterFragment.FragmentListMonsterKilled
import fr.emile.ddmanager.gestionFragment.monsterFragment.FragmentListMonsterToKill
import fr.emile.ddmanager.gestionFragment.monsterFragment.FragmentStuff

class ButtonManager(activity: MainAcvtivity){

    var buttonKillMonster: ImageButton=activity.findViewById(R.id.buttonKillMonster)
    var buttonLoseXpMonster: ImageButton=activity.findViewById(R.id.buttonLoseXpMonster)
    var buttonShowPower: ImageButton=activity.findViewById(R.id.buttonshowPower)
    var buttonDeckPicker: ImageButton=activity.findViewById(R.id.buttonDeckPicker)
    var buttonShowStuff:ImageButton=activity.findViewById(R.id.buttonShowStuff)


    init {
        buttonKillMonster.associateFragment(FragmentListMonsterToKill(),activity)
        buttonLoseXpMonster.associateFragment(FragmentListMonsterKilled(),activity)
        buttonShowPower.associateFragment(FragmentHeroPower(),activity)
        buttonDeckPicker.associateFragment(DeckFragment(),activity)
        buttonShowStuff.associateFragment(FragmentStuff(),activity)
    }

    private fun ImageButton.associateFragment(fragToAssociate:CustomFragment, activity: MainAcvtivity)
    {
        setOnClickListener { activity.generateFrag(fragToAssociate) }
    }

    fun placeButtons()
    {
        //we create the buttons for the personnage
        setDimensionButtonPersonnage(1,0,buttonKillMonster)
        setDimensionButtonPersonnage(1,1,buttonLoseXpMonster)
        setDimensionButtonPersonnage(8,0,buttonShowPower)
        setDimensionButtonPersonnage(8,1,buttonDeckPicker)
        setDimensionButtonPersonnage(8,2,buttonShowStuff)
    }

    private fun setDimensionButtonPersonnage(posX:Int,posY:Int,buttonToSet: View)
    {

        val width= (WIDTH_SCREEN!!* ((1f- RAPPORT_IMAGE_ECRAN_X)/2f)).toInt()
        val height=width// (HEIGHT_SCREEN!!* RAPPORT_IMAGE_ECRAN_Y).toInt()

        val paramRelativeLayout= RelativeLayout.LayoutParams(width,height)
        paramRelativeLayout.marginStart=width*posX
        paramRelativeLayout.topMargin=height*(posY)

        buttonToSet.layoutParams=paramRelativeLayout
    }
}