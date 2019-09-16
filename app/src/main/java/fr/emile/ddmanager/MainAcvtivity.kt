package fr.emile.ddmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import fr.emile.ddmanager.mainClass.Monster
import fr.emile.ddmanager.mainClass.Personnage
import fr.emile.ddmanager.mainClass.StuffCard
import fr.emile.ddmanager.gestionFragment.DeckFragment
import fr.emile.ddmanager.gestionFragment.FragmentGenerate
import fr.emile.ddmanager.gestionFragment.customFragment.CustomFragment
import fr.emile.ddmanager.gestionFragment.heroPower.FragmentHeroPower
import kotlinx.android.synthetic.main.activity_main.*



lateinit var joueur: Personnage

class MainAcvtivity : AppCompatActivity() {
    lateinit var affichage:Affichage
    var fragGenerator:FragmentGenerate= FragmentGenerate()

    var currentPlayedPerso=Container(
            Personnage(Personnage.containerRef.getEltWith("Regdar")!!),
            Personnage(Personnage.containerRef.getEltWith("Jozan")!!),
            Personnage(Personnage.containerRef.getEltWith("Lidda")!!),
            Personnage(Personnage.containerRef.getEltWith("Myalië")!!))



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        affichage=Affichage(
                this,
                ecran,
                manaGrid,
                pvGrid,
                imagePersonnage,
                expBar,
                buttonKillMonster,
                buttonLoseXpMonster,
                buttonshowPower,
                buttonDeckPicker,
                textViewLevel
        )

        changeToNextPersonnage()
    }

    fun updateStatJoueurWithGridView(mana:Int,vie:Int)
    {
        joueur.mana=mana
        joueur.vie=vie
    }

    fun changeToNextPersonnage()
    {
        joueur= currentPlayedPerso.getItemNext()
        updateUi()
    }

    fun updateUi()
    {
        affichage.initPersonnage(joueur)
    }

    fun playerSelectMonster(monsterClicked: Monster, joueurAction: (Personnage.( Monster) -> Unit)?)
    {
        joueurAction?.let { joueur.it(monsterClicked) }
        updateUi()
        supportFragmentManager.popBackStack()
    }

    fun generateFrag(fragToGenerate:CustomFragment)
    {
        fragToGenerate.launch()
        fragGenerator.createFrag(fragToGenerate,this)
    }

}
