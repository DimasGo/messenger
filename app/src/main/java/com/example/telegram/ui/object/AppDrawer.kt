package com.example.telegram.ui.`object`

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.telegram.R
import com.example.telegram.ui.fragment.*
import com.example.telegram.ui.utility.replaceFragment
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawer(val mainActivity: AppCompatActivity, val toolbar: androidx.appcompat.widget.Toolbar) {

    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mDrawerLayout: DrawerLayout

    fun createMenu(){
        createHeader()
        createDrawer()

        mDrawerLayout = mDrawer.drawerLayout
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
            .withActivity(mainActivity)
            .withToolbar(toolbar)
            .withActionBarDrawerToggle(true)
            .withSelectedItem(-1)
            .withAccountHeader(mHeader)
            .addDrawerItems(
                PrimaryDrawerItem().withIdentifier(100)
                    .withIconTintingEnabled(true)
                    .withName("Create Group")
                    .withIcon(R.drawable.ic_sharp_people_outline_24)
                    .withSelectable(false),

                PrimaryDrawerItem().withIdentifier(102)
                    .withIconTintingEnabled(true)
                    .withName("Create a secret chat")
                    .withIcon(R.drawable.ic_baseline_security_24)
                    .withSelectable(false),

                PrimaryDrawerItem().withIdentifier(103)
                    .withIconTintingEnabled(true)
                    .withName("Contacts")
                    .withIcon(R.drawable.ic_baseline_call_24)
                    .withSelectable(false),

                PrimaryDrawerItem().withIdentifier(104)
                    .withIconTintingEnabled(true)
                    .withName("Favorite")
                    .withIcon(R.drawable.ic_baseline_favorite_border_24)
                    .withSelectable(false),

                PrimaryDrawerItem().withIdentifier(105)
                    .withIconTintingEnabled(true)
                    .withName("Options")
                    .withIcon(R.drawable.ic_baseline_settings_24)
                    .withSelectable(false),
            ).withOnDrawerItemClickListener(object : Drawer.OnDrawerItemClickListener {
                override fun onItemClick(
                    view: View?,
                    position: Int,
                    drawerItem: IDrawerItem<*>
                ): Boolean {

                    when (position) {
                        1 -> mainActivity.replaceFragment(CreateGroupFragment())
                        2 -> mainActivity.replaceFragment(SecurityChatFragment())
                        3 -> mainActivity.replaceFragment(ContactsFragment())
                        4 -> mainActivity.replaceFragment(FavoriteFragment())
                        5 -> mainActivity.replaceFragment(OptionsFragment())
                    }
                    return false
                }
            }).build()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
            .withActivity(mainActivity)
            .withHeaderBackground(R.drawable.header)
            .addProfiles(
                ProfileDrawerItem().withName("Dima Goncharuk")
                    .withEmail("+38066298752")
            ).build()
    }

    fun disebleDrawer(){
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        toolbar.setNavigationOnClickListener{
            mainActivity.supportFragmentManager.popBackStack()
        }
    }

    fun enbledDrawer(){
        mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        toolbar.setNavigationOnClickListener{
            mDrawer.openDrawer()
        }
    }
}