package fr.emile.ddmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import fr.emile.ddmanager.gestionAffichage.Affichage
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.gestionFragment.FragmentGenerate
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.mainClass.Game
import fr.emile.ddmanager.mainClass.StuffCard
import kotlinx.android.synthetic.main.activity_main.*



//lateinit var joueur: Personnage
var game=Game()


class MainAcvtivity : AppCompatActivity() {
    lateinit var affichage: Affichage
    var fragGenerator:FragmentGenerate= FragmentGenerate()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //joueur= currentPlayedPerso.getItemNext()
        game.changePerso()
        updateUi()
    }

    private fun updateUi()
    {
        affichage.initPersonnage(game.joueur)
    }

    fun playerSelectIShowImage(imageClicked: IShowImage, joueurAction: (Personnage.(IShowImage) -> Unit)?)
    {
        joueurAction?.let { game.joueur.it(imageClicked) }
        //updateUi()

        //don't delete the fragment if it's the stuff
        if(imageClicked is StuffCard)
        {
            //fragGenerator.updateAdapter(game.joueur.containerStuff.toListSorted())
            fragGenerator.updateFrag()
        }else
        {
            supportFragmentManager.popBackStack()

        }
    }

    fun generateFrag(fragToGenerate:CustomFragment)
    {
        fragToGenerate.launch()
        fragGenerator.createFrag(fragToGenerate,this)
    }

}
