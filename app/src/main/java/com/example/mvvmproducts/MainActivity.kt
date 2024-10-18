package com.example.mvvmproducts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvvmproducts.AllProducts.View.AllProducts
import com.example.mvvmproducts.FavProducts.View.AllFavProducts

class MainActivity : AppCompatActivity() {

    private lateinit var  btnAllProd: Button
    private lateinit var  btnFavProd: Button
    private lateinit var  btnExit   : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAllProd = findViewById(R.id.btnAllProd)
        btnFavProd = findViewById(R.id.btnFavProd)
        btnExit = findViewById(R.id.btnExit)

        btnAllProd.setOnClickListener {
            val outIntent = Intent(this@MainActivity, AllProducts::class.java)
            startActivity(outIntent)
        }

        btnFavProd.setOnClickListener {
            val outIntent = Intent(this@MainActivity, AllFavProducts::class.java)
            startActivity(outIntent)
        }

        btnExit.setOnClickListener {
               finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}