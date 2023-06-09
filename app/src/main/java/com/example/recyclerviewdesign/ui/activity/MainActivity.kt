package com.example.recyclerviewdesign.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.recyclerviewdesign.R
import com.example.recyclerviewdesign.data.Model
import com.example.recyclerviewdesign.ui.adapter.AdapterClass
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView;
    private lateinit var dataList: ArrayList<Model>;
    private lateinit var imageList: Array<Int>;
    private lateinit var descList: Array<String>;
    private lateinit var detailImagesList: Array<Int>;
    private lateinit var titleList: Array<String>;
    private lateinit var searchView: SearchView;
    private lateinit var myAdapter: AdapterClass;
    private lateinit var searchList: ArrayList<Model>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assign value of data
        imageList = arrayOf(
            R.drawable.ic_action_list,
            R.drawable.ic_action_checkboxes,
            R.drawable.ic_action_gallery,
            R.drawable.ic_action_toggle,
            R.drawable.ic_action_date,
            R.drawable.ic_action_rating,
            R.drawable.ic_action_time,
            R.drawable.ic_action_text,
            R.drawable.ic_action_edit,
            R.drawable.ic_action_camera
        );
        titleList = arrayOf(
            "ListView",
            "CheckBox",
            "Images",
            "Toggles",
            "Date",
            "Rating",
            "Time",
            "Text",
            "Edit",
            "Camera"
        );
        descList = arrayOf(
            getString(R.string.listview),
            getString(R.string.checkbox),
            getString(R.string.imageview),
            getString(R.string.toggle),
            getString(R.string.date),
            getString(R.string.rating),
            getString(R.string.time),
            getString(R.string.textview),
            getString(R.string.edit),
            getString(R.string.camera),
        );
        detailImagesList = arrayOf(
            R.drawable.list_detail,
            R.drawable.check_detail,
            R.drawable.image_detail,
            R.drawable.toggle_detail,
            R.drawable.date_detail,
            R.drawable.rating_detail,
            R.drawable.time_detail,
            R.drawable.text_detail,
            R.drawable.edit_detail,
            R.drawable.camera_detail
        );

        //Layout manager to define RecyclerView in activity-main-layout as the LinearLayout
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        searchView = findViewById(R.id.search);

        dataList = arrayListOf<Model>();
        searchList = arrayListOf<Model>();
        getData();

        //Set Listener to SearchView
        searchView.clearFocus();
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus();
                return true;
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear();
                val searchText = newText!!.toLowerCase(Locale.getDefault());
                if(searchText.isNotEmpty()){
                    dataList.forEach{
                        if(it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)){
                            searchList.add(it);
                        }

                    }
                    recyclerView.adapter!!.notifyDataSetChanged();
                }else{
                    searchList.clear();
                    searchList.addAll(dataList);
                    recyclerView.adapter!!.notifyDataSetChanged();
                }

                return false;
            }

        })
        myAdapter = AdapterClass(searchList);
        recyclerView.adapter = myAdapter;

        //Navigate from Main-Activity to Detail-Activity by click Recycler-View(item)
        myAdapter.onItemClick = {
            val intent = Intent(this,DetailActivity::class.java);
            intent.putExtra("android",it);
            startActivity(intent);
        }
    }

    //get data and then give to adapter
    private fun getData(){
        for(i in imageList.indices){
            val data = Model(imageList[i],titleList[i],descList[i],detailImagesList[i])
            dataList.add(data);
        }
        searchList.addAll(dataList);
        recyclerView.adapter = AdapterClass(searchList);
    }
}