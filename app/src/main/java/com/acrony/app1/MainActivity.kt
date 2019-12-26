package com.acrony.app1

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

val i=0;


class MainActivity : AppCompatActivity() {

    private val sharedPrefFile="AndroidClass"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val ButtonSubmit=findViewById(R.id.btnLogin) as Button

        val ButtonView=findViewById<Button>(R.id.btnView)

        val ButtonClear=findViewById<Button>(R.id.btnClear)

        val etEmail=findViewById(R.id.etEmail) as EditText

        val etPassword=findViewById(R.id.etPassword) as EditText

        val SpnCountries=findViewById<Spinner>(R.id.btnSpinner)

        val rbgp=findViewById<RadioGroup>(R.id.rbgroup)

        val radiogroup=findViewById(R.id.rbgroup) as RadioGroup

        val Age=findViewById<EditText>(R.id.etAge)

        radiogroup.setBackgroundColor(Color.CYAN)

        rbgp.setOnCheckedChangeListener({group,checkedId->

            val rd:RadioButton=findViewById(checkedId)
            Toast.makeText(this,"${rd.text}",Toast.LENGTH_LONG).show()

        })





        var switch=findViewById<Switch>(R.id.switch1)

        switch.setOnCheckedChangeListener({_,isChecked->

            if(isChecked){
                Toast.makeText(this,"Switch On",Toast.LENGTH_LONG).show()
                switchname.setBackgroundColor(Color.RED)

                val webView=findViewById<WebView>(R.id.webview)

                webView.webViewClient = MyWebViewClient(this)

                val URLFromUser=findViewById<EditText>(R.id.etURL)

                //  webView.loadUrl("https://www.google.com/")

                webView.loadUrl(URLFromUser.text.toString())
            }
            else
            {
                Toast.makeText(this,"Switch Off",Toast.LENGTH_LONG).show()
                switchname.setBackgroundColor(Color.BLUE)
            }


        })




        // access the items of the list
        val languages = resources.getStringArray(R.array.Languages)

        // access the spinner
        val spinner = findViewById<Spinner>(R.id.btnSpinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " +
                                "" + languages[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        // val etPassword1=findViewById<EditText>(R.id.etPassword)

        val sharepref:SharedPreferences=this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)



        ButtonSubmit.setOnClickListener(){

            intent= Intent(this,Main3Activity::class.java)
            intent.putExtra("ID",3)

            val age=etAge.text.toString()

            //Toast.makeText(applicationContext,age,Toast.LENGTH_LONG).show();
            intent.putExtra("Age",age)
            intent.putExtra("Name",etEmail.text.toString())
            startActivity(intent)

            val Email:String=etEmail.text.toString()
            val editor:SharedPreferences.Editor=sharepref.edit()
            editor.putString("EmailofUser_key",Email)
            editor.apply()
            editor.commit()

            //Toast.makeText(applicationContext,"HI",Toast.LENGTH_LONG).show();

        }

            ButtonView.setOnClickListener(){

                val shareEmail=sharepref.getString("EmailofUser_key","You have not entered any details")
                Toast.makeText(applicationContext,shareEmail,Toast.LENGTH_LONG).show();
            }

        ButtonClear.setOnClickListener(){
            val editor=sharepref.edit()

            editor.clear()
            editor.apply()
        }


    }



    class MyWebViewClient internal constructor(private val activity: Activity) : WebViewClient()
    {

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            val url: String = request?.url.toString();
            view?.loadUrl(url)
            return true
        }

        override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
            webView.loadUrl(url)
            return true
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
            Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
        }
    }

}
