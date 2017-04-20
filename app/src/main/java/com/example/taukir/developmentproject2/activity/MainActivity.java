package com.example.taukir.developmentproject2.activity;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.taukir.developmentproject2.fragment.BottomFragment;
import com.example.taukir.developmentproject2.fragment.ConnectFragment;
import com.example.taukir.developmentproject2.myinterface.DataCallback;
import com.example.taukir.developmentproject2.model.DataModel;
import com.example.taukir.developmentproject2.adapter.DrawerItemCustomAdapter;
import com.example.taukir.developmentproject2.fragment.FixturesFragment;
import com.example.taukir.developmentproject2.R;
import com.example.taukir.developmentproject2.fragment.TableFragment;
import com.example.taukir.developmentproject2.fragment.TopFragment;

public class MainActivity extends AppCompatActivity implements DataCallback {

    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    FrameLayout contentframe, firstfragment, secondfragment;
    TopFragment topFragment;
    BottomFragment bottomFragment;
    FragmentTransaction transaction;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        contentframe = (FrameLayout) findViewById(R.id.content_frame);
        firstfragment = (FrameLayout) findViewById(R.id.firstfragment);
        secondfragment = (FrameLayout) findViewById(R.id.secondfragment);
        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        contentframe.setVisibility(View.GONE);
        firstfragment.setVisibility(View.VISIBLE);
        secondfragment.setVisibility(View.VISIBLE);
        topFragment = new TopFragment();
        bottomFragment = new BottomFragment();
        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.add(R.id.firstfragment, topFragment, "Frag_Top_tag");
        transaction.add(R.id.secondfragment, bottomFragment, "Frag_Bottom_tag");
        transaction.commit();


        setupToolbar();

        DataModel[] drawerItem = new DataModel[3];

        drawerItem[0] = new DataModel(R.mipmap.ic_launcher, "Connect");
        drawerItem[1] = new DataModel(R.mipmap.ic_launcher, "Fixtures");
        drawerItem[2] = new DataModel(R.mipmap.ic_launcher, "Table");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    @Override
    public void callback(String value) {
        bottomFragment.displayValue(value);
    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new ConnectFragment();
                break;
            case 1:
                fragment = new FixturesFragment();
                break;
            case 2:
                fragment = new TableFragment();
                break;

            default:
                break;
        }

        if (fragment != null) {

            firstfragment.setVisibility(View.GONE);
            secondfragment.setVisibility(View.GONE);
            contentframe.setVisibility(View.VISIBLE);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.gridview:

                Intent intent=new Intent(this,GridViewActivity.class);
                this.startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int orientation = newConfig.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            linearLayout.setOrientation(LinearLayout.VERTICAL);

        } else {
            Log.d("other", "onConfigurationChanged: " + "other");

        }

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

}