package com.example.shopping_ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 作者：Created by 权.
 * 时间：2018/8/19.
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager(){

    }

    private DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder{
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance(){
        return Holder.INSTANCE;
    }


    private void initDao(Context context){
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context,"fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }


    public final UserProfileDao getmDao(){
        return mDao;
    }




}
