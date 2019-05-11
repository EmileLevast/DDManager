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
    var killMonster=true//if true the player is selecting a monster that he killed otherwise he lose the xp of this monster


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
        joueur.selectMonster(monsterClicked.clone(),killMonster)
        updateUi()
        supportFragmentManager.popBackStack()
    }

    /**
    [posOrNeg] is 1 when the user select a monster to win xp and -1 when the user select a monster to lose xp
     */
    fun createFragmentMonster(killMonster:Boolean)
    {
        this.killMonster=killMonster

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val frag=FragmentMonsterList()

        if(killMonster)
        {
            frag.setAdapter(Monster.containerRef.toListSorted())
        }
        else
        {
            frag.setAdapter(joueur.containerKills.toListSorted())
        }

        ft.add(R.id.ecran,frag)
        ft.addToBackStack(null)
        ft.commit()
    }

    fun createFragmentPower()
    {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        val frag=FragmentHeroPower()
        frag.setAdapter(joueur.listPowers)

        ft.add(R.id.ecran,frag)
        ft.addToBackStack(null)
        ft.commit()
    }

}
