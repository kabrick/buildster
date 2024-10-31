package com.kaizen.buildster.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBHelper extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "buildster";

    //Table names
    private static final String PROJECT_LIST = "project_list";
    private static final String SUBSTRUCTURE = "sub";
    private static final String SUPERSTRUCTURE = "super";
    private static final String ROOFING = "roofing";
    private static final String FINISHES = "finishes";

    //Common column names
    //PROJECT_LIST column names
    public static final String PROJECT_LIST_ID = "_id";
    public static final String PROJECT_LIST_NAME = "title";
    public static final String PROJECT_DATE_CREATED = "date";
    public static final String PROJECT_COST = "cost";
    public static final String PROJECT_COMP_LEVEL = "comp_level";

    //CardView Table
    private static final String TABLE_CARD_VIEW = "card_view";
    public static final String SUB_COST = "sub_cost";
    public static final String SUP_COST = "sup_cost";
    public static final String ROOF_COST = "roof_cost";
    public static final String FINISH_COST = "finish_cost";
    public static final String SUB_PRO = "sub_pro";
    public static final String SUP_PRO = "sup_pro";
    public static final String ROOF_PRO = "roof_pro";
    public static final String FINISH_PRO = "finish_pro";

    //cost table
    private static final String TABLE_COST = "table_cost";
    public static final String TITLE = "title";
    public static final String COST = "cost";

    //NEW_PROJECT column names
    public static final String AA = "a_cement";
    public static final String AB = "a_sand";
    public static final String AC = "a_aggregates";
    public static final String BA = "b_clay_bricks";
    public static final String BB = "b_cement";
    public static final String BC = "b_sand";
    public static final String CA = "hard_core";
    public static final String DA = "d_cement";
    public static final String DB = "d_sand";
    public static final String DC = "d_aggregates";
    public static final String EA = "e_blocks";
    public static final String EB = "e_cement";
    public static final String EC = "e_sand";
    public static final String FA = "f_nails";
    public static final String FB = "f_timber";
    public static final String GA = "g_cement";
    public static final String GB = "g_sand";
    public static final String GC = "g_aggregates";
    public static final String GD = "steel_bars";
    public static final String GE = "rings";
    public static final String GF = "binding_wire";
    public static final String H = "h_timber";
    public static final String I = "i_timber";
    public static final String J = "j_timber";
    public static final String K = "k_sheets";
    public static final String L = "l_boards";
    public static final String MA = "m_cement";
    public static final String MB = "m_sand";
    public static final String NA = "n_tiles";
    public static final String NB = "n_cement";
    public static final String OA = "o_tiles";
    public static final String OB = "o_cement";
    public static final String P = "metal";
    public static final String Q = "q_timber";
    public static final String RA = "r_cement";
    public static final String RB = "r_sand";
    public static final String SA = "s_cement";
    public static final String SB = "s_sand";
    public static final String TA = "t_cement";
    public static final String TB = "t_sand";

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO Auto-generated method stub
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO Auto-generated method stub
    }

    //delete all tables
    public void delete(){
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD_VIEW);
        db.execSQL("DROP TABLE IF EXISTS " + FINISHES);
        db.execSQL("DROP TABLE IF EXISTS " + ROOFING);
        db.execSQL("DROP TABLE IF EXISTS " + SUPERSTRUCTURE);
        db.execSQL("DROP TABLE IF EXISTS " + SUBSTRUCTURE);
        db.execSQL("DROP TABLE IF EXISTS " + PROJECT_LIST);
    }

    //create table projects_list called from MainActivity.java
    public String createProjectList() {
        String result;
        final SQLiteDatabase db = getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + PROJECT_LIST + " (" +
                PROJECT_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PROJECT_LIST_NAME + " TEXT NOT NULL," + PROJECT_DATE_CREATED + " INTEGER," +
                PROJECT_COST + " INTEGER," + PROJECT_COMP_LEVEL + " INTEGER);";
        try {
            db.execSQL(CREATE_TABLE);
            result = "Table created successfully";
        } catch (SQLException e) {
            result = "Table not created because of" + e;
        }

        createMultipleTable();

        createCardViewTable();

        createCostTable();

        db.close();

        return result;
    }

    //displaying list of open projects by checking if table is empty and return values if not
    public boolean projectList() {
        boolean result;
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "SELECT COUNT(*) FROM " + PROJECT_LIST;
        Cursor cursor = db.rawQuery(count, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        if (icount > 0) {
            //table is populated and results should be sent to data model
            result = true;
            cursor.close();
        } else {
            //table is empty
            result = false;
            cursor.close();
        }
        return result;
    }

    public Cursor getProjectList() {

        // get all columns for all rows
        SQLiteDatabase db = this.getWritableDatabase();

        String[] allColumns = new String[]{
                PROJECT_LIST_ID, PROJECT_LIST_NAME,
        };
        Cursor c = db.query(PROJECT_LIST, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public void createCostTable(){
        SQLiteDatabase db = this.getWritableDatabase();

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_COST + " (" +
                TITLE + " TEXT," +
                COST + " INTEGER);";

        db.execSQL(CREATE_TABLE);

        //insertCostTable();
    }

    public void insertCostTable(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO '" + TABLE_COST + "' ('" + TITLE + "', '" + COST + "') VALUES " +
                "('Cement','30000')," +
                "('Sand','30000')," +
                "('Aggregates','36000')," +
                "('Clay Bricks','200')," +
                "('Hardcore','45000')," +
                "('Bitumen Felt','1000')," +
                "('Polythene Sheeting','2500')," +
                "('Blocks','200')," +
                "('12mm Steel Bars'," + name + ")," +
                "('8mm Rings','20000')," +
                "('Binding Wire','80000')," +
                "('Timber','15000')," +
                "('Hoop Iron','40000')," +
                "('Nails(1kg)','8000')," +
                "('Iron Sheets','58000')," +
                "('Fascia Boards','20000')," +
                "('Tiles','27000')," +
                "('Paint (20 Litre Jerrycan)','250000')";

            db.execSQL(query);
            db.close();
    }

    public Cursor getCostTable(){
        // get all columns for all rows
        SQLiteDatabase db = this.getWritableDatabase();

        String[] allColumns = new String[]{
                TITLE, COST,
        };
        Cursor c = db.query(TABLE_COST, allColumns, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }

        return c;
    }

    public String addProject(String name) {
        String result;
        try {
            ContentValues values = new ContentValues();
            values.put(PROJECT_LIST_NAME, name);
            values.put(PROJECT_DATE_CREATED, 0);
            values.put(PROJECT_COST, 0);
            values.put(PROJECT_COMP_LEVEL, 0);

            SQLiteDatabase db = this.getWritableDatabase();

            db.insert(PROJECT_LIST, null, values);
            db.close();
            result = "Success";
        } catch (SQLException e) {
            result = "Error" + e;
        }

        insertMultipleTable(name);

        insertCardViewTable(name);

        return result;
    }

    //create table for substructure, super..........
    public void createMultipleTable() {
        String sub_structure = "CREATE TABLE IF NOT EXISTS " + SUBSTRUCTURE + " (" +
                PROJECT_LIST_NAME + " TEXT PRIMARY KEY NOT NULL," + AA + " INTEGER," + AB + " INTEGER," + AC + " INTEGER," +
                BA + " INTEGER," + BB + " INTEGER," + BC + " INTEGER," + CA + " INTEGER," + DA + " INTEGER," + DB + " INTEGER," +
                DC + " INTEGER);";
        String super_structure = "CREATE TABLE IF NOT EXISTS " + SUPERSTRUCTURE + " (" +
                PROJECT_LIST_NAME + " TEXT PRIMARY KEY NOT NULL," + EA + " INTEGER," + EB + " INTEGER," + EC + " INTEGER," +
                FA + " INTEGER," + FB + " INTEGER," + GA + " INTEGER," + GB + " INTEGER," + GC + " INTEGER," +
                GD + " INTEGER," + GE + " INTEGER," + GF + " INTEGER);";
        String roofing = "CREATE TABLE IF NOT EXISTS " + ROOFING + " (" +
                PROJECT_LIST_NAME + " TEXT PRIMARY KEY NOT NULL," + H + " INTEGER," + I + " INTEGER," + J + " INTEGER," +
                K + " INTEGER," + L + " INTEGER);";
        String finishes = "CREATE TABLE IF NOT EXISTS " + FINISHES + " (" +
                PROJECT_LIST_NAME + " TEXT PRIMARY KEY NOT NULL," + MA + " INTEGER," + MB + " INTEGER," + NA + " INTEGER," +
                NB + " INTEGER," + OA + " INTEGER," + OB + " INTEGER," + P + " INTEGER," + Q + " INTEGER," + RA + " INTEGER," +
                RB + " INTEGER," + SA + " INTEGER," +
                SB + " INTEGER," + TA + " INTEGER," + TB + " INTEGER);";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sub_structure);
        db.execSQL(finishes);
        db.execSQL(super_structure);
        db.execSQL(roofing);
        db.close();
    }

    public void insertMultipleTable(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        //substructure values
        ContentValues sub = new ContentValues();
        sub.put(PROJECT_LIST_NAME, name);
        sub.put(AA, 0);
        sub.put(AB, 0);
        sub.put(AC, 0);
        sub.put(BA, 0);
        sub.put(BB, 0);
        sub.put(BC, 0);
        sub.put(CA, 0);
        sub.put(DA, 0);
        sub.put(DB, 0);
        sub.put(DC, 0);

        //superstructure values
        ContentValues supers = new ContentValues();
        supers.put(PROJECT_LIST_NAME, name);
        supers.put(EA, 0);
        supers.put(EB, 0);
        supers.put(EC, 0);
        supers.put(FA, 0);
        supers.put(FB, 0);
        supers.put(GA, 0);
        supers.put(GB, 0);
        supers.put(GC, 0);
        supers.put(GD, 0);
        supers.put(GE, 0);
        supers.put(GF, 0);

        //roofing values
        ContentValues roofing = new ContentValues();
        roofing.put(PROJECT_LIST_NAME, name);
        roofing.put(H, 0);
        roofing.put(I, 0);
        roofing.put(J, 0);
        roofing.put(K, 0);
        roofing.put(L, 0);

        //finish values
        ContentValues finish = new ContentValues();
        finish.put(PROJECT_LIST_NAME, name);
        finish.put(MA, 0);
        finish.put(MB, 0);
        finish.put(NA, 0);
        finish.put(NB, 0);
        finish.put(OA, 0);
        finish.put(OB, 0);
        finish.put(P, 0);
        finish.put(Q, 0);
        finish.put(RA, 0);
        finish.put(RB, 0);
        finish.put(SA, 0);
        finish.put(SB, 0);
        finish.put(TA, 0);
        finish.put(TB, 0);

        db.insert(SUBSTRUCTURE, null, sub);
        db.insert(SUPERSTRUCTURE, null, supers);
        db.insert(ROOFING, null, roofing);
        db.insert(FINISHES, null, finish);

        db.close();
    }

    public String deleteProject(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String report;
        try {
            db.delete(PROJECT_LIST, PROJECT_LIST_NAME + "='" + name + "'", null);
            db.delete(SUBSTRUCTURE, PROJECT_LIST_NAME + "='" + name + "'", null);
            db.delete(SUPERSTRUCTURE, PROJECT_LIST_NAME + "='" + name + "'", null);
            db.delete(ROOFING, PROJECT_LIST_NAME + "='" + name + "'", null);
            db.delete(FINISHES, PROJECT_LIST_NAME + "='" + name + "'", null);
            report = "Database deleted";
        } catch (SQLException e) {
            report = "Error due to" + e;
        }

        db.close();

        return report;
    }

    public int[] getSubProjectDetails(String name, int id){
        int[] result = new int[6];
        String query = "SELECT * FROM " + SUBSTRUCTURE + " WHERE " + PROJECT_LIST_NAME + "='" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        switch (id){
            case 1:
                //for cast concrete
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(1));
                    result[1] = Integer.parseInt(cursor.getString(2));
                    result[2] = Integer.parseInt(cursor.getString(3));
                }
                break;
            case 2:
                //for clay blocks
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(4));
                    result[1] = Integer.parseInt(cursor.getString(5));
                    result[2] = Integer.parseInt(cursor.getString(6));
                }
                break;
            case 3:
                //for hard core
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(7));
                }
                break;
            case 4:
                //for cast con
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(8));
                    result[1] = Integer.parseInt(cursor.getString(9));
                    result[2] = Integer.parseInt(cursor.getString(10));
                }
                break;
        }

        cursor.close();
        return result;
    }

    public int[] getSupProjectDetails(String name, int id){
        int[] result = new int[6];
        String query = "SELECT * FROM " + SUPERSTRUCTURE + " WHERE " + PROJECT_LIST_NAME + "='" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        switch (id){
            case 1:
                //for clay blocks
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(1));
                    result[1] = Integer.parseInt(cursor.getString(2));
                    result[2] = Integer.parseInt(cursor.getString(3));
                }
                break;
            case 2:
                //for kirundu
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(4));
                    result[1] = Integer.parseInt(cursor.getString(5));
                }
                break;
            case 3:
                //for cast concrete
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(6));
                    result[1] = Integer.parseInt(cursor.getString(7));
                    result[2] = Integer.parseInt(cursor.getString(8));
                    result[3] = Integer.parseInt(cursor.getString(9));
                    result[4] = Integer.parseInt(cursor.getString(10));
                    result[5] = Integer.parseInt(cursor.getString(11));
                }
                break;
        }

        cursor.close();
        return result;
    }

    public int[] getRoofingProjectDetails(String name, int id){
        int[] result = new int[6];
        String query = "SELECT * FROM " + ROOFING + " WHERE " + PROJECT_LIST_NAME + "='" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        switch (id){
            case 1:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(1));
                }
                break;
            case 2:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(2));
                }
                break;
            case 3:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(3));
                }
                break;
            case 4:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(4));
                }
                break;
            case 5:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(5));
                }
                break;
        }

        cursor.close();
        return result;
    }

    public int[] getFinishesProjectDetails(String name, int id){
        int[] result = new int[6];
        String query = "SELECT * FROM " + FINISHES + " WHERE " + PROJECT_LIST_NAME + "='" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        switch (id){
            case 1:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(1));
                    result[0] = Integer.parseInt(cursor.getString(2));
                }
                break;
            case 2:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(3));
                    result[0] = Integer.parseInt(cursor.getString(4));
                }
                break;
            case 3:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(5));
                    result[0] = Integer.parseInt(cursor.getString(6));
                }
                break;
            case 4:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(7));
                }
                break;
            case 5:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(8));
                }
                break;
            case 6:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(9));
                    result[0] = Integer.parseInt(cursor.getString(10));
                }
                break;
            case 7:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(11));
                    result[0] = Integer.parseInt(cursor.getString(12));
                }
                break;
            case 8:
                if(cursor.moveToFirst()){
                    result[0] = Integer.parseInt(cursor.getString(13));
                    result[0] = Integer.parseInt(cursor.getString(14));
                }
                break;
        }

        cursor.close();
        return result;
    }

    public int[] getCardDetails(String name) {
        int[] result = new int[8];
        String sub = "SELECT * FROM " + TABLE_CARD_VIEW + " WHERE " + PROJECT_LIST_NAME + "='" + name + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sub, null);
        if (cursor.moveToFirst()) {

            result[0] = Integer.parseInt(cursor.getString(1));
            result[1] = Integer.parseInt(cursor.getString(2));
            result[2] = Integer.parseInt(cursor.getString(3));
            result[3] = Integer.parseInt(cursor.getString(4));
            result[4] = Integer.parseInt(cursor.getString(5));
            result[5] = Integer.parseInt(cursor.getString(6));
            result[6] = Integer.parseInt(cursor.getString(7));
            result[7] = Integer.parseInt(cursor.getString(8));
        }
        cursor.close();
        return result;
    }

    private void createCardViewTable(){
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CARD_VIEW + " (" +
                PROJECT_LIST_NAME + " TEXT NOT NULL," + SUB_COST + " INTEGER," + SUB_PRO + " INTEGER," +
                SUP_COST + " INTEGER," + SUP_PRO + " INTEGER," + ROOF_COST + " INTEGER," + ROOF_PRO + " INTEGER," +
                FINISH_COST + " INTEGER," + FINISH_PRO + " INTEGER);";

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(CREATE_TABLE);
    }

    private void insertCardViewTable(String name){
        ContentValues values = new ContentValues();
        values.put(PROJECT_LIST_NAME, name);
        values.put(SUB_COST, 0);
        values.put(SUB_PRO, 0);
        values.put(SUP_COST, 0);
        values.put(SUP_PRO, 0);
        values.put(ROOF_COST, 0);
        values.put(ROOF_PRO, 0);
        values.put(FINISH_COST, 0);
        values.put(FINISH_PRO, 0);

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_CARD_VIEW, null, values);
        db.close();
    }

    public void updateSubData(String name, int[] data, int item){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        switch(item){
            case 1:
                contentValues.put(AA, data[0]);
                contentValues.put(AB, data[1]);
                contentValues.put(AC, data[2]);
                db.update(SUBSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 2:
                contentValues.put(BA, data[0]);
                contentValues.put(BB, data[1]);
                contentValues.put(BC, data[2]);
                db.update(SUBSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 3:
                contentValues.put(CA, data[0]);
                db.update(SUBSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 4:
                contentValues.put(DA, data[0]);
                contentValues.put(DB, data[1]);
                contentValues.put(DC, data[2]);
                db.update(SUBSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
        }
    }

    public void updateSupData(String name, int[] data, int item){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        switch(item){
            case 1:
                contentValues.put(EA, data[0]);
                contentValues.put(EB, data[1]);
                contentValues.put(EC, data[2]);
                db.update(SUPERSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 2:
                contentValues.put(FA, data[0]);
                contentValues.put(FB, data[1]);
                db.update(SUPERSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 3:
                contentValues.put(GA, data[0]);
                contentValues.put(GB, data[1]);
                contentValues.put(GC, data[2]);
                contentValues.put(GD, data[3]);
                contentValues.put(GE, data[4]);
                contentValues.put(GF, data[5]);
                db.update(SUPERSTRUCTURE, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
        }
    }

    public void updateRoofData(String name, int[] data, int item){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        switch(item){
            case 1:
                contentValues.put(H, data[0]);
                db.update(ROOFING, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 2:
                contentValues.put(I, data[0]);
                db.update(ROOFING, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 3:
                contentValues.put(J, data[0]);
                db.update(ROOFING, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 4:
                contentValues.put(K, data[0]);
                db.update(ROOFING, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 5:
                contentValues.put(L, data[0]);
                db.update(ROOFING, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
        }
    }

    public void updateFinData(String name, int[] data, int item){
        ContentValues contentValues = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        switch(item){
            case 1:
                contentValues.put(MA, data[0]);
                contentValues.put(MB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 2:
                contentValues.put(NA, data[0]);
                contentValues.put(NB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 3:
                contentValues.put(OA, data[0]);
                contentValues.put(OB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 4:
                contentValues.put(P, data[0]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 5:
                contentValues.put(Q, data[0]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 6:
                contentValues.put(RA, data[0]);
                contentValues.put(RB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 7:
                contentValues.put(SA, data[0]);
                contentValues.put(SB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
            case 8:
                contentValues.put(TA, data[0]);
                contentValues.put(TB, data[1]);
                db.update(FINISHES, contentValues, PROJECT_LIST_NAME + "='" + name + "'", null);
                break;
        }
    }
}