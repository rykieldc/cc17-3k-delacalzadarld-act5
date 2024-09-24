package com.example.artspace

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ArtworkItemActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var backButton: Button
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button
    private lateinit var titleText: TextView
    private lateinit var artistText: TextView
    private lateinit var yearMediumText: TextView
    private lateinit var artistHeaderText: TextView
    private lateinit var homeButton: TextView
    private var currentArtworkIndex = 0
    private lateinit var artworks: List<Artwork>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_artwork_item)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the action bar to display the back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Initialize views
        imageView = findViewById(R.id.image_view)
        previousButton = findViewById(R.id.button_previous)
        nextButton = findViewById(R.id.button_next)
        backButton = findViewById(R.id.back_button)
        titleText = findViewById(R.id.artwork_title)
        artistText = findViewById(R.id.artist_name)
        yearMediumText = findViewById(R.id.artwork_year_medium)
        artistHeaderText = findViewById(R.id.artist_header_title)
        homeButton = findViewById(R.id.home_button)

        // Set up artworks based on the artist ID passed from the previous activity
        val artistId = intent.getStringExtra("artist_id")
        artworks = getArtworksForArtist(artistId)

        // Set the artist's header title
        artistHeaderText.text = when (artistId) {
            "1" -> "Leonardo Da Vinci's\nArtworks"
            "2" -> "Michelangelo's\nArtworks"
            "3" -> "Pablo Picasso's\nArtworks"
            "4" -> "Vincent van Gogh's\nArtworks"
            else -> ""
        }

        // Display the first artwork
        displayArtwork(currentArtworkIndex)

        // Previous button click listener
        previousButton.setOnClickListener {
            if (currentArtworkIndex > 0) {
                currentArtworkIndex--
                displayArtwork(currentArtworkIndex)
            }
        }

        // Next button click listener
        nextButton.setOnClickListener {
            if (currentArtworkIndex < artworks.size - 1) {
                currentArtworkIndex++
                displayArtwork(currentArtworkIndex)
            }
        }

        // Custom back button click listener
        backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Navigate back to the previous activity
        }

        homeButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Handle back button presses using OnBackPressedDispatcher
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish() // Close this activity
            }
        })
    }

    // Display the artwork at the specified index
    private fun displayArtwork(index: Int) {
        val artwork = artworks[index]
        imageView.setImageResource(artwork.imageResId)
        titleText.text = artwork.title
        artistText.text = artwork.artistName
        yearMediumText.text = artwork.yearMedium

        previousButton.isEnabled = currentArtworkIndex > 0
        nextButton.isEnabled = currentArtworkIndex < artworks.size - 1
    }

    // Provide a list of artworks based on the artist's ID
    private fun getArtworksForArtist(artistId: String?): List<Artwork> {
        return when (artistId) {
            "1" -> listOf(
                Artwork("Lady with an Emrine", "Leonardo da Vinci", "Year: c. 1489–91\nMedium: Oil on panel", R.drawable.dv_lady_with_an_ermine),
                Artwork("Vitruvian Man", "Leonardo da Vinci", "Year: c. 1490\nMedium: Pen and ink on paper", R.drawable.dv_vitruvian_man),
                Artwork("Salvator Mundi", "Leonardo da Vinci", "Year: c. 1500\nMedium: Oil on walnut panel", R.drawable.dv_salvator_mundi),
                Artwork("La Scapigliata", "Leonardo da Vinci", "Year: 1500-10\nMedium: Oil, earth, and white lead pigments\non poplar wood", R.drawable.dv_la_scapigliata),
                Artwork("Mona Lisa", "Leonardo da Vinci", "Year: c. 1503–19\nMedium: Oil on wood panel", R.drawable.dv_mona_lisa)
            )
            "2" -> listOf(
                Artwork("Pietà", "Michelangelo", "Year: 1499\nMedium: Marble", R.drawable.m_pieta),
                Artwork("David", "Michelangelo", "Year: 1501–1504\nMedium: Marble", R.drawable.m_david),
                Artwork("Sistine Chapel Ceiling Detail", "Michelangelo", "Year: 1508–12\nMedium: Fresco", R.drawable.m_sistine_ceiling),
                Artwork("Tomb of Giuliano de' Medici", "Michelangelo", "Year: 1520–34\nMedium: Marble", R.drawable.m_tomb),
                Artwork("Last Judgment", "Michelangelo", "Year: 1536–41\nMedium: Fresco", R.drawable.m_last_judgment)

            )

            "3" -> listOf(
                Artwork("Les Demoiselles d'Avignon", "Pablo Picasso", "Year: 1907\nMedium: Oil on canvas", R.drawable.p_avignon),
                Artwork("Girl with Mandolin", "Pablo Picasso", "Year: 1910\nMedium: Oil on canvas", R.drawable.p_girl_wtih_mandolin),
                Artwork("Still Life with Chair Caning", "Pablo Picasso", "Year: 1912\nMedium: Oil on canvas, \npasted oilcloth, and rope", R.drawable.p_chair_caning),
                Artwork("Girl Before A Mirror", "Pablo Picasso", "Year: 1932\nMedium: Oil on canvas", R.drawable.p_girl_before_a_mirror),
                Artwork("The Weeping Woman", "Pablo Picasso", "Year: 1937\nMedium: Oil on canvas", R.drawable.p_the_weeping_woman)
            )

            "4" -> listOf(
                Artwork("The Potato Eaters", "Vincent van Gogh", "Year: 1885\nMedium: Oil on Canvas", R.drawable.vg_potato_eaters),
                Artwork("The Bedroom", "Vincent van Gogh", "Year: 1889\nMedium: Oil on Canvas", R.drawable.vg_bedroom),
                Artwork("A Wheatfield, with Cypresses", "Vincent van Gogh", "Year: 1889\nMedium: Oil on Canvas", R.drawable.vg_wheatfield),
                Artwork("Self-Portrait", "Vincent van Gogh", "Year: 1889\nMedium: Oil on Canvas", R.drawable.vg_self_portrait),
                Artwork("The Starry Night", "Vincent van Gogh", "Year: 1889\nMedium: Oil on Canvas", R.drawable.vg_starry)
            )

            else -> emptyList()
        }
    }
}
