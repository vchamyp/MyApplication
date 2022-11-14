package com.example.myapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: MyAdapter

    // search view variable
    lateinit var searchview: SearchView
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // local json reading with Gson
        val json: String = jsonFileRead()
        val gson = Gson()
        val model = gson.fromJson(json, Model::class.java)

        // from local json to model class
        var arrlist: ArrayList<Data> = model.data

        recyclerView = findViewById(R.id.recycleview)

        //list status set with GridLayoutManager or linearlayoutmanager
        layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = GridLayoutManager.VERTICAL

        recyclerView.layoutManager = layoutManager
        adapter = MyAdapter(this, arrlist)
        recyclerView.isNestedScrollingEnabled = false

        // reset adapter class
        setAdapter()

    }

    fun setAdapter(){
        recyclerView.adapter = adapter;
    }

    fun jsonFileRead(): String {

        var json: String? = null
        try {
            val inputStreams: InputStream = assets.open("model.json")
            json = inputStreams.bufferedReader().use { it.readText() }
            return json
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return "empty"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_v, menu)

         val item : MenuItem? = menu?.findItem(R.id.search_v)
        searchview  = item?.actionView as SearchView

         searchview.queryHint="search..."
         // below line is to call set on query text listener method.
         searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
             android.widget.SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(p0: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(msg: String): Boolean {
                 // inside on query text change method we are
                 // calling a method to fi lter our recycler view.
                 adapter.filter.filter(msg)
                 return false
             }
         })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_toggle) {

            setAdapter()

            //switch to grid or list
            switchLayout()
            switchIcon(item)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun switchLayout() {
        if (layoutManager.getSpanCount() == Constant.LISTCOUNTONE) {

            adapter.view_TypeOne = Constant.LISTCOUNTTHREE
            layoutManager.setSpanCount(Constant.LISTCOUNTTHREE)
        } else {
            adapter.view_TypeOne = Constant.LISTCOUNTONE
            layoutManager.setSpanCount(Constant.LISTCOUNTONE)
        }
        adapter.notifyItemRangeChanged(0, adapter.getItemCount())

    }

    private fun switchIcon(item: MenuItem) {
        if (layoutManager.getSpanCount() === adapter.view_TypeOne) {
            item.icon = resources.getDrawable(R.drawable.icgrid_view_24)
        } else {
            item.icon = resources.getDrawable(R.drawable.ic_view_list_24)
        }
    }
}