package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;
import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Gerson Sosa on 8/4/14.
 */
public class EspecimenDTO implements Parcelable {

    private Long id;
    private String numeroDeColeccion;
    private long alturaDeLaPlanta;
    private long dap;
    private String abundancia;
    private Fenologia fenologia;
    private String descripcionEspecimen;
    private Habito habito;
    private Habitat habitat;
    //private Localidad localidad;
    private Long localidadId;
    private String localidadNombre;
    private double latitud;
    private double longitud;
    private String datum;
    private double altitudMinima;
    private double altitudMaxima;
    private String localidadDescripcion;
    private String marcaDispositivo;
    private RegionDTO region;
    private Date fechaInicial;
    private Date fechaFinal;
    private String metodoColeccion;
    private String estacionDelAño;
    private long viajeID;
    private long colectorPrincipalID;
    private Flor flor;
    private Inflorescencia inflorescencia;
    private Hoja hoja;
    private Fruto fruto;
    private Tallo tallo;
    private Raiz raiz;
    private List<ColectorSecundarioDTO> colectoresSecundarios;
    private List<MuestraAsociada> muestrasAsociadas;
    private List<Fotografia> fotografias;
    //private IdentidadTaxonomica identidadTaxonomica;
    private Date fechaIdentificacion;
    private String tipo;
    private Persona determinador;
    private TaxonDTO taxon;
    private long usuarioId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeColeccion() {
        return numeroDeColeccion;
    }

    public void setNumeroDeColeccion(String numeroDeColeccion) {
        this.numeroDeColeccion = numeroDeColeccion;
    }

    public long getAlturaDeLaPlanta() {
        return alturaDeLaPlanta;
    }

    public void setAlturaDeLaPlanta(long alturaDeLaPlanta) {
        this.alturaDeLaPlanta = alturaDeLaPlanta;
    }

    public long getDap() {
        return dap;
    }

    public void setDap(long dap) {
        this.dap = dap;
    }

    public String getAbundancia() {
        return abundancia;
    }

    public void setAbundancia(String abundancia) {
        this.abundancia = abundancia;
    }

    public Fenologia getFenologia() {
        return fenologia;
    }

    public void setFenologia(Fenologia fenologia) {
        this.fenologia = fenologia;
    }

    public String getDescripcionEspecimen() {
        return descripcionEspecimen;
    }

    public void setDescripcionEspecimen(String descripcionEspecimen) {
        this.descripcionEspecimen = descripcionEspecimen;
    }

    public Habito getHabito() {
        return habito;
    }

