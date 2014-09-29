package edu.udistrital.plantae.persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;


public class EspecimenSencilloDao {
	
	public EspecimenSencilloDao() {
	}
	
	protected void bindValues(SQLiteStatement stmt, Especimen entity) {
        String numeroDeColeccion = entity.getNumeroDeColeccion();
        if (numeroDeColeccion != null) {
            stmt.bindString(2, numeroDeColeccion);
        }

        String abundancia = entity.getAbundancia();
        if (abundancia != null) {
            stmt.bindString(3, abundancia);
        }

        String fenologia = entity.getFenologia();
        if (fenologia != null) {
            stmt.bindString(4, fenologia);
        }

        String descripcionEspecimen = entity.getDescripcionEspecimen();
        if (descripcionEspecimen != null) {
            stmt.bindString(5, descripcionEspecimen);
        }

        Long alturaDeLaPlanta = entity.getAlturaDeLaPlanta();
        if (alturaDeLaPlanta != null) {
            stmt.bindLong(6, alturaDeLaPlanta);
        }

        Long dap = entity.getDap();
        if (dap != null) {
            stmt.bindLong(7, dap);
        }

        Long habitoID = entity.getHabitoID();
        if (habitoID != null) {
            stmt.bindLong(10, habitoID);
        }

        Long habitatID = entity.getHabitatID();
        if (habitatID != null) {
            stmt.bindLong(11, habitatID);
        }

        Long localidadID = entity.getLocalidadID();
        if (localidadID != null) {
            stmt.bindLong(12, localidadID);
        }

        Long florID = entity.getFlorID();
        if (florID != null) {
            stmt.bindLong(17, florID);
        }

        stmt.bindString(19, "ES");
    }
	
	public Especimen readEntity(Cursor cursor, int offset) {
        Especimen entity = new Especimen();
        readEntity(cursor, entity, offset);
        return entity;
    }

    public void readEntity(Cursor cursor, Especimen entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNumeroDeColeccion(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAbundancia(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFenologia(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDescripcionEspecimen(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAlturaDeLaPlanta(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setDap(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setHabitoID(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setHabitatID(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setLocalidadID(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
        entity.setFlorID(cursor.isNull(offset + 16) ? null : cursor.getLong(offset + 16));
     }

}
