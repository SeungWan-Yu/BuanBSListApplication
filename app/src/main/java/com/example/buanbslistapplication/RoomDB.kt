package com.example.buanbslistapplication

import android.content.Context
import android.graphics.Bitmap
import androidx.room.*

class RoomDB {

    @Entity
    data class User(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "phone") val phone: String?,
        @ColumnInfo(name = "address") val address: String?,
        @ColumnInfo(name = "area") val area: String?,
        @ColumnInfo(name = "belong") val belong: String?,
        @ColumnInfo(name = "photo") val photo: String?,
        @ColumnInfo(name = "etc") val etc: String?,
        @ColumnInfo(name = "photourl") val photourl: ByteArray?
    )

    @Dao
    interface UserDao {
        @Query("SELECT * FROM user")
        fun getAll(): List<User>

        @Query("SELECT * FROM user WHERE id IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): List<User>

        @Query("SELECT * FROM user WHERE name LIKE '%' || :name || '%'")
        fun findByName(name: String): List<User>

        @Query("SELECT * FROM user WHERE belong IN (:belong) ORDER BY name ASC")
        fun findByBelong(belong: String): List<User>

        @Query("SELECT * FROM user WHERE area IN (:area)")
        fun findByArea(area: String): List<User>

        @Insert
        fun insertAll(vararg users: User)

        @Insert
        fun insert(user: User)

        @Delete
        fun delete(user: User)

        @Query("DELETE FROM user")
        fun deleteAll()

        @Query("UPDATE user SET photourl = (:image) WHERE id = (:id)")
        fun imageDao(id : Int, image : ByteArray?)
    }

    @Database(entities = [User::class], version = 1, exportSchema = false)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao

        companion object {
            private var instance: AppDatabase? = null

            @Synchronized
            fun getInstance(context: Context): AppDatabase? {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-contacts"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
                return instance
            }
        }
    }


}