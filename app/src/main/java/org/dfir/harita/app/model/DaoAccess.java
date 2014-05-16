package org.dfir.harita.app.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.dfir.harita.app.model.dao.DaoMaster;
import org.dfir.harita.app.model.dao.DaoSession;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.FirsatlarimDao;
import org.dfir.harita.app.model.dao.IsletmeDao;
import org.dfir.harita.app.model.dao.MusteriDao;
import org.dfir.harita.app.model.dao.Musteri_FirsatDao;
import org.dfir.harita.app.model.dao.Musteri_IsletmeDao;


/**
 * Created by ismail ARILIK on 16.05.2014.
 * implements singleton pattern
 */
public class DaoAccess
{
    //// declarations
    private static DaoAccess ormAccess;
    private static final String DB_NAME = "app_db";
    private SQLiteDatabase db;
    // DAO's
    private FirsatDao firsatDao;
    private FirsatlarimDao firsatlarimDao;
    private IsletmeDao isletmeDao;
    private MusteriDao musteriDao;
    private Musteri_FirsatDao musteriFirsatDao;
    private Musteri_IsletmeDao musteriIsletmeDao;


    /**
     * private constructor to prevent instantiating
     */
    private DaoAccess() {

    }


    /**
     *
     * @param appContext the APPLICATION context
     * @return the single instance of this class
     */
    public static DaoAccess getSingletonObject(Context appContext) {
        if (ormAccess == null) {
            ormAccess = new DaoAccess();
            ormAccess.initialize(appContext);
        }

        return ormAccess;
    }

    private void initialize(Context appContext) {
        SQLiteOpenHelper helper = new DaoMaster.DevOpenHelper(appContext, DB_NAME, null);

        db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        firsatDao = daoSession.getFirsatDao();
        firsatlarimDao = daoSession.getFirsatlarimDao();
        isletmeDao = daoSession.getIsletmeDao();
        musteriDao = daoSession.getMusteriDao();
        musteriFirsatDao = daoSession.getMusteri_FirsatDao();
        musteriIsletmeDao = daoSession.getMusteri_IsletmeDao();
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    // GETTERS AND SETTERS

    public SQLiteDatabase getDb()
    {
        return db;
    }

    public FirsatDao getFirsatDao() {
        return firsatDao;
    }

    public FirsatlarimDao getFirsatlarimDao() {
        return firsatlarimDao;
    }

    public IsletmeDao getIsletmeDao() {
        return isletmeDao;
    }

    public MusteriDao getMusteriDao() {
        return musteriDao;
    }

    public Musteri_FirsatDao getMusteriFirsatDao() {
        return musteriFirsatDao;
    }

    public Musteri_IsletmeDao getMusteriIsletmeDao() {
        return musteriIsletmeDao;
    }

    public static void nullify() {
        DaoAccess.ormAccess = null;
    }
}
