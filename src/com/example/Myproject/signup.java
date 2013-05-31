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

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class signup extends Activity {
	String[] Knowledgeare;
	String know,qus;
	String[] presidents;
	Button btn1,signup;
	TextView tv;
	EditText name,loginemail,password,answer;
	Spinner knowledge,question;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
       // btn1=(Button)findViewById(R.id.signup);
       // btn1.setOnClickListener(new View.OnClickListener() {
			
		//	public void onClick(View v) {
				// TODO Auto-generated method stub
		// finish();
		//	}
		//});
        
        Knowledgeare =getResources().getStringArray(R.array.knowledge);
        Spinner s1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        android.R.layout.simple_spinner_item, Knowledgeare);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(new OnItemSelectedListener()
        {
        
        public void onItemSelected(AdapterView<?> arg0,View arg1, int arg2, long arg3)
        {
        int index = arg0.getSelectedItemPosition();
        Toast.makeText(getBaseContext(),"You have selected item : " +Knowledgeare[index],
    			Toast.LENGTH_SHORT).show();
        know=Knowledgeare[index];
       }
      
        public void onNothingSelected(AdapterView<?> arg0) { }
        });
        
        presidents =getResources().getStringArray(R.array.security);
        Spinner s2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, presidents);
        s2.setAdapter(adapter1);
        s2.setOnItemSelectedListener(new OnItemSelectedListener()
        {
        	public void onItemSelected(AdapterView<?> arg0,
        			View arg1, int arg2, long arg3)
        			{
        			int index = arg0.getSelectedItemPosition();
        			Toast.makeText(getBaseContext(),"You have selected item : " + presidents[index],
        			Toast.LENGTH_SHORT).show();
        			qus= presidents[index];
        			}
        			
        			public void onNothingSelected(AdapterView<?> arg0) { }
        			});
           
        
        signup = (Button)findViewById(R.id.signup); 
        name = (EditText)findViewById(R.id.loginid);
        loginemail= (EditText)findViewById(R.id.emailid);
        password = (EditText)findViewById(R.id.loginpassword);
        knowledge=(Spinner) findViewById(R.id.spinner1);
        question=(Spinner) findViewById(R.id.spinner2);
        answer=(EditText)findViewById(R.id.answer);
        signup.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                dialog = ProgressDialog.show(signup.this, "", "Please Wait...", true);
                 new Thread(new Runnable() {
                        public void run() {
                            login();                         
                        }

													 
                      }).start();              
            }
        });
        
    }
     void login() {
		 try{           
             
	            httpclient=new DefaultHttpClient();
	            httppost= new HttpPost("http://10.0.2.2/sathesh/registration.php");
	            nameValuePairs = new ArrayList<NameValuePair>(6);
	            nameValuePairs.add(new BasicNameValuePair("username",name.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
	            nameValuePairs.add(new BasicNameValuePair("loginemail",loginemail.getText().toString().trim()));
	             nameValuePairs.add(new BasicNameValuePair("password",password.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
                nameValuePairs.add(new BasicNameValuePair("knowledge",know.toString().trim()));
                nameValuePairs.add(new BasicNameValuePair("question",qus.toString().trim()));
                nameValuePairs.add(new BasicNameValuePair("answer",answer.getText().toString().trim()));// $Edittext_value = $_POST['Edittext_value'];
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
                 if(response.toString()==" ")
                 {
                     runOnUiThread(new Runnable() {
                         public void run() {
                             Toast.makeText(signup.this,"Login Success", Toast.LENGTH_SHORT).show();
                         }
                     });
                     Intent i = new Intent(getApplicationContext(), MainActivity.class);
                     
                     startActivity(i);
                     
                     //startActivity(new Intent(MainActivity.this, UserPage.class));
                 }else
                 {
                     showAlert();               
                 }
                  
             }catch(Exception e){
                 dialog.dismiss();
                 System.out.println("Exception : " + e.getMessage());
             }
         }
         public void showAlert(){
             signup.this.runOnUiThread(new Runnable() {
                 public void run() {
                     AlertDialog.Builder builder = new AlertDialog.Builder(signup.this);
                     builder.setTitle("Login Error.");
                     builder.setMessage("User not Found.") 
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
         }