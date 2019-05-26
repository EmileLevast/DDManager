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
                Personnage(15,0,30,R.drawable.regdardescriptioncard,"Regdar",Power.listPowerRegdar),
                Personnage(10,0,20,R.drawable.liddadescriptioncard,"Lidda",Power.listPowerLidda),
                Personnage(9,11,26,R.drawable.myaliedescriptioncard,"Myalië",Power.listPowerMyalie),
                Personnage(11,9,24,R.drawable.jozandescriptioncard,"Jozan",Power.listPowerJozan))
    }
}

class Power (var idImg:Int,var textExplanation:String,var availableLevel:Int): ToIdDrawable
{
    override fun getDrawableId()=idImg

    companion object {
        val listPowerLidda= mutableListOf(
                Power(R.drawable.gnollcard,"Lidda peut effectuer un deplacement gratuit de 1 case après avoir frappe",2),
                Power(R.drawable.gnollcard,"Lidda peut passer sous un monstre, sans effectuer d'attaque sournoise, " +
                "et sur etoile elle peut piocher une carte dans la pioche de tresors. Si elle rate le jet etoile le monstre peut la frapper" +
                        " immediatement, lidda reste sur la case par laquelle elle allait passer sous le monstre et son tour est fini",3),
                Power(R.drawable.gnollcard,"Si lidda desamorce un piège elle peut le lancer sur un monstre en ligne de vue et à moins de 3 cases d'elle." +
                        "Le monstre prend les effets du piège (si c'est un resurrection du mal ou autre piège semblable, on resout les effets du pièges comme si un heros l'avait active",3),
                Power(R.drawable.gnollcard,"Au corps à corps , pour 2 d'action, Lidda ignore l'armure",4),
                Power(R.drawable.gnollcard,"Lidda peut marcher sur les pièges sans les declencher, le mj ne doit pas indiquer si " +
                        "elle a marche sur un pièges ou non",5)
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
                Power(R.drawable.gnollcard,"Les ennemis au corps à corps avec regdar ne peuvent quitter leur case que sur etoile",2),
                Power(R.drawable.gnollcard,"Avant de lancer les des pour un corps à corps, regdar peut diminuer son attaque de 1 de degat mais tous les monstres au corps à corps avec" +
                        "lui subiront cette attaque",3),
                Power(R.drawable.gnollcard,"Regdar possède maintenant 3 d'armure",4),
                Power(R.drawable.gnollcard,"Sur etoile Regdar peut infliger le double de degat, ne marche pas avec les degats de zone",5)
                )

        val listPowerJozan= mutableListOf(
                Power(R.drawable.gnollcard,"Lorsque Jozan tue un monstre il restaure un point de mana ou de vie lui-même " +
                        "et aux allies en contact avec lui (diagonales non comprises.",2),
                Power(R.drawable.gnollcard,"Les sorts coûtent un point de moins de mana à Jozan",3),
                Power(R.drawable.gnollcard,"Jozan inflige 2 pts de degats supplementaires aux mort-vivants",3),
                Power(R.drawable.gnollcard,"Jozan peut effectuer un renvoi des mort-vivant pour 0 action s'il reussit un jet etoile." +
                        "S'il rate il ne peut plus essayer de le faire pour 1 d'action",4),
                Power(R.drawable.gnollcard,"Jozan peut prendre le contrôled'un coequipier et jouer son tour entier comme s'il etait ce coequipier." +
                        "Au final le coequipier jouera 2 fois dans ce tour mais dirige 2 fois par un joueur different tandis que jozan n'aura pas joue du tout.",4),
                Power(R.drawable.gnollcard,"Jozan peut repartir comme il le souhaite les degat entre lui et un coequipier en contact avec lui qui vient d'être frappe;" +
                        "ou entre lui qui vient de se faire frappe et un coequipier en contact avec lui.",5)
                )
    }
}