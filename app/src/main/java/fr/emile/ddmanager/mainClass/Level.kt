package fr.emile.ddmanager.mainClass


private const val FACTOR_BY_LEVEL=40

class Level(var xpNeededLevel1:Int) {


    var currentLevel: Int = 0
    var xp: Int = 0

    init {
        currentLevel = 1
    }

    fun addXp(addXp: Int): Int {

        //make sure we win xp and not lose
        //if (addXp<=0) return 0

        var nbrLevelChange = 0

        xp += addXp
        if (xp >= calculateXpForLevel()) {
            //we win a level

            //we calculate the xpSupp already won for the next level(and maybe we can get a second level
            var xpTempo = xp
            xp = 0
            xpTempo -= calculateXpForLevel()
            currentLevel++

            nbrLevelChange = 1 + addXp(xpTempo)

        }else if (xp < 0) {
            if (currentLevel > 1) {
                val xpTempo = xp
                currentLevel--
                xp = calculateXpForLevel()

                nbrLevelChange = 1 + addXp(xpTempo)

            } else {
                xp = 0
            }
        }
        return nbrLevelChange

    }

    fun calculateXpForLevel():Int
    {
        return (currentLevel-1)* FACTOR_BY_LEVEL + xpNeededLevel1
    }


    constructor():this(0)//only for Room

}