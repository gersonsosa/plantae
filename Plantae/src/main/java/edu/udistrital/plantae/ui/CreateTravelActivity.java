package edu.udistrital.plantae.ui;

import android.animation.TimeInterpolator;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import android.support.v7.widget.Toolbar;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.recoleccion.*;
import edu.udistrital.plantae.persistencia.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Gerson Sosa on 4/11/14.
 */
public class CreateTravelActivity extends ActionBarActivity implements ProjectListFragment.OnProjectSelectedListener, SecondaryCollectorsListFragment.OnSecondaryCollectorSelectedListener {
    private ViajeDao viajeDao;
    private ProyectoDao proyectoDao;
    private ViajeColectorSecundarioDao viajeColectorSecundarioDao;
    private ColectorPrincipal colectorPrincipal;
    private Viaje viaje;
    private EditText nombreView;
    private TextView proyectoTextView;
    private final Rect mTmpRect = new Rect();
    private int halfHeight;
    private FrameLayout editModeContainer, editFragmentContainer;
    private RelativeLayout formContainer, viajeProyectoCard, viajeNombreCard, colectoresSecundariosContainer;
    private TextView listSecondaryCollectors;
    private List<ColectorSecundario> colectoresSecundarios;

    private TimeInterpolator ANIMATION_INTERPOLATOR = new DecelerateInterpolator();
    private int ANIMATION_DURATION = 300;
    private Fragment fragmentProjectList;
    private Fragment fragmentSecondaryCollectosList;
    private Proyecto proyecto;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view = getLayoutInflater().inflate(R.layout.activity_create_travel, null);
        view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                v.removeOnLayoutChangeListener(this);
                halfHeight = view.getHeight() / 2;
                editModeContainer.setTranslationY(halfHeight);
                editModeContainer.setAlpha(0f);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(0, colectoresSecundariosContainer.getHeight(), 0, 0);
                editFragmentContainer.setLayoutParams(layoutParams);
            }
        });

        setContentView(view);

        /**
         * Initialize Toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_navigation_back);
        setSupportActionBar(toolbar);

        retrieveViews();

        DaoSession daoSession = DataBaseHelper.getDataBaseHelper(getApplicationContext()).getDaoSession();
        viajeDao = daoSession.getViajeDao();

        colectoresSecundarios = new ArrayList<ColectorSecundario>();

        // Load viaje id if exists in extras
        Long id = getIntent().getExtras().getLong("viaje", 0l);
        if (id != 0l) {
            viaje = viajeDao.loadDeep(id);
            setTitle(R.string.action_edit_travel);
            nombreView.setText(viaje.getNombre());
            proyectoTextView.setText(viaje.getProyecto().getNombre());
            for (ViajeColectorSecundario viajeColectorSecundario:viaje.getColectoresSecundarios()) {
                colectoresSecundarios.add(viajeColectorSecundario.getColectorSecundario());
            }
            listSecondaryCollectors.setText(nombresColectoresSecundarios());
        }else {
            proyectoTextView.setText(getString(R.string.default_project_name));
        }

        proyectoDao = daoSession.getProyectoDao();
        viajeColectorSecundarioDao = daoSession.getViajeColectorSecundarioDao();
        // Cargar el colector principal
        colectorPrincipal = daoSession.getColectorPrincipalDao().load(getIntent().getLongExtra("colectorPrincipal", 0l));


        fragmentProjectList = new ProjectListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("colectorPrincipal", colectorPrincipal.getId());
        fragmentProjectList.setArguments(bundle);

        viajeProyectoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fragmentProjectList.isAdded()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.edit_mode_fragment_container, fragmentProjectList, "projectList").commit();
                    focusOn(viajeProyectoCard, formContainer, true);
                    fadeOutToBottom(viajeNombreCard, true);
                    fadeOutToBottom(colectoresSecundariosContainer, true);
                    slideInToTop(editModeContainer, true);
                    editModeContainer.setVisibility(View.VISIBLE);
                }
            }
        });

        fragmentSecondaryCollectosList = new SecondaryCollectorsListFragment();
        ((SecondaryCollectorsListFragment) fragmentSecondaryCollectosList).updateSelectedSecondaryCollectors(getSelectedSecondaryCollectors(daoSession));

        colectoresSecundariosContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fragmentSecondaryCollectosList.isAdded()) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.edit_mode_fragment_container, fragmentSecondaryCollectosList, "secondaryCollectorsList").commit();
                    focusOn(colectoresSecundariosContainer, formContainer, true);
                    slideInToTop(editModeContainer, true);
                    editModeContainer.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void retrieveViews() {
        formContainer = (RelativeLayout) findViewById(R.id.form_container);
        viajeProyectoCard = (RelativeLayout) findViewById(R.id.viaje_proyecto_card);
        proyectoTextView = (TextView) findViewById(R.id.viaje_proyecto);
        viajeNombreCard = (RelativeLayout) findViewById(R.id.viaje_nombre_card);
        nombreView = (EditText) findViewById(R.id.viaje_nombre);
        colectoresSecundariosContainer = (RelativeLayout) findViewById(R.id.colectores_secundarios);
        editModeContainer = (FrameLayout) findViewById(R.id.project_selection_mode);
        editFragmentContainer = (FrameLayout) findViewById(R.id.edit_mode_fragment_container);
        listSecondaryCollectors = (TextView) findViewById(R.id.secondary_collectors_normal_mode);
    }

    @Override
    public void onBackPressed() {
        // check if in edit mode reverse animations
        if (editModeContainer.getVisibility() == View.VISIBLE) {
            if (fragmentProjectList.isVisible()) {
                ((ProjectListFragment) fragmentProjectList).destroyActionMode();
            }else if (fragmentSecondaryCollectosList.isVisible()) {
                ((SecondaryCollectorsListFragment) fragmentSecondaryCollectosList).destroyActionMode();
            }
        }else{
            supportFinishAfterTransition();
        }
    }

    private void hideProjectList() {
        slideOutToBottom(editModeContainer, true);
        fadeInToTop(viajeNombreCard, true);
        fadeInToTop(colectoresSecundariosContainer, true);
        unFocus(viajeProyectoCard, formContainer, true);
        editModeContainer.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().remove(fragmentProjectList).commit();
        invalidateOptionsMenu();
    }

    private void hideSecondaryCollectorList() {
        slideOutToBottom(editModeContainer, true);
        unFocus(colectoresSecundariosContainer, formContainer, true);
        editModeContainer.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().remove(fragmentSecondaryCollectosList).commit();
        invalidateOptionsMenu();
    }

    @Override
    public void onSecondaryCollectorAdded(ColectorSecundario colectorSecundario) {
        colectoresSecundarios.add(colectorSecundario);
        listSecondaryCollectors.setText(nombresColectoresSecundarios());
    }

    private Long[] getSelectedSecondaryCollectors(DaoSession daoSession) {
        List<ColectorSecundario> secondaryCollectors = daoSession.getColectorSecundarioDao().loadAll();
        final Long[] itemsSelected = new Long[secondaryCollectors.size()];
        int pos = 0;
        for (ColectorSecundario colectorSecundario:secondaryCollectors) {
            if (colectoresSecundarios.contains(colectorSecundario)) {
                itemsSelected[pos] = colectorSecundario.getId();
            }
            pos++;
        }
        return itemsSelected;
    }

    private String nombresColectoresSecundarios() {
        StringBuilder stringBuilder = new StringBuilder();
        if (colectoresSecundarios.size() == 0) {
            return getString(R.string.no_secondary_collectors_selected);
        }else if (colectoresSecundarios.size() > 0) {
            Iterator<ColectorSecundario> iterator = colectoresSecundarios.iterator();
            for (int i = 0; i < colectoresSecundarios.size(); i++) {
                ColectorSecundario colectorSecundario = iterator.next();
                if (i > 0) {
                    if (i == colectoresSecundarios.size() - 1) {
                        stringBuilder.append(" ").append(getString(R.string.and)).append(" ");
                    }else {
                        stringBuilder.append(getString(R.string.comma)).append(" ");
                    }
                }
                stringBuilder.append(colectorSecundario.getPersona().getNombres()).append(" ").append(colectorSecundario.getPersona().getApellidos());
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public void onSecondaryCollectorRemoved(ColectorSecundario colectorSecundario) {
        colectoresSecundarios.remove(colectorSecundario);
        listSecondaryCollectors.setText(nombresColectoresSecundarios());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SecondaryCollectorsListFragment.SECONDARY_COLLECTOR_CREATION_REQUEST) {
            fragmentSecondaryCollectosList.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActionModeFinished() {
        if (fragmentProjectList.isVisible()) {
            hideProjectList();
        }else if (fragmentSecondaryCollectosList.isVisible()) {
            hideSecondaryCollectorList();
        }
    }

    private void focusOn(View v, View movableView, boolean animated) {

        v.getDrawingRect(mTmpRect);
        formContainer.offsetDescendantRectToMyCoords(v, mTmpRect);

        movableView.animate().
                translationY(-mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void unFocus(View v, View movableView, boolean animated) {
        movableView.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(movableView)).
                start();
    }

    private void fadeOutToBottom(View v, boolean animated) {
        v.animate().
                translationYBy(halfHeight).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void fadeInToTop(View v, boolean animated) {
        v.animate().
                translationYBy(-halfHeight).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(v)).
                start();
    }

    private void slideInToTop(View v, boolean animated) {
        v.animate().
                translationY(0).
                alpha(1).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void slideOutToBottom(View v, boolean animated) {
        v.animate().
                translationY(halfHeight * 2).
                alpha(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setListener(new LayerEnablingAnimatorListener(v)).
                setInterpolator(ANIMATION_INTERPOLATOR);
    }

    private void stickTo(View v, View viewToStickTo, boolean animated) {
        v.getDrawingRect(mTmpRect);
        formContainer.offsetDescendantRectToMyCoords(v, mTmpRect);

        v.animate().
                translationY(viewToStickTo.getHeight() - mTmpRect.top).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                start();
    }

    private void unstickFrom(View v, View viewToStickTo, boolean animated) {
        v.animate().
                translationY(0).
                setDuration(animated ? ANIMATION_DURATION : 0).
                setInterpolator(ANIMATION_INTERPOLATOR).
                setListener(new LayerEnablingAnimatorListener(viewToStickTo)).
                start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (editModeContainer.getVisibility() == View.GONE || editModeContainer.getVisibility() == View.INVISIBLE) {
            menu.clear();
            getMenuInflater().inflate(R.menu.create, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveTravel();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveTravel() {
        String nombre = nombreView.getText().toString().trim();
        if (proyectoTextView.getText().equals(getString(R.string.default_project_name))){
            proyecto = new Proyecto(getString(R.string.default_project_name));
            proyectoDao.insert(proyecto);
        }
        if (viaje == null) {
            createTravel(nombre, proyecto, colectoresSecundarios);
        }else{
            updateTravel(nombre, proyecto, colectoresSecundarios);
        }
        setResult(RESULT_OK);
    }

    private void createTravel(String nombre, Proyecto proyecto, List<ColectorSecundario> colectorSecundarioList){
        List<Viaje> viajes = colectorPrincipal.getViajes();
        viaje = new Viaje(colectorPrincipal);
        viaje.setNombre(nombre);
        viaje.setProyecto(proyecto);
        viajeDao.insert(viaje);
        viajes.add(viaje);
        for (ColectorSecundario colectorSecundario:colectorSecundarioList) {
            ViajeColectorSecundario viajeColectorSecundario = new ViajeColectorSecundario();
            viajeColectorSecundario.setColectorSecundario(colectorSecundario);
            viajeColectorSecundario.setViaje(viaje);
            viajeColectorSecundarioDao.insert(viajeColectorSecundario);
        }
    }

    private void updateTravel(String nombre, Proyecto proyecto, List<ColectorSecundario> colectorSecundarioList) {
        viaje.setNombre(nombre);
        viaje.setProyecto(proyecto);
        viajeDao.update(viaje);
        for (ColectorSecundario colectorSecundario:colectorSecundarioList) {
            ViajeColectorSecundario viajeColectorSecundario = new ViajeColectorSecundario();
            viajeColectorSecundario.setColectorSecundario(colectorSecundario);
            viajeColectorSecundario.setViaje(viaje);
            viajeColectorSecundarioDao.insert(viajeColectorSecundario);
        }
    }

    private void updateProjectSelected(Proyecto proyecto) {
        this.proyecto = proyecto;
        proyectoTextView.setText(proyecto.getNombre());
    }

    @Override
    public void onProjectSelected(Proyecto proyecto) {
        updateProjectSelected(proyecto);
    }
}