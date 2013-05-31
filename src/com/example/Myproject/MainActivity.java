package com.example.Myproject;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.widget.*;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {
Button btn;
Button myaccount;
EditText username,password;
TextView tv;
HttpPost httppost;
StringBuffer buffer;
HttpResponse response;
HttpClient httpclient;
List<NameValuePair> nameValuePairs;
ProgressDialog dialog = null;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.Newuser);
        btn.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,signup.class);
				
				startActivity(intent);
			}
		});
        myaccount=(Button)findViewById(R.id.login);
       
        username = (EditText)findViewById(R.id.loginid);
        password= (EditText)findViewById(R.id.password);
        myaccount.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                dialog = ProgressDialog.show(MainActivity.this, "",
                        "Validating user...", true);
                 new Thread(new Runnable() {
                        public void run() {
                            login();                         
                        }
                      }).start();              
            }
        });
        
        
    }
    void login(){
        try{           
              
            httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://10.0.2.2/sathesh/login.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username",username.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
            nameValuePairs.add(new BasicNameValuePair("password",password.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response=httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
                   // tv.setText("Response from PHP : " + response);
                    dialog.dismiss();
                }
            });
            if(response.equalsIgnoreCase("Welcome")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(MainActivity.this,"Login Success", Toast.LENGTH_SHORT).show();
                        
						
                       
                        
                    
                    }

					
                });
               //Intent activity passing username with this intent to myaccount
                Intent i = new Intent(getApplicationContext(), myaccount.class);
                String value= username.getText().toString();
                i.putExtra("new_variable_name",value);
                startActivity(i);
            }else{
                showAlert();               
            }
        }catch(Exception e){
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }

    }
    public void showAlert(){
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("Enter Valid EmailId.") 
                       .setCancelable(false)
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                           }
                       });                    
                AlertDialog alert = builder.create();
                alert.show();              
            }
        });
    }
  
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}