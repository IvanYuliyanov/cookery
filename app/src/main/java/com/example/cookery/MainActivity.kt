package com.example.cookery

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.cookery.base.BaseActivity
import dagger.android.support.HasSupportFragmentInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.cookery.base.BaseFragment
import com.example.cookery.ui.receipts.ReceiptsFragment
import com.example.cookery.ui.search.SearchFragment
import com.example.cookery.ui.send.SendFragment
import dagger.android.AndroidInjection


class MainActivity : BaseActivity(), HasSupportFragmentInjector, BaseFragment.OnFragmentInteractionListener, NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    private lateinit var mAppBarConfiguration: AppBarConfiguration
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        mDrawerLayout = findViewById(R.id.drawer_layout)
        mNavView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_receipts, R.id.nav_search, R.id.nav_favourites,
                R.id.nav_shopping_list, R.id.nav_campaign, R.id.nav_stores
            ), mDrawerLayout
        )

        val mDrawerToggle = ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close)
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        setupActionBarWithNavController(navController, mAppBarConfiguration)
        mNavView.setupWithNavController(navController)
        mNavView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onFragmentInteraction(fragment: BaseFragment) {
        setFragment(fragment)
    }

    private fun setFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
        transaction.replace(R.id.nav_host_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()

        if(fragment is NavHostFragment || fragment is ReceiptsFragment) {
            mNavView.menu.findItem(R.id.nav_receipts).isChecked = true
        } else if (fragment is SearchFragment) {
            mNavView.menu.findItem(R.id.nav_search).isChecked = true
        }
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id : Int = item.itemId
        item.isChecked = true

        if (id == R.id.nav_receipts)
            setReceiptsFragment()
/*        if (id == R.id.nav_search)
            setSearchFragment()*/

        if(mDrawerLayout.isShown)
            mDrawerLayout.closeDrawers()

        return false
    }

    private fun setReceiptsFragment() {
        if(getCurrentFragment() !is NavHostFragment && getCurrentFragment() !is ReceiptsFragment)
            setFragment(ReceiptsFragment())
    }

    private fun setSearchFragment() {
        if(getCurrentFragment() !is SearchFragment)
            setFragment(SearchFragment())
    }

    override fun onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START))
            mDrawerLayout.closeDrawer(GravityCompat.START)
        else if(getCurrentFragment() is SearchFragment || getCurrentFragment() is SendFragment)
            setReceiptsFragment()
        else if(getCurrentFragment() is ReceiptsFragment)
            finish()
        else
            super.onBackPressed()
    }
}
