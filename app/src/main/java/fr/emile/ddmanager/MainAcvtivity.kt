package fr.emile.ddmanager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fr.emile.ddmanager.gestionAffichage.Affichage
import fr.emile.ddmanager.gestionBDD.AppDatabase
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.gestionFragment.FragmentGenerate
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.gestionFragment.monsterFragment.FragmentListPerso
import fr.emile.ddmanager.mainClass.Game
import fr.emile.ddmanager.mainClass.StuffCard
import kotlinx.android.synthetic.main.activity_main.*



//lateinit var joueur: Personnage
lateinit var game:Game


class MainAcvtivity : AppCompatActivity() {
    lateinit var affichage: Affichage
    var fragGenerator:FragmentGenerate= FragmentGenerate()
    var bdd:AppDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //game=Game()
        bdd=AppDatabase.getInstance(this)
        createGame()

        affichage= Affichage(
                this,
                ecran,
                manaGrid,
                pvGrid,
                imagePersonnage,
                expBar,
                textViewLevel
        )

        changeToNextPersonnage()

    }

    fun updateStatJoueurWithGridView(mana:Int,vie:Int)
    {
        game.joueur.mana=mana
        game.joueur.vie=vie
    }

    fun changeToNextPersonnage()
    {
        game.changePerso()
        saveGame()
        updateUi()
    }

    private fun updateUi()
    {
        affichage.initPersonnage(game.joueur)
    }

    fun playerSelectIShowImage(imageClicked: IShowImage, joueurAction: (Personnage.(IShowImage) -> Unit)?)
    {
        joueurAction?.let { game.joueur.it(imageClicked) }
        updateUi()

        //don't delete the fragment if it's the stuff
        if(imageClicked is StuffCard)
        {
            //fragGenerator.updateAdapter(game.joueur.equipment.toListSorted())
            fragGenerator.updateFrag()
        }else
        {
            supportFragmentManager.popBackStack()

        }
    }

    fun playerGiveStuffCard(imageClicked: StuffCard)
    {
        supportFragmentManager.popBackStack()
        generateFrag(FragmentListPerso())
        game.joueur.cardToGiveToAnOtherPlayer=imageClicked
    }

    fun playerWantDeleteStuffCard(imageClicked: StuffCard)
    {
        fragGenerator.createAlertDialog(this,
                {
                    //if the player click on yes , so we delete teh card
                    game.joueur.removeStuffCard(imageClicked)
                    fragGenerator.updateFrag()
            },
                {
                    //we do nothing if he doesn't want to delete the card
             })
    }

    fun generateFrag(fragToGenerate:CustomFragment)
    {
        fragToGenerate.launch()
        fragGenerator.createFrag(fragToGenerate,this)
    }

    fun personnageResurrection()
    {
        game.joueur.initStuff()
        updateUi()
    }

    /**
     * we search in the database if there is a previous game registered
     */
    fun createGame()
    {
        val registeredGames= bdd?.gameDao()?.loadAllGames()

        game= if(  registeredGames!=null && !registeredGames.isEmpty())
            {
                registeredGames[0]
            }else
            {
                Game()
            }
    }

    fun saveGame()
    {
        bdd?.gameDao()?.insertGames(game)
    }
}
