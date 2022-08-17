package com.example.project
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.*
import android.widget.*
import android.net.Uri
import com.example.project.BEST_APP.Companion.APP_ID
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.view.MenuInflater as MenuInflater1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ap: Button = findViewById(R.id.button)
        var bes: TextView = findViewById(R.id.textView)
        var flag: String = "Search"
        var maxi: Int= 0
        var name :String= ""
        var cate = arrayOf("Search", "Games", "Food")
        var spin: Spinner = findViewById(R.id.spinner)
        spin.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cate)
        val cols = listOf<String>(BEST_APP._ID,BEST_APP.NAME,
        BEST_APP.RANK).toTypedArray()

        ap.setOnClickListener {
             if (flag == "Search") {
                 val uri = contentResolver.query(BEST_APP.CONTENT_URI , cols , "category LIKE ?", Array(1){ "Search"  },cols[0] )
                 while(uri?.moveToNext()==true){
                     if(maxi< uri.getInt(2)){
                        maxi=uri.getInt(2)
                         name=uri.getString(1)
                     }
                 }
 var a :TextView =findViewById(R.id.textView)
                     a.text=name

            } else if (flag == "Games") {
                 val uri = contentResolver.query(BEST_APP.CONTENT_URI , cols , "category LIKE ?", Array(1){ "Games"  },cols[0] )
                 while(uri?.moveToNext()==true){
                     if(maxi< uri.getInt(2)){
                         maxi=uri.getInt(2)
                         name=uri.getString(1)
                     }
                 }
                 var a :TextView =findViewById(R.id.textView)
                 a.text=name


             } else{
                 val uri = contentResolver.query(BEST_APP.CONTENT_URI , cols , "category LIKE ?", Array(1){ "Food"  },cols[0] )
                 while(uri?.moveToNext()==true){
                     if(maxi< uri.getInt(2)){
                         maxi=uri.getInt(2)
                         name=uri.getString(1)
                     }
                 }
                 var a :TextView =findViewById(R.id.textView)
                 a.text=name


        }


        }
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                flag = cate.get(p2)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.settings, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        var dialog_var = insartdia()
        var dialog_var1 = deletedia()
        var dialog_var2 = updatedia()
        when(item.itemId){
            R.id.item1 -> dialog_var.show(supportFragmentManager, "Custom Dialog")
            R.id.item2 -> dialog_var1.show(supportFragmentManager, "Custom Dialog")
            R.id.item3 -> dialog_var2.show(supportFragmentManager, "Custom Dialog")

        }
        return true;
    }
    fun inserts(values : ContentValues?){
        val uri = contentResolver.insert(BEST_APP.CONTENT_URI, values)

    }
    fun delete(appName: String){
        val uri =contentResolver.delete(BEST_APP.CONTENT_URI,"_id like ?",Array(1){"%$appName%"})

    }
fun up(values : ContentValues? , appName: String){
    val uri =contentResolver.update(BEST_APP.CONTENT_URI ,values , "_id like ?" , Array(1){"$appName"})

}


}






