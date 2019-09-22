package fr.emile.ddmanager.gestionFragment

import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import fr.emile.ddmanager.R
import fr.emile.ddmanager.calculateRatioWidthHeigth
import fr.emile.ddmanager.game
import fr.emile.ddmanager.gestionAffichage.WIDTH_SCREEN
import fr.emile.ddmanager.mainClass.StuffCard
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment

class DeckFragment : CustomFragment()
{
    var card: StuffCard?=null
    private var height:Int=0
    private var width:Int=0
    private lateinit var layout:RelativeLayout

    override val idLayoutToInflate: Int= R.layout.card_from_deck

    override fun createView(inflater: LayoutInflater) {
        //nothing to do
    }

    override fun activityCreated() {
        val img=activity?.findViewById<ImageView>(R.id.imgCardPicked)
        layout=activity?.findViewById(R.id.deckRootLayout)!!

        //implement the things to do when click on "Prendre"
        activity?.findViewById<Button>(R.id.buttonYes)?.setOnClickListener{
            activity?.supportFragmentManager?.popBackStack()
            game.playerPickCard(card!!)
        }

        //implement the things to do when click on "Jeter"
        activity?.findViewById<Button>(R.id.buttonNo)?.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        card?.let {
            width = (WIDTH_SCREEN!! * RatioWidthImageViewScreen).toInt()
            height=(width/calculateRatioWidthHeigth(it.imgId,context!!)).toInt()
            val param = RelativeLayout.LayoutParams(width,height )
            img?.layoutParams=param

            img?.setBackgroundResource(it.imgId)
        }

        //prevent more click on the chest
        layout.setOnTouchListener{ _, _ -> true }

    }

    override fun launch() {
        card=game.piocher()
    }

    companion object {
        const val RatioWidthImageViewScreen=1/5f
    }
}