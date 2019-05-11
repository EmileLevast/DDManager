package fr.emile.ddmanager

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.*

/**
 * Top Level Declaration
 */
//for intial screen, with the hero
private const val NBR_CHILD_GRID=16
private const val RAPPORT_IMAGE_ECRAN_X=4f/5f
const val RAPPORT_IMAGE_ECRAN_Y=4f/5f



//for all
var WIDTH_SCREEN:Int?=null
var HEIGHT_SCREEN:Int?=null

/**
 * Created by emile on 02/03/2019.
 */
class Affichage(
        var activity: MainAcvtivity,
        private var ecran:RelativeLayout,
        var manaGrid: GridCustomPoint,
        var pvGrid:GridCustomPoint,
        var imagePersonnage:ImageView,
        var xpBarre:ProgressBar,
        var buttonKillMonster:ImageButton,
        var buttonLoseXpMonster:ImageButton,
        var buttonShowPower:ImageButton,
        var textViewLevel:TextView
) {

    init {

        initTypeface()

        //we prepare the layout
        setDimensionImagesPersonnage()
        fillGridWithView(manaGrid,activity,R.drawable.fillpointgridmana,R.color.pointMana)
        fillGridWithView(pvGrid,activity,R.drawable.fillpointgridvie,R.color.red)

        //on indique l'effet quand on clique sur l'image
        imagePersonnage.setOnClickListener {
            activity.changeToNextPersonnage()
        }

        buttonKillMonster.setOnClickListener {
            activity.createFragmentMonster(true)
        }

        buttonLoseXpMonster.setOnClickListener {
            activity.createFragmentMonster(false)
        }

        buttonShowPower.setOnClickListener {
            activity.createFragmentPower()
        }

    }

    //affiche les caracteristiques du perso
    fun initPersonnage(personnage: Personnage)
    {
        initProgressBar(personnage)
        initTextView(personnage)

        //nous faisons pointer chacune des grilles sur la bonne valeur du joueur
        manaGrid.initValueToRefer(personnage.mana,personnage.referenceInitCara.mana)
        pvGrid.initValueToRefer(personnage.vie, personnage.referenceInitCara.vie)

        initCaraGrid(pvGrid)
        initCaraGrid(manaGrid)
        imagePersonnage.setImageResource(personnage.imgId)


    }

    //affiche les points restants sur cette grille avec les couleurs indiquees
    private fun initCaraGrid(gridLayout: GridCustomPoint)
    {
        for(i in 0 until gridLayout.childCount)
        {
            val pointGrid:PointGrid= gridLayout.getChildAt(i) as PointGrid

            if(i<gridLayout.currentNumber)
            {
                pointGrid.fill()
                pointGrid.visibility=View.VISIBLE

            }else if(i<gridLayout.numberReferenceInit)
            {
                pointGrid.visibility=View.VISIBLE
                pointGrid.empty()
            }else
            {
                pointGrid.visibility=View.INVISIBLE
            }
        }
    }


    private fun initProgressBar(personnage: Personnage)
    {
        xpBarre.max=personnage.levelStat.calculateXpForLevel()
        xpBarre.progress=personnage.levelStat.xp
    }

    private fun initTextView(personnage: Personnage)
    {
        textViewLevel.text="Level : "+personnage.levelStat.currentLevel
    }

    //function for creation of the layout

    private fun setDimensionImagesPersonnage()
    {
        imagePersonnage.post{

            WIDTH_SCREEN=ecran.width
            HEIGHT_SCREEN=ecran.height

            val width= (WIDTH_SCREEN!!* RAPPORT_IMAGE_ECRAN_X).toInt()
            val height= (HEIGHT_SCREEN!!* RAPPORT_IMAGE_ECRAN_Y).toInt()

            val paramRelativeLayout=RelativeLayout.LayoutParams(width,height)

            paramRelativeLayout.marginStart=(WIDTH_SCREEN!!* (1f-RAPPORT_IMAGE_ECRAN_X)/2f).toInt()
            imagePersonnage.layoutParams=paramRelativeLayout

            //we create the buttons for the personnage
            setDimensionButtonPersonnage(1,0,buttonKillMonster)
            setDimensionButtonPersonnage(1,1,buttonLoseXpMonster)
            setDimensionButtonPersonnage(8,0,buttonShowPower)
        }
    }

    private fun setDimensionButtonPersonnage(posX:Int,posY:Int,buttonToSet:View)
    {

        val width= (WIDTH_SCREEN!!* ((1f-RAPPORT_IMAGE_ECRAN_X)/2f)).toInt()
        val height=width// (HEIGHT_SCREEN!!* RAPPORT_IMAGE_ECRAN_Y).toInt()

        val paramRelativeLayout=RelativeLayout.LayoutParams(width,height)
        paramRelativeLayout.marginStart=width*posX
        paramRelativeLayout.topMargin=height*(posY)

        buttonToSet.layoutParams=paramRelativeLayout

    }

    private fun fillGridWithView(gridTofill:GridCustomPoint, activity: MainAcvtivity,
                                 drawableFillBackgroundId:Int,
                                 colorEmptyTextId:Int)
    {

        gridTofill.columnCount=2
        //we add all new Child

        for(i in 1..NBR_CHILD_GRID)
        {
            val pointGrid= PointGrid(activity,drawableFillBackgroundId,colorEmptyTextId,i,false)
            pointGrid.text = i.toString()
            pointGrid.gravity= Gravity.CENTER


            //que faire quand on clique sur une vue
            pointGrid.setOnClickListener {

                //si on a clique sur un point qui est vide, on veut augmenter la vie
                if(pointGrid.isEmpty && pointGrid.value<=gridTofill.numberReferenceInit )//<=joueur.referenceInitCara.vie)
                {
                    //si c'est null c'est aue ça a bugge t'facon
                    (gridTofill.getChildAt(gridTofill.currentNumber) as PointGrid).fill()

                    //we can't use ++ operator because they are boxed value not primitive
                    gridTofill.currentNumber=gridTofill.currentNumber+1
                }
                //sinon notre vie peut encore augmenter et qu'on depasse pas la grid
                else if(!pointGrid.isEmpty)
                {
                    (gridTofill.getChildAt(gridTofill.currentNumber-1) as PointGrid).empty()
                    gridTofill.currentNumber=gridTofill.currentNumber-1
                }

                //after we clicked we update the value of the player
                activity.updateStatJoueurWithGridView(manaGrid.currentNumber,pvGrid.currentNumber)
            }

            pointGrid.post{
                val width= (ecran.width*(1f-RAPPORT_IMAGE_ECRAN_X)/4f).toInt()
                val paramRelativeLayout=GridLayout.LayoutParams(gridTofill.layoutParams)
                paramRelativeLayout.width=width
                paramRelativeLayout.height=width
                pointGrid.layoutParams=paramRelativeLayout
            }

            gridTofill.addView(pointGrid)
        }
    }

    private fun initTypeface()
    {
        val typeface=Typeface.createFromAsset(activity.assets,"font/hobbitonbrushhand.ttf")
        textViewLevel.typeface = typeface
        activity.findViewById<TextView>(R.id.textPowerDescription).typeface = typeface
    }

}