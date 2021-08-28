package com.example.remotejobsonly

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide

class JobDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)
        val ivClogo= findViewById<ImageView>(R.id.logoimage2)
        val tvCname=findViewById<TextView>(R.id.tvCname)
        val tvTitle=findViewById<TextView>(R.id.tvJtitle)
        val tvdeescription=findViewById<TextView>(R.id.tvDescription)
        val btn=findViewById<Button>(R.id.btnapply)
        val logoUrl= intent.getStringExtra("logoUrl").toString()
        val applyUrl=intent.getStringExtra("joburl").toString()
            Glide.with(this).load(logoUrl).into(ivClogo)
            tvCname.text=intent.getStringExtra("companyName").toString()
            tvTitle.text=intent.getStringExtra("title").toString()
                var desstr=intent.getStringExtra("detail")
                 desstr= desstr?.replace("<br>","")
                desstr=desstr?.replace("</br>","")
        desstr=desstr?.replace("<div>","")
        desstr=desstr?.replace("</div>","")
        desstr=desstr?.replace("<li>","")
        desstr=desstr?.replace("</li>","")
        desstr=desstr?.replace("<h3>","")
        desstr=desstr?.replace("</h3>","")
        desstr=desstr?.replace("<ul>","")
        desstr=desstr?.replace("</ul>","")
        desstr=desstr?.replace("<strong>","")
        desstr=desstr?.replace("</strong>","")
        tvdeescription.text=desstr
        btn.setOnClickListener {
            val  url =intent.getStringExtra("joburl")
            val builder: CustomTabsIntent.Builder  = CustomTabsIntent.Builder();
            val customTabsIntent: CustomTabsIntent = builder.build();
            customTabsIntent.launchUrl(this, Uri.parse(url));
        }
    }
}