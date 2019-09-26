package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R

class Game {

    private lateinit var deck: MutableList<StuffCard>

    val currentPlayedPerso= Container(
            Personnage(Personnage.containerRef.getEltWith("Regdar")!!),
            Personnage(Personnage.containerRef.getEltWith("Jozan")!!),
            Personnage(Personnage.containerRef.getEltWith("Lidda")!!),
            Personnage(Personnage.containerRef.getEltWith("Myalië")!!))

    var joueur:Personnage=currentPlayedPerso.getItemNext()
    private set

    init {
        //initialiser la pioche
        creerPioche()
    }

    fun changePerso()
    {
        joueur=currentPlayedPerso.getItemNext()
    }

    fun playerPickCard(card: StuffCard)
    {
        joueur.addStuffCard(card.clone() as StuffCard)
    }

    fun piocher():StuffCard?
    {
        //si la pioche est pas vide on donne une carte mais on l'enleve de la pioche
        return if(deck.isEmpty())
        {
            creerPioche()
            piocher()
        }
        else {
            deck.first().also{deck.remove(it)}
        }



    }

    private fun creerPioche()
    {
        //on ajoute toutes les cartes que j'ai dans StuffCard et son companion object
        deck= mutableListOf<StuffCard>().apply{
            addAll(StuffCard.CardReference)

            //on melange la pioche
            shuffle()
        }
    }
}