package com.stuartsullivan.unibwikiguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Stuart on 2014-12-26.
 */
public class DatabaseAdapter {
    // Log Constants
    private static final String TAG = "APP-DEBUG";
    // Character Keys
    public static final String KEY_CHARACTER_ROWID = "_id";
    public static final String KEY_CHARACTER_NAME = "name";
    public static final String KEY_CHARACTER_IMAGE = "image";
    public static final String KEY_CHARACTER_HEALTH = "health";
    public static final String KEY_CHARACTER_TRAITS = "traits";
    // Cancels Keys
    public static final String KEY_CANCELS_ROWID = "_id";
    public static final String KEY_CANCELS_NAME = "name";
    public static final String KEY_CANCELS_VALUE = "value";
    // Blocks Keys
    public static final String KEY_BLOCKS_ROWID = "_id";
    public static final String KEY_BLOCKS_NAME = "name";
    public static final String KEY_BLOCKS_VALUE = "value";
    // Move Keys
    public static final String KEY_MOVE_ROWID = "_id";
    public static final String KEY_MOVE_CHARACTERID = "character_id";
    public static final String KEY_MOVE_MOVETYPEID = "move_type_id";
    public static final String KEY_MOVE_NAME = "name";
    public static final String KEY_MOVE_INPUT = "input";
    // Move Data Keys
    public static final String KEY_MOVEDATA_ROWID = "_id";
    public static final String KEY_MOVEDATA_MOVEID = "move_id";
    public static final String KEY_MOVEDATA_VERSION = "version";
    public static final String KEY_MOVEDATA_DAMAGE = "damage";
    public static final String KEY_MOVEDATA_STARTUP = "startup";
    public static final String KEY_MOVEDATA_ACTIVE = "active";
    public static final String KEY_MOVEDATA_RECOVERY = "recovery";
    public static final String KEY_MOVEDATA_ADVANTAGE = "frame_adv";
    public static final String KEY_MOVEDATA_CANCELS = "cancels";
    public static final String KEY_MOVEDATA_DESCRIPTION = "description";
    public static final String KEY_MOVEDATA_BLOCKTYPE = "block_type_id";
    // Move Type Keys
    public static final String KEY_MOVETYPE_ROWID = "_id";
    public static final String KEY_MOVETYPE_TYPENAME = "name";
    // Combo Keys
    public static final String KEY_COMBO_ROWID = "_id";
    public static final String KEY_COMBO_CHARACTERID = "character_id";
    public static final String KEY_COMBO_TYPE = "type";
    public static final String KEY_COMBO_SEQUENCE = "sequence";
    // Combo Type Keys
    public static final String KEY_COMBOTYPE_ROWID = "_id";
    public static final String KEY_COMBOTYPE_TYPENAME = "name";

    // Database constants
    private static final String DATABASE_NAME = "UNiBGuide";
    private static final String DATABASE_CHARACTER_TABLE = "characters";
    private static final String DATABASE_CANCELS_TABLE = "cancels";
    private static final String DATABASE_BLOCKS_TABLE  = "blocks";
    private static final String DATABASE_MOVE_TABLE = "moves";
    private static final String DATABASE_MOVEDATA_TABLE = "move_data";
    private static final String DATABASE_MOVETYPE_TABLE = "move_types";
    private static final String DATABASE_COMBO_TABLE = "combos";
    private static final String DATABASE_COMBOTYPE_TABLE = "combo_types";
    private static final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDB;

    public DatabaseAdapter(Context c) {
        ourContext = c;
    }

    public DatabaseAdapter open() {
        ourHelper = new DBHelper(ourContext);
        ourDB = ourHelper.getWritableDatabase();
        return this;
    }

    public void close()
    {
        ourHelper.close();
    }

