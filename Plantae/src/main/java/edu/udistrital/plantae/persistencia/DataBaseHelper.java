package edu.udistrital.plantae.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import edu.udistrital.plantae.persistencia.DaoMaster.DevOpenHelper;

public class DataBaseHelper {
	
	private static DataBaseHelper helper;
	private SQLiteDatabase dataBase;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private final String DATABASE_NAME = "plantae.db";
	
	private DataBaseHelper(Context context) {
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, DATABASE_NAME, null);
		dataBase = helper.getWritableDatabase();
        daoMaster = new DaoMaster(dataBase);
		daoSession = daoMaster.newSession();
    }
	
	public static DataBaseHelper getDataBaseHelper(Context context) {
		if (helper == null){
			return helper = new DataBaseHelper(context);
		}
		return helper;
	}

	public SQLiteDatabase getDataBase() {
		return dataBase;
	}

	public void setDataBase(SQLiteDatabase dataBase) {
		this.dataBase = dataBase;
	}

	public DaoMaster getDaoMaster() {
		return daoMaster;
	}

	public void setDaoMaster(DaoMaster daoMaster) {
		this.daoMaster = daoMaster;
	}

	public DaoSession getDaoSession() {
		return daoSession;
	}

	public void setDaoSession(DaoSession daoSession) {
		this.daoSession = daoSession;
	}

}
