package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container

class Game {

    //TODO ajouter une pioche qui contient les elements restants

    private val currentPlayedPerso= Container(
            Personnage(Personnage.containerRef.getEltWith("Regdar")!!),
            Personnage(Personnage.containerRef.getEltWith("Jozan")!!),
            Personnage(Personnage.containerRef.getEltWith("Lidda")!!),
            Personnage(Personnage.containerRef.getEltWith("Myalië")!!))

    var joueur:Personnage=currentPlayedPerso.getItemNext()
    private set

    fun changePerso()
    {
        joueur=currentPlayedPerso.getItemNext()
    }

    fun playerPickCard(card: StuffCard)
    {
        joueur.addStuffCard(card.clone() as StuffCard)
    }
}