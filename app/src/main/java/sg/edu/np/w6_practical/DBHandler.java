package sg.edu.np.w6_practical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.service.autofill.UserData;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "userDB.db";
    public static String USERS = "Users"; // this the table name
    public static String COLUMN_ID = "_id"; // here below are all properties in the class
    public static String COLUMN_NAME = "Name";
    public static String COLUMN_DESCRIPTION = "Description";
    public static String COLUMN_FOLLOWED = "Followed";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory,
                     int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE " + USERS +
                "(" +
                COLUMN_ID + " INTEGER,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " INTEGER,"
                + "CONSTRAINT" + " PK_" + USERS + " PRIMARY KEY" + " (" + COLUMN_ID + ")"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }


    public void updateUser(User user) {
        ContentValues values = new ContentValues();
        if (user.isFollowed()){
            values.put(COLUMN_FOLLOWED, 1);
        }
        else {
            values.put(COLUMN_FOLLOWED, 0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(USERS, values, COLUMN_ID + " = ?",
                new String[] {String.valueOf(user.getId())});
        db.close();
    }

    public void addUser(User newUser){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, newUser.getId());
        values.put(COLUMN_NAME, newUser.getName());
        values.put(COLUMN_DESCRIPTION, newUser.getDescription());
        if (newUser.isFollowed()){
            values.put(COLUMN_FOLLOWED, 1);
        }
        else{
            values.put(COLUMN_FOLLOWED, 0);
        }
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USERS, null, values);
        db.close();
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> list = new ArrayList<User>();

        String query = "SELECT * FROM " + USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User currentUser = new User();
        if (cursor.moveToFirst()) {
            do {
                currentUser.setId(cursor.getInt(0));
                currentUser.setName(cursor.getString(1));
                currentUser.setDescription(cursor.getString(2));
                int boolvalue = cursor.getInt(3);
                if (boolvalue == 1) {
                    currentUser.setFollowed(true);
                }
                else {
                    currentUser.setFollowed(false);
                }
                list.add(currentUser);
                } while(cursor.moveToNext());
            }
        else {
            list = null;
        }
        db.close();
        return list;
    }


}
