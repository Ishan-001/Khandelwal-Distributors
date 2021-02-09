package com.khandelwal.distributors

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var requests: ArrayList<Request>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requests=ArrayList();

        val db = FirebaseDatabase.getInstance()
        val ref=db.getReference("requests/all")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (request in snapshot.children) {
                    requests.add(request.getValue(Request::class.java)!!)
                }
                if(requests.size==0) findViewById<TextView>(R.id.alert).visibility=View.VISIBLE
                else findViewById<TextView>(R.id.alert).visibility=View.GONE

                val adapter = Adapter(baseContext, requests)
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(baseContext)

                findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE

            }

            override fun onCancelled(error: DatabaseError) {
                Snackbar.make(
                    findViewById(R.id.main_layout),
                    "Connection error",
                    Snackbar.LENGTH_LONG
                )
            }
        })

        findViewById<ImageView>(R.id.add).setOnClickListener{
            val addFragment=AddComplaint()
            addFragment.show(supportFragmentManager, addFragment::class.java.simpleName)
        }

    }


    /*
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

     */

}