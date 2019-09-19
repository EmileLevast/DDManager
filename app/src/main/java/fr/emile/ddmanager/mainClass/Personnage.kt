package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R
import fr.emile.ddmanager.IShowImage

/**
 * Created by emile on 10/03/2019.
 */

class Personnage private constructor(vie:Int,
                                     var mana:Int,
                                     xpNeededLevel1:Int,
                                     imgId:Int,
                                     nom:String,
                                     var listPowers:MutableList<Power>): Character(imgId,nom)
{
    var levelStat= Level(xpNeededLevel1)
    var containerKills= Container<Monster>()
    var containerStuff= Container<StuffCard>()

    var vie=vie
    set(value)
    {
        field = when {
                    value<0 -> 0
                    value>referenceInitCara.vie -> referenceInitCara.vie
                    else -> value
                }
    }

    //just null for the companion object
    lateinit var referenceInitCara: Personnage

    constructor(persoRef: Personnage):this(persoRef.vie,persoRef.mana,persoRef.levelStat.xpNeededLevel1,
            persoRef.imgId,persoRef.nom, persoRef.listPowers.toMutableList())
    {
        referenceInitCara=persoRef
    }

    //if kill monster is false the player lose xp
    /*fun selectMonster(monsterSelected: Monster, killMonster:Boolean)
    {
        if(killMonster)
        {
            //there is already a monster with this name we change his number
            while(containerKills.contains(monsterSelected))monsterSelected.number++

            containerKills.pushFront(monsterSelected)

            //if the player win a level
            if(levelStat.addXp(monsterSelected.costXp)>=1)
            {
                vie+=referenceInitCara.vie/4
            }

        }else
        {
            containerKills.remove(monsterSelected)
            levelStat.addXp(monsterSelected.costXp*(-1))
        }
    }*/

    /**function called when click on card in Fragment**/

    fun killMonster(monsterSelected: IShowImage)
    {
        if( monsterSelected is Monster)
        {
            while(containerKills.contains(monsterSelected))monsterSelected.number++

            containerKills.pushFront(monsterSelected)

            //if the player win a level
            if(levelStat.addXp(monsterSelected.costXp)>=1)
            {
                vie+=referenceInitCara.vie/4
            }
        }
    }

    fun loseMonsterXp(monsterSelected: IShowImage)
    {
        if (monsterSelected is Monster)
        {
            containerKills.remove(monsterSelected)
            levelStat.addXp(monsterSelected.costXp*(-1))
        }
    }

    fun removeStuffCard(cardToRemove: IShowImage)
    {
        if (cardToRemove is StuffCard)
        {
            //we need to say it's deleted, the adapter will not draw it when update (because it always has a reference to this card
            containerStuff[cardToRemove.toKey()]?.isDeleted=true
            containerStuff.remove(cardToRemove)
        }
    }

    fun switchStuffCardIsUsed(cardToRemove: IShowImage)
    {
        if (cardToRemove is StuffCard)
        {
            containerStuff[cardToRemove.toKey()]?.apply { isUsed=!isUsed }
        }
    }

    /********************************************/

    fun addStuffCard(newCard: StuffCard)
    {
        if(!containerStuff.contains(newCard))
        {
            containerStuff.pushFront(newCard)
        }
    }

    companion object {
        val containerRef= Container(
                Personnage(15, 0, 36, R.drawable.regdardescriptioncard, "Regdar", Power.listPowerRegdar),
                Personnage(10, 0, 20, R.drawable.liddadescriptioncard, "Lidda", Power.listPowerLidda),
                Personnage(9, 11, 29, R.drawable.myaliedescriptioncard, "Myalië", Power.listPowerMyalie),
                Personnage(11, 9, 25, R.drawable.jozandescriptioncard, "Jozan", Power.listPowerJozan))
    }
}

class Power (override var imgId:Int, var textExplanation:String, var availableLevel:Int): IShowImage
{


