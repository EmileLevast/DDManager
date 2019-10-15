package fr.emile.ddmanager.gestionFragment.monsterFragment


import android.app.Activity
import android.view.LayoutInflater
import android.widget.AdapterView
import android.widget.GridView
import fr.emile.ddmanager.MainAcvtivity
import fr.emile.ddmanager.mainClass.Monster
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.R
import fr.emile.ddmanager.IShowImage
import fr.emile.ddmanager.game
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.mainClass.StuffCard

/**
 * Created by emile on 07/04/2019.
 */
abstract class FragmentShowImageList : CustomFragment() {
    override val idLayoutToInflate: Int=R.layout.fragment_monster

    //value to give the number of column in the grid view
    open val nbrColumnGridView=4

    var gridAdapterMonster: GridAdapterMonster? = null

    lateinit var gridView:GridView

    var listToShow:MutableList<out IShowImage>? = null
    var joueurAction: (Personnage.(IShowImage) -> Unit)? = null

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

    fun setAdapter(listToShow:MutableList<out IShowImage>)
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
        gridAdapterMonster = GridAdapterMonster(context as Activity, listToShow!!,nbrColumnGridView)
        gridView.numColumns = gridAdapterMonster!!.nbrColumnGridView
        gridView.adapter = gridAdapterMonster
    }

    protected open fun initGridView()
    {
        gridView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position: Int,_ ->

            //get the monster at this position
            val imageClicked= listToShow!![position]

            (context as MainAcvtivity).playerSelectIShowImage(imageClicked,joueurAction)
        }
    }
}


class FragmentListMonsterToKill:FragmentShowImageList() {
    override fun launch() {

        setAdapter(Monster.containerRef.toListSorted())
        joueurAction= Personnage::killMonster
    }
}

class FragmentListMonsterKilled:FragmentShowImageList() {
    override fun launch() {

        setAdapter(game.joueur.monsterKilled)//.toListSorted())
        joueurAction= Personnage::loseMonsterXp
    }
}

class FragmentListPerso:FragmentShowImageList() {
    override val nbrColumnGridView=2

    override fun launch() {

        setAdapter(game.currentPlayedPerso.toListSorted())
        joueurAction= Personnage::giveStuffCard
    }
}

class FragmentStuff:FragmentShowImageList(){

    override fun initGridView() {
        super.initGridView()

        gridView.onItemLongClickListener = AdapterView.OnItemLongClickListener{ _, _, position: Int,_ ->

            //get the monster at this position
            val imageClicked= listToShow!![position]

            //we want to do 2 dofferent things : if the card is used we give it to another player if not we delete it
            if(imageClicked is StuffCard) {
                when (imageClicked.isUsed) {
                    false -> (context as MainAcvtivity).playerWantDeleteStuffCard(imageClicked)//playerSelectIShowImage(imagerClicked, Personnage::removeStuffCard)
                    true -> (context as MainAcvtivity).playerGiveStuffCard(imageClicked)
                }
            }
            true
        }
    }

    override fun launch() {
        setAdapter(game.joueur.equipment)//.toListSorted())
        joueurAction=Personnage::switchStuffCardIsUsed
    }
}

