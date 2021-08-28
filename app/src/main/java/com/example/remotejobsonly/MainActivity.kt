package com.example.remotejobsonly

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.Wave
import org.json.JSONArray

class MainActivity : AppCompatActivity(),OnItemClick{
    lateinit var progressBar: ProgressBar
    var url:String=""
    private lateinit var mAdapter:Rvadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview=findViewById<RecyclerView>(R.id.rvone)
        recyclerview.layoutManager=LinearLayoutManager(this)
        val searchkeyword=intent.getStringExtra("keyword")
        url="https://remotewx.com/api?q=$searchkeyword"
        request()
        mAdapter=Rvadapter(this)
        recyclerview.adapter=mAdapter
         progressBar=findViewById<View>(R.id.spin_kit) as ProgressBar
        val doubleBounce: Sprite = Wave()
        progressBar.indeterminateDrawable = doubleBounce
    }

    fun request(){
        val loadingscrn=findViewById<ImageView>(R.id.loadingscrn)
        loadingscrn.visibility= View.VISIBLE
         // Add a request (in this example, called stringRequest) to your RequestQueue.
        // Formulate the request and handle the response.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val jobArray: JSONArray = response.getJSONArray("jobs")
                val jobListt=ArrayList<jobdetails>()
                for (i in 0 until jobArray.length()) {
                    val currentjob =jobArray.getJSONObject(i)
                    val nj:TextView=findViewById(R.id.tvjobsno)
                    nj.text=jobArray.length().toString()

                    val joburl=currentjob.getString("url")
                    val logourl = currentjob.getString("logo")
                    val companyname = currentjob.getString("company")
                    val jobtitle = currentjob.getString("title")
                    val regionn=currentjob.getString("region")
                    val jobtype=currentjob.getString("type")
                    val jobDescription=currentjob.getString("description")
                    val job=jobdetails(joburl,logourl,companyname,jobtitle, jobtype, regionn,jobDescription)
                    jobListt.add(job)
                }
                mAdapter.updatejob(jobListt)
                loadingscrn.visibility=View.GONE
                progressBar.visibility=View.GONE
            },
            { error ->
                Toast.makeText(this, "!Error loading please try again", Toast.LENGTH_SHORT).show()
                loadingscrn.visibility=View.GONE
                progressBar.visibility=View.GONE
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun itemClick(jobdetail: jobdetails) {
       val intent=Intent(this,JobDetailActivity::class.java)
        intent.apply {

            putExtra("companyName",jobdetail.companyname)
            putExtra("title",jobdetail.jobtitle)
            putExtra("logoUrl",jobdetail.imageurl)
            putExtra("detail",jobdetail.jobDescription)
            putExtra("joburl",jobdetail.url)
        }
         startActivity(intent)
    }
}  