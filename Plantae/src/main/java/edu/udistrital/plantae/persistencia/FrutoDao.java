package edu.udistrital.plantae.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fruto;

import java.util.ArrayList;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table FRUTO.
*/
public class FrutoDao extends AbstractDao<Fruto, Long> {

    public static final String TABLENAME = "FRUTO";

    /**
     * Properties of entity Fruto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ConsistenciaDelPericarpio = new Property(1, String.class, "consistenciaDelPericarpio", false, "CONSISTENCIA_DEL_PERICARPIO");
        public final static Property Descripcion = new Property(2, String.class, "descripcion", false, "DESCRIPCION");
        public final static Property ColorDelExocarpioID = new Property(3, Long.class, "colorDelExocarpioID", false, "COLOR_DEL_EXOCARPIO_ID");
        public final static Property ColorDelMesocarpioID = new Property(4, Long.class, "colorDelMesocarpioID", false, "COLOR_DEL_MESOCARPIO_ID");
        public final static Property ColorDelExocarpioInmaduroID = new Property(5, Long.class, "colorDelExocarpioInmaduroID", false, "COLOR_DEL_EXOCARPIO_INMADURO_ID");
        public final static Property ColorDelMesocarpioInmaduroID = new Property(6, Long.class, "colorDelMesocarpioInmaduroID", false, "COLOR_DEL_MESOCARPIO_INMADURO_ID");
    };

    private DaoSession daoSession;


    public FrutoDao(DaoConfig config) {
        super(config);
    }
    
    public FrutoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'FRUTO' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'CONSISTENCIA_DEL_PERICARPIO' TEXT," + // 1: consistenciaDelPericarpio
                "'DESCRIPCION' TEXT," + // 2: descripcion
                "'COLOR_DEL_EXOCARPIO_ID' INTEGER," + // 3: colorDelExocarpioID
                "'COLOR_DEL_MESOCARPIO_ID' INTEGER," + // 4: colorDelMesocarpioID
                "'COLOR_DEL_EXOCARPIO_INMADURO_ID' INTEGER," + // 5: colorDelExocarpioInmaduroID
                "'COLOR_DEL_MESOCARPIO_INMADURO_ID' INTEGER);"); // 6: colorDelMesocarpioInmaduroID
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_FRUTO_COLOR_DEL_EXOCARPIO_ID ON FRUTO" +
                " (COLOR_DEL_EXOCARPIO_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_FRUTO_COLOR_DEL_MESOCARPIO_ID ON FRUTO" +
                " (COLOR_DEL_MESOCARPIO_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_FRUTO_COLOR_DEL_EXOCARPIO_INMADURO_ID ON FRUTO" +
                " (COLOR_DEL_EXOCARPIO_INMADURO_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_FRUTO_COLOR_DEL_MESOCARPIO_INMADURO_ID ON FRUTO" +
                " (COLOR_DEL_MESOCARPIO_INMADURO_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FRUTO'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Fruto entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String consistenciaDelPericarpio = entity.getConsistenciaDelPericarpio();
        if (consistenciaDelPericarpio != null) {
            stmt.bindString(2, consistenciaDelPericarpio);
        }
 
        String descripcion = entity.getDescripcion();
        if (descripcion != null) {
            stmt.bindString(3, descripcion);
        }

        Long colorDelExocarpioID = entity.getColorDelExocarpioID();
        if (colorDelExocarpioID != null) {
            stmt.bindLong(4, colorDelExocarpioID);
        }

        Long colorDelMesocarpioID = entity.getColorDelMesocarpioID();
        if (colorDelMesocarpioID != null) {
            stmt.bindLong(5, colorDelMesocarpioID);
        }

        Long colorDelExocarpioInmaduroID = entity.getColorDelExocarpioInmaduroID();
        if (colorDelExocarpioInmaduroID != null) {
            stmt.bindLong(6, colorDelExocarpioInmaduroID);
        }

        Long colorDelMesocarpioInmaduroID = entity.getColorDelMesocarpioInmaduroID();
        if (colorDelMesocarpioInmaduroID != null) {
            stmt.bindLong(7, colorDelMesocarpioInmaduroID);
        }
    }

    @Override
    protected void attachEntity(Fruto entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Fruto readEntity(Cursor cursor, int offset) {
        Fruto entity = new Fruto();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Fruto entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setConsistenciaDelPericarpio(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDescripcion(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setColorDelExocarpioID(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setColorDelMesocarpioID(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setColorDelExocarpioInmaduroID(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setColorDelMesocarpioInmaduroID(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Fruto entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Fruto entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getColorEspecimenDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getColorEspecimenDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T2", daoSession.getColorEspecimenDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T3", daoSession.getColorEspecimenDao().getAllColumns());
            builder.append(" FROM FRUTO T");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T0 ON T.'COLOR_DEL_MESOCARPIO_ID'=T0.'_id'");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T1 ON T.'COLOR_DEL_EXOCARPIO_ID'=T1.'_id'");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T2 ON T.'COLOR_DEL_MESOCARPIO_INMADURO_ID'=T2.'_id'");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T3 ON T.'COLOR_DEL_EXOCARPIO_INMADURO_ID'=T3.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Fruto loadCurrentDeep(Cursor cursor, boolean lock) {
        Fruto entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ColorEspecimen colorDelMesocarpio = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDelMesocarpio(colorDelMesocarpio);
        offset += daoSession.getColorEspecimenDao().getAllColumns().length;

        ColorEspecimen colorDelExocarpio = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDelExocarpio(colorDelExocarpio);
        offset += daoSession.getColorEspecimenDao().getAllColumns().length;

        ColorEspecimen colorDelMesocarpioInmaduro = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDelMesocarpioInmaduro(colorDelMesocarpioInmaduro);
        offset += daoSession.getColorEspecimenDao().getAllColumns().length;

        ColorEspecimen colorDelExocarpioInmaduro = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDelExocarpioInmaduro(colorDelExocarpioInmaduro);

        return entity;    
    }

    public Fruto loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Fruto> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Fruto> list = new ArrayList<Fruto>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Fruto> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Fruto> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
