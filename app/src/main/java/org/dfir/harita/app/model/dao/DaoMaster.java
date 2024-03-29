package org.dfir.harita.app.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import de.greenrobot.dao.AbstractDaoMaster;
import de.greenrobot.dao.identityscope.IdentityScopeType;

import org.dfir.harita.app.model.dao.IsletmeDao;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.MusteriDao;
import org.dfir.harita.app.model.dao.Musteri_IsletmeDao;
import org.dfir.harita.app.model.dao.Musteri_FirsatDao;
import org.dfir.harita.app.model.dao.FirsatlarimDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * Master of DAO (schema version 1): knows all DAOs.
*/
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 1;

    /** Creates underlying database table using DAOs. */
    public static void createAllTables(SQLiteDatabase db, boolean ifNotExists) {
        IsletmeDao.createTable(db, ifNotExists);
        FirsatDao.createTable(db, ifNotExists);
        MusteriDao.createTable(db, ifNotExists);
        Musteri_IsletmeDao.createTable(db, ifNotExists);
        Musteri_FirsatDao.createTable(db, ifNotExists);
        FirsatlarimDao.createTable(db, ifNotExists);
    }
    
    /** Drops underlying database table using DAOs. */
    public static void dropAllTables(SQLiteDatabase db, boolean ifExists) {
        IsletmeDao.dropTable(db, ifExists);
        FirsatDao.dropTable(db, ifExists);
        MusteriDao.dropTable(db, ifExists);
        Musteri_IsletmeDao.dropTable(db, ifExists);
        Musteri_FirsatDao.dropTable(db, ifExists);
        FirsatlarimDao.dropTable(db, ifExists);
    }
    
    public static abstract class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory, SCHEMA_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i("greenDAO", "Creating tables for schema version " + SCHEMA_VERSION);
            createAllTables(db, false);
        }
    }
    
    /** WARNING: Drops all table on Upgrade! Use only during development. */
    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String name, CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            dropAllTables(db, true);
            onCreate(db);
        }
    }

    public DaoMaster(SQLiteDatabase db) {
        super(db, SCHEMA_VERSION);
        registerDaoClass(IsletmeDao.class);
        registerDaoClass(FirsatDao.class);
        registerDaoClass(MusteriDao.class);
        registerDaoClass(Musteri_IsletmeDao.class);
        registerDaoClass(Musteri_FirsatDao.class);
        registerDaoClass(FirsatlarimDao.class);
    }
    
    public DaoSession newSession() {
        return new DaoSession(db, IdentityScopeType.Session, daoConfigMap);
    }
    
    public DaoSession newSession(IdentityScopeType type) {
        return new DaoSession(db, type, daoConfigMap);
    }
    
}
