package edu.udistrital.plantae.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Hoja;

import java.util.ArrayList;
import java.util.List;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table HOJA.
*/
public class HojaDao extends AbstractDao<Hoja, Long> {

    public static final String TABLENAME = "HOJA";

    /**
     * Properties of entity Hoja.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property CoberturaDelPeciolo = new Property(1, String.class, "coberturaDelPeciolo", false, "COBERTURA_DEL_PECIOLO");
        public final static Property DispocicionDeLasPinnas = new Property(2, String.class, "dispocicionDeLasPinnas", false, "DISPOCICION_DE_LAS_PINNAS");
        public final static Property DisposicionDeLasHojas = new Property(3, String.class, "disposicionDeLasHojas", false, "DISPOSICION_DE_LAS_HOJAS");
        public final static Property FormaDelPeciolo = new Property(4, String.class, "formaDelPeciolo", false, "FORMA_DEL_PECIOLO");
        public final static Property LonguitudDelRaquis = new Property(5, String.class, "longuitudDelRaquis", false, "LONGUITUD_DEL_RAQUIS");
        public final static Property NaturalezaDeLaVaina = new Property(6, String.class, "naturalezaDeLaVaina", false, "NATURALEZA_DE_LA_VAINA");
        public final static Property NaturalezaDelLimbo = new Property(7, String.class, "naturalezaDelLimbo", false, "NATURALEZA_DEL_LIMBO");
        public final static Property NumeroDePinnas = new Property(8, String.class, "numeroDePinnas", false, "NUMERO_DE_PINNAS");
        public final static Property NumeroHojas = new Property(9, String.class, "numeroHojas", false, "NUMERO_HOJAS");
        public final static Property TamañoDeLaVaina = new Property(10, String.class, "tamañoDeLaVaina", false, "TAMAÑO_DE_LA_VAINA");
        public final static Property TamañoDelPeciolo = new Property(11, String.class, "tamañoDelPeciolo", false, "TAMAÑO_DEL_PECIOLO");
        public final static Property Descripcion = new Property(12, String.class, "descripcion", false, "DESCRIPCION");
        public final static Property ColorDeLaVainaID = new Property(13, Long.class, "colorDeLaVainaID", false, "COLOR_DE_LA_VAINA_ID");
        public final static Property ColorDelPecioloID = new Property(14, Long.class, "colorDelPecioloID", false, "COLOR_DEL_PECIOLO_ID");
    };

    private DaoSession daoSession;


    public HojaDao(DaoConfig config) {
        super(config);
    }
    
    public HojaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'HOJA' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'COBERTURA_DEL_PECIOLO' TEXT," + // 1: coberturaDelPeciolo
                "'DISPOCICION_DE_LAS_PINNAS' TEXT," + // 2: dispocicionDeLasPinnas
                "'DISPOSICION_DE_LAS_HOJAS' TEXT," + // 3: disposicionDeLasHojas
                "'FORMA_DEL_PECIOLO' TEXT," + // 4: formaDelPeciolo
                "'LONGUITUD_DEL_RAQUIS' TEXT," + // 5: longuitudDelRaquis
                "'NATURALEZA_DE_LA_VAINA' TEXT," + // 6: naturalezaDeLaVaina
                "'NATURALEZA_DEL_LIMBO' TEXT," + // 7: naturalezaDelLimbo
                "'NUMERO_DE_PINNAS' TEXT," + // 8: numeroDePinnas
                "'NUMERO_HOJAS' TEXT," + // 9: numeroHojas
                "'TAMAÑO_DE_LA_VAINA' TEXT," + // 10: tamañoDeLaVaina
                "'TAMAÑO_DEL_PECIOLO' TEXT," + // 11: tamañoDelPeciolo
                "'DESCRIPCION' TEXT," + // 12: descripcion
                "'COLOR_DE_LA_VAINA_ID' INTEGER," + // 13: colorDeLaVainaID
                "'COLOR_DEL_PECIOLO_ID' INTEGER);"); // 14: colorDelPecioloID
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_HOJA_COLOR_DE_LA_VAINA_ID ON HOJA" +
                " (COLOR_DE_LA_VAINA_ID);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_HOJA_COLOR_DEL_PECIOLO_ID ON HOJA" +
                " (COLOR_DEL_PECIOLO_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'HOJA'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Hoja entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String coberturaDelPeciolo = entity.getCoberturaDelPeciolo();
        if (coberturaDelPeciolo != null) {
            stmt.bindString(2, coberturaDelPeciolo);
        }
 
        String dispocicionDeLasPinnas = entity.getDispocicionDeLasPinnas();
        if (dispocicionDeLasPinnas != null) {
            stmt.bindString(3, dispocicionDeLasPinnas);
        }
 
        String disposicionDeLasHojas = entity.getDisposicionDeLasHojas();
        if (disposicionDeLasHojas != null) {
            stmt.bindString(4, disposicionDeLasHojas);
        }
 
        String formaDelPeciolo = entity.getFormaDelPeciolo();
        if (formaDelPeciolo != null) {
            stmt.bindString(5, formaDelPeciolo);
        }
 
        String longuitudDelRaquis = entity.getLonguitudDelRaquis();
        if (longuitudDelRaquis != null) {
            stmt.bindString(6, longuitudDelRaquis);
        }
 
        String naturalezaDeLaVaina = entity.getNaturalezaDeLaVaina();
        if (naturalezaDeLaVaina != null) {
            stmt.bindString(7, naturalezaDeLaVaina);
        }
 
        String naturalezaDelLimbo = entity.getNaturalezaDelLimbo();
        if (naturalezaDelLimbo != null) {
            stmt.bindString(8, naturalezaDelLimbo);
        }
 
        String numeroDePinnas = entity.getNumeroDePinnas();
        if (numeroDePinnas != null) {
            stmt.bindString(9, numeroDePinnas);
        }
 
        String numeroHojas = entity.getNumeroHojas();
        if (numeroHojas != null) {
            stmt.bindString(10, numeroHojas);
        }
 
        String tamañoDeLaVaina = entity.getTamañoDeLaVaina();
        if (tamañoDeLaVaina != null) {
            stmt.bindString(11, tamañoDeLaVaina);
        }
 
        String tamañoDelPeciolo = entity.getTamañoDelPeciolo();
        if (tamañoDelPeciolo != null) {
            stmt.bindString(12, tamañoDelPeciolo);
        }
 
        String descripcion = entity.getDescripcion();
        if (descripcion != null) {
            stmt.bindString(13, descripcion);
        }
 
        Long colorDeLaVainaID = entity.getColorDeLaVainaID();
        if (colorDeLaVainaID != null) {
            stmt.bindLong(14, colorDeLaVainaID);
        }
 
        Long colorDelPecioloID = entity.getColorDelPecioloID();
        if (colorDelPecioloID != null) {
            stmt.bindLong(15, colorDelPecioloID);
        }
    }

    @Override
    protected void attachEntity(Hoja entity) {
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
    public Hoja readEntity(Cursor cursor, int offset) {
        Hoja entity = new Hoja();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Hoja entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCoberturaDelPeciolo(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDispocicionDeLasPinnas(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setDisposicionDeLasHojas(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setFormaDelPeciolo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setLonguitudDelRaquis(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setNaturalezaDeLaVaina(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNaturalezaDelLimbo(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNumeroDePinnas(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setNumeroHojas(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setTamañoDeLaVaina(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTamañoDelPeciolo(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setDescripcion(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setColorDeLaVainaID(cursor.isNull(offset + 13) ? null : cursor.getLong(offset + 13));
        entity.setColorDelPecioloID(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Hoja entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Hoja entity) {
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
            builder.append(" FROM HOJA T");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T0 ON T.'COLOR_DE_LA_VAINA_ID'=T0.'_id'");
            builder.append(" LEFT JOIN COLOR_ESPECIMEN T1 ON T.'COLOR_DEL_PECIOLO_ID'=T1.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Hoja loadCurrentDeep(Cursor cursor, boolean lock) {
        Hoja entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        ColorEspecimen colorDeLaVaina = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDeLaVaina(colorDeLaVaina);
        offset += daoSession.getColorEspecimenDao().getAllColumns().length;

        ColorEspecimen colorDelPeciolo = loadCurrentOther(daoSession.getColorEspecimenDao(), cursor, offset);
        entity.setColorDelPeciolo(colorDelPeciolo);

        return entity;    
    }

    public Hoja loadDeep(Long key) {
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
    public List<Hoja> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Hoja> list = new ArrayList<Hoja>(count);
        
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
    
    protected List<Hoja> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Hoja> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
