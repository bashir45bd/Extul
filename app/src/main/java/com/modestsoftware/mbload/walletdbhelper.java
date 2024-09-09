package com.modestsoftware.mbload;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class walletdbhelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "walletdatabase";
    private static final int DATABASE_VERSION = 16;

    public static final String in_id = "id";
    public static final String in_amount = "amount";
    public static final String in_time = "time";
    private static final String TABLE_NAME = "table1";

    public walletdbhelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" Create table "+TABLE_NAME+" ("+in_id+" INTEGER primary key autoincrement,"+in_amount+" DOUBLE, reason Text,"+in_time+" DOUBLE, condition INTEGER)");

        db.execSQL(" Create table expence (id INTEGER primary key autoincrement, amount DOUBLE, reason Text, time DOUBLE, condition INTEGER)");

        db.execSQL(" Create table note (id INTEGER primary key autoincrement, title Text, note Text, time DOUBLE)");

        db.execSQL(" Create table user (user_id INTEGER primary key autoincrement, name Text, address Text, time DOUBLE)");

        db.execSQL(" Create table rec (id INTEGER primary key autoincrement,amount DOUBLE, reason Text, user_id INTEGER, time DOUBLE, FOREIGN KEY (user_id) REFERENCES Users(user_id))");

        db.execSQL(" Create table pay (id INTEGER primary key autoincrement,amount DOUBLE, reason Text, user_id INTEGER, time DOUBLE, FOREIGN KEY (user_id) REFERENCES Users(user_id))");

        db.execSQL(" Create table to_do_task (id INTEGER primary key autoincrement, task Text, time DOUBLE)");

        db.execSQL(" Create table to_do_com (id INTEGER primary key autoincrement, task Text, time DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists table1");
        db.execSQL("drop table if exists expence");
        db.execSQL("drop table if exists note");
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists rec");
        db.execSQL("drop table if exists pay");
        db.execSQL("drop table if exists to_do_task");
        db.execSQL("drop table if exists to_do_com");

    }

    public void add_income (double amount,String reason){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        contentValues.put("condition",4);
        database.insert("table1",null, contentValues);

    }

    public double calculateincome(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select * from table1",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public void add_extence (double amount,String reason){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        contentValues.put("condition",5);
        database.insert("expence",null, contentValues);

    }

    public double calculateextence(){

        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(" select * from expence",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }



    public double calculateextenceday(){

        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateincomeday(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateincomeweek(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', '-7 days') * 1000 AND strftime('%s', 'now') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateexpenceweek(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', '-7 days') * 1000 AND strftime('%s', 'now') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateincomemonth(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of month') * 1000 AND strftime('%s', 'now', 'start of month', '+1 month', '-1 second') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateexpencemonth(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of month') * 1000 AND strftime('%s', 'now', 'start of month', '+1 month', '-1 second') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public double calculateincomeyear(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of year') * 1000 AND strftime('%s', 'now', 'start of year', '+1 year', '-1 second') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }

    public double calculateexpenceyear(){
        double totalincome = 0;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of year') * 1000 AND strftime('%s', 'now', 'start of year', '+1 year', '-1 second') * 1000",null);

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }

    public Cursor getallincome (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'table1' AS Source FROM table1) AS Combined ORDER BY time DESC;",null);

        return cursor;
    }

    public Cursor in_today (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);
        return cursor;
    }

    public Cursor ex_today (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);
        return cursor;
    }


    public Cursor in_lastday (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', '-1 day', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);
        return cursor;
    }

    public Cursor ex_lastday (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', '-1 day', 'start of day') * 1000 AND strftime('%s', 'now', 'start of day', '+1 day', '-1 second') * 1000;",null);
        return cursor;
    }


    public Cursor in_lastweek (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', '-7 days') * 1000 AND strftime('%s', 'now') * 1000",null);
        return cursor;
    }

    public Cursor ex_lastweek (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', '-7 days') * 1000 AND strftime('%s', 'now') * 1000",null);
        return cursor;
    }


    public Cursor in_lastmonth (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of month') * 1000 AND strftime('%s', 'now', 'start of month', '+1 month', '-1 second') * 1000",null);
        return cursor;
    }


    public Cursor ex_lastmonth (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of month') * 1000 AND strftime('%s', 'now', 'start of month', '+1 month', '-1 second') * 1000",null);
        return cursor;
    }


    public Cursor in_lastyear (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE time BETWEEN strftime('%s', 'now', 'start of year') * 1000 AND strftime('%s', 'now', 'start of year', '+1 year', '-1 second') * 1000",null);
        return cursor;
    }


    public Cursor ex_lastyear (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE time BETWEEN strftime('%s', 'now', 'start of year') * 1000 AND strftime('%s', 'now', 'start of year', '+1 year', '-1 second') * 1000",null);
        return cursor;
    }




    public Cursor getallexpence (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'expence' AS Source FROM expence) AS Combined ORDER BY time DESC;",null);

        return cursor;
    }

    public Cursor recent (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT *, 'table1' AS Source FROM table1 UNION ALL SELECT *, 'expence' AS Source FROM expence) AS Combined ORDER BY time DESC;",null);

        return cursor;
    }

    public Cursor search_income ( String key1){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM table1 WHERE reason LIKE '"+key1+"%'",null);


        return cursor;
    }

    public Cursor search_expence ( String key){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM expence WHERE reason LIKE '"+key+"%'",null);

        return cursor;
    }

    public void update_income ( String id,double amount,String reason){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        database.update("table1", contentValues,  "id=?", new String[]{id});

    }

    public void update_expence ( String id,double amount,String reason){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        database.update("expence", contentValues,  "id=?", new String[]{id});

    }

    public void delete (String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from table1 where id like "+id);



    }
    public void delete_ex(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from expence where id like "+id);



    }


    public void add_note (String title,String note){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("note",note);
        contentValues.put("time",System.currentTimeMillis());
        database.insert("note",null, contentValues);

    }

    public Cursor get_allnote (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'note' AS Source FROM note) AS Combined ORDER BY time DESC;",null);

        return cursor;
    }

    public Cursor search_note ( String key1){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM note WHERE title LIKE '"+key1+"%'",null);


        return cursor;
    }


    public void delete_note(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from note where id like "+id);


    }


    public void update_note( String id,String title,String note){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("note",note);
        database.update("note", contentValues,  "id=?", new String[]{id});

    }


    public void add_user_tali (String name,String address){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        contentValues.put("time",System.currentTimeMillis());
        database.insert("user",null, contentValues);

    }

    public Cursor get_user_tali (){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'user' AS Source FROM user) AS Combined ORDER BY time DESC;",null);

        return cursor;
    }


    public void add_rec_tali (double amount,String reason,int user_id){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        contentValues.put("user_id",user_id);
        database.insert("rec",null, contentValues);

    }

    public void up_rec_tali ( String id,double amount,String reason,int user_id){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("user_id",user_id);
        database.update("rec", contentValues,  "id=?", new String[]{id});

    }

    public void delete_rec(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from rec where id like "+id);


    }

    public void add_pay_tali (double amount,String reason,int user_id){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("time",System.currentTimeMillis());
        contentValues.put("user_id",user_id);
        database.insert("pay",null, contentValues);

    }

    public void up_pay_tali ( String id,double amount,String reason,int user_id){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount",amount);
        contentValues.put("reason",reason);
        contentValues.put("user_id",user_id);
        database.update("pay", contentValues,  "id=?", new String[]{id});

    }

    public void delete_pay(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from pay where id like "+id);


    }

    public double calculate_rec(String key){

        double total_rec = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM rec WHERE user_id = ?", new String[]{String.valueOf(key)});

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                total_rec = total_rec+amount;

            }
        }


        return total_rec;
    }

    public double calculate_pay(String key1){
        double totalincome = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM pay WHERE user_id = ?", new String[]{String.valueOf(key1)});

        if (cursor!=null && cursor.getCount()>0){

            while ( cursor.moveToNext() ){
                double amount = cursor.getDouble(1);
                totalincome = totalincome+amount;
            }
        }


        return totalincome;
    }


    public Cursor get_user_history (String key2){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT *, 'rec' AS Source FROM rec WHERE user_id = ?) AS Combined ORDER BY time DESC;", new String[]{String.valueOf(key2)});

        return cursor;
    }


    public Cursor take_user_history (String key2){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'pay' AS Source FROM pay WHERE user_id = ? ) AS Combined ORDER BY time DESC;", new String[]{String.valueOf(key2)});

        return cursor;
    }



    public void add_to_do_task (String task){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task",task);
        contentValues.put("time",System.currentTimeMillis());
        database.insert("to_do_task",null, contentValues);

    }


    public Cursor get_task(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'to_do_task' AS Source FROM to_do_task) AS Combined ORDER BY time DESC;",null);
        return cursor;
    }

    public void Update_task (String id,String task){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task",task);
        database.update("to_do_task", contentValues,  "id=?", new String[]{id});

    }

    public void delete_to_do_task (String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from to_do_task where id like "+id);


    }



    public Cursor search_task ( String key3){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM to_do_task WHERE task LIKE '"+key3+"%'",null);


        return cursor;
    }

    public void add_to_do_com (String task){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("task",task);
        contentValues.put("time",System.currentTimeMillis());
        database.insert("to_do_com",null, contentValues);

    }



    public Cursor get_com(){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM (SELECT *, 'to_do_com' AS Source FROM to_do_com) AS Combined ORDER BY time DESC;",null);
        return cursor;
    }

    public void delete_com(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from to_do_com where id like "+id);


    }

    public Cursor search_tali_user ( String key1){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE name LIKE '"+key1+"%'",null);


        return cursor;
    }


    public void delete_user(String id){

        SQLiteDatabase db =  this.getWritableDatabase();

        db.execSQL("delete from user where user_id like "+id);


    }

    public void tali_user_up (String id,String name,String address){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("address",address);
        database.update("user", contentValues,  "user_id=?", new String[]{id});

    }
//================================================================




}
