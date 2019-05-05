package fr.emile.ddmanager


const val XP_BY_LEVEL = 50

class Level {


    var currentLevel: Int = 0
        private set
    var xp: Int = 0
        private set

    init {
        currentLevel = 1
    }

    fun addXp(addXp: Int): Int {

        //make sure we win xp and not lose
        //if (addXp<=0) return 0

        var nbrLevelChange = 0

        xp += addXp
        if (xp >= currentLevel * XP_BY_LEVEL) {
            //we win a level

            //we calculate the xpSupp already won for the next level(and maybe we can get a second level
            var xpTempo = xp
            xp = 0
            xpTempo -= currentLevel * XP_BY_LEVEL
            currentLevel++

            nbrLevelChange = 1 + addXp(xpTempo)

        }else if (xp < 0) {
            if (currentLevel > 1) {
                val xpTempo = xp
                currentLevel--
                xp = currentLevel * XP_BY_LEVEL

                nbrLevelChange = 1 + addXp(xpTempo)

            } else {
                xp = 0
            }
        }
        return nbrLevelChange

    }


}