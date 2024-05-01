package com.example.uppercutu

import com.example.uppercutu.fragments.VotarFragment
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.uppercutu.fragments.HomeFragment
import com.example.uppercutu.fragments.PerfilFragment
import com.example.uppercutu.fragments.RankingFragment
import com.example.uppercutu.fragments.UserFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setup()
    }

    /**
     * Configura la actividad.
     */
    private fun setup() {
        initBottomNavigationViewAndFragments()
        initDrawerLayout()


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    /**
     * Gestiona la selección de elementos en el menú de opciones.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbarMenu_themeMenu -> {
                val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                    // Cambiar al modo claro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                } else {
                    // Cambiar al modo oscuro
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate() // Reiniciar la actividad para aplicar el cambio de tema
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }



    /**
     * Inicializa la barra de navegación inferior y los fragmentos asociados.
     */
    private fun initBottomNavigationViewAndFragments() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragmentHome = HomeFragment()
        val votarFragment = VotarFragment()
        val fragmentRanking = RankingFragment()
        val fragmentUser = UserFragment()

        replaceFragment(fragmentHome)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            val newFragment = when (menuItem.itemId) {
                R.id.page_home -> fragmentHome
                R.id.page_votar -> votarFragment
                R.id.page_ranking -> fragmentRanking

                else -> fragmentHome
            }

            if (currentFragment != null && currentFragment != newFragment) {
                replaceFragment(newFragment)
            }

            true
        }
    }



    /**
     * Reemplaza el fragmento actual por uno nuevo.
     *
     * @param fragment El nuevo fragmento a mostrar.
     * @param tag La etiqueta opcional para identificar el fragmento.
     */
    private fun replaceFragment(fragment: Fragment, tag : String = "") {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()

        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        if (tag.isNotEmpty()) {
            transaction.replace(R.id.fragmentContainerView, fragment,tag)
            transaction.addToBackStack(null)
        } else {
            transaction.replace(R.id.fragmentContainerView, fragment)
        }


        transaction.commit()
    }


    /**
     * Inicializa el diseño del menú lateral.
     */
    private fun initDrawerLayout() {
        val drawerLayout : DrawerLayout = findViewById(R.id.main_drawerLayout)
        val drawerLayoutToggleBtn: ImageButton = findViewById(R.id.main_drawerLayoutToggle)

        drawerLayoutToggleBtn.setOnClickListener {
            drawerLayout.open()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    isEnabled = false
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        })

        val drawerNavigationView: NavigationView = findViewById(R.id.main_drawerNavigationView)

        val drawerHeader = drawerNavigationView.getHeaderView(0)

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        drawerHeader.findViewById<TextView>(R.id.drawerHeader_usernameTextView).text = prefs.getString("username", "")
        drawerHeader.findViewById<TextView>(R.id.drawerHeader_userEmailTextView).text = prefs.getString("email", "")

        drawerNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.drawerMenu_myLists -> {
                    val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                    if (currentFragment?.tag != "LISTS_FRAGMENT") {
                        replaceFragment(PerfilFragment(),"LISTS_FRAGMENT")
                    }
                    drawerLayout.close()
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }


}