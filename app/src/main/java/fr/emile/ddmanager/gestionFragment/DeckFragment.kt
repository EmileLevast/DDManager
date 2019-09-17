package fr.emile.ddmanager.gestionFragment

import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import fr.emile.ddmanager.*
import fr.emile.ddmanager.gestionAffichage.WIDTH_SCREEN
import fr.emile.ddmanager.mainClass.StuffCard
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment

class DeckFragment : CustomFragment()
{
    var card: StuffCard?=null
    private var height:Int=0
    private var width:Int=0
    private lateinit var layout:RelativeLayout

    override val idLayoutToInflate: Int=R.layout.card_from_deck

    override fun createView(inflater: LayoutInflater) {
        //nothing to do
    }

    override fun activityCreated() {
        val img=activity?.findViewById<ImageView>(R.id.imgCardPicked)
        layout=activity?.findViewById(R.id.deckRootLayout)!!


        card?.let {
            width = (WIDTH_SCREEN!! * RatioWidthImageViewScreen).toInt()
            height=(width/calculateRatioWidthHeigth(it.idImg,context!!)).toInt()
            val param = RelativeLayout.LayoutParams(width,height )
            img?.layoutParams=param

            img?.setBackgroundResource(it.idImg)

            img?.setOnTouchListener { _, _ -> Log.w("msg","epee touchee");false }
        }

        //prevent more click on the chest
        layout.setOnTouchListener{ _, _ -> true }

    }

    override fun launch() {
        card=StuffCard.CardReference.random()
    }


    /*override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        layout = inflater.inflate(R.layout.card_from_deck, container, false)
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        initCustomLayout(layout as RelativeLayout,context!!)

        super.onActivityCreated(savedInstanceState)
    }*/



    companion object {
        const val RatioWidthImageViewScreen=1/5f
    }
}