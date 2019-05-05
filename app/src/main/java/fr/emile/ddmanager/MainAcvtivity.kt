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


    //littel var just because I should have done a MVC
    var posOrNeg=1//must be 1 or -1 , used to determine if player win or loseXp


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

    fun playerSelectMonster(monsterClicked:Monster)
    {
        joueur.levelStat.addXp(monsterClicked.costXp*posOrNeg)
        updateUi()
        supportFragmentManager.popBackStack()
    }

    /**
    [posOrNeg] is 1 when the user select a monster to win xp and -1 when the user select a monster to lose xp
     */
    fun createCustomFragment(posOrNeg:Int)
    {
        this.posOrNeg=posOrNeg

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val frag=CustomFragment()
        ft.add(R.id.ecran,frag)
        ft.addToBackStack(null)
        ft.commit()
    }

}
