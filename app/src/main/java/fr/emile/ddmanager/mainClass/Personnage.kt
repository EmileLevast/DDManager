package fr.emile.ddmanager.mainClass

import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R
import fr.emile.ddmanager.ToIdDrawable

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

    private var containerStuff= Container<StuffCard>()

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
    fun selectMonster(monsterSelected: Monster, killMonster:Boolean)
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
    }

    fun killMonster(monsterSelected: Monster)
    {
        while(containerKills.contains(monsterSelected))monsterSelected.number++

        containerKills.pushFront(monsterSelected)

        //if the player win a level
        if(levelStat.addXp(monsterSelected.costXp)>=1)
        {
            vie+=referenceInitCara.vie/4
        }
    }

    fun loseMonsterXp(monsterSelected: Monster)
    {
        containerKills.remove(monsterSelected)
        levelStat.addXp(monsterSelected.costXp*(-1))
    }

    fun addStuffCard(newCard: StuffCard)
    {
        containerStuff.pushFront(newCard)
    }

    companion object {
        val containerRef= Container(
                Personnage(15, 0, 36, R.drawable.regdardescriptioncard, "Regdar", Power.listPowerRegdar),
                Personnage(10, 0, 20, R.drawable.liddadescriptioncard, "Lidda", Power.listPowerLidda),
                Personnage(9, 11, 29, R.drawable.myaliedescriptioncard, "Myalië", Power.listPowerMyalie),
                Personnage(11, 9, 25, R.drawable.jozandescriptioncard, "Jozan", Power.listPowerJozan))
    }
}

class Power (var idImg:Int,var textExplanation:String,var availableLevel:Int): ToIdDrawable
{
    override fun getDrawableId()=idImg

    companion object {
        val listPowerLidda= mutableListOf(
                Power(R.drawable.esquive_power, "Lidda peut effectuer un deplacement gratuit de 1 case apres avoir frappe", 2),
                Power(R.drawable.serrure_oeil, "Lidda peut regarder a travers la serrure des portes. Pour 1 d'action le MJ doit devoiler les monstres" +
                        " presents derriere la porte sur laquelle se tient Lidda (si les monstres ne sont pas deja presents le MJ ne doit pas les devoiler)." +
                        "Lidda n'est pas obligee d'ouvrir la porte par la suite.", 2),

                Power(R.drawable.pickpocket, "Lidda peut passer sous un monstre, sans effectuer d'attaque sournoise, " +
                        "et sur etoile elle peut piocher une carte dans la pioche de tresors. Si elle rate le jet etoile son tour est fini ", 3),
                Power(R.drawable.discard, "Lidda peut ignorer les effets des pieges pioches et piocher une nouvelle carte a la place.", 3),

                Power(R.drawable.backstab, "au corps a corps , si Lidda se trouve derriere le monstre (regarder la position de la figurine" +
                        ") alors elle ignore son armure.", 4),
                Power(R.drawable.hide, "si Lidda est en contact avec un mur, on considere qu'elle se camoufle et un monstre doit faire etoile " +
                        "pour reussir a rentrer au corps a corps avec elle (si l'etoile rate le deplacement est fini pour le monstre). ", 4),


                Power(R.drawable.run, "Lidda peut se deplacer de 3 manieres au choix (1 seul deplacement par tour):\n" +
                        "-furtivement, Lidda se deplace de 5 mais peut tenter de desamorcer les pieges sur lesquels elle marche. Si elle" +
                        "reussit son tour continue sinon elle subit les effets." +
                        "\n-prestement, Lidda se deplace de 7 et le MJ n'active pas les pieges qu'elle rencontre ni n'indique leur existence." +
                        "\n-normalement.", 5)
                )

        val listPowerMyalie=mutableListOf(
                Power(R.drawable.teleportation, "Myalie peut se teleporter de 3 cases dans une direction, elle ne tient pas compte des obstacles (murs compris)" +
                        "du moment que la case d'arrivee est libre", 2),
                Power(R.drawable.mana, "Si myalie inflige 3 points de degats ou plus elle recupere 1 pt de sort", 2),
                Power(R.drawable.arc, "Myalie inflige +1 degat a l'arc ,et uniquement a l'arc", 3),
                Power(R.drawable.spell_book, "Myalie peut avoir autant de sort actif qu'elle le desire", 3),
                Power(R.drawable.arrow, "lorsque myalie tire a l'arc, avant de lancer les des elle peut depenser du mana." +
                        "Pour chaque point de mana la fleche peut traverser un monstre supplementaire et frapper si possible un monstre derriere", 4),
                Power(R.drawable.super_arc, "Myalie peut tirer dans 3 directions differentes uniquement a l'arc", 5)
        )

        val listPowerRegdar= mutableListOf(
                Power(R.drawable.gnollcard, "les ennemis au corps a corps avec regdar ne peuvent quitter leur case que sur etoile", 2),
                Power(R.drawable.gnollcard, "avant de lancer les des pour un corps a corps, regdar peut diminuer son attaque de 1 de degat mais tous les monstres au corps a corps avec" +
                        "lui subiront cette attaque", 3),
                Power(R.drawable.gnollcard, "Regdar possede maintenant 3 d'armure", 4),
                Power(R.drawable.gnollcard, "sur etoile Regdar peut infliger le double de degat, ne marche pas avec les degats de zone", 5)
                )

        val listPowerJozan= mutableListOf(
                Power(R.drawable.gnollcard, "lorsque Jozan tue un monstre il se restaure 2 pts de mana (ne peut pas depasser sa reserve max)" +
                        "et les allies en contact avec lui (diagonales non comprises) restaurent 1 pv ou 1 mana.", 2),

                Power(R.drawable.gnollcard, "les sorts coutent un point de moins de mana a Jozan", 3),
                Power(R.drawable.gnollcard, "Jozan inflige 2 pts de degats supplementaires aux morts-vivants", 3),

                Power(R.drawable.gnollcard, "le renvoi de morts-vivants ne coute plus d'action pour Jozan mais seulement 2 renvois max par tour.", 4),
                Power(R.drawable.gnollcard, "si Jozan depense 2 pts de mana ou plus en meme temps pour soigner un heros (dont lui-meme)," +
                        " celui-ci recupere 1 pt de vie supplementaire.", 4),

                Power(R.drawable.gnollcard, "Jozan peut prendre le controle d'un coequipier et jouer son tour entier comme s'il etait ce coequipier." +
                        "au final le coequipier jouera 2 fois dans ce tour mais dirige 2 fois par un joueur " +
                        "different tandis que Jozan n'aura pas joue du tout.", 5),
                Power(R.drawable.gnollcard, "le renvoi des morts-vivants inflige le nombre de cranes en degat contre la cible.\n(n'ignore pas l'armure mais " +
                        "ne pas oublier d'y ajouter le +2 grace au bonus d'attaque contre les morts-vivants)", 5)
                )
    }
}