    public void setHabito(Habito habito) {
        this.habito = habito;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    public Long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(Long localidadId) {
        this.localidadId = localidadId;
    }

    public String getLocalidadNombre() {
        return localidadNombre;
    }

    public void setLocalidadNombre(String localidadNombre) {
        this.localidadNombre = localidadNombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getAltitudMinima() {
        return altitudMinima;
    }

    public void setAltitudMinima(double altitudMinima) {
        this.altitudMinima = altitudMinima;
    }

    public double getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(double altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public String getLocalidadDescripcion() {
        return localidadDescripcion;
    }

    public void setLocalidadDescripcion(String localidadDescripcion) {
        this.localidadDescripcion = localidadDescripcion;
    }

    public String getMarcaDispositivo() {
        return marcaDispositivo;
    }

    public void setMarcaDispositivo(String marcaDispositivo) {
        this.marcaDispositivo = marcaDispositivo;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getMetodoColeccion() {
        return metodoColeccion;
    }

    public void setMetodoColeccion(String metodoColeccion) {
        this.metodoColeccion = metodoColeccion;
    }

    public String getEstacionDelAño() {
        return estacionDelAño;
    }

    public void setEstacionDelAño(String estacionDelAño) {
        this.estacionDelAño = estacionDelAño;
    }

    public long getViajeID() {
        return viajeID;
    }

    public void setViajeID(long viajeID) {
        this.viajeID = viajeID;
    }

    public long getColectorPrincipalID() {
        return colectorPrincipalID;
    }

    public void setColectorPrincipalID(long colectorPrincipalID) {
        this.colectorPrincipalID = colectorPrincipalID;
    }

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

    public Inflorescencia getInflorescencia() {
        return inflorescencia;
    }

    public void setInflorescencia(Inflorescencia inflorescencia) {
        this.inflorescencia = inflorescencia;
    }

    public Hoja getHoja() {
        return hoja;
    }

    public void setHoja(Hoja hoja) {
        this.hoja = hoja;
    }

    public Fruto getFruto() {
        return fruto;
    }

    public void setFruto(Fruto fruto) {
        this.fruto = fruto;
    }

    public Tallo getTallo() {
        return tallo;
    }

    public void setTallo(Tallo tallo) {
        this.tallo = tallo;
    }

    public Raiz getRaiz() {
        return raiz;
    }

    public void setRaiz(Raiz raiz) {
        this.raiz = raiz;
    }

    public List<ColectorSecundarioDTO> getColectoresSecundarios() {
        return colectoresSecundarios;
    }

    public void setColectoresSecundarios(List<ColectorSecundarioDTO> colectoresSecundarios) {
        this.colectoresSecundarios = colectoresSecundarios;
    }

    public List<MuestraAsociada> getMuestrasAsociadas() {
        return muestrasAsociadas;
    }

    public void setMuestrasAsociadas(List<MuestraAsociada> muestrasAsociadas) {
        this.muestrasAsociadas = muestrasAsociadas;
    }

    public List<Fotografia> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<Fotografia> fotografias) {
        this.fotografias = fotografias;
    }

    public Date getFechaIdentificacion() {
        return fechaIdentificacion;
    }

    public void setFechaIdentificacion(Date fechaIdentificacion) {
        this.fechaIdentificacion = fechaIdentificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Persona getDeterminador() {
        return determinador;
    }

    public void setDeterminador(Persona determinador) {
        this.determinador = determinador;
    }

    public TaxonDTO getTaxon() {
        return taxon;
    }

    public void setTaxon(TaxonDTO taxon) {
        this.taxon = taxon;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.numeroDeColeccion);
        dest.writeLong(this.alturaDeLaPlanta);
        dest.writeLong(this.dap);
        dest.writeString(this.abundancia);
        dest.writeParcelable(this.fenologia, 0);
        dest.writeString(this.descripcionEspecimen);
        dest.writeParcelable(this.habito, 0);
        dest.writeParcelable(this.habitat, 0);
        dest.writeValue(this.localidadId);
        dest.writeString(this.localidadNombre);
        dest.writeDouble(this.latitud);
        dest.writeDouble(this.longitud);
        dest.writeString(this.datum);
        dest.writeDouble(this.altitudMinima);
        dest.writeDouble(this.altitudMaxima);
        dest.writeString(this.localidadDescripcion);
        dest.writeString(this.marcaDispositivo);
        dest.writeParcelable(this.region, 0);
        dest.writeLong(fechaInicial != null ? fechaInicial.getTime() : -1);
        dest.writeLong(fechaFinal != null ? fechaFinal.getTime() : -1);
        dest.writeString(this.metodoColeccion);
        dest.writeString(this.estacionDelAño);
        dest.writeLong(this.viajeID);
        dest.writeLong(this.colectorPrincipalID);
        dest.writeParcelable(this.flor, 0);
        dest.writeParcelable(this.inflorescencia, 0);
        dest.writeParcelable(this.hoja, 0);
        dest.writeParcelable(this.fruto, 0);
        dest.writeParcelable(this.tallo, 0);
        dest.writeParcelable(this.raiz, 0);
        dest.writeTypedList(colectoresSecundarios);
        dest.writeTypedList(muestrasAsociadas);
        dest.writeTypedList(fotografias);
        dest.writeLong(fechaIdentificacion != null ? fechaIdentificacion.getTime() : -1);
        dest.writeString(this.tipo);
        dest.writeParcelable(this.determinador, 0);
        dest.writeParcelable(this.taxon, flags);
        dest.writeLong(this.usuarioId);
    }

    public EspecimenDTO() {
    }

    private EspecimenDTO(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.numeroDeColeccion = in.readString();
        this.alturaDeLaPlanta = in.readLong();
        this.dap = in.readLong();
        this.abundancia = in.readString();
        this.fenologia = in.readParcelable(Fenologia.class.getClassLoader());
        this.descripcionEspecimen = in.readString();
        this.habito = in.readParcelable(Habito.class.getClassLoader());
        this.habitat = in.readParcelable(Habitat.class.getClassLoader());
        this.localidadId = (Long) in.readValue(Long.class.getClassLoader());
        this.localidadNombre = in.readString();
        this.latitud = in.readDouble();
        this.longitud = in.readDouble();
        this.datum = in.readString();
        this.altitudMinima = in.readDouble();
        this.altitudMaxima = in.readDouble();
        this.localidadDescripcion = in.readString();
        this.marcaDispositivo = in.readString();
        this.region = in.readParcelable(RegionDTO.class.getClassLoader());
        long tmpFechaInicial = in.readLong();
        this.fechaInicial = tmpFechaInicial == -1 ? null : new Date(tmpFechaInicial);
        long tmpFechaFinal = in.readLong();
        this.fechaFinal = tmpFechaFinal == -1 ? null : new Date(tmpFechaFinal);
        this.metodoColeccion = in.readString();
        this.estacionDelAño = in.readString();
        this.viajeID = in.readLong();
        this.colectorPrincipalID = in.readLong();
        this.flor = in.readParcelable(Flor.class.getClassLoader());
        this.inflorescencia = in.readParcelable(Inflorescencia.class.getClassLoader());
        this.hoja = in.readParcelable(Hoja.class.getClassLoader());
        this.fruto = in.readParcelable(Fruto.class.getClassLoader());
        this.tallo = in.readParcelable(Tallo.class.getClassLoader());
        this.raiz = in.readParcelable(Raiz.class.getClassLoader());
        in.readTypedList(colectoresSecundarios, ColectorSecundarioDTO.CREATOR);
        in.readTypedList(muestrasAsociadas, MuestraAsociada.CREATOR);
        in.readTypedList(fotografias, Fotografia.CREATOR);
        long tmpFechaIdentificacion = in.readLong();
        this.fechaIdentificacion = tmpFechaIdentificacion == -1 ? null : new Date(tmpFechaIdentificacion);
        this.tipo = in.readString();
        this.determinador = (Persona) in.readParcelable(Persona.class.getClassLoader());
        this.taxon = in.readParcelable(TaxonDTO.class.getClassLoader());
        this.usuarioId = in.readLong();
    }

    public static final Parcelable.Creator<EspecimenDTO> CREATOR = new Parcelable.Creator<EspecimenDTO>() {
        public EspecimenDTO createFromParcel(Parcel source) {
            return new EspecimenDTO(source);
        }

        public EspecimenDTO[] newArray(int size) {
            return new EspecimenDTO[size];
        }
    };
}
