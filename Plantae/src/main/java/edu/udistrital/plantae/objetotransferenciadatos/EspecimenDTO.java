package edu.udistrital.plantae.objetotransferenciadatos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.udistrital.plantae.logicadominio.autenticacion.Persona;
import edu.udistrital.plantae.logicadominio.datosespecimen.ColorEspecimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.Especimen;
import edu.udistrital.plantae.logicadominio.datosespecimen.EspecimenColectorSecundario;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fenologia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habitat;
import edu.udistrital.plantae.logicadominio.datosespecimen.Habito;
import edu.udistrital.plantae.logicadominio.datosespecimen.MuestraAsociada;
import edu.udistrital.plantae.ui.SpecimenPagesAdapter;

/**
 * Created by Gerson Sosa on 8/4/14.
 */
public class EspecimenDTO implements Parcelable {

    private Long id;
    private String numeroDeColeccion;
    private Long alturaDeLaPlanta;
    private Long dap;
    private String abundancia;
    private Fenologia fenologia;
    private String descripcionEspecimen;
    private Habito habito;
    private Habitat habitat;
    //Localidad;
    private Long localidadId;
    private String localidadNombre;
    private Double latitud;
    private Double longitud;
    private String datum;
    private Double altitudMinima;
    private Double altitudMaxima;
    private String localidadDescripcion;
    private String marcaDispositivo;
    private RegionDTO region;
    private Date fechaInicial;
    private Date fechaFinal;
    private String metodoColeccion;
    private String estacionDelAño;
    private Long viajeID;
    private Long colectorPrincipalID;
    private Long florId;
    private String florDescripcion;
    private Long colorDeLaCorolaID;
    private ColorEspecimenDTO colorDeLaCorola;
    private Long colorDelCalizID;
    private ColorEspecimenDTO colorDelCaliz;
    private Long colorDelGineceoID;
    private ColorEspecimenDTO colorDelGineceo;
    private Long colorDeLosEstambresID;
    private ColorEspecimenDTO colorDeLosEstambres;
    private Long colorDeLosEstigmasID;
    private ColorEspecimenDTO colorDeLosEstigmas;
    private Long colorDeLosPistiliodiosID;
    private ColorEspecimenDTO colorDeLosPistiliodios;
    private Long inflorescenciaId;
    private Long colorDeLaInflorescenciaEnFlorID;
    private ColorEspecimenDTO colorDeLaInflorescenciaEnFlor;
    private Long colorDeLaInflorescenciaEnFrutoID;
    private ColorEspecimenDTO colorDeLaInflorescenciaEnFruto;
    private String naturalezaDeLasBracteasPedunculares;
    private String naturalezaDelProfilo;
    private String posicionDeLasBracteasPedunculares;
    private String posicionDeLasInflorescencias;
    private String raquilas;
    private String raquis;
    private String tamañoDeLasBracteasPedunculares;
    private String tamañoDelPedunculo;
    private String tamañoDelProfilo;
    private String tamañoDelRaquis;
    private String tamañoDeRaquilas;
    private String inflorescenciaDescripcion;
    private Boolean inflorescenciaSolitaria;
    private Integer numeroDeLasBracteasPedunculares;
    private Integer numeroDeRaquilas;
    private Long hojaId;
    private String coberturaDelPeciolo;
    private Long colorDeLaVainaID;
    private ColorEspecimenDTO colorDeLaVaina;
    private Long colorDelPecioloID;
    private ColorEspecimenDTO colorDelPeciolo;
    private String dispocicionDeLasPinnas;
    private String disposicionDeLasHojas;
    private String formaDelPeciolo;
    private String longuitudDelRaquis;
    private String naturalezaDeLaVaina;
    private String naturalezaDelLimbo;
    private String numeroDePinnas;
    private String numeroHojas;
    private String tamañoDeLaVaina;
    private String tamañoDelPeciolo;
    private String hojaDescripcion;
    private Long frutoId;
    private String consistenciaDelPericarpio;
    private String frutoDescripcion;
    private ColorEspecimenDTO colorDelMesocarpio;
    private Long colorDelExocarpioID;
    private ColorEspecimenDTO colorDelExocarpio;
    private Long colorDelMesocarpioID;
    private ColorEspecimenDTO colorDelMesocarpioInmaduro;
    private Long colorDelExocarpioInmaduroID;
    private ColorEspecimenDTO colorDelExocarpioInmaduro;
    private Long colorDelMesocarpioInmaduroID;
    private Long talloId;
    private String alturaDelTallo;
    private Long colorDelTalloID;
    private ColorEspecimenDTO colorDelTallo;
    private String diametroDelTallo;
    private String disposicionDeLasEspinas;
    private String formaDelTallo;
    private String longitudEntrenudos;
    private String naturalezaDelTallo;
    private String talloDescripcion;
    private Boolean desnudoCubierto;
    private Boolean entrenudosConspicuos;
    private Boolean espinas;
    private Long raizId;
    private String diametroDeLasRaices;
    private String diametroEnLaBase;
    private String formaDeLasEspinas;
    private String tamañoDeLasEspinas;
    private String raizDescripcion;
    private Boolean raizArmada;
    private Long alturaDelCono;
    private Long colorDelConoID;
    private ColorEspecimenDTO colorDelCono;
    private List<EspecimenColectorSecundario> colectoresSecundarios;
    private List<MuestraAsociada> muestrasAsociadas;
    private List<ColorEspecimenDTO> colores;
    private List<Fotografia> fotografias;
    //identidad taxonomica;
    private Date fechaIdentificacion;
    private String tipo;
    private Persona determinador;
    private TaxonDTO taxon;
    private Long usuarioId;
    private int tipoCaptura;

