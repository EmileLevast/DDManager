package fr.emile.ddmanager

interface IShowImage :Cloneable{
    var imgId:Int
    public override fun clone(): IShowImage {
        return super.clone() as IShowImage
    }
}

fun List<IShowImage>.getOnlyIdImage():List<Int>{
    val list= mutableListOf<Int>()
    this.forEach { list.add(it.imgId) }
    return list
}