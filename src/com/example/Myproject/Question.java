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


public class Question extends Activity {
	String[] Knowledgeare;
	String username;
	 String knownArea;
	Button post;
	EditText quesion;
	HttpPost httppost;
	StringBuffer buffer;
	HttpResponse response;
	HttpClient httpclient;
	List<NameValuePair> nameValuePairs;
	ProgressDialog dialog = null;
	EditText loginemail,question;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        TextView tview = (TextView)findViewById(R.id.loginlabel);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("new_variable_name");
            
            tview.setText(username);
        }
        Knowledgeare =getResources().getStringArray(R.array.knowledge);
        Spinner s1 = (Spinner) findViewById(R.id.spinner2);
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
         knownArea=Knowledgeare[index].toString();
       }
      
        public void onNothingSelected(AdapterView<?> arg0) { }
        });
        post = (Button)findViewById(R.id.Post); 
        quesion = (EditText)findViewById(R.id.questionfield);
post.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                dialog = ProgressDialog.show(Question.this, "", "Posting Wait...", true);
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
	            httppost= new HttpPost("http://10.0.2.2/sathesh/question.php");
	            nameValuePairs = new ArrayList<NameValuePair>(6);
	            nameValuePairs.add(new BasicNameValuePair("loginemail",username));  // $Edittext_value = $_POST['Edittext_value'];
	            nameValuePairs.add(new BasicNameValuePair("knowledge",knownArea));
	             nameValuePairs.add(new BasicNameValuePair("question",quesion.getText().toString().trim()));  // $Edittext_value = $_POST['Edittext_value'];
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
                
                     finish();
               
                 
                
                  
             }catch(Exception e){
                 dialog.dismiss();
                 System.out.println("Exception : " + e.getMessage());
             }
}
   
    }

