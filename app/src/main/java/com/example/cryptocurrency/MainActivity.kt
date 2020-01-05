package com.example.cryptocurrency

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val obj=getPrice(price,high,close,Open,Volume,TradeID,Update,pieChart)
        if(item.itemId==R.id.BitCoin)
        {
            imageView.setImageResource(R.drawable.bitcoin)
            obj.priceData(this,"BTC","21000000")
            CoinName.text="BITCOIN"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"BTC")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"BTC")
            ExchangeCoin.text="BITCOIN = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"BTC")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.BitCoinCash)
        {
            imageView.setImageResource(R.drawable.ic_9_bitcoin_cash_circle)
            val x=Graph(LineChart,barChart)
            obj.priceData(this,"BCH","84000000")
            CoinName.text="BITCOIN CASH"
            x.LineGraph(this,"BCH")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"BHC")
            ExchangeCoin.text="BITCOINCASH = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"BCH")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.LiteCoin)
        {
            imageView.setImageResource(R.drawable.ic_iconfinder_ltc_alt_1175272)
            obj.priceData(this,"LTC","21000000")
            CoinName.text="LITE COIN"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"LTC")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"LTC")
            ExchangeCoin.text="LITECOIN = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"LTC")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.NEO)
        {
            imageView.setImageResource(R.drawable.ic_icons8_neo_cryptocurrency)
            obj.priceData(this,"NEO","100000000")
            CoinName.text="NEO"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"NEO")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"NEO")
            ExchangeCoin.text="NEO = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"NEO")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.IOTA)
        {
            imageView.setImageResource(R.drawable.ic_icons8_iota)
            obj.priceData(this,"miota","100000000")
            CoinName.text="IOTA"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"miota")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"miota")
            ExchangeCoin.text="IOTA = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"miota")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.Ethereum)
        {
            imageView.setImageResource(R.drawable.ic_ethereum)
            obj.priceData(this,"ETH","100000000")
            CoinName.text="ETHEREUM"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"ETH")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"ETH")
            ExchangeCoin.text="ETHEREUM = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"ETH")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.DogeCoin)
        {
            imageView.setImageResource(R.drawable.ic_dogecoin)
            obj.priceData(this,"doge","21000000000")
            CoinName.text="DOGE COIN"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"doge")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"doge")
            ExchangeCoin.text="DOGE COIN = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"doge")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.Ripple)
        {
            imageView.setImageResource(R.drawable.ic_icons8_ripple)
            obj.priceData(this,"XRP","38000000000")
            CoinName.text="RIPPLE"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"XRP")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"XRP")
            ExchangeCoin.text="RIPPLE = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"XRP")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else if(item.itemId==R.id.Cardano)
        {
            imageView.setImageResource(R.drawable.ic_cardano)
            obj.priceData(this,"ADA","45000000000")
            CoinName.text="CARDANO"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"ADA")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"ADA")
            ExchangeCoin.text="CARDANO = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"ADA")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        else if(item.itemId==R.id.Stellar)
        {
            imageView.setImageResource(R.drawable.ic_stellar)
            obj.priceData(this,"XLM","104323820467")
            CoinName.text="STELLAR"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"XLM")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"XLM")
            ExchangeCoin.text="STELLAR = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"XLM")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        else if(item.itemId==R.id.Monero)
        {
            imageView.setImageResource(R.drawable.ic_monero)
            obj.priceData(this,"XMR","19000000")
            CoinName.text="MONERO"
            val x=Graph(LineChart,barChart)
            x.LineGraph(this,"XMR")
            val ex=Exchange(value,textToBeDisplayed)
            ex.coin_exchange(this,"XMR")
            ExchangeCoin.text="MONERO = USD "
            send.setOnClickListener {
                Exchange(value,textToBeDisplayed).coin_exchange(this,"XMR")
            }
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        return true

    }
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        //val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send
            ), drawerLayout
        )
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
        setupActionBarWithNavController(navController, appBarConfiguration)
        //
        getPrice(price,high,close,Open,Volume,TradeID,Update,pieChart).priceData(this,"BTC","21000000")
        imageView.setImageResource(R.drawable.bitcoin)
        val x=Graph(LineChart,barChart)
        x.LineGraph(this,"BTC")
        Exchange(value,textToBeDisplayed).coin_exchange(this,"BTC")
        ExchangeCoin.text="BTC = USD "
        send.setOnClickListener {
            Exchange(value,textToBeDisplayed).coin_exchange(this,"BTC")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.NEWS)
        {
            val i =Intent(this,Main3Activity::class.java)
            startActivity(i)
        }
        else if(item.itemId==R.id.showAllCoin)
        {
            val i=Intent(this,Main2Activity::class.java)
            startActivity(i)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else {
            super.onBackPressed()
        }
    }
}
