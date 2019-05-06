package fr.emile.ddmanager


import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView

/**
 * Created by emile on 07/04/2019.
 */
class CustomFragment : Fragment() {

    var gridAdapter:CustomGridAdapter? = null
    lateinit var gridView:GridView

    var listToShow:MutableList<Monster>? = null




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment,
                container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        gridView=(context as Activity).findViewById(R.id.gridViewMonster)

        if(listToShow!=null)
        {
            initAdapter()
        }

        initGridView()

        super.onActivityCreated(savedInstanceState)
    }

    fun setAdapter(listToShow:MutableList<Monster>)
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
        gridAdapter = CustomGridAdapter(context as Activity, listToShow!!)
        gridView.numColumns = gridAdapter!!.nbrColumnGridView
        gridView.adapter = gridAdapter
    }

    private fun initGridView()
    {
        gridView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position: Int,_ ->

            //get the monster at this position
            val monsterClicked= listToShow!![position]
            (context as MainAcvtivity).playerSelectMonster(monsterClicked)
        }
    }


}