package fr.emile.ddmanager.gestionFragment.monsterFragment


import android.app.Activity
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.GridView
import fr.emile.ddmanager.MainAcvtivity
import fr.emile.ddmanager.mainClass.Monster
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.R
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.joueur
import fr.emile.ddmanager.mainClass.StuffCard

/**
 * Created by emile on 07/04/2019.
 */
abstract class FragmentMonsterList : CustomFragment() {
    override val idLayoutToInflate: Int=R.layout.fragment_monster

    var gridAdapterMonster: GridAdapterMonster? = null

    lateinit var gridView:GridView

    var listToShow:MutableList<Monster>? = null
    var joueurAction: (Personnage.(Monster) -> Unit)? = null

    final override fun createView(inflater: LayoutInflater) {
        //nothing to do
    }

    final override fun activityCreated() {
        gridView=(context as Activity).findViewById(R.id.gridViewMonster)
        if(listToShow!=null)
        {
            initAdapter()
        }
        initGridView()
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
    private fun initAdapter()
    {
        gridAdapterMonster = GridAdapterMonster(context as Activity, listToShow!!)
        gridView.numColumns = gridAdapterMonster!!.nbrColumnGridView
        gridView.adapter = gridAdapterMonster
    }

    private fun initGridView()
    {
        gridView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position: Int,_ ->

            //get the monster at this position
            val monsterClicked= listToShow!![position]

            (context as MainAcvtivity).playerSelectMonster(monsterClicked.clone(),joueurAction)
        }
    }
}

class FragmentListMonsterToKill:FragmentMonsterList() {
    override fun launch() {
        setAdapter(Monster.containerRef.toListSorted())
        joueurAction= Personnage::killMonster
    }
}

class FragmentListMonsterKilled:FragmentMonsterList() {
    override fun launch() {
        setAdapter(joueur.containerKills.toListSorted())
        joueurAction= Personnage::loseMonsterXp
    }
}

