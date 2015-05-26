package edu.udistrital.plantae.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;
import edu.udistrital.plantae.logicadominio.taxonomia.NombreComun;
import edu.udistrital.plantae.logicadominio.taxonomia.Taxon;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table NOMBRE_COMUN.
*/
public class NombreComunDao extends AbstractDao<NombreComun, Long> {

    public static final String TABLENAME = "NOMBRE_COMUN";

    /**
     * Properties of entity NombreComun.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Idioma = new Property(1, String.class, "idioma", false, "IDIOMA");
        public final static Property Nombre = new Property(2, String.class, "nombre", false, "NOMBRE");
        public final static Property TaxonID = new Property(3, long.class, "taxonID", false, "TAXON_ID");
    };

    private DaoSession daoSession;

    private Query<NombreComun> taxon_NombresComunesQuery;

    public NombreComunDao(DaoConfig config) {
        super(config);
    }
    
    public NombreComunDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'NOMBRE_COMUN' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'IDIOMA' TEXT," + // 1: idioma
                "'NOMBRE' TEXT," + // 2: nombre
                "'TAXON_ID' INTEGER NOT NULL );"); // 3: taxonID
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_NOMBRE_COMUN_NOMBRE ON NOMBRE_COMUN" +
                " (NOMBRE);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_NOMBRE_COMUN_TAXON_ID ON NOMBRE_COMUN" +
                " (TAXON_ID);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'NOMBRE_COMUN'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NombreComun entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String idioma = entity.getIdioma();
        if (idioma != null) {
            stmt.bindString(2, idioma);
        }
 
        String nombre = entity.getNombre();
        if (nombre != null) {
            stmt.bindString(3, nombre);
        }
        stmt.bindLong(4, entity.getTaxonID());
    }

    @Override
    protected void attachEntity(NombreComun entity) {
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
    public NombreComun readEntity(Cursor cursor, int offset) {
        NombreComun entity = new NombreComun();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NombreComun entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdioma(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNombre(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setTaxonID(cursor.getLong(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NombreComun entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NombreComun entity) {
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
    
    /** Internal query to resolve the "nombresComunes" to-many relationship of Taxon. */
    public List<NombreComun> _queryTaxon_NombresComunes(long taxonID) {
        synchronized (this) {
            if (taxon_NombresComunesQuery == null) {
                QueryBuilder<NombreComun> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.TaxonID.eq(null));
                taxon_NombresComunesQuery = queryBuilder.build();
            }
        }
        Query<NombreComun> query = taxon_NombresComunesQuery.forCurrentThread();
        query.setParameter(0, taxonID);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getTaxonDao().getAllColumns());
            builder.append(" FROM NOMBRE_COMUN T");
            builder.append(" LEFT JOIN TAXON T0 ON T.'TAXON_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected NombreComun loadCurrentDeep(Cursor cursor, boolean lock) {
        NombreComun entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Taxon taxon = loadCurrentOther(daoSession.getTaxonDao(), cursor, offset);
         if(taxon != null) {
            entity.setTaxon(taxon);
        }

        return entity;    
    }

    public NombreComun loadDeep(Long key) {
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
    public List<NombreComun> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<NombreComun> list = new ArrayList<NombreComun>(count);
        
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
    
    protected List<NombreComun> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<NombreComun> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}