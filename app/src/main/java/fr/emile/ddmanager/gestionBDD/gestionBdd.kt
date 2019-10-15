package fr.emile.ddmanager.gestionBDD

import android.content.Context
import androidx.room.*
import fr.emile.ddmanager.mainClass.Game
import androidx.room.Room




//we use this dao to register a game in the bdd
@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGames(vararg game: Game)

    @Query("SELECT * FROM game")
    fun loadAllGames(): Array<Game>
}


//we create the database to manage all teh daos
@Database(entities = [Game::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao


    //get an Instance of the Database
    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "game.db")
                            .fallbackToDestructiveMigration()//delete the database when change it
                            .allowMainThreadQueries()//otherwise you have to create asynchronous task
                            .build()
                }
            }
            return INSTANCE
        }
    }

}
