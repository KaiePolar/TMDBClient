package com.a.tmdbclient.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.a.tmdbclient.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout mDrawer;
    private List<MenuItem> mHeaderList = new ArrayList<>();

    private NavController mNavController;
    private MenuListViewAdapter mExpandableListAdapter;
    private ExpandableListView mExpandableListView;
    private HashMap<MenuItem, List<MenuItem>> mChildList = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_movies, R.id.nav_shows,
                R.id.nav_peoples)
                .setDrawerLayout(mDrawer)
                .build();
        mExpandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, mNavController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void prepareMenuData() {
        MenuItem mainMenuItem = new MenuItem("Main", true, R.id.nav_home);
        MenuItem moviesMenuItem = new MenuItem("Movies", true, true);
        MenuItem showsMenuItem = new MenuItem("TV Shows", true, true);
        MenuItem peoplesMenuItem = new MenuItem("Peoples", true, true);

        mHeaderList.add(mainMenuItem);
        mHeaderList.add(moviesMenuItem);
        mHeaderList.add(showsMenuItem);
        mHeaderList.add(peoplesMenuItem);

        List<MenuItem> childModelsList = new ArrayList<>();
        childModelsList.add(new MenuItem("Popular", false, R.id.nav_movies));
        childModelsList.add(new MenuItem("Best", false, R.id.nav_movies));
        childModelsList.add(new MenuItem("Waiting", false, R.id.nav_movies));
        childModelsList.add(new MenuItem("Watched now", false, R.id.nav_movies));
        mChildList.put(moviesMenuItem, childModelsList);

        childModelsList = new ArrayList<>();
        childModelsList.add(new MenuItem("Popular", false, R.id.nav_shows));
        childModelsList.add(new MenuItem("Best", false, R.id.nav_shows));
        childModelsList.add(new MenuItem("TV", false, R.id.nav_shows));
        childModelsList.add(new MenuItem("Today", false, R.id.nav_shows));
        mChildList.put(showsMenuItem, childModelsList);

        childModelsList = new ArrayList<>();
        childModelsList.add(new MenuItem("Popular", false, R.id.nav_peoples));
        mChildList.put(peoplesMenuItem, childModelsList);
    }

    private void populateExpandableList() {
        mExpandableListAdapter = new MenuListViewAdapter(this, mHeaderList, mChildList);
        mExpandableListView.setAdapter(mExpandableListAdapter);

        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (!mExpandableListAdapter.getGroup(groupPosition).hasChildren()) {
                    mNavController.navigate(mExpandableListAdapter.getGroup(groupPosition).getResId());
                    mDrawer.closeDrawers();
                }
                return false;
            }
        });

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mNavController.navigate(mExpandableListAdapter.getChild(groupPosition, childPosition).getResId());
                mDrawer.closeDrawers();
                return false;
            }
        });
    }
}
