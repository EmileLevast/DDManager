package fr.emile.ddmanager


class Monster(imgId:Int,nom:String,var number:Int,var costXp:Int) :Character(imgId,nom){


    override fun toKey(): String {
        return super.toKey()+number
    }

    companion object {
        val containerRef=Container(
        Monster(R.drawable.gnollcard,"gnoll",1,10),
        Monster(R.drawable.gnollcard,"gnoll",2,10),
        Monster(R.drawable.gnollcard,"gnoll",3,10),
        Monster(R.drawable.gnollcard,"gnoll",4,10),
        Monster(R.drawable.gnollcard,"gnoll",5,10),
        Monster(R.drawable.gnollcard,"gnoll",6,10),
        Monster(R.drawable.gnollcard,"gnoll",7,10),
        Monster(R.drawable.gnollcard,"gnoll",8,10),
        Monster(R.drawable.gnollcard,"gnoll",9,10),
        Monster(R.drawable.gnollcard,"gnoll",10,10))
    }
}