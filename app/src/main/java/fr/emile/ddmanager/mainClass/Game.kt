package fr.emile.ddmanager.mainClass

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import fr.emile.ddmanager.Container

@Entity
class Game {

    @PrimaryKey(autoGenerate = true)
    var id:Int?=null

    lateinit var deck: MutableList<StuffCard>

    var currentPlayedPerso= Container(
            Personnage(PersoReference.containerRef.getEltWith("Regdar")!!),
            Personnage(PersoReference.containerRef.getEltWith("Jozan")!!),
            Personnage(PersoReference.containerRef.getEltWith("Lidda")!!),
            Personnage(PersoReference.containerRef.getEltWith("Myalië")!!))

    @Ignore
    var joueur:Personnage=currentPlayedPerso.getItemNext()

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
            addAll(StuffCard.CardReference.toListSorted())

            //on melange la pioche
            shuffle()
        }
    }
}