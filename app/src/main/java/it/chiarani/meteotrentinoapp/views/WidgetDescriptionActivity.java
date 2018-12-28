package it.chiarani.meteotrentinoapp.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import it.chiarani.meteotrentinoapp.R;
import it.chiarani.meteotrentinoapp.adapters.CartListAdapter;
import it.chiarani.meteotrentinoapp.databinding.ActivityWidgetDescriptionBinding;
import it.chiarani.meteotrentinoapp.helper.RecyclerItemTouchHelper;
import it.chiarani.meteotrentinoapp.models.CardRemovableInformation;

public class WidgetDescriptionActivity extends SampleActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener{

    // #region private fields
    private ActivityWidgetDescriptionBinding binding;
    private RecyclerView recyclerView, recyclerViewBlocked;
    private List<CardRemovableInformation> data, dataBlocked;
    private CartListAdapter mAdapter, mAdapterBlocked;
    private CoordinatorLayout coordinatorLayout;
    // #endregion

    @Override
    protected int getLayoutID() {
        return R.layout.activity_widget_description;
    }

    @Override
    protected void setActivityBinding() {
      //  binding = DataBindingUtil.setContentView(this, getLayoutID());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_description);

        View yourView = findViewById(R.id.activity_widget_scrollview);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (yourView != null) {
                yourView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }

        // set toolbar color
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#FFFFFF"));


        //----------


        recyclerView = findViewById(R.id.widget_recycler_view);
        recyclerViewBlocked= findViewById(R.id.widget_recycler_view_blocked);

        data = new ArrayList<>();
        data.add(
                new CardRemovableInformation(
                        "TIP: Prova un Widget!",
                        "Il widget ti permette di portare alcune funzionalità dell'app sulla schemrata principale del tuo telefono!",
                        R.drawable.ic_android_widget,
                        R.drawable.card_violet_background,
                        false,
                        null
                )
        );
        mAdapter = new CartListAdapter(this, data);


        dataBlocked = new ArrayList<>();
        dataBlocked.add(
                new CardRemovableInformation(
                        "Aggiungimi alla Home",
                        "Aggiungi un widget alla home per provare una o più funzionalità. Verrà aggionrato automaticamente dal sistema!",
                        R.drawable.ic_touch_me,
                        R.drawable.card_green_background,
                        true,
                        null
                )
        );

        mAdapterBlocked = new CartListAdapter(this, dataBlocked);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        RecyclerView.LayoutManager mLayoutManager1 = new LinearLayoutManager(getApplicationContext());
        recyclerViewBlocked.setLayoutManager(mLayoutManager1);
        recyclerViewBlocked.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBlocked.setAdapter(mAdapterBlocked);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT , this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        mAdapter.removeItem(viewHolder.getAdapterPosition());
    }
}
