package edu.udistrital.plantae.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.greenrobot.dao.DaoException;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;
import edu.udistrital.plantae.ui.SpecimenListItem;

/**
 * Created by hghar on 1/25/15.
 */
public class FTSEspecimenDao {

    private static final String  tableName = "FTS_ESPECIMEN";

    private String[] columns;
    private SQLiteDatabase db;
    private DaoSession daoSession;

    public static class Properties {
        public final static Property docId = new Property(0, Long.class, "id", true, "docid");
        public final static Property NumeroDeColeccion = new Property(1, String.class, "numeroDeColeccion", false, "NUMERO_DE_COLECCION");
        public final static Property Abundancia = new Property(2, String.class, "abundancia", false, "ABUNDANCIA");
        public final static Property DescripcionEspecimen = new Property(3, String.class, "descripcionEspecimen", false, "DESCRIPCION_ESPECIMEN");
        public final static Property FechaInicial = new Property(4, java.util.Date.class, "fechaInicial", false, "FECHA_INICIAL");
        public final static Property FechaFinal = new Property(5, java.util.Date.class, "fechaFinal", false, "FECHA_FINAL");
        public final static Property MetodoColeccion = new Property(6, String.class, "metodoColeccion", false, "METODO_COLECCION");
        public final static Property EstacionDelAño = new Property(7, String.class, "estacionDelAño", false, "ESTACION_DEL_AÑO");
        public final static Property Colores = new Property(8, String.class, "colores", false, "COLORES");
        public final static Property Localidad = new Property(9, String.class, "localidad", false, "LOCALIDAD");
        public final static Property Determinacion = new Property(10, String.class, "determinacion", false, "DETERMINACION");
    };

    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE VIRTUAL TABLE " + constraint + tableName + //
                " USING fts4(" + //
                "NUMERO_DE_COLECCION," + // 1: numeroDeColeccion
                "ABUNDANCIA," + // 2: abundancia
                "DESCRIPCION_ESPECIMEN," + // 3: descripcionEspecimen
                "FECHA_INICIAL," + // 6: fechaInicial
                "FECHA_FINAL," + // 7: fechaFinal
                "METODO_COLECCION," + // 8: metodoColeccion
                "ESTACION_DEL_AÑO," + // 9: estacionDelAño
                "COLORES," + // 3: colores
                "LOCALIDAD," + // 4: localidad
                "DETERMINACION);"); // 6: Determinacion actual
        db.execSQL("CREATE TRIGGER ESPECIMEN_BU BEFORE UPDATE ON ESPECIMEN BEGIN" +
                " DELETE FROM FTS_ESPECIMEN WHERE docid=old.rowid;" +
                "END;");
        db.execSQL("CREATE TRIGGER ESPECIMEN_BD BEFORE DELETE ON ESPECIMEN BEGIN" +
                " DELETE FROM FTS_ESPECIMEN WHERE docid=old.rowid;" +
                "END;");
        db.execSQL("CREATE TRIGGER ESPECIMEN_AU AFTER UPDATE ON ESPECIMEN BEGIN" +
                " INSERT INTO FTS_ESPECIMEN(docid, NUMERO_DE_COLECCION, ABUNDANCIA, DESCRIPCION_ESPECIMEN, FECHA_INICIAL, FECHA_FINAL, METODO_COLECCION, ESTACION_DEL_AÑO, LOCALIDAD) VALUES(new.rowid, new.NUMERO_DE_COLECCION, new.ABUNDANCIA, new.DESCRIPCION_ESPECIMEN, new.FECHA_INICIAL, new.FECHA_FINAL, new.METODO_COLECCION, new.ESTACION_DEL_AÑO, (SELECT (COALESCE(`NOMBRE`,'') || COALESCE(\" \"||`DATUM`,'') || COALESCE(\" \"||`DESCRIPCION`,'') || COALESCE(\" \"||REGION.`NOMBRE_COMPLETO`, '')) AS Localidad FROM LOCALIDAD JOIN REGION ON LOCALIDAD.REGION_ID = REGION._id WHERE new.LOCALIDAD_ID = LOCALIDAD._id));" +
                "END;");
        db.execSQL("CREATE TRIGGER ESPECIMEN_AI AFTER INSERT ON ESPECIMEN BEGIN" +
                " INSERT INTO FTS_ESPECIMEN(docid, NUMERO_DE_COLECCION, ABUNDANCIA, DESCRIPCION_ESPECIMEN, FECHA_INICIAL, FECHA_FINAL, METODO_COLECCION, ESTACION_DEL_AÑO, LOCALIDAD) VALUES(new.rowid, new.NUMERO_DE_COLECCION, new.ABUNDANCIA, new.DESCRIPCION_ESPECIMEN, new.FECHA_INICIAL, new.FECHA_FINAL, new.METODO_COLECCION, new.ESTACION_DEL_AÑO, (SELECT (COALESCE(`NOMBRE`,'') || COALESCE(\" \"||`DATUM`,'') || COALESCE(\" \"||`DESCRIPCION`,'') || COALESCE(\" \"||REGION.`NOMBRE_COMPLETO`, '')) AS Localidad FROM LOCALIDAD JOIN REGION ON LOCALIDAD.REGION_ID = REGION._id WHERE new.LOCALIDAD_ID = LOCALIDAD._id));" +
                "END;");
    }

    public FTSEspecimenDao(DaoSession daoSession) {
        this.db = daoSession.getDatabase();
        this.daoSession = daoSession;
        Property[] properties = new Property[0];
        try {
            properties = reflectProperties(this.getClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        columns = new String[properties.length];
        for (int i = 0; i < properties.length; i++) {
            columns[i] = properties[i].columnName;
        }
    }

    private static Property[] reflectProperties(Class daoClass)
            throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        Class<?> propertiesClass = Class.forName(daoClass.getName() + "$Properties");
        Field[] fields = propertiesClass.getDeclaredFields();

        ArrayList<Property> propertyList = new ArrayList<>();
        final int modifierMask = Modifier.STATIC | Modifier.PUBLIC;
        for (Field field : fields) {
            // There might be other fields introduced by some tools, just ignore them (see issue #28)
            if ((field.getModifiers() & modifierMask) == modifierMask) {
                Object fieldValue = field.get(null);
                if (fieldValue instanceof Property) {
                    propertyList.add((Property) fieldValue);
                }
            }
        }

        Property[] properties = new Property[propertyList.size()];
        for (Property property : propertyList) {
            if (properties[property.ordinal] != null) {
                throw new DaoException("Duplicate property ordinals");
            }
            properties[property.ordinal] = property;
        }
        return properties;
    }

    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FTS_ESPECIMEN'";
        db.execSQL(sql);
    }

    public void update(String colores, String determinacion, Long especimenId) {
        String[] updateColumns = {Properties.Colores.columnName, Properties.Determinacion.columnName};
        String[] whereColumns = {Properties.docId.columnName};
        SQLiteStatement statement = db.compileStatement(SqlUtils.createSqlUpdate(tableName, updateColumns, whereColumns));
        if (db.isDbLockedByCurrentThread()) {
            synchronized (statement) {
                updateInsideSynchronized(colores, determinacion, especimenId, statement);
            }
        }else{
            db.beginTransaction();
            try {
                synchronized (statement){
                    updateInsideSynchronized(colores, determinacion, especimenId, statement);
                }
                db.setTransactionSuccessful();
            }finally {
                db.endTransaction();
            }
        }
    }

    protected void updateInsideSynchronized(String colores, String determinacion, Long especimenId, SQLiteStatement stmt) {
        stmt.clearBindings();
        stmt.bindString(1, colores);
        stmt.bindString(2, determinacion);
        stmt.bindLong(3, especimenId);
        stmt.execute();
    }

    public List<SpecimenListItem> query(String query) {
        String[] args = new String[1];
        args[0] = query;
        Cursor cursor = db.rawQuery(createSqlMatchSelect(tableName, "T"), args);
        try{
            int count = cursor.getCount();
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            }else {
                List<SpecimenListItem> result = new ArrayList<>(count);
                do
                    result.add(readEntity(cursor));
                while (cursor.moveToNext());
                return result;
            }
        }finally {
            cursor.close();
        }
    }

    private SpecimenListItem readEntity(Cursor cursor) {
        Long id = cursor.getLong(0);
        String numeroDeColeccion = cursor.getString(1);
        String abundancia  = cursor.getString(2);
        String descripcionEspecimen  = cursor.getString(3);
        Date fechaInicial  = new Date(cursor.getLong(4));
        Date fechaFinal  = new Date(cursor.getLong(5));
        String metodoColeccion  = cursor.getString(6);
        String estacionDelAño  = cursor.getString(7);
        String colores  = cursor.getString(8);
        String localidad  = cursor.getString(9);
        localidad = localidad == null ? "" : localidad;
        String determinacion  = cursor.getString(10);
        return new SpecimenListItem(id, numeroDeColeccion, determinacion, localidad, descripcionEspecimen, R.drawable.plantae, !localidad.isEmpty());
    }

    public String createSqlMatchSelect(String tableName, String tableAlias) {
        StringBuilder builder = new StringBuilder("SELECT ");
        if (tableAlias == null || tableAlias.length() < 0) {
            throw new DaoException("Table alias required");
        }
        SqlUtils.appendColumns(builder, tableAlias, columns).append(" FROM ");
        builder.append(tableName).append(' ').append(tableAlias).append(' ');
        builder.append("WHERE ").append(tableName).append(' ');
        builder.append("MATCH ");
        SqlUtils.appendPlaceholders(builder, 1);
        return builder.toString();
    }
}
