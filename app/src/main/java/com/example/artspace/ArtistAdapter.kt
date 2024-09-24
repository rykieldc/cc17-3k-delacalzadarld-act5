package com.example.artspace

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ArtistAdapter(private val context: Context, private val artists: List<Artist>) : BaseAdapter() {
    override fun getCount(): Int {
        return artists.size
    }

    override fun getItem(position: Int): Any {
        return artists[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.row_data, parent, false)

        val artist = getItem(position) as Artist

        val imageView = view.findViewById<ImageView>(R.id.images)

        imageView.setImageResource(artist.imageResId)

        return view
    }
}