package fr.emile.ddmanager


import android.app.Activity
import android.os.Bundle
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

    lateinit var gridAdapter:CustomGridAdapter
    lateinit var gridView:GridView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_fragment,
                container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        gridAdapter= CustomGridAdapter(context as Activity,Monster.containerRef.listElmt.values.toMutableList())
        gridView=(context as Activity).findViewById(R.id.gridViewMonster)
        gridView.numColumns=gridAdapter.nbrColumnGridView
        gridView.adapter=gridAdapter

        initGridView()

        super.onActivityCreated(savedInstanceState)
    }

    private fun initGridView()
    {
        gridView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position: Int,_ ->

            //get the monster at this position
            val monsterClicked=Monster.containerRef[position]!!
            (context as MainAcvtivity).playerSelectMonster(monsterClicked)

        }
    }
}