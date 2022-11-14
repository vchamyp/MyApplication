package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class ImageViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_view)

        val bundle = intent.extras
        val url = bundle!!.getString("img")


        var image : ImageView =findViewById(R.id.imageView)

        Picasso.with(this).load(url).into(image);
    }
}