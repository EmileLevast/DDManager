package fr.emile.ddmanager.mainClass

import androidx.room.Embedded
import fr.emile.ddmanager.Container
import fr.emile.ddmanager.R
import fr.emile.ddmanager.IShowImage

/**
 * Created by emile on 10/03/2019.
 */

//only used to construct the reference
class Personnage (persoRef: PersoReference): Entity(persoRef.imgId,persoRef.nom) {

    var mana:Int=persoRef.mana

    var listPowers:List<Power> = persoRef.listPowers

    @Embedded
    var levelStat = Level(persoRef.xpNeededLevel1)

    var monsterKilled = mutableListOf<Monster>()

    var equipment = mutableListOf<StuffCard>()

    //used to save the card from the stuff of this player that he wants to give
    @Embedded(prefix = "cardTogive")
    var cardToGiveToAnOtherPlayer: StuffCard? = null

    var referenceInitCara=persoRef

    var vie = persoRef.vie
        set(value) {
            field = when {
                value < 0 -> 0
                value > referenceInitCara.vie -> referenceInitCara.vie
                else -> value
            }
        }

    init {
        initStuff()
    }

    //constructor for Room only
    constructor() : this(PersoReference.containerRef[0]!!)//nom = "Unknown", listPowers = mutableListOf<Power>())

    fun initStuff() {
        equipment.clear()

        for (BasicStuffToAdd in referenceInitCara.listEquipment) {
            //if we found the stuff we add it
            StuffCard.CardReference[BasicStuffToAdd]?.let { equipment.add(it.clone() as StuffCard) }
        }
        //equipment.addAll(referenceInitCara.listEquipment)
    }


    /**function called when click on card in Fragment**/

    fun killMonster(monsterSelected: IShowImage) {
        //here we clone the monster because it must be a different card when we will add it to the list of killed mosnter
        val monsterSelectedClone = monsterSelected.clone()

        if (monsterSelectedClone is Monster) {
            //we search if there is already a monster of this kind and we adapt the name by add 1 to it "gnoll1","gnoll2"
            while (monsterKilled.contains(monsterSelectedClone)) monsterSelectedClone.number++

            monsterKilled.add(0, monsterSelectedClone)//pushFront(monsterSelected)

            //if the player win a level
            if (levelStat.addXp(monsterSelectedClone.costXp) >= 1) {
                vie += (referenceInitCara.vie * ratioGainLifeWhenChangeLevel).toInt()
            }
        }
    }

    fun loseMonsterXp(monsterSelected: IShowImage) {
        if (monsterSelected is Monster) {
            monsterKilled.remove(monsterSelected)
            levelStat.addXp(monsterSelected.costXp * (-1))
        }
    }

    fun removeStuffCard(cardToRemove: IShowImage) {
        if (cardToRemove is StuffCard) {
            equipment.remove(cardToRemove)
        }
    }

    fun switchStuffCardIsUsed(cardToSwitch: IShowImage) {
        if (cardToSwitch is StuffCard) {
            cardToSwitch.apply { isUsed = !isUsed }
            //equipment[cardToSwitch.toKey()]?.apply { isUsed=!isUsed }
            //equipment[equipment.indexOf(cardToSwitch)].apply{isUsed=!isUsed}
        }
    }

    fun giveStuffCard(persoReceiver: IShowImage) {
        //si on a toujours la carte a donner
        if (persoReceiver is Personnage && cardToGiveToAnOtherPlayer != null && equipment.contains(cardToGiveToAnOtherPlayer!!)) {
            //on supprime la carte que si l'ajout a bien pu se faire
            if (persoReceiver.addStuffCard(cardToGiveToAnOtherPlayer!!))
                removeStuffCard(cardToGiveToAnOtherPlayer!!)
        }
    }

    /********************************************/

    fun addStuffCard(newCard: StuffCard): Boolean {
        //si la carte n'est pas presente on renvoit true et la carte est ajoutee
        return if (!equipment.contains(newCard)) {
            equipment.add(0, newCard)//.pushFront(newCard)
            true
        } else
            false
    }

    companion object {
        val ratioGainLifeWhenChangeLevel = 0.25f

    }
}

class Power (override var imgId:Int, var textExplanation:String, var availableLevel:Int): IShowImage {


    companion object {
        val listPowerLidda = listOf(
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

        val listPowerMyalie = listOf(
                Power(R.drawable.teleportation, "myalie peut se teleporter de 3 cases dans une direction, elle ne tient pas compte des obstacles (murs compris)" +
                        "du moment que la case d'arrivee est libre", 2),
                Power(R.drawable.mana, "Si myalie inflige 3 points de degats ou plus elle recupere 1 pt de sort", 2),
                Power(R.drawable.arc, "myalie inflige +1 degat a l'arc ,et uniquement a l'arc", 3),
                Power(R.drawable.spell_book, "myalie peut avoir autant de sort actif qu'elle le desire", 3),
                Power(R.drawable.arrow, "lorsque myalie tire a l'arc, avant de lancer les des elle peut depenser du mana." +
                        "Pour chaque point de mana la fleche peut traverser un monstre supplementaire et frapper si possible un monstre derriere", 4),
                Power(R.drawable.super_arc, "myalie peut tirer dans 3 directions differentes uniquement a l'arc", 5)
        )

        val listPowerRegdar = listOf(
                Power(R.drawable.afraid, "les ennemis au corps a corps avec regdar ne peuvent quitter leur case que sur etoile", 2),
                Power(R.drawable.explosion, "avant de lancer les des pour un corps a corps, regdar peut diminuer son attaque de 1 de degat mais tous les monstres au corps a corps avec" +
                        "lui subiront cette attaque", 3),
                Power(R.drawable.armor, "regdar possede maintenant 3 d'armure", 4),
                Power(R.drawable.damage, "sur etoile regdar peut infliger le double de degat, ne marche pas avec les degats de zone", 5)
        )

        val listPowerJozan = listOf(
                Power(R.drawable.care, "lorsque jozan tue un monstre il se restaure 2 pts de mana (ne peut pas depasser sa reserve max)" +
                        "et les allies en contact avec lui (diagonales non comprises) restaurent 1 pv ou 1 mana.", 2),

                Power(R.drawable.spell_symbol, "les sorts coutent un point de moins de mana a jozan", 3),
                Power(R.drawable.undead_symbol, "jozan inflige 2 pts de degats supplementaires aux morts-vivants", 3),

                Power(R.drawable.skeleton_hand, "le renvoi de morts-vivants ne coute plus d'action pour jozan mais seulement 1 renvoi par tour.", 4),
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
