package com.example.artspace

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var artistList: List<Artist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        gridView = findViewById(R.id.grid_view)
        artistList = getArtists()

        val adapter = ArtistAdapter(this, artistList) // Create your adapter
        gridView.adapter = adapter

        gridView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ArtworkItemActivity::class.java)
            intent.putExtra("artist_id", artistList[position].id) // Pass artist ID to next activity
            startActivity(intent)
        }
    }

    private fun getArtists(): List<Artist> {
        return listOf(
            Artist("1", "Leonardo da Vinci", R.drawable.da_vinci),
            Artist("2", "Michelangelo", R.drawable.michelangelo),
            Artist("3", "Pablo Picasso", R.drawable.picasso),
            Artist("4", "Vincent van Gogh", R.drawable.van_gogh)
        )
    }
}