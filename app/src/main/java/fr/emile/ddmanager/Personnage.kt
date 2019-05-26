package fr.emile.ddmanager

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
    var levelStat=Level(xpNeededLevel1)

    var containerKills= Container<Monster>()

    var vie=vie
    set(value)
    {
        field =
                when {
                    value<0 -> 0
                    value>referenceInitCara.vie -> referenceInitCara.vie
                    else -> value
                }
    }

    //just null for the companion object
    lateinit var referenceInitCara:Personnage

    constructor(persoRef:Personnage):this(persoRef.vie,persoRef.mana,persoRef.levelStat.xpNeededLevel1,
            persoRef.imgId,persoRef.nom, persoRef.listPowers.toMutableList())
    {
        referenceInitCara=persoRef
    }

    //if kill monster is false the player lose xp
    fun selectMonster(monsterSelected: Monster,killMonster:Boolean)
    {
        if(killMonster)
        {
            //there is already a monster with this name we change his number
            while(containerKills.contains(monsterSelected))monsterSelected.number++

            containerKills.add(monsterSelected)

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

    companion object {
        val containerRef=Container(
                Personnage(15,0,36,R.drawable.regdardescriptioncard,"Regdar",Power.listPowerRegdar),
                Personnage(10,0,20,R.drawable.liddadescriptioncard,"Lidda",Power.listPowerLidda),
                Personnage(9,11,29,R.drawable.myaliedescriptioncard,"Myalië",Power.listPowerMyalie),
                Personnage(11,9,25,R.drawable.jozandescriptioncard,"Jozan",Power.listPowerJozan))
    }
}

class Power (var idImg:Int,var textExplanation:String,var availableLevel:Int): ToIdDrawable
{
    override fun getDrawableId()=idImg

    companion object {
        val listPowerLidda= mutableListOf(
                Power(R.drawable.gnollcard,"Lidda peut effectuer un deplacement gratuit de 1 case après avoir frappe",2),
                Power(R.drawable.gnollcard,"Lidda peut regarder à travers la serrure des portes. Pour 1 d'action le MJ doit devoiler les monstres" +
                        "présents derrière la porte sur laquelle se tient Lidda (si les monstres ne sont pas deja presents le MJ ne doit pas les devoiler)." +
                        "Lidda n'est pas obligee d'ouvrir la porte par la suite.",2),

                Power(R.drawable.gnollcard,"Lidda peut passer sous un monstre, sans effectuer d'attaque sournoise, " +
                "et sur etoile elle peut piocher une carte dans la pioche de tresors. Si elle rate le jet etoile son tour est fini et elle doit se defausser d'un artefact ou d'une arme.",3),
                Power(R.drawable.gnollcard,"Lidda peut ignorer les effets des pieges pioches et piocher une nouvelle carte a la place.",3),

                Power(R.drawable.gnollcard,"Au corps à corps , si Lidda se trouve derriere le monstre (regarder la position de la figurin" +
                        "e) alors elle ignore son armure.",4),
                Power(R.drawable.gnollcard,"Si Lidda est en contact avec un mur, on considere qu'elle se camoufle et un monstre doit faire etoile " +
                        "pour reussir a rentrer au corps a corps avec elle (si l'etoile rate le deplacement est fini pour le monstre). ",4),



                Power(R.drawable.gnollcard,"Lidda peut se deplacer de 3 manieres au choix (1 seul deplacement par tour):\n" +
                        "-furtivement, Lidda se deplace de 5 mais peut tenter de desamorcer les pieges sur lesquels elle marche. Si elle" +
                        "reussit son tour continue sinon elle subit les effets." +
                        "\n-prestement, Lidda se deplace de 7 et le MJ n'active pas les pieges qu'elle rencontre ni n'indique leur existence." +
                        "\n-normalement.",5)
                )

        val listPowerMyalie=mutableListOf(
                Power(R.drawable.gnollcard,"Myalie peut se teleporter de 3 cases dans une direction, elle ne tient pas compte des obstacles (murs compris)" +
                        "du moment que la case d'arrivee est libre",2),
                Power(R.drawable.gnollcard,"Si myalie inflige 3 points de degats ou plus elle recupere 1 pt de sort",2),
                Power(R.drawable.gnollcard,"Myalie inflige +1 degat a l'arc ,et uniquement à l'arc",3),
                Power(R.drawable.gnollcard,"Myalie peut avoir autant de sort actif qu'elle le desire",3),
                Power(R.drawable.gnollcard,"Lorsque myalie tire à l'arc, avant de lancer les des elle peut depenser du mana." +
                        "Pour chaque point de mana la fleche peut traverser un monstre supplementaire et frapper si possible un monstre derrière",4),
                Power(R.drawable.gnollcard,"Myalie peut tirer dans 3 directions differentes uniquement à l'arc",5)
        )

        val listPowerRegdar= mutableListOf(
                Power(R.drawable.gnollcard,"Les ennemis au corps a corps avec regdar ne peuvent quitter leur case que sur etoile",2),
                Power(R.drawable.gnollcard,"Avant de lancer les des pour un corps à corps, regdar peut diminuer son attaque de 1 de degat mais tous les monstres au corps à corps avec" +
                        "lui subiront cette attaque",3),
                Power(R.drawable.gnollcard,"Regdar possede maintenant 3 d'armure",4),
                Power(R.drawable.gnollcard,"Sur etoile Regdar peut infliger le double de degat, ne marche pas avec les degats de zone",5)
                )

        val listPowerJozan= mutableListOf(
                Power(R.drawable.gnollcard,"Lorsque Jozan tue un monstre il se restaure 2 pts de mana (ne peut pas depasser sa reserve max)" +
                        "et les allies en contact avec lui (diagonales non comprises) restaurent 1 pv ou 1 mana.",2),

                Power(R.drawable.gnollcard,"Les sorts coûtent un point de moins de mana à Jozan",3),
                Power(R.drawable.gnollcard,"Jozan inflige 2 pts de degats supplementaires aux morts-vivants",3),

                Power(R.drawable.gnollcard,"Le renvoi de morts-vivants ne coute plus d'action pour Jozan mais seulement 2 renvois max par tour.",4),
                Power(R.drawable.gnollcard,"Si Jozan depense 2 pts de mana ou plus en meme temps pour soigner un heros (dont lui-meme)," +
                        " celui-ci recupere 1 pt de vie supplémentaire.",4),

                Power(R.drawable.gnollcard,"Jozan peut prendre le controle d'un coequipier et jouer son tour entier comme s'il etait ce coequipier." +
                        "Au final le coequipier jouera 2 fois dans ce tour mais dirige 2 fois par un joueur " +
                        "different tandis que jozan n'aura pas joue du tout.",5),
                Power(R.drawable.gnollcard,"Le renvoi des morts-vivants inflige le nombre de cranes en degat contre la cible.\n(n'ignore pas l'armure mais " +
                        "ne pas oublier d'y ajouter le +2 grace au bonus d'attaque contre les morts-vivants)",5)
                )
    }
}