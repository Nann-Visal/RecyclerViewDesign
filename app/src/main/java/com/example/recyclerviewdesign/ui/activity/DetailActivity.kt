package com.example.recyclerviewdesign.ui.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewdesign.R
import com.example.recyclerviewdesign.data.Model

class DetailActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        val getData = intent.getParcelableExtra<Model>("android");
        if(getData != null){
            val detailTitle: TextView   = findViewById(R.id.detailTitle);
            val detailDesc:  TextView   = findViewById(R.id.detailDesc);
            val detailImage: ImageView  = findViewById(R.id.detailImage);

            detailTitle.text = getData.dataTitle;
            detailDesc.text  = getData.dataDesc;
            detailImage.setImageResource(getData.dataDetailImage);

        }
    }
}