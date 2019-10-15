package fr.emile.ddmanager.gestionBDD

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fr.emile.ddmanager.Container
import fr.emile.ddmanager.mainClass.*


class Converter {

    @TypeConverter
    fun stringToStuffType(value: String): StuffType {
        return StuffType.valueOf(value)
    }

    @TypeConverter
    fun stuffType(type: StuffType): String {
        return type.name
    }

    @TypeConverter
    fun stringToPersoReference(str: String): PersoReference {
        return PersoReference.containerRef.getEltWith(str)!!
    }

    @TypeConverter
    fun persoReferenceToString(persoRef: PersoReference): String {
        return persoRef.toKey()
    }

    @TypeConverter
    fun stringToListStuffCard(value: String): List<StuffCard> {
        val listType = object : TypeToken<List<StuffCard>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listStuffCardToString(list: List<StuffCard>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToListMonster(value: String): List<Monster> {
        val listType = object : TypeToken<List<Monster>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listMonsterToString(list: List<Monster>): String {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun stringToListPower(value: String): List<Power> {
        val listType = object : TypeToken<List<Power>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listPowerToString(list: List<Power>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToListString(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun listStringToString(map: List<String>): String {
        val gson = Gson()
        return gson.toJson(map)
    }

    @TypeConverter
    fun stringToContainer(value: String): Container<Personnage> {
        val containerType = object : TypeToken<Container<Personnage>>() {}.type
        return Gson().fromJson(value, containerType)
    }

    @TypeConverter
    fun containerToString(container: Container<Personnage>): String {
        val gson = Gson()
        return gson.toJson(container)
    }

}