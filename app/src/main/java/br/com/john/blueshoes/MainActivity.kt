package br.com.john.blueshoes

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import br.com.john.blueshoes.data.NavMenuItemsDataBase
import br.com.john.blueshoes.util.NavMenuItemDetails
import br.com.john.blueshoes.util.NavMenuItemDetailsLookup
import br.com.john.blueshoes.util.NavMenuItemKeyProvider
import br.com.john.blueshoes.view.NavMenuItemsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_menu.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var selectNavMenuItems: SelectionTracker<Long>
    var navMenuItems = NavMenuItemsDataBase(this).items

    lateinit var selectNavMenuItemsLogged: SelectionTracker<Long>
    var navMenuItemsLogged = NavMenuItemsDataBase(this).itemsLogged

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun initNavMenuItems() {
        with(rv_menu_items){
            setHasFixedSize( false )
            layoutManager = LinearLayoutManager( this@MainActivity)
            adapter = NavMenuItemsAdapter( navMenuItems )

            initNavMenuItemsSelection()
        }
    }

    private fun initNavMenuItemsSelection() {
        selectNavMenuItems = SelectionTracker.Builder<Long>(
                "id-selected-items",
                rv_menu_items,
                NavMenuItemKeyProvider( navMenuItems ),
                NavMenuItemDetailsLookup( rv_menu_items ),
                StorageStrategy.createLongStorage()
        ).build()

        selectNavMenuItems.addObserver(
                SelectObserverNavMenuItems (
                {
                    selectNavMenuItemsLogged.selection.filter {
                       item -> selectNavMenuItemsLogged.deselect(item)
                    }
                }
            )
        )

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItems
    }

    private fun initNavMenuItemsLogged() {
        with(rv_menu_items_logged){
            setHasFixedSize( false )
            layoutManager = LinearLayoutManager( this@MainActivity)
            adapter = NavMenuItemsAdapter( NavMenuItemsDataBase( this@MainActivity ).itemsLogged)
        }
        initNavMenuItemsLoggedSelection();
    }

    private fun initNavMenuItemsLoggedSelection() {
        selectNavMenuItemsLogged = SelectionTracker.Builder<Long>(
                "id-selected-items-logged",
                rv_menu_items_logged,
                NavMenuItemKeyProvider( navMenuItemsLogged ),
                NavMenuItemDetailsLookup( rv_menu_items_logged ),
                StorageStrategy.createLongStorage()
        ).build()

        selectNavMenuItemsLogged.addObserver(
                SelectObserverNavMenuItems (
                        {
                            selectNavMenuItems.selection.filter {
                                item -> selectNavMenuItems  .deselect(item)
                            }
                        }
                )
        )

        (rv_menu_items.adapter as NavMenuItemsAdapter).selectionTracker = selectNavMenuItemsLogged
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        selectNavMenuItems.onSaveInstanceState(outState!!)
        selectNavMenuItemsLogged.onSaveInstanceState(outState!!)

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    inner class SelectObserverNavMenuItems(
            val callbackRemoveSelection: ()->Unit ) : SelectionTracker.SelectionObserver<Long>(){

        /*
         * Método responsável por permitir que seja possível
         * disparar alguma ação de acordo com a mudança de
         * status de algum item em algum dos objetos de seleção
         * de itens de menu gaveta. Aqui vamos proceder com
         * alguma ação somente em caso de item obtendo seleção,
         * para item perdendo seleção não haverá processamento,
         * pois este status não importa na lógica de negócio
         * deste método.
         * */
        override fun onItemStateChanged(
                key: Long,
                selected: Boolean ) {
            super.onItemStateChanged( key, selected )

            /*
             * Padrão Cláusula de Guarda para não seguirmos
             * com o processamento em caso de item perdendo
             * seleção. O processamento posterior ao condicional
             * abaixo é somente para itens obtendo a seleção,
             * selected = true.
             * */
            if( !selected ){
                return
            }

            /*
             * Para garantir que somente um item de lista se
             * manterá selecionado, é preciso acessar o objeto
             * de seleção da lista de itens de usuário conectado
             * para então remover qualquer possível seleção
             * ainda presente nela. Sempre haverá somente um
             * item selecionado, mas infelizmente o método
             * clearSelection() não estava respondendo como
             * esperado, por isso a estratégia a seguir.
             * */
            callbackRemoveSelection()

            /*
             * TODO: Mudança de Fragment
             * */

            /*
             * Fechando o menu gaveta.
             * */
            drawer_layout.closeDrawer( GravityCompat.START )
        }
    }
}