    public EspecimenDTO(Especimen especimen) {
        this.id = especimen.getId();
        this.numeroDeColeccion = especimen.getNumeroDeColeccion();
        this.alturaDeLaPlanta = especimen.getAlturaDeLaPlanta();
        this.dap = especimen.getDap();
        this.abundancia = especimen.getAbundancia();
        this.fenologia = especimen.getFenologiaID() == null ? null : especimen.getFenologia();
        this.descripcionEspecimen = especimen.getDescripcionEspecimen();
        this.habito = especimen.getHabitoID() == null ? null : especimen.getHabito();
        this.habitat = especimen.getHabitatID() == null ? null : especimen.getHabitat();
        if (especimen.getLocalidadID() != null) {
            this.localidadId = especimen.getLocalidad().getId();
            this.localidadNombre = especimen.getLocalidad().getNombre();
            this.latitud = especimen.getLocalidad().getLatitud();
            this.longitud = especimen.getLocalidad().getLongitud();
            this.datum = especimen.getLocalidad().getDatum();
            this.altitudMinima = especimen.getLocalidad().getAltitudMinima();
            this.altitudMaxima = especimen.getLocalidad().getAltitudMaxima();
            this.localidadDescripcion = especimen.getLocalidad().getDescripcion();
            this.marcaDispositivo = especimen.getLocalidad().getMarcaDispositivo();
            if (especimen.getLocalidad().getRegion() != null) {
                this.region = new RegionDTO(especimen.getLocalidad().getRegion());
            }
        }
        this.fechaInicial = especimen.getFechaInicial();
        this.fechaFinal = especimen.getFechaFinal();
        this.metodoColeccion = especimen.getMetodoColeccion();
        this.estacionDelAño = especimen.getEstacionDelAño();
        this.viajeID = especimen.getViajeID();
        this.colectorPrincipalID = especimen.getColectorPrincipalID();
        this.florId = especimen.getFlorID();
        if (florId != null) {
            this.florDescripcion = especimen.getFlor().getDescripcion();
            this.colorDeLaCorolaID = especimen.getFlor().getColorDeLaCorolaID();
            if (especimen.getFlor().getColorDeLaCorola() != null) {
                this.colorDeLaCorola = new ColorEspecimenDTO(especimen.getFlor().getColorDeLaCorola().getId(),
                        especimen.getFlor().getColorDeLaCorola().getNombre(),
                        especimen.getFlor().getColorDeLaCorola().getDescripcion(),
                        especimen.getFlor().getColorDeLaCorola().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDeLaCorola().getColorRGB(),
                        especimen.getFlor().getColorDeLaCorola().getColorMunsellID(),
                        especimen.getFlor().getColorDeLaCorola().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDeLaCorola().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDeLaCorola().getColorMunsell().getChroma());
            }
            this.colorDelCalizID = especimen.getFlor().getColorDelCalizID();
            if (especimen.getFlor().getColorDelCaliz() != null) {
                this.colorDelCaliz = new ColorEspecimenDTO(especimen.getFlor().getColorDelCaliz().getId(),
                        especimen.getFlor().getColorDelCaliz().getNombre(),
                        especimen.getFlor().getColorDelCaliz().getDescripcion(),
                        especimen.getFlor().getColorDelCaliz().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDelCaliz().getColorRGB(),
                        especimen.getFlor().getColorDelCaliz().getColorMunsellID(),
                        especimen.getFlor().getColorDelCaliz().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDelCaliz().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDelCaliz().getColorMunsell().getChroma());
            }
            this.colorDelGineceoID = especimen.getFlor().getColorDelGineceoID();
            if (especimen.getFlor().getColorDelGineceo() != null) {
                this.colorDelGineceo = new ColorEspecimenDTO(especimen.getFlor().getColorDelGineceo().getId(),
                        especimen.getFlor().getColorDelGineceo().getNombre(),
                        especimen.getFlor().getColorDelGineceo().getDescripcion(),
                        especimen.getFlor().getColorDelGineceo().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDelGineceo().getColorRGB(),
                        especimen.getFlor().getColorDelGineceo().getColorMunsellID(),
                        especimen.getFlor().getColorDelGineceo().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDelGineceo().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDelGineceo().getColorMunsell().getChroma());
            }
            this.colorDeLosEstambresID = especimen.getFlor().getColorDeLosEstambresID();
            if (especimen.getFlor().getColorDeLosEstambres() != null) {
                this.colorDeLosEstambres = new ColorEspecimenDTO(especimen.getFlor().getColorDeLosEstambres().getId(),
                        especimen.getFlor().getColorDeLosEstambres().getNombre(),
                        especimen.getFlor().getColorDeLosEstambres().getDescripcion(),
                        especimen.getFlor().getColorDeLosEstambres().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDeLosEstambres().getColorRGB(),
                        especimen.getFlor().getColorDeLosEstambres().getColorMunsellID(),
                        especimen.getFlor().getColorDeLosEstambres().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDeLosEstambres().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDeLosEstambres().getColorMunsell().getChroma());
            }
            this.colorDeLosEstigmasID = especimen.getFlor().getColorDeLosEstigmasID();
            if (especimen.getFlor().getColorDeLosEstigmas() != null) {
                this.colorDeLosEstigmas = new ColorEspecimenDTO(especimen.getFlor().getColorDeLosEstigmas().getId(),
                        especimen.getFlor().getColorDeLosEstigmas().getNombre(),
                        especimen.getFlor().getColorDeLosEstigmas().getDescripcion(),
                        especimen.getFlor().getColorDeLosEstigmas().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDeLosEstigmas().getColorRGB(),
                        especimen.getFlor().getColorDeLosEstigmas().getColorMunsellID(),
                        especimen.getFlor().getColorDeLosEstigmas().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDeLosEstigmas().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDeLosEstigmas().getColorMunsell().getChroma());
            }
            this.colorDeLosPistiliodiosID = especimen.getFlor().getColorDeLosPistiliodiosID();
            if (especimen.getFlor().getColorDeLosPistiliodios() != null) {
                this.colorDeLosPistiliodios = new ColorEspecimenDTO(especimen.getFlor().getColorDeLosPistiliodios().getId(),
                        especimen.getFlor().getColorDeLosPistiliodios().getNombre(),
                        especimen.getFlor().getColorDeLosPistiliodios().getDescripcion(),
                        especimen.getFlor().getColorDeLosPistiliodios().getOrganoDeLaPlanta(),
                        especimen.getFlor().getColorDeLosPistiliodios().getColorRGB(),
                        especimen.getFlor().getColorDeLosPistiliodios().getColorMunsellID(),
                        especimen.getFlor().getColorDeLosPistiliodios().getColorMunsell().getHue(),
                        especimen.getFlor().getColorDeLosPistiliodios().getColorMunsell().getValue(),
                        especimen.getFlor().getColorDeLosPistiliodios().getColorMunsell().getChroma());
            }
        }
        this.inflorescenciaId = especimen.getInflorescenciaID();
        if (inflorescenciaId != null) {
            this.colorDeLaInflorescenciaEnFlorID = especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlorID();
            if (especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor() != null) {
                this.colorDeLaInflorescenciaEnFlor = new ColorEspecimenDTO(especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getId(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getNombre(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getDescripcion(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getOrganoDeLaPlanta(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getColorRGB(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getColorMunsellID(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getColorMunsell().getHue(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getColorMunsell().getValue(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFlor().getColorMunsell().getChroma());
            }
            this.colorDeLaInflorescenciaEnFrutoID = especimen.getInflorescencia().getColorDeLaInflorescenciaEnFrutoID();
            if (especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto() != null) {
                this.colorDeLaInflorescenciaEnFruto = new ColorEspecimenDTO(especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getId(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getNombre(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getDescripcion(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getOrganoDeLaPlanta(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getColorRGB(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getColorMunsellID(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getColorMunsell().getHue(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getColorMunsell().getValue(),
                        especimen.getInflorescencia().getColorDeLaInflorescenciaEnFruto().getColorMunsell().getChroma());
            }
            this.naturalezaDeLasBracteasPedunculares = especimen.getInflorescencia().getNaturalezaDeLasBracteasPedunculares();
            this.naturalezaDelProfilo = especimen.getInflorescencia().getNaturalezaDelProfilo();
            this.posicionDeLasBracteasPedunculares = especimen.getInflorescencia().getPosicionDeLasBracteasPedunculares();
            this.posicionDeLasInflorescencias = especimen.getInflorescencia().getPosicionDeLasInflorescencias();
            this.raquilas = especimen.getInflorescencia().getRaquilas();
            this.raquis = especimen.getInflorescencia().getRaquis();
            this.tamañoDeLasBracteasPedunculares = especimen.getInflorescencia().getTamañoDeLasBracteasPedunculares();
            this.tamañoDelPedunculo = especimen.getInflorescencia().getTamañoDelPedunculo();
            this.tamañoDelProfilo = especimen.getInflorescencia().getTamañoDelProfilo();
            this.tamañoDelRaquis = especimen.getInflorescencia().getTamañoDelRaquis();
            this.tamañoDeRaquilas = especimen.getInflorescencia().getTamañoDeRaquilas();
            this.inflorescenciaDescripcion = especimen.getInflorescencia().getDescripcion();
            this.inflorescenciaSolitaria = especimen.getInflorescencia().getInflorescenciaSolitaria();
            this.numeroDeLasBracteasPedunculares = especimen.getInflorescencia().getNumeroDeLasBracteasPedunculares();
            this.numeroDeRaquilas = especimen.getInflorescencia().getNumeroDeRaquilas();
        }
        this.hojaId = especimen.getHojaID();
        if (hojaId != null) {
            this.coberturaDelPeciolo = especimen.getHoja().getCoberturaDelPeciolo();
            this.colorDeLaVainaID = especimen.getHoja().getColorDeLaVainaID();
            if (especimen.getHoja().getColorDeLaVaina() != null) {
                this.colorDeLaVaina = new ColorEspecimenDTO(especimen.getHoja().getColorDeLaVaina().getId(),
                        especimen.getHoja().getColorDeLaVaina().getNombre(),
                        especimen.getHoja().getColorDeLaVaina().getDescripcion(),
                        especimen.getHoja().getColorDeLaVaina().getOrganoDeLaPlanta(),
                        especimen.getHoja().getColorDeLaVaina().getColorRGB(),
                        especimen.getHoja().getColorDeLaVaina().getColorMunsellID(),
                        especimen.getHoja().getColorDeLaVaina().getColorMunsell().getHue(),
                        especimen.getHoja().getColorDeLaVaina().getColorMunsell().getValue(),
                        especimen.getHoja().getColorDeLaVaina().getColorMunsell().getChroma());
            }
            this.colorDelPecioloID = especimen.getHoja().getColorDelPecioloID();
            if (especimen.getHoja().getColorDelPeciolo() != null) {
                this.colorDelPeciolo = new ColorEspecimenDTO(especimen.getHoja().getColorDelPeciolo().getId(),
                        especimen.getHoja().getColorDelPeciolo().getNombre(),
                        especimen.getHoja().getColorDelPeciolo().getDescripcion(),
                        especimen.getHoja().getColorDelPeciolo().getOrganoDeLaPlanta(),
                        especimen.getHoja().getColorDelPeciolo().getColorRGB(),
                        especimen.getHoja().getColorDelPeciolo().getColorMunsellID(),
                        especimen.getHoja().getColorDelPeciolo().getColorMunsell().getHue(),
                        especimen.getHoja().getColorDelPeciolo().getColorMunsell().getValue(),
                        especimen.getHoja().getColorDelPeciolo().getColorMunsell().getChroma());
            }
            this.dispocicionDeLasPinnas = especimen.getHoja().getDispocicionDeLasPinnas();
            this.disposicionDeLasHojas = especimen.getHoja().getDisposicionDeLasHojas();
            this.formaDelPeciolo = especimen.getHoja().getFormaDelPeciolo();
            this.longuitudDelRaquis = especimen.getHoja().getLonguitudDelRaquis();
            this.naturalezaDeLaVaina = especimen.getHoja().getNaturalezaDeLaVaina();
            this.naturalezaDelLimbo = especimen.getHoja().getNaturalezaDelLimbo();
            this.numeroDePinnas = especimen.getHoja().getNumeroDePinnas();
            this.numeroHojas = especimen.getHoja().getNumeroHojas();
            this.tamañoDeLaVaina = especimen.getHoja().getTamañoDeLaVaina();
            this.tamañoDelPeciolo = especimen.getHoja().getTamañoDelPeciolo();
            this.hojaDescripcion = especimen.getHoja().getDescripcion();
        }
        this.frutoId = especimen.getFrutoID();
        if (frutoId != null) {
            this.colorDelExocarpioID = especimen.getFruto().getColorDelExocarpioID();
            if (especimen.getFruto().getColorDelExocarpio() != null) {
                this.colorDelExocarpio = new ColorEspecimenDTO(especimen.getFruto().getColorDelExocarpio().getId(),
                        especimen.getFruto().getColorDelExocarpio().getNombre(),
                        especimen.getFruto().getColorDelExocarpio().getDescripcion(),
                        especimen.getFruto().getColorDelExocarpio().getOrganoDeLaPlanta(),
                        especimen.getFruto().getColorDelExocarpio().getColorRGB(),
                        especimen.getFruto().getColorDelExocarpio().getColorMunsellID(),
                        especimen.getFruto().getColorDelExocarpio().getColorMunsell().getHue(),
                        especimen.getFruto().getColorDelExocarpio().getColorMunsell().getValue(),
                        especimen.getFruto().getColorDelExocarpio().getColorMunsell().getChroma());
            }
            this.colorDelMesocarpioID = especimen.getFruto().getColorDelMesocarpioID();
            if (especimen.getFruto().getColorDelMesocarpio() != null) {
                this.colorDelMesocarpio = new ColorEspecimenDTO(especimen.getFruto().getColorDelMesocarpio().getId(),
                        especimen.getFruto().getColorDelMesocarpio().getNombre(),
                        especimen.getFruto().getColorDelMesocarpio().getDescripcion(),
                        especimen.getFruto().getColorDelMesocarpio().getOrganoDeLaPlanta(),
                        especimen.getFruto().getColorDelMesocarpio().getColorRGB(),
                        especimen.getFruto().getColorDelMesocarpio().getColorMunsellID(),
                        especimen.getFruto().getColorDelMesocarpio().getColorMunsell().getHue(),
                        especimen.getFruto().getColorDelMesocarpio().getColorMunsell().getValue(),
                        especimen.getFruto().getColorDelMesocarpio().getColorMunsell().getChroma());
            }
            this.colorDelExocarpioInmaduroID = especimen.getFruto().getColorDelExocarpioInmaduroID();
            if (especimen.getFruto().getColorDelExocarpioInmaduro() != null) {
                this.colorDelExocarpioInmaduro = new ColorEspecimenDTO(especimen.getFruto().getColorDelExocarpioInmaduro().getId(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getNombre(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getDescripcion(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getOrganoDeLaPlanta(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getColorRGB(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getColorMunsellID(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getColorMunsell().getHue(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getColorMunsell().getValue(),
                        especimen.getFruto().getColorDelExocarpioInmaduro().getColorMunsell().getChroma());
            }
            this.colorDelMesocarpioInmaduroID = especimen.getFruto().getColorDelMesocarpioInmaduroID();
            if (especimen.getFruto().getColorDelMesocarpioInmaduro() != null) {
                this.colorDelMesocarpioInmaduro = new ColorEspecimenDTO(especimen.getFruto().getColorDelMesocarpioInmaduro().getId(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getNombre(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getDescripcion(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getOrganoDeLaPlanta(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getColorRGB(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getColorMunsellID(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getColorMunsell().getHue(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getColorMunsell().getValue(),
                        especimen.getFruto().getColorDelMesocarpioInmaduro().getColorMunsell().getChroma());
            }
            this.consistenciaDelPericarpio = especimen.getFruto().getConsistenciaDelPericarpio();
            this.frutoDescripcion = especimen.getFruto().getDescripcion();
        }
        this.talloId = especimen.getTalloID();
        if (talloId != null) {
            this.alturaDelTallo = especimen.getTallo().getAlturaDelTallo();
            this.colorDelTalloID = especimen.getTallo().getColorDelTalloID();
            if (especimen.getTallo().getColorDelTallo() != null) {
                this.colorDelTallo = new ColorEspecimenDTO(especimen.getTallo().getColorDelTallo().getId(),
                        especimen.getTallo().getColorDelTallo().getNombre(),
                        especimen.getTallo().getColorDelTallo().getDescripcion(),
                        especimen.getTallo().getColorDelTallo().getOrganoDeLaPlanta(),
                        especimen.getTallo().getColorDelTallo().getColorRGB(),
                        especimen.getTallo().getColorDelTallo().getColorMunsellID(),
                        especimen.getTallo().getColorDelTallo().getColorMunsell().getHue(),
                        especimen.getTallo().getColorDelTallo().getColorMunsell().getValue(),
                        especimen.getTallo().getColorDelTallo().getColorMunsell().getChroma());
            }
            this.diametroDelTallo = especimen.getTallo().getDiametroDelTallo();
            this.disposicionDeLasEspinas = especimen.getTallo().getDisposicionDeLasEspinas();
            this.formaDelTallo = especimen.getTallo().getFormaDelTallo();
            this.longitudEntrenudos = especimen.getTallo().getLongitudEntrenudos();
            this.naturalezaDelTallo = especimen.getTallo().getNaturalezaDelTallo();
            this.talloDescripcion = especimen.getTallo().getDescripcion();
            this.desnudoCubierto = especimen.getTallo().getDesnudoCubierto();
            this.entrenudosConspicuos = especimen.getTallo().getEntrenudosConspicuos();
            this.espinas = especimen.getTallo().getEspinas();
        }
        this.raizId = especimen.getRaizID();
        if (raizId != null) {
            this.diametroDeLasRaices = especimen.getRaiz().getDiametroDeLasRaices();
            this.diametroEnLaBase = especimen.getRaiz().getDiametroEnLaBase();
            this.formaDeLasEspinas = especimen.getRaiz().getFormaDeLasEspinas();
            this.tamañoDeLasEspinas = especimen.getRaiz().getTamañoDeLasEspinas();
            this.raizDescripcion = especimen.getRaiz().getDescripcion();
            this.raizArmada = especimen.getRaiz().getRaizArmada();
            this.alturaDelCono = especimen.getRaiz().getAlturaDelCono();
            this.colorDelConoID = especimen.getRaiz().getColorDelConoID();
            if (especimen.getRaiz().getColorDelCono() != null) {
                this.colorDelCono = new ColorEspecimenDTO(especimen.getRaiz().getColorDelCono().getId(),
                        especimen.getRaiz().getColorDelCono().getNombre(),
                        especimen.getRaiz().getColorDelCono().getDescripcion(),
                        especimen.getRaiz().getColorDelCono().getOrganoDeLaPlanta(),
                        especimen.getRaiz().getColorDelCono().getColorRGB(),
                        especimen.getRaiz().getColorDelCono().getColorMunsellID(),
                        especimen.getRaiz().getColorDelCono().getColorMunsell().getHue(),
                        especimen.getRaiz().getColorDelCono().getColorMunsell().getValue(),
                        especimen.getRaiz().getColorDelCono().getColorMunsell().getChroma());
            }
        }
        this.colores = new ArrayList<>();
        for (ColorEspecimen colorEspecimen:especimen.getColores()){
            this.colores.add(new ColorEspecimenDTO(colorEspecimen.getId(),
                    colorEspecimen.getNombre(),
                    colorEspecimen.getDescripcion(),
                    colorEspecimen.getOrganoDeLaPlanta(),
                    colorEspecimen.getColorRGB(),
                    colorEspecimen.getColorMunsellID(),
                    colorEspecimen.getColorMunsell().getHue(),
                    colorEspecimen.getColorMunsell().getValue(),
                    colorEspecimen.getColorMunsell().getChroma()));
        }
        this.colectoresSecundarios = especimen.getColectoresSecundarios() != null ? especimen.getColectoresSecundarios() : new ArrayList<EspecimenColectorSecundario>();
        this.muestrasAsociadas = especimen.getMuestrasAsociadas() != null ? especimen.getMuestrasAsociadas() : new ArrayList<MuestraAsociada>();
        this.fotografias = especimen.getFotografias() != null ? especimen.getFotografias() : new ArrayList<Fotografia>();
        if (especimen.getDeterminacionActual() != null) {
            this.fechaIdentificacion = especimen.getDeterminacionActual().getFechaIdentificacion();
            this.tipo = especimen.getDeterminacionActual().getTipo();
            this.determinador = especimen.getDeterminacionActual().getDeterminador();
            if (especimen.getDeterminacionActual().getTaxon() != null) {
                this.taxon = new TaxonDTO(especimen.getDeterminacionActual().getTaxon());
            }
        }
        if (especimen.getColectorPrincipalID() != 0l) {
            this.usuarioId = especimen.getColectorPrincipal().getPersona().getUsuarioID();
        }
        if (especimen.getTipo().equals("ES")) {
            this.tipoCaptura = SpecimenPagesAdapter.SPECIMEN_SINGLE;
        }else{
            this.tipoCaptura = SpecimenPagesAdapter.SPECIMEN_DETAILED;
        }
    }

    public EspecimenDTO() {
        colectoresSecundarios = new ArrayList<>();
        muestrasAsociadas = new ArrayList<>();
        colores = new ArrayList<>();
        fotografias = new ArrayList<>();
    }

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

    public Long getAlturaDeLaPlanta() {
        return alturaDeLaPlanta;
    }

    public void setAlturaDeLaPlanta(Long alturaDeLaPlanta) {
        this.alturaDeLaPlanta = alturaDeLaPlanta;
    }

    public Long getDap() {
        return dap;
    }

    public void setDap(Long dap) {
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Double getAltitudMinima() {
        return altitudMinima;
    }

    public void setAltitudMinima(Double altitudMinima) {
        this.altitudMinima = altitudMinima;
    }

    public Double getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(Double altitudMaxima) {
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

    public Long getViajeID() {
        return viajeID;
    }

    public void setViajeID(Long viajeID) {
        this.viajeID = viajeID;
    }

    public Long getColectorPrincipalID() {
        return colectorPrincipalID;
    }

    public void setColectorPrincipalID(Long colectorPrincipalID) {
        this.colectorPrincipalID = colectorPrincipalID;
    }

    public Long getFlorId() {
        return florId;
    }

    public void setFlorId(Long florId) {
        this.florId = florId;
    }

    public String getFlorDescripcion() {
        return florDescripcion;
    }

    public void setFlorDescripcion(String florDescripcion) {
        this.florDescripcion = florDescripcion;
    }

    public Long getColorDeLaCorolaID() {
        return colorDeLaCorolaID;
    }

    public void setColorDeLaCorolaID(Long colorDeLaCorolaID) {
        this.colorDeLaCorolaID = colorDeLaCorolaID;
    }

    public ColorEspecimenDTO getColorDeLaCorola() {
        return colorDeLaCorola;
    }

    public void setColorDeLaCorola(ColorEspecimenDTO colorDeLaCorola) {
        this.colorDeLaCorola = colorDeLaCorola;
    }

    public Long getColorDelCalizID() {
        return colorDelCalizID;
    }

    public void setColorDelCalizID(Long colorDelCalizID) {
        this.colorDelCalizID = colorDelCalizID;
    }

    public ColorEspecimenDTO getColorDelCaliz() {
        return colorDelCaliz;
    }

    public void setColorDelCaliz(ColorEspecimenDTO colorDelCaliz) {
        this.colorDelCaliz = colorDelCaliz;
    }

    public Long getColorDelGineceoID() {
        return colorDelGineceoID;
    }

    public void setColorDelGineceoID(Long colorDelGineceoID) {
        this.colorDelGineceoID = colorDelGineceoID;
    }

    public ColorEspecimenDTO getColorDelGineceo() {
        return colorDelGineceo;
    }

    public void setColorDelGineceo(ColorEspecimenDTO colorDelGineceo) {
        this.colorDelGineceo = colorDelGineceo;
    }

    public Long getColorDeLosEstambresID() {
        return colorDeLosEstambresID;
    }

    public void setColorDeLosEstambresID(Long colorDeLosEstambresID) {
        this.colorDeLosEstambresID = colorDeLosEstambresID;
    }

    public ColorEspecimenDTO getColorDeLosEstambres() {
        return colorDeLosEstambres;
    }

    public void setColorDeLosEstambres(ColorEspecimenDTO colorDeLosEstambres) {
        this.colorDeLosEstambres = colorDeLosEstambres;
    }

    public Long getColorDeLosEstigmasID() {
        return colorDeLosEstigmasID;
    }

    public void setColorDeLosEstigmasID(Long colorDeLosEstigmasID) {
        this.colorDeLosEstigmasID = colorDeLosEstigmasID;
    }

    public ColorEspecimenDTO getColorDeLosEstigmas() {
        return colorDeLosEstigmas;
    }

    public void setColorDeLosEstigmas(ColorEspecimenDTO colorDeLosEstigmas) {
        this.colorDeLosEstigmas = colorDeLosEstigmas;
    }

    public Long getColorDeLosPistiliodiosID() {
        return colorDeLosPistiliodiosID;
    }

    public void setColorDeLosPistiliodiosID(Long colorDeLosPistiliodiosID) {
        this.colorDeLosPistiliodiosID = colorDeLosPistiliodiosID;
    }

    public ColorEspecimenDTO getColorDeLosPistiliodios() {
        return colorDeLosPistiliodios;
    }

    public void setColorDeLosPistiliodios(ColorEspecimenDTO colorDeLosPistiliodios) {
        this.colorDeLosPistiliodios = colorDeLosPistiliodios;
    }

    public Long getInflorescenciaId() {
        return inflorescenciaId;
    }

    public void setInflorescenciaId(Long inflorescenciaId) {
        this.inflorescenciaId = inflorescenciaId;
    }

    public Long getColorDeLaInflorescenciaEnFlorID() {
        return colorDeLaInflorescenciaEnFlorID;
    }

    public void setColorDeLaInflorescenciaEnFlorID(Long colorDeLaInflorescenciaEnFlorID) {
        this.colorDeLaInflorescenciaEnFlorID = colorDeLaInflorescenciaEnFlorID;
    }

    public ColorEspecimenDTO getColorDeLaInflorescenciaEnFlor() {
        return colorDeLaInflorescenciaEnFlor;
    }

    public void setColorDeLaInflorescenciaEnFlor(ColorEspecimenDTO colorDeLaInflorescenciaEnFlor) {
        this.colorDeLaInflorescenciaEnFlor = colorDeLaInflorescenciaEnFlor;
    }

    public Long getColorDeLaInflorescenciaEnFrutoID() {
        return colorDeLaInflorescenciaEnFrutoID;
    }

    public void setColorDeLaInflorescenciaEnFrutoID(Long colorDeLaInflorescenciaEnFrutoID) {
        this.colorDeLaInflorescenciaEnFrutoID = colorDeLaInflorescenciaEnFrutoID;
    }

    public ColorEspecimenDTO getColorDeLaInflorescenciaEnFruto() {
        return colorDeLaInflorescenciaEnFruto;
    }

    public void setColorDeLaInflorescenciaEnFruto(ColorEspecimenDTO colorDeLaInflorescenciaEnFruto) {
        this.colorDeLaInflorescenciaEnFruto = colorDeLaInflorescenciaEnFruto;
    }

    public String getNaturalezaDeLasBracteasPedunculares() {
        return naturalezaDeLasBracteasPedunculares;
    }

    public void setNaturalezaDeLasBracteasPedunculares(String naturalezaDeLasBracteasPedunculares) {
        this.naturalezaDeLasBracteasPedunculares = naturalezaDeLasBracteasPedunculares;
    }

    public String getNaturalezaDelProfilo() {
        return naturalezaDelProfilo;
    }

    public void setNaturalezaDelProfilo(String naturalezaDelProfilo) {
        this.naturalezaDelProfilo = naturalezaDelProfilo;
    }

    public String getPosicionDeLasBracteasPedunculares() {
        return posicionDeLasBracteasPedunculares;
    }

    public void setPosicionDeLasBracteasPedunculares(String posicionDeLasBracteasPedunculares) {
        this.posicionDeLasBracteasPedunculares = posicionDeLasBracteasPedunculares;
    }

    public String getPosicionDeLasInflorescencias() {
        return posicionDeLasInflorescencias;
    }

    public void setPosicionDeLasInflorescencias(String posicionDeLasInflorescencias) {
        this.posicionDeLasInflorescencias = posicionDeLasInflorescencias;
    }

    public String getRaquilas() {
        return raquilas;
    }

    public void setRaquilas(String raquilas) {
        this.raquilas = raquilas;
    }

    public String getRaquis() {
        return raquis;
    }

    public void setRaquis(String raquis) {
        this.raquis = raquis;
    }

    public String getTamañoDeLasBracteasPedunculares() {
        return tamañoDeLasBracteasPedunculares;
    }

    public void setTamañoDeLasBracteasPedunculares(String tamañoDeLasBracteasPedunculares) {
        this.tamañoDeLasBracteasPedunculares = tamañoDeLasBracteasPedunculares;
    }

    public String getTamañoDelPedunculo() {
        return tamañoDelPedunculo;
    }

    public void setTamañoDelPedunculo(String tamañoDelPedunculo) {
        this.tamañoDelPedunculo = tamañoDelPedunculo;
    }

    public String getTamañoDelProfilo() {
        return tamañoDelProfilo;
    }

    public void setTamañoDelProfilo(String tamañoDelProfilo) {
        this.tamañoDelProfilo = tamañoDelProfilo;
    }

    public String getTamañoDelRaquis() {
        return tamañoDelRaquis;
    }

    public void setTamañoDelRaquis(String tamañoDelRaquis) {
        this.tamañoDelRaquis = tamañoDelRaquis;
    }

    public String getTamañoDeRaquilas() {
        return tamañoDeRaquilas;
    }

    public void setTamañoDeRaquilas(String tamañoDeRaquilas) {
        this.tamañoDeRaquilas = tamañoDeRaquilas;
    }

    public String getInflorescenciaDescripcion() {
        return inflorescenciaDescripcion;
    }

    public void setInflorescenciaDescripcion(String inflorescenciaDescripcion) {
        this.inflorescenciaDescripcion = inflorescenciaDescripcion;
    }

    public Boolean getInflorescenciaSolitaria() {
        return inflorescenciaSolitaria;
    }

    public void setInflorescenciaSolitaria(Boolean inflorescenciaSolitaria) {
        this.inflorescenciaSolitaria = inflorescenciaSolitaria;
    }

    public Integer getNumeroDeLasBracteasPedunculares() {
        return numeroDeLasBracteasPedunculares;
    }

    public void setNumeroDeLasBracteasPedunculares(Integer numeroDeLasBracteasPedunculares) {
        this.numeroDeLasBracteasPedunculares = numeroDeLasBracteasPedunculares;
    }

    public Integer getNumeroDeRaquilas() {
        return numeroDeRaquilas;
    }

    public void setNumeroDeRaquilas(Integer numeroDeRaquilas) {
        this.numeroDeRaquilas = numeroDeRaquilas;
    }

    public Long getHojaId() {
        return hojaId;
    }

    public void setHojaId(Long hojaId) {
        this.hojaId = hojaId;
    }

    public String getCoberturaDelPeciolo() {
        return coberturaDelPeciolo;
    }

    public void setCoberturaDelPeciolo(String coberturaDelPeciolo) {
        this.coberturaDelPeciolo = coberturaDelPeciolo;
    }

    public Long getColorDeLaVainaID() {
        return colorDeLaVainaID;
    }

    public void setColorDeLaVainaID(Long colorDeLaVainaID) {
        this.colorDeLaVainaID = colorDeLaVainaID;
    }

    public ColorEspecimenDTO getColorDeLaVaina() {
        return colorDeLaVaina;
    }

    public void setColorDeLaVaina(ColorEspecimenDTO colorDeLaVaina) {
        this.colorDeLaVaina = colorDeLaVaina;
    }

    public Long getColorDelPecioloID() {
        return colorDelPecioloID;
    }

    public void setColorDelPecioloID(Long colorDelPecioloID) {
        this.colorDelPecioloID = colorDelPecioloID;
    }

    public ColorEspecimenDTO getColorDelPeciolo() {
        return colorDelPeciolo;
    }

    public void setColorDelPeciolo(ColorEspecimenDTO colorDelPeciolo) {
        this.colorDelPeciolo = colorDelPeciolo;
    }

    public String getDispocicionDeLasPinnas() {
        return dispocicionDeLasPinnas;
    }

    public void setDispocicionDeLasPinnas(String dispocicionDeLasPinnas) {
        this.dispocicionDeLasPinnas = dispocicionDeLasPinnas;
    }

    public String getDisposicionDeLasHojas() {
        return disposicionDeLasHojas;
    }

    public void setDisposicionDeLasHojas(String disposicionDeLasHojas) {
        this.disposicionDeLasHojas = disposicionDeLasHojas;
    }

    public String getFormaDelPeciolo() {
        return formaDelPeciolo;
    }

    public void setFormaDelPeciolo(String formaDelPeciolo) {
        this.formaDelPeciolo = formaDelPeciolo;
    }

    public String getLonguitudDelRaquis() {
        return longuitudDelRaquis;
    }

    public void setLonguitudDelRaquis(String longuitudDelRaquis) {
        this.longuitudDelRaquis = longuitudDelRaquis;
    }

    public String getNaturalezaDeLaVaina() {
        return naturalezaDeLaVaina;
    }

    public void setNaturalezaDeLaVaina(String naturalezaDeLaVaina) {
        this.naturalezaDeLaVaina = naturalezaDeLaVaina;
    }

    public String getNaturalezaDelLimbo() {
        return naturalezaDelLimbo;
    }

    public void setNaturalezaDelLimbo(String naturalezaDelLimbo) {
        this.naturalezaDelLimbo = naturalezaDelLimbo;
    }

    public String getNumeroDePinnas() {
        return numeroDePinnas;
    }

    public void setNumeroDePinnas(String numeroDePinnas) {
        this.numeroDePinnas = numeroDePinnas;
    }

    public String getNumeroHojas() {
        return numeroHojas;
    }

    public void setNumeroHojas(String numeroHojas) {
        this.numeroHojas = numeroHojas;
    }

    public String getTamañoDeLaVaina() {
        return tamañoDeLaVaina;
    }

    public void setTamañoDeLaVaina(String tamañoDeLaVaina) {
        this.tamañoDeLaVaina = tamañoDeLaVaina;
    }

    public String getTamañoDelPeciolo() {
        return tamañoDelPeciolo;
    }

    public void setTamañoDelPeciolo(String tamañoDelPeciolo) {
        this.tamañoDelPeciolo = tamañoDelPeciolo;
    }

    public String getHojaDescripcion() {
        return hojaDescripcion;
    }

    public void setHojaDescripcion(String hojaDescripcion) {
        this.hojaDescripcion = hojaDescripcion;
    }

    public Long getFrutoId() {
        return frutoId;
    }

    public void setFrutoId(Long frutoId) {
        this.frutoId = frutoId;
    }

    public Long getColorDelExocarpioID() {
        return colorDelExocarpioID;
    }

    public void setColorDelExocarpioID(Long colorDelExocarpioID) {
        this.colorDelExocarpioID = colorDelExocarpioID;
    }

    public ColorEspecimenDTO getColorDelExocarpio() {
        return colorDelExocarpio;
    }

    public void setColorDelExocarpio(ColorEspecimenDTO colorDelExocarpio) {
        this.colorDelExocarpio = colorDelExocarpio;
    }

    public Long getColorDelMesocarpioID() {
        return colorDelMesocarpioID;
    }

    public void setColorDelMesocarpioID(Long colorDelMesocarpioID) {
        this.colorDelMesocarpioID = colorDelMesocarpioID;
    }

    public ColorEspecimenDTO getColorDelMesocarpio() {
        return colorDelMesocarpio;
    }

    public void setColorDelMesocarpio(ColorEspecimenDTO colorDelMesocarpio) {
        this.colorDelMesocarpio = colorDelMesocarpio;
    }

    public Long getColorDelExocarpioInmaduroID() {
        return colorDelExocarpioInmaduroID;
    }

    public void setColorDelExocarpioInmaduroID(Long colorDelExocarpioInmaduroID) {
        this.colorDelExocarpioInmaduroID = colorDelExocarpioInmaduroID;
    }

    public ColorEspecimenDTO getColorDelExocarpioInmaduro() {
        return colorDelExocarpioInmaduro;
    }

    public void setColorDelExocarpioInmaduro(ColorEspecimenDTO colorDelExocarpioInmaduro) {
        this.colorDelExocarpioInmaduro = colorDelExocarpioInmaduro;
    }

    public Long getColorDelMesocarpioInmaduroID() {
        return colorDelMesocarpioInmaduroID;
    }

    public void setColorDelMesocarpioInmaduroID(Long colorDelMesocarpioInmaduroID) {
        this.colorDelMesocarpioInmaduroID = colorDelMesocarpioInmaduroID;
    }

    public ColorEspecimenDTO getColorDelMesocarpioInmaduro() {
        return colorDelMesocarpioInmaduro;
    }

    public void setColorDelMesocarpioInmaduro(ColorEspecimenDTO colorDelMesocarpioInmaduro) {
        this.colorDelMesocarpioInmaduro = colorDelMesocarpioInmaduro;
    }

    public String getConsistenciaDelPericarpio() {
        return consistenciaDelPericarpio;
    }

    public void setConsistenciaDelPericarpio(String consistenciaDelPericarpio) {
        this.consistenciaDelPericarpio = consistenciaDelPericarpio;
    }

    public String getFrutoDescripcion() {
        return frutoDescripcion;
    }

    public void setFrutoDescripcion(String frutoDescripcion) {
        this.frutoDescripcion = frutoDescripcion;
    }

    public Long getTalloId() {
        return talloId;
    }

    public void setTalloId(Long talloId) {
        this.talloId = talloId;
    }

    public String getAlturaDelTallo() {
        return alturaDelTallo;
    }

    public void setAlturaDelTallo(String alturaDelTallo) {
        this.alturaDelTallo = alturaDelTallo;
    }

    public Long getColorDelTalloID() {
        return colorDelTalloID;
    }

    public void setColorDelTalloID(Long colorDelTalloID) {
        this.colorDelTalloID = colorDelTalloID;
    }

    public ColorEspecimenDTO getColorDelTallo() {
        return colorDelTallo;
    }

    public void setColorDelTallo(ColorEspecimenDTO colorDelTallo) {
        this.colorDelTallo = colorDelTallo;
    }

    public String getDiametroDelTallo() {
        return diametroDelTallo;
    }

    public void setDiametroDelTallo(String diametroDelTallo) {
        this.diametroDelTallo = diametroDelTallo;
    }

    public String getDisposicionDeLasEspinas() {
        return disposicionDeLasEspinas;
    }

    public void setDisposicionDeLasEspinas(String disposicionDeLasEspinas) {
        this.disposicionDeLasEspinas = disposicionDeLasEspinas;
    }

    public String getFormaDelTallo() {
        return formaDelTallo;
    }

    public void setFormaDelTallo(String formaDelTallo) {
        this.formaDelTallo = formaDelTallo;
    }

    public String getLongitudEntrenudos() {
        return longitudEntrenudos;
    }

    public void setLongitudEntrenudos(String longitudEntrenudos) {
        this.longitudEntrenudos = longitudEntrenudos;
    }

    public String getNaturalezaDelTallo() {
        return naturalezaDelTallo;
    }

    public void setNaturalezaDelTallo(String naturalezaDelTallo) {
        this.naturalezaDelTallo = naturalezaDelTallo;
    }

    public String getTalloDescripcion() {
        return talloDescripcion;
    }

    public void setTalloDescripcion(String talloDescripcion) {
        this.talloDescripcion = talloDescripcion;
    }

    public Boolean getDesnudoCubierto() {
        return desnudoCubierto;
    }

    public void setDesnudoCubierto(Boolean desnudoCubierto) {
        this.desnudoCubierto = desnudoCubierto;
    }

    public Boolean getEntrenudosConspicuos() {
        return entrenudosConspicuos;
    }

    public void setEntrenudosConspicuos(Boolean entrenudosConspicuos) {
        this.entrenudosConspicuos = entrenudosConspicuos;
    }

    public Boolean getEspinas() {
        return espinas;
    }

    public void setEspinas(Boolean espinas) {
        this.espinas = espinas;
    }

    public Long getRaizId() {
        return raizId;
    }

    public void setRaizId(Long raizId) {
        this.raizId = raizId;
    }

    public String getDiametroDeLasRaices() {
        return diametroDeLasRaices;
    }

    public void setDiametroDeLasRaices(String diametroDeLasRaices) {
        this.diametroDeLasRaices = diametroDeLasRaices;
    }

    public String getDiametroEnLaBase() {
        return diametroEnLaBase;
    }

    public void setDiametroEnLaBase(String diametroEnLaBase) {
        this.diametroEnLaBase = diametroEnLaBase;
    }

    public String getFormaDeLasEspinas() {
        return formaDeLasEspinas;
    }

    public void setFormaDeLasEspinas(String formaDeLasEspinas) {
        this.formaDeLasEspinas = formaDeLasEspinas;
    }

    public String getTamañoDeLasEspinas() {
        return tamañoDeLasEspinas;
    }

    public void setTamañoDeLasEspinas(String tamañoDeLasEspinas) {
        this.tamañoDeLasEspinas = tamañoDeLasEspinas;
    }

    public String getRaizDescripcion() {
        return raizDescripcion;
    }

    public void setRaizDescripcion(String raizDescripcion) {
        this.raizDescripcion = raizDescripcion;
    }

    public Boolean getRaizArmada() {
        return raizArmada;
    }

    public void setRaizArmada(Boolean raizArmada) {
        this.raizArmada = raizArmada;
    }

    public Long getAlturaDelCono() {
        return alturaDelCono;
    }

    public void setAlturaDelCono(Long alturaDelCono) {
        this.alturaDelCono = alturaDelCono;
    }

    public Long getColorDelConoID() {
        return colorDelConoID;
    }

    public void setColorDelConoID(Long colorDelConoID) {
        this.colorDelConoID = colorDelConoID;
    }

    public ColorEspecimenDTO getColorDelCono() {
        return colorDelCono;
    }

    public void setColorDelCono(ColorEspecimenDTO colorDelCono) {
        this.colorDelCono = colorDelCono;
    }

    public List<EspecimenColectorSecundario> getColectoresSecundarios() {
        return colectoresSecundarios;
    }

    public void setColectoresSecundarios(List<EspecimenColectorSecundario> colectoresSecundarios) {
        this.colectoresSecundarios = colectoresSecundarios;
    }

    public List<MuestraAsociada> getMuestrasAsociadas() {
        return muestrasAsociadas;
    }

    public void setMuestrasAsociadas(List<MuestraAsociada> muestrasAsociadas) {
        this.muestrasAsociadas = muestrasAsociadas;
    }

    public List<ColorEspecimenDTO> getColores() {
        return colores;
    }

    public void setColores(List<ColorEspecimenDTO> colores) {
        this.colores = colores;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getTipoCaptura() {
        return tipoCaptura;
    }

    public void setTipoCaptura(int tipoCaptura) {
        this.tipoCaptura = tipoCaptura;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.numeroDeColeccion);
        dest.writeValue(this.alturaDeLaPlanta);
        dest.writeValue(this.dap);
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
        dest.writeValue(this.viajeID);
        dest.writeValue(this.colectorPrincipalID);
        dest.writeValue(this.florId);
        dest.writeString(this.florDescripcion);
        dest.writeValue(this.colorDeLaCorolaID);
        dest.writeParcelable(this.colorDeLaCorola, 0);
        dest.writeValue(this.colorDelCalizID);
        dest.writeParcelable(this.colorDelCaliz, 0);
        dest.writeValue(this.colorDelGineceoID);
        dest.writeParcelable(this.colorDelGineceo, 0);
        dest.writeValue(this.colorDeLosEstambresID);
        dest.writeParcelable(this.colorDeLosEstambres, 0);
        dest.writeValue(this.colorDeLosEstigmasID);
        dest.writeParcelable(this.colorDeLosEstigmas, 0);
        dest.writeValue(this.colorDeLosPistiliodiosID);
        dest.writeParcelable(this.colorDeLosPistiliodios, 0);
        dest.writeValue(this.inflorescenciaId);
        dest.writeValue(this.colorDeLaInflorescenciaEnFlorID);
        dest.writeParcelable(this.colorDeLaInflorescenciaEnFlor, 0);
        dest.writeValue(this.colorDeLaInflorescenciaEnFrutoID);
        dest.writeParcelable(this.colorDeLaInflorescenciaEnFruto, 0);
        dest.writeString(this.naturalezaDeLasBracteasPedunculares);
        dest.writeString(this.naturalezaDelProfilo);
        dest.writeString(this.posicionDeLasBracteasPedunculares);
        dest.writeString(this.posicionDeLasInflorescencias);
        dest.writeString(this.raquilas);
        dest.writeString(this.raquis);
        dest.writeString(this.tamañoDeLasBracteasPedunculares);
        dest.writeString(this.tamañoDelPedunculo);
        dest.writeString(this.tamañoDelProfilo);
        dest.writeString(this.tamañoDelRaquis);
        dest.writeString(this.tamañoDeRaquilas);
        dest.writeString(this.inflorescenciaDescripcion);
        dest.writeValue(this.inflorescenciaSolitaria);
        dest.writeValue(this.numeroDeLasBracteasPedunculares);
        dest.writeValue(this.numeroDeRaquilas);
        dest.writeValue(this.hojaId);
        dest.writeString(this.coberturaDelPeciolo);
        dest.writeValue(this.colorDeLaVainaID);
        dest.writeParcelable(this.colorDeLaVaina, 0);
        dest.writeValue(this.colorDelPecioloID);
        dest.writeParcelable(this.colorDelPeciolo, 0);
        dest.writeString(this.dispocicionDeLasPinnas);
        dest.writeString(this.disposicionDeLasHojas);
        dest.writeString(this.formaDelPeciolo);
        dest.writeString(this.longuitudDelRaquis);
        dest.writeString(this.naturalezaDeLaVaina);
        dest.writeString(this.naturalezaDelLimbo);
        dest.writeString(this.numeroDePinnas);
        dest.writeString(this.numeroHojas);
        dest.writeString(this.tamañoDeLaVaina);
        dest.writeString(this.tamañoDelPeciolo);
        dest.writeString(this.hojaDescripcion);
        dest.writeValue(this.frutoId);
        dest.writeValue(this.colorDelExocarpioID);
        dest.writeParcelable(this.colorDelExocarpio, 0);
        dest.writeValue(this.colorDelMesocarpioID);
        dest.writeParcelable(this.colorDelMesocarpio, 0);
        dest.writeValue(this.colorDelExocarpioInmaduroID);
        dest.writeParcelable(this.colorDelExocarpioInmaduro, 0);
        dest.writeValue(this.colorDelMesocarpioInmaduroID);
        dest.writeParcelable(this.colorDelMesocarpioInmaduro, 0);
        dest.writeString(this.consistenciaDelPericarpio);
        dest.writeString(this.frutoDescripcion);
        dest.writeValue(this.talloId);
        dest.writeString(this.alturaDelTallo);
        dest.writeValue(this.colorDelTalloID);
        dest.writeParcelable(this.colorDelTallo, 0);
        dest.writeString(this.diametroDelTallo);
        dest.writeString(this.disposicionDeLasEspinas);
        dest.writeString(this.formaDelTallo);
        dest.writeString(this.longitudEntrenudos);
        dest.writeString(this.naturalezaDelTallo);
        dest.writeString(this.talloDescripcion);
        dest.writeValue(this.desnudoCubierto);
        dest.writeValue(this.entrenudosConspicuos);
        dest.writeValue(this.espinas);
        dest.writeValue(this.raizId);
        dest.writeString(this.diametroDeLasRaices);
        dest.writeString(this.diametroEnLaBase);
        dest.writeString(this.formaDeLasEspinas);
        dest.writeString(this.tamañoDeLasEspinas);
        dest.writeString(this.raizDescripcion);
        dest.writeValue(this.raizArmada);
        dest.writeValue(this.alturaDelCono);

        dest.writeTypedList(colectoresSecundarios);
        dest.writeTypedList(muestrasAsociadas);
        dest.writeTypedList(colores);
        dest.writeTypedList(fotografias);
        dest.writeLong(fechaIdentificacion != null ? fechaIdentificacion.getTime() : -1);
        dest.writeString(this.tipo);
        dest.writeParcelable(this.determinador, 0);
        dest.writeParcelable(this.taxon, 0);
        dest.writeValue(this.usuarioId);
        dest.writeInt(this.tipoCaptura);
    }

    private EspecimenDTO(Parcel in) {
        this();
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.numeroDeColeccion = in.readString();
        this.alturaDeLaPlanta = (Long) in.readValue(Long.class.getClassLoader());
        this.dap = (Long) in.readValue(Long.class.getClassLoader());
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
        this.viajeID = (Long) in.readValue(Long.class.getClassLoader());
        this.colectorPrincipalID = (Long) in.readValue(Long.class.getClassLoader());
        this.florId = (Long) in.readValue(Long.class.getClassLoader());
        this.florDescripcion = in.readString();
        this.colorDeLaCorolaID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaCorola = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelCalizID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelCaliz = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelGineceoID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelGineceo = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDeLosEstambresID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstambres = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDeLosEstigmasID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosEstigmas = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDeLosPistiliodiosID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLosPistiliodios = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.inflorescenciaId = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFlorID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFlor = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFrutoID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaInflorescenciaEnFruto = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.naturalezaDeLasBracteasPedunculares = in.readString();
        this.naturalezaDelProfilo = in.readString();
        this.posicionDeLasBracteasPedunculares = in.readString();
        this.posicionDeLasInflorescencias = in.readString();
        this.raquilas = in.readString();
        this.raquis = in.readString();
        this.tamañoDeLasBracteasPedunculares = in.readString();
        this.tamañoDelPedunculo = in.readString();
        this.tamañoDelProfilo = in.readString();
        this.tamañoDelRaquis = in.readString();
        this.tamañoDeRaquilas = in.readString();
        this.inflorescenciaDescripcion = in.readString();
        this.inflorescenciaSolitaria = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.numeroDeLasBracteasPedunculares = (Integer) in.readValue(Integer.class.getClassLoader());
        this.numeroDeRaquilas = (Integer) in.readValue(Integer.class.getClassLoader());
        this.hojaId = (Long) in.readValue(Long.class.getClassLoader());
        this.coberturaDelPeciolo = in.readString();
        this.colorDeLaVainaID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDeLaVaina = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelPecioloID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelPeciolo = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.dispocicionDeLasPinnas = in.readString();
        this.disposicionDeLasHojas = in.readString();
        this.formaDelPeciolo = in.readString();
        this.longuitudDelRaquis = in.readString();
        this.naturalezaDeLaVaina = in.readString();
        this.naturalezaDelLimbo = in.readString();
        this.numeroDePinnas = in.readString();
        this.numeroHojas = in.readString();
        this.tamañoDeLaVaina = in.readString();
        this.tamañoDelPeciolo = in.readString();
        this.hojaDescripcion = in.readString();
        this.frutoId = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelExocarpioID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelExocarpio = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelMesocarpioID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelMesocarpio = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelExocarpioInmaduroID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelExocarpioInmaduro = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.colorDelMesocarpioInmaduroID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelMesocarpioInmaduro = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.consistenciaDelPericarpio = in.readString();
        this.frutoDescripcion = in.readString();
        this.talloId = (Long) in.readValue(Long.class.getClassLoader());
        this.alturaDelTallo = in.readString();
        this.colorDelTalloID = (Long) in.readValue(Long.class.getClassLoader());
        this.colorDelTallo = in.readParcelable(ColorEspecimenDTO.class.getClassLoader());
        this.diametroDelTallo = in.readString();
        this.disposicionDeLasEspinas = in.readString();
        this.formaDelTallo = in.readString();
        this.longitudEntrenudos = in.readString();
        this.naturalezaDelTallo = in.readString();
        this.talloDescripcion = in.readString();
        this.desnudoCubierto = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.entrenudosConspicuos = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.espinas = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.raizId = (Long) in.readValue(Long.class.getClassLoader());
        this.diametroDeLasRaices = in.readString();
        this.diametroEnLaBase = in.readString();
        this.formaDeLasEspinas = in.readString();
        this.tamañoDeLasEspinas = in.readString();
        this.raizDescripcion = in.readString();
        this.raizArmada = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.alturaDelCono = (Long) in.readValue(Long.class.getClassLoader());
        in.readTypedList(colectoresSecundarios, EspecimenColectorSecundario.CREATOR);
        in.readTypedList(muestrasAsociadas, MuestraAsociada.CREATOR);
        in.readTypedList(colores, ColorEspecimenDTO.CREATOR);
        in.readTypedList(fotografias, Fotografia.CREATOR);
        long tmpFechaIdentificacion = in.readLong();
        this.fechaIdentificacion = tmpFechaIdentificacion == -1 ? null : new Date(tmpFechaIdentificacion);
        this.tipo = in.readString();
        this.determinador = in.readParcelable(Persona.class.getClassLoader());
        this.taxon = in.readParcelable(TaxonDTO.class.getClassLoader());
        this.usuarioId = (Long) in.readValue(Long.class.getClassLoader());
        this.tipoCaptura = in.readInt();
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
