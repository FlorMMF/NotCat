package com.Mtimes.notcat.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.Mtimes.notcat.model.ListVM


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
                        "CREATE TABLE $TABLE_LIST (" +
                        "$ID_LIST INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$USER_LIST TEXT, " +
                        "$NAME_LIST TEXT)"
                    )

                    db.execSQL(
                        "CREATE TABLE $TABLE_ITEM (" +
                        "$ID_ITEM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$IDLIST_ITEM INTEGER, " +
                        "$NAME_ITEM TEXT, " +
                        "$QUANTITY_ITEM INTEGER, " +
                        "$PURCHASED_ITEM BOOLEAN)"

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
                db.execSQL("DROP TABLE IF EXISTS $TABLE_LIST")
                db.execSQL("DROP TABLE IF EXISTS $TABLE_ITEM")
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
    fun checkUser(email: String, password: String): Long {
        val db = this.readableDatabase

        val columns = arrayOf(ID_COL)
        val selection = "$EMAIL_COL = ? AND $PASS_COL = ?"
        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var userId: Long = -1

        if (cursor.moveToFirst()) {
            userId = cursor.getLong(cursor.getColumnIndexOrThrow(ID_COL))
        }

        cursor.close()
        return userId
    }

    fun addList(userID: Int, name: String): Long{
        var id: Long = -1

        val databaseAccess : SQLiteDatabase = getWritableDatabase()
        val values = ContentValues().apply{
            put(USER_LIST, userID)
           put(NAME_LIST, name)
        }

        try{
            id= databaseAccess.insert(TABLE_LIST, null, values)
        }catch(e: Exception){
            Log.e("DB", "Error al guardar la lista", e)
        }
        return id
    }

    fun addItemList(name: String, quantity: Int, purchased: Boolean): Long{
        var id: Long = -1

        val databaseAccess : SQLiteDatabase = getWritableDatabase()
        val values = ContentValues().apply{
           put(NAME_ITEM, name)
            put(QUANTITY_ITEM, quantity)
            put(PURCHASED_ITEM, purchased)
        }

        try{
            id= databaseAccess.insert(TABLE_ITEM, null, values)
        }catch(e: Exception){
            Log.e("DB", "Error al guardar el item", e)
        }
        return id
    }





    fun addList(userID: Int, name: String): Long{
        var id: Long = -1
        val databaseAccess : SQLiteDatabase = getWritableDatabase()
        val values = ContentValues().apply{
            put(USER_LIST, userID)
           put(NAME_LIST, name)
        }

        try{
            id= databaseAccess.insert(TABLE_LIST, null, values)
        }catch(e: Exception){
            Log.e("DB", "Error al guardar la lista", e)
        }
        return id
    }

    fun addItemList(name: String, quantity: Int, purchased: Boolean): Long{
        var id: Long = -1

        val databaseAccess : SQLiteDatabase = getWritableDatabase()
        val values = ContentValues().apply{
           put(NAME_ITEM, name)
            put(QUANTITY_ITEM, quantity)
            put(PURCHASED_ITEM, purchased)
        }

        try{
            id= databaseAccess.insert(TABLE_ITEM, null, values)
        }catch(e: Exception){
            Log.e("DB", "Error al guardar el item", e)
        }
        return id
    }

    fun getListById(listId: Int): ListVM? {
        val db = readableDatabase

        val cursor = db.query(
            TABLE_LIST,
            arrayOf(ID_LIST, USER_LIST, NAME_LIST),
            "$ID_LIST = ?",
            arrayOf(listId.toString()),
            null,
            null,
            null
        )

        var listInfo: ListVM? = null

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_LIST))
            val user = cursor.getInt(cursor.getColumnIndexOrThrow(USER_LIST))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(NAME_LIST))

            listInfo = ListVM(id, user, name)
        }

        cursor.close()
        return listInfo
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

            //para las listas
            const val TABLE_LIST = "list_info"
            const val ID_LIST = "id"
            const val USER_LIST = "user"
            const val NAME_LIST = "name"
            //para los items de las listas
            const val TABLE_ITEM = "item_info"
            const val ID_ITEM = "id"
            const val IDLIST_ITEM = "id_list"
            const val NAME_ITEM = "name"
            const val QUANTITY_ITEM = "quantity"
            const val PURCHASED_ITEM = "purchased"



        }
    }