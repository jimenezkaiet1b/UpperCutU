package com.example.uppercutu

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.uppercutu.fragments.HomeFragment
import com.example.uppercutu.fragments.VotarFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

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
//        initDrawerLayout()


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = null
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
     * Infla el menú de opciones en la barra de herramientas.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    /**
     * Inicializa la barra de navegación inferior y los fragmentos asociados.
     */
    private fun initBottomNavigationViewAndFragments() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val fragmentHome = HomeFragment()
        val votarFragment = VotarFragment()
//        val fragmentExplore = ExploreFragment()
//        val fragmentUser = UserFragment()

        replaceFragment(fragmentHome)

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
            val newFragment = when (menuItem.itemId) {
                R.id.page_home -> fragmentHome
                R.id.page_votar -> votarFragment
//                R.id.page_explore -> fragmentExplore
//                R.id.page_user -> fragmentUser
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
}