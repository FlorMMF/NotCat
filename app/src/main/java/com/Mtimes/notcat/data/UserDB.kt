package com.Mtimes.notcat.data


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class UserDB(context: Context, factory: SQLiteDatabase.CursorFactory?) :
        SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

            //para crear y mantener la bd
        override fun onCreate(db: SQLiteDatabase) {
                Log.d("DB", "onCreate ejecutado: creando tabla...")
                try {
                    Log.d("DB", "onCreate: creando tabla $TABLE_NAME")
                    db.execSQL(
                        "CREATE TABLE $TABLE_NAME (" +
                                "$ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "$NAME_COL TEXT, " +
                                "$EMAIL_COL TEXT, " +
                                "$PASS_COL TEXT)"


                    )

                    db.execSQL(
                        "CREATE TABLE $TABLE_RMND (" +
                                "$ID_RMND INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "$USER_RMND TEXT, " +
                                "$TITLE_RMND TEXT, " +
                                "$DESCRIPTION_RMND TEXT," +
                                "$DATE_RMND TEXT,"
                                + "$TIME_RMND INTEGER,"
                                + "$REPEAT_RMND INTEGER)"

                    )
                    Log.d("DB", "onCreate: tabla creada correctamente")
                } catch (e: Exception) {
                    Log.e("DB", "onCreate: error creando la tabla", e)
                }
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            try {
                Log.d("DB", "onUpgrade: borrando tabla antigua y recreando")
                db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
                onCreate(db)
            } catch (e: Exception) {
                Log.e("DB", "onUpgrade: error durante upgrade", e)
            }
        }

        //para interactuar con la bd (insertar y recuperar)

        fun addUser (name: String, email: String, password: String): Long{
            //la fun insert deberia devolver el id (0...n) si no lo hace se manda -1 como error
            var id: Long = -1

            val databaseAccess : SQLiteDatabase = getWritableDatabase()
            val values = ContentValues().apply{
                put(NAME_COL, name)
                put(EMAIL_COL, email)
                put(PASS_COL, password)
            }

            try{
                id= databaseAccess.insert(TABLE_NAME, null, values)
            }catch(e: Exception){
                Log.e("DB", "Error al guardar el usuario", e)
            }
            return id
        }



    fun checkUser(user_name: String): Boolean {
        val sqLiteDatabase = this.readableDatabase

        val columns = arrayOf<String>(NAME_COL)
        val selection: String = NAME_COL + " LIKE ?" // WHERE nombre LIKE ?
        val selectionArgs = arrayOf(user_name)

        val c = sqLiteDatabase.query(
            TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val b = c.count > 0
        c.close()
        return b
    }

    fun checkEmail(user_email: String): Boolean {
        val sqLiteDatabase = this.readableDatabase

        val columns = arrayOf<String>(EMAIL_COL)
        val selection: String = EMAIL_COL + " LIKE ?" // WHERE nombre LIKE ?
        val selectionArgs = arrayOf(user_email)

        val c = sqLiteDatabase.query(
            TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val b = c.count > 0
        c.close()
        return b
    }

    fun addRemind (user: String, title: String, descripition: String, date: String, time: Int, repeat: Int): Long{
        //la fun insert deberia devolver el id (0...n) si no lo hace se manda -1 como error
        var id: Long = -1

        val databaseAccess : SQLiteDatabase = getWritableDatabase()
        val values = ContentValues().apply{
            put(USER_RMND, user)
            put(TITLE_RMND,title)
            put(DESCRIPTION_RMND, descripition)
            put(DATE_RMND, date)
            put(TIME_RMND, time)
            put(REPEAT_RMND, repeat)

        }

        try{
            id= databaseAccess.insert(TABLE_RMND, null, values)
        }catch(e: Exception){
            Log.e("DB", "Error al guardar el usuario", e)
        }
        return id
    }

    //para las variables de objetos
        companion object {
            private const val DATABASE_NAME = "REMINDERS_APP"
            private const val DATABASE_VERSION = 7
            const val TABLE_NAME = "user_info"
            const val ID_COL = "id"
            const val NAME_COL = "name"
            const val EMAIL_COL = "email"
            const val PASS_COL = "password"

            const val TABLE_RMND = "reminder"
            const val ID_RMND = "id"
            const val USER_RMND = "user"
            const val TITLE_RMND = "title"
            const val DESCRIPTION_RMND = "description"
            const val DATE_RMND = "date"
            const val TIME_RMND = "time"
            const val REPEAT_RMND = "repeat"

        }
    }