    public long createCharacterEntry(String name, String image, int health, String traits) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_CHARACTER_NAME, name);
        cv.put(KEY_CHARACTER_IMAGE, image);
        cv.put(KEY_CHARACTER_HEALTH, health);
        cv.put(KEY_CHARACTER_TRAITS, traits);
        return ourDB.insert(DATABASE_CHARACTER_TABLE, null, cv);
    }

    public long createMoveEntry(String name, int character_id, int move_type_id, String input) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_MOVE_CHARACTERID, character_id);
        cv.put(KEY_MOVE_NAME, name);
        cv.put(KEY_MOVE_MOVETYPEID, move_type_id);
        cv.put(KEY_MOVE_INPUT, input);
        return ourDB.insert(DATABASE_MOVE_TABLE, null, cv);
    }

    public long createMoveDataEntry(int move_id, String version, String damage, String startup, String active, String recovery, String frameAdv, String cancels, String desc, String blocktype) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_MOVEDATA_MOVEID, move_id);
        cv.put(KEY_MOVEDATA_VERSION, version);
        cv.put(KEY_MOVEDATA_DAMAGE, damage);
        cv.put(KEY_MOVEDATA_STARTUP, startup);
        cv.put(KEY_MOVEDATA_ACTIVE, active);
        cv.put(KEY_MOVEDATA_RECOVERY, recovery);
        cv.put(KEY_MOVEDATA_ADVANTAGE, frameAdv);
        cv.put(KEY_MOVEDATA_CANCELS, cancels);
        cv.put(KEY_MOVEDATA_DESCRIPTION, desc);
        cv.put(KEY_MOVEDATA_BLOCKTYPE, blocktype);
        return ourDB.insert(DATABASE_MOVEDATA_TABLE, null, cv);
    }

    public long createComboEntry(int character_id, String type, String sequence) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_COMBO_CHARACTERID, character_id);
        cv.put(KEY_COMBO_TYPE, type);
        cv.put(KEY_COMBO_SEQUENCE, sequence);
        return ourDB.insert(DATABASE_COMBO_TABLE, null, cv);
    }

    public long createMoveTypeEntry(int id, String name) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_MOVETYPE_ROWID, id);
        cv.put(KEY_MOVETYPE_TYPENAME, name);
        return ourDB.insert(DATABASE_MOVETYPE_TABLE, null, cv);
    }

    public long createComboTypeEntry(int id, String name) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_COMBOTYPE_ROWID, id);
        cv.put(KEY_COMBOTYPE_TYPENAME, name);
        return ourDB.insert(DATABASE_COMBOTYPE_TABLE, null, cv);
    }

    public long createBlockTypeEntry(int id, String name, String value) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_BLOCKS_ROWID, id);
        cv.put(KEY_BLOCKS_NAME, name);
        cv.put(KEY_BLOCKS_VALUE, value);
        return ourDB.insert(DATABASE_BLOCKS_TABLE, null, cv);
    }

    public long createCancelTypeEntry(int id, String name, String value) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_CANCELS_ROWID, id);
        cv.put(KEY_CANCELS_NAME, name);
        cv.put(KEY_CANCELS_VALUE, value);
        return ourDB.insert(DATABASE_CANCELS_TABLE, null, cv);
    }

    public int getMoveType(String moveType) {
        // The entities id
        int _id = -1;
        // Query the type id
        Cursor cur = ourDB.rawQuery("SELECT " + KEY_MOVETYPE_ROWID + " " +
                "FROM " + DATABASE_MOVETYPE_TABLE + " " +
                "WHERE " + KEY_MOVETYPE_TYPENAME + " = '" + moveType + "'", null);

        // if there is  id returned
        if (cur.getCount() > 0) {
            if (cur.moveToFirst())
                _id = cur.getInt(0);
        }
        Log.i(TAG, "Move Type: " +_id + " - Name: " + moveType);
        return _id;
    }

    public int getCharacterId(String name) {
        // The character id
        int _id = -1;
        // Query the characters id
        Cursor cur = ourDB.rawQuery("SELECT " + KEY_CHARACTER_ROWID + " " +
                "FROM " + DATABASE_CHARACTER_TABLE + " " +
                "WHERE " + KEY_CHARACTER_NAME + " = '" + name + "'"
                ,null);
        // if you get one back return it
        if (cur.getCount() > 0) {
            if (cur.moveToFirst())
                _id = cur.getInt(0);
        }
        Log.i(TAG, "Character: " + _id + " - Name: " + name);
        return _id;
    }

    public String getCharacterInfo(int id) {
        // The number of moves
        String result = "";

        // The query
        String query = "SELECT " + KEY_CHARACTER_NAME + ", " +
                KEY_CHARACTER_HEALTH + ", " + KEY_CHARACTER_TRAITS+ " " +
                "FROM " + DATABASE_CHARACTER_TABLE + " " +
                "WHERE " + KEY_CHARACTER_ROWID + " = " + id;
        // Set up the cursor
        Cursor cur = ourDB.rawQuery(query, null);

        // check if the cursor is empty
        if (cur.getCount() > 0) {
            if (cur.moveToFirst())
                result = cur.getString(0) + ":" + cur.getInt(1) + ":" + cur.getString(2);
        }
        Log.i(TAG, "Character: " + id + " - INFO: " + result);
        return result;
    }


    public int getCharacterMoveCount(int id) {
        // The number of moves
        int count = -1;

        // The query
        String query = "SELECT COUNT(*) " +
                "FROM " + DATABASE_MOVE_TABLE + " " +
                "WHERE " + KEY_MOVE_CHARACTERID + " = " + id;
        // Set up the cursor
        Cursor cur = ourDB.rawQuery(query, null);

        // check if the cursor is empty
        if (cur.getCount() > 0) {
            if (cur.moveToFirst())
                count = cur.getInt(0);
        }
        Log.i(TAG, "Character: " + id + " - Move Count: " + count);
        return count;
    }

    public String getCharacterMoves(int id) {
        // return string
        String result = "";

        // The query
        String query = "SELECT m." + KEY_MOVE_NAME + ", " +
                "mt." + KEY_MOVETYPE_TYPENAME + ", " +
                "md." + KEY_MOVEDATA_DAMAGE + ", " +
                "md." + KEY_MOVEDATA_STARTUP + ", " +
                "md." + KEY_MOVEDATA_ACTIVE + ", " +
                "md." + KEY_MOVEDATA_RECOVERY + ", " +
                "md." + KEY_MOVEDATA_ADVANTAGE + ", " +
                "md." + KEY_MOVEDATA_BLOCKTYPE + ", " +
                "md." + KEY_MOVEDATA_CANCELS + " " +
                "FROM " + DATABASE_MOVE_TABLE + " m, " + DATABASE_MOVETYPE_TABLE + " mt, " + DATABASE_MOVEDATA_TABLE + " md " +
                "WHERE m." + KEY_MOVE_CHARACTERID + " = " + id + " " +
                "AND md." + KEY_MOVEDATA_ROWID + " = m." + KEY_MOVE_ROWID + " " +
                "AND m." + KEY_MOVE_MOVETYPEID + " = mt." + KEY_MOVETYPE_ROWID;
        // Set up the cursor
        Cursor cur = ourDB.rawQuery(query, null);

        // get the data
        if (cur.getCount() > 0) {
            cur.moveToFirst();
            while(cur.isAfterLast() == false) {
                // build the result string
                result += cur.getString(0) + ":" +
                          cur.getString(1) + ":" +
                          cur.getString(2) + ":" +
                          cur.getString(3) + ":" +
                          cur.getString(4) + ":" +
                          cur.getString(5) + ":" +
                          cur.getString(6) + ":" +
                          cur.getString(7) + ":" +
                          cur.getString(8) + "\n";

                // move to next line
                cur.moveToNext();
            }
        }

        return result;
    }

    public int getCharacterComboCount(int id) {
        // The number of moves
        int count = -1;

        // The query
        String query = "SELECT COUNT(*) " +
                "FROM " + DATABASE_COMBO_TABLE + " " +
                "WHERE " + KEY_MOVE_CHARACTERID + " = " + id;
        // Set up the cursor
        Cursor cur = ourDB.rawQuery(query, null);

        // check if the cursor is empty
        if (cur.getCount() > 0) {
            if (cur.moveToFirst())
                count = cur.getInt(0);
        }

        Log.i(TAG, "Character: " + id + " - Combo Count: " + count);
        return count;
    }

    public String getCharacterCombos(int id) {
        // return string
        String result = "";

        // The query
        String query = "SELECT " + KEY_COMBO_TYPE + ", " +
                KEY_COMBO_SEQUENCE + " " +
                "FROM " + DATABASE_COMBO_TABLE + " " +
                "WHERE " + KEY_COMBO_CHARACTERID + " = " + id;
        // Set up the cursor
        Cursor cur = ourDB.rawQuery(query, null);

        // get the data
        if (cur.getCount() > 0) {
            cur.moveToFirst();
            while(cur.isAfterLast() == false) {
                // build the result string
                result += cur.getString(0) + ":" +
                        cur.getString(1) + "\n";

                // move to next line
                cur.moveToNext();
            }
        }

        return result;
    }

    private static class DBHelper extends SQLiteOpenHelper {
        // Create Table SQL
        private static final String CREATE_CHARACTER_TABLE = "CREATE TABLE " + DATABASE_CHARACTER_TABLE + " (" +
                KEY_CHARACTER_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CHARACTER_NAME + " TEXT NOT NULL UNIQUE, " +
                KEY_CHARACTER_IMAGE + " TEXT," +
                KEY_CHARACTER_HEALTH + " INTEGER, " +
                KEY_CHARACTER_TRAITS + " TEXT)";
        private static final String CREATE_CANCELS_TABLE = "CREATE TABLE " + DATABASE_CANCELS_TABLE + " (" +
                KEY_CANCELS_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_CANCELS_NAME + " TEXT NOT NULL UNIQUE, " +
                KEY_CANCELS_VALUE + " TEXT)";
        private static final String CREATE_BLOCKS_TABLE = "CREATE TABLE " + DATABASE_BLOCKS_TABLE + " (" +
                KEY_BLOCKS_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_BLOCKS_NAME + " TEXT NOT NULL UNIQUE, " +
                KEY_BLOCKS_VALUE + " TEXT)";
        private static final String CREATE_MOVE_TABLE = "CREATE TABLE " + DATABASE_MOVE_TABLE + " (" +
                KEY_MOVE_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MOVE_CHARACTERID + " INTEGER NOT NULL, " +
                KEY_MOVE_NAME + " INTEGER NOT NULL, " +
                KEY_MOVE_MOVETYPEID + " INTEGER NOT NULL, "+
                KEY_MOVE_INPUT + " TEXT, " +
                "FOREIGN KEY (" + KEY_MOVE_CHARACTERID + ") REFERENCES " + DATABASE_CHARACTER_TABLE + "(" + KEY_CHARACTER_ROWID + ")" +
                "FOREIGN KEY (" + KEY_MOVE_MOVETYPEID + ") REFERENCES " + DATABASE_MOVETYPE_TABLE + "(" + KEY_MOVETYPE_ROWID + "))";
        private static final String CREATE_MOVEDATA_TABLE = "CREATE TABLE " + DATABASE_MOVEDATA_TABLE + "(" +
                KEY_MOVEDATA_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_MOVEDATA_MOVEID + " INTEGER NOT NULL, " +
                KEY_MOVEDATA_VERSION + " TEXT NOT NULL, " +
                KEY_MOVEDATA_DAMAGE + " TEXT, " +
                KEY_MOVEDATA_STARTUP + " TEXT, " +
                KEY_MOVEDATA_ACTIVE + " TEXT, " +
                KEY_MOVEDATA_RECOVERY + " TEXT, " +
                KEY_MOVEDATA_ADVANTAGE + " TEXT, " +
                KEY_MOVEDATA_BLOCKTYPE + " TEXT, " +
                KEY_MOVEDATA_CANCELS + " TEXT, " +
                KEY_MOVEDATA_DESCRIPTION + " TEXT, " +
                "FOREIGN KEY (" + KEY_MOVEDATA_MOVEID + ") REFERENCES " + DATABASE_MOVE_TABLE + "( " + KEY_MOVE_ROWID + "))";
        private static final String CREATE_MOVETYPE_TABLE = "CREATE TABLE " + DATABASE_MOVETYPE_TABLE + " (" +
                KEY_MOVETYPE_ROWID + " INTEGER PRIMARY KEY, " +
                KEY_MOVETYPE_TYPENAME + " TEXT NOT NULL)";
        private static final String CREATE_COMBO_TABLE = "CREATE TABLE " + DATABASE_COMBO_TABLE + " (" +
                KEY_COMBO_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_COMBO_CHARACTERID + " INTEGER NOT NULL, " +
                KEY_COMBO_TYPE + " TEXT, " +
                KEY_COMBO_SEQUENCE + " TEXT, " +
                "FOREIGN KEY (" + KEY_COMBO_CHARACTERID + ") REFERENCES " + DATABASE_CHARACTER_TABLE + "(" + KEY_CHARACTER_ROWID + ")" +
                "FOREIGN KEY (" + KEY_COMBO_TYPE + ") REFERENCES " + DATABASE_COMBOTYPE_TABLE + "(" + KEY_CHARACTER_ROWID + "))";
        private static final String CREATE_COMBOTYPE_TABLE = "CREATE TABLE " + DATABASE_COMBOTYPE_TABLE + " (" +
                KEY_COMBOTYPE_ROWID + " INTEGER PRIMARY KEY, " +
                KEY_COMBOTYPE_TYPENAME + " TEXT NOT NULL)";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "Created: " + DATABASE_CHARACTER_TABLE);
            db.execSQL(CREATE_CHARACTER_TABLE);
            Log.i(TAG, "Created: " + DATABASE_CANCELS_TABLE);
            db.execSQL(CREATE_CANCELS_TABLE);
            Log.i(TAG, "Created: " + DATABASE_BLOCKS_TABLE);
            db.execSQL(CREATE_BLOCKS_TABLE);
            Log.i(TAG, "Created: " + DATABASE_MOVETYPE_TABLE);
            db.execSQL(CREATE_MOVETYPE_TABLE);
            Log.i(TAG, "Created: " + DATABASE_COMBOTYPE_TABLE);
            db.execSQL(CREATE_COMBOTYPE_TABLE);
            Log.i(TAG, "Created: " + DATABASE_MOVE_TABLE);
            db.execSQL(CREATE_MOVE_TABLE);
            Log.i(TAG, "Created: " + DATABASE_MOVEDATA_TABLE);
            db.execSQL(CREATE_MOVEDATA_TABLE);
            Log.i(TAG, "Created: " +DATABASE_COMBO_TABLE);
            db.execSQL(CREATE_COMBO_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_BLOCKS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CANCELS_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_COMBOTYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MOVETYPE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MOVE_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_MOVEDATA_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_COMBO_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_CHARACTER_TABLE);
            onCreate(db);
        }
    }
}
