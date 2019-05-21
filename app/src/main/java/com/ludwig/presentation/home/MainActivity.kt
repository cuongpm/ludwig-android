package com.ludwig.presentation.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.ludwig.R
import com.ludwig.presentation.base.BaseActivity
import com.ludwig.util.fragment.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUI()

        replaceFragment(R.id.fragment_content, fragmentFactory.createHomeFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initUI() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_avatar_b)
            setDisplayHomeAsUpEnabled(true)
        }

        nav_drawer.getHeaderView(0).findViewById<View>(R.id.layout_profile).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_home).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_premium).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_history).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_contact).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_business).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_about).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_how_to_use).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_blog).setOnClickListener(onDrawerMenuItemClicked)
        nav_drawer.getHeaderView(0).findViewById<View>(R.id.tv_press_kit).setOnClickListener(onDrawerMenuItemClicked)
    }

    private val onDrawerMenuItemClicked = View.OnClickListener {
        drawer_layout.closeDrawer(GravityCompat.START)
        Toast.makeText(this, "This feature is in development...", Toast.LENGTH_SHORT).show()
    }
}