    companion object {
        val listPowerLidda= mutableListOf(
                Power(R.drawable.esquive_power, "lidda peut effectuer un deplacement gratuit de 1 case apres avoir frappe", 2),
                Power(R.drawable.serrure_oeil, "lidda peut regarder a travers la serrure des portes. Pour 1 d'action le mj doit devoiler les monstres" +
                        " presents derriere la porte sur laquelle se tient lidda (si les monstres ne sont pas deja presents le mj ne doit pas les devoiler)." +
                        "lidda n'est pas obligee d'ouvrir la porte par la suite.", 2),

                Power(R.drawable.pickpocket, "lidda peut passer sous un monstre, sans effectuer d'attaque sournoise, " +
                        "et sur etoile elle peut piocher une carte dans la pioche de tresors. Si elle rate le jet etoile son tour est fini ", 3),
                Power(R.drawable.discard, "lidda peut ignorer les effets des pieges pioches et piocher une nouvelle carte a la place.", 3),

                Power(R.drawable.backstab, "au corps a corps , si lidda se trouve derriere le monstre (regarder la position de la figurine" +
                        ") alors elle ignore son armure.", 4),
                Power(R.drawable.hide, "si lidda est en contact avec un mur, on considere qu'elle se camoufle et un monstre doit faire etoile " +
                        "pour reussir a rentrer au corps a corps avec elle (si l'etoile rate le deplacement est fini pour le monstre). ", 4),


                Power(R.drawable.run, "lidda peut se deplacer de 3 manieres au choix (1 seul deplacement par tour):\n" +
                        "-furtivement, lidda se deplace de 5 mais peut tenter de desamorcer les pieges sur lesquels elle marche. Si elle" +
                        "reussit son tour continue sinon elle subit les effets." +
                        "\n-prestement, lidda se deplace de 7 et le mj n'active pas les pieges qu'elle rencontre ni n'indique leur existence." +
                        "\n-normalement.", 5)
                )

        val listPowerMyalie=mutableListOf(
                Power(R.drawable.teleportation, "myalie peut se teleporter de 3 cases dans une direction, elle ne tient pas compte des obstacles (murs compris)" +
                        "du moment que la case d'arrivee est libre", 2),
                Power(R.drawable.mana, "Si myalie inflige 3 points de degats ou plus elle recupere 1 pt de sort", 2),
                Power(R.drawable.arc, "myalie inflige +1 degat a l'arc ,et uniquement a l'arc", 3),
                Power(R.drawable.spell_book, "myalie peut avoir autant de sort actif qu'elle le desire", 3),
                Power(R.drawable.arrow, "lorsque myalie tire a l'arc, avant de lancer les des elle peut depenser du mana." +
                        "Pour chaque point de mana la fleche peut traverser un monstre supplementaire et frapper si possible un monstre derriere", 4),
                Power(R.drawable.super_arc, "myalie peut tirer dans 3 directions differentes uniquement a l'arc", 5)
        )

        val listPowerRegdar= mutableListOf(
                Power(R.drawable.afraid, "les ennemis au corps a corps avec regdar ne peuvent quitter leur case que sur etoile", 2),
                Power(R.drawable.explosion, "avant de lancer les des pour un corps a corps, regdar peut diminuer son attaque de 1 de degat mais tous les monstres au corps a corps avec" +
                        "lui subiront cette attaque", 3),
                Power(R.drawable.armor, "regdar possede maintenant 3 d'armure", 4),
                Power(R.drawable.damage, "sur etoile regdar peut infliger le double de degat, ne marche pas avec les degats de zone", 5)
                )

        val listPowerJozan= mutableListOf(
                Power(R.drawable.care, "lorsque jozan tue un monstre il se restaure 2 pts de mana (ne peut pas depasser sa reserve max)" +
                        "et les allies en contact avec lui (diagonales non comprises) restaurent 1 pv ou 1 mana.", 2),

                Power(R.drawable.spell_symbol, "les sorts coutent un point de moins de mana a jozan", 3),
                Power(R.drawable.undead_symbol, "jozan inflige 2 pts de degats supplementaires aux morts-vivants", 3),

                Power(R.drawable.skeleton_hand, "le renvoi de morts-vivants ne coute plus d'action pour jozan mais seulement 2 renvois max par tour.", 4),
                Power(R.drawable.resurrection_symbol, "si jozan depense 2 pts de mana ou plus en meme temps pour soigner un heros (dont lui-meme)," +
                        " celui-ci recupere 1 pt de vie supplementaire.", 4),

                Power(R.drawable.godfather_symbol, "jozan peut prendre le controle d'un coequipier et jouer son tour entier comme s'il etait ce coequipier." +
                        "au final le coequipier jouera 2 fois dans ce tour mais dirige 2 fois par un joueur " +
                        "different tandis que jozan n'aura pas joue du tout.", 5),
                Power(R.drawable.heap_of_skull, "le renvoi des morts-vivants inflige le nombre de cranes en degat contre la cible.\n(n'ignore pas l'armure mais " +
                        "ne pas oublier d'y ajouter le +2 grace au bonus d'attaque contre les morts-vivants)", 5)
                )
    }
}