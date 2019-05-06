package fr.emile.ddmanager


class Monster(imgId:Int,nom:String,var number:Int,var costXp:Int) :Character(imgId,nom),Cloneable{


    public override fun clone(): Monster {
        return super.clone() as Monster
    }

    override fun toKey(): String {
        return super.toKey()+number
    }

    companion object {
        val containerRef=Container(
        Monster(R.drawable.gobelincard,"Gobelin",1,5),
        Monster(R.drawable.squelettecard,"Squelette",1,8),
        Monster(R.drawable.gobelourscard,"Gobelours",1,14),
        Monster(R.drawable.gnollcard,"Gnoll",1,16),
        Monster(R.drawable.ogrecard,"Ogre",1,21),
        Monster(R.drawable.vasecard,"Vase",1,25),
        Monster(R.drawable.charognardcard,"Charognard",1,28),
        Monster(R.drawable.necrophagecard,"Necrophage",1,31),
        Monster(R.drawable.spectercard,"Spectre",1,33),
        Monster(R.drawable.trollcard,"Troll",1,42),
        Monster(R.drawable.lichecard,"Liche",1,53))
    }
}