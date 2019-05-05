package fr.emile.ddmanager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*



class MainAcvtivity : AppCompatActivity() {

    lateinit var affichage:Affichage
    lateinit var joueur:Personnage

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

    fun createCustomFragment()
    {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val frag=CustomFragment()
        ft.add(R.id.ecran,frag)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun playerKillMonster(monsterClicked:Monster)
    {
        joueur.levelStat.addXp(monsterClicked.costXp)
        updateUi()
        supportFragmentManager.popBackStack()
    }
}
