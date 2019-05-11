package fr.emile.ddmanager

import android.app.Activity
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

class FragmentHeroPower : Fragment() {

    var listAdapter:ListAdapterPowerHero? = null
    lateinit var listView: ListView

    var listToShow:MutableList<Power>? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_power,
                container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        listView=(context as Activity).findViewById(R.id.listViewPower)

        if(listToShow!=null)
        {
            initAdapter()
        }



        super.onActivityCreated(savedInstanceState)
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



}