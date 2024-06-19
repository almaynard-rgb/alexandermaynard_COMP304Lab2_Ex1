package com.example.alexandermaynard_comp304lab2_ex1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        createMenuOptions(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return menuItemSelected(item)
    }

    private fun createMenuOptions(menu: Menu) {
        menu.add(0,0,0, "Apartment")
        menu.add(0,1,1, "Detached Home")
        menu.add(0,2,2, "Semi-Detached Home")
        menu.add(0,3,3, "Condominium Apartment")
        menu.add(0,4,4, "Townhouse")
    }

    private fun menuItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            0 -> {
                val nextScreenIntent = Intent(this, Apartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            1 -> {
                val nextScreenIntent = Intent(this, DetachedHome::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            2 -> {
                val nextScreenIntent = Intent(this, SemiDetachedHome::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            3 -> {
                val nextScreenIntent = Intent(this, CondominiumApartment::class.java)
                startActivity(nextScreenIntent)
                return true
            }
            4 -> {
                val nextScreenIntent = Intent(this, Townhouse::class.java)
                startActivity(nextScreenIntent)
                return true
            }
        }
        return false
    }
}