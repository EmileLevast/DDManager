package fr.emile.ddmanager.gestionFragment.heroPower

import android.app.Activity
import android.graphics.Typeface
import android.view.LayoutInflater
import android.widget.ListView
import android.widget.TextView
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.mainClass.Power
import fr.emile.ddmanager.R
import fr.emile.ddmanager.game
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment

class FragmentHeroPower : CustomFragment() {

    var listAdapter: ListAdapterPowerHero? = null

    //listView to show monster
    lateinit var listView: ListView

    //the monster list
    var listToShow:MutableList<Power>? = null

    //the layout we want for this fragment
    override val idLayoutToInflate: Int=R.layout.fragment_power

    override fun createView(inflater: LayoutInflater) {
        val layout=inflater.inflate(R.layout.layout_list_view,null)
        val txtView=layout.findViewById<TextView>(R.id.textPowerDescription)
        val typeface= Typeface.createFromAsset(context?.assets,"font/bilboregular.ttf")
        txtView.typeface=typeface
    }


    override fun activityCreated() {
        listView=(context as Activity).findViewById(R.id.listViewPower)

        if(listToShow!=null)
        {
            initAdapter()
        }
    }

    fun setAdapter(listToShow:MutableList<Power>)
    {

        this.listToShow=listToShow

        if(context!=null)
        {
            initAdapter()
        }

    }

    //instantiate listToShow before this method
    fun initAdapter()
    {
        listAdapter = ListAdapterPowerHero(context as Activity, listToShow!!)
        listView.adapter = listAdapter
    }

    override fun launch() {

        val joueur=game.joueur
        setAdapter(joueur.listPowers.filter
        {
            joueur.levelStat.currentLevel>=it.availableLevel
        }.toMutableList())

    }

}