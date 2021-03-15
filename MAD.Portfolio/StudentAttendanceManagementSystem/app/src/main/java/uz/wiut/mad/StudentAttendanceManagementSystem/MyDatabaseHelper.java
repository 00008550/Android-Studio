package uz.wiut.mad.StudentAttendanceManagementSystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "AttendanceLibrary.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_attendance";
    private static final String COLUMN_ID_ID = "_id2";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DATE = "user_date";
    private static final String COLUMN_MODULE = "user_module";



    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ID + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_MODULE + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void AddUser(int id, String date, String module){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_MODULE, module);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();

        }
    }
    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
           cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
    void updateData(String row_id, String id, String date, String module){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_MODULE, module);

        long result = db.update(TABLE_NAME, cv, "_id2=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context,"Failed To Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context,"Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id2=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context,"Failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successfully Deleted", Toast.LENGTH_SHORT);
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
