
    package com.example.Myproject;

    import android.os.Bundle;
    import android.annotation.SuppressLint;
    import android.app.Activity;
    import android.app.ListActivity;
    import android.view.KeyEvent;
    import android.view.Menu;
    import android.view.View.OnClickListener;
    import android.view.View.OnKeyListener;

    import java.util.ArrayList;
    import java.util.List;

    import org.apache.http.NameValuePair;
    import org.apache.http.message.BasicNameValuePair;
    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;
    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;
    import android.os.StrictMode;

    import com.example.Myproject.CustomHttpClient;
    import android.util.Log;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.AdapterView.OnItemClickListener;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ListView;
    import android.widget.TextView;
    import android.widget.Toast;
    public class Answer extends Activity {
    	
    public ListView list;; 
    	EditText byear;   // To take birthyear as input from user

    	String question;
    	EditText questions;
    	Button postanswer; 

     String[] strArray;
     TextView tv;      // TextView to show the result of MySQL query 
    //String test={"one","two","three"};
     String  username;
     
     String[] countries; 
    String[] arrayOfObjects;
    String[]  items =  {"Item 1", "Item 2", "Item 3"};
    //String name="h";
     String returnString;   // to store the result of MySQL query after decoding JSON
     
     String answer;
     
     String[] namesArr;

        /** Called when the activity is first created. */


     List<String> content = new ArrayList<String>();
        @SuppressLint({ "NewApi", "NewApi", "NewApi", "NewApi", "NewApi", "NewApi" })
    	public void onCreate(Bundle savedInstanceState) {


         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()


      .detectDiskReads().detectDiskWrites().detectNetwork() 

      .penaltyLog().build());


            super.onCreate(savedInstanceState);

           
          setContentView(R.layout.answer);
          TextView tview = (TextView)findViewById(R.id.loginlabel);
          Bundle extras = getIntent().getExtras();
          if (extras != null) {
              question = extras.getString("new_variable_name");
              username=extras.getString("new_variable_name1");
              tview.setText(question);
          }
             
          questions = (EditText)findViewById(R.id.answerfield);
         
          postanswer = (Button)findViewById(R.id.postanswer); 
          postanswer.setOnClickListener(new View.OnClickListener(){        
        	  
            


           public void onClick(View v) {
         
         
         
          
    

            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();


              


              // define the parameter

            postParameters.add(new BasicNameValuePair("username",


            		username));
            postParameters.add(new BasicNameValuePair("name",


            		question));
            postParameters.add(new BasicNameValuePair("answer",


            		questions.getText().toString()));


              String response = null;


              


              // call executeHttpPost method passing necessary parameters 


              try {


         response = CustomHttpClient.executeHttpPost(


           //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server


           "http://10.0.2.2/sathesh/answer.php",  // in case of a remote server


           postParameters);


         


         // store the result returned by PHP script that runs MySQL query


         String result = response.toString();  


                  


          //parse json data


          


        }


              catch (Exception e) {


         Log.e("log_tag","Error in http connection!!" + e.toString());     


        }


             }         


           });
          
           
            
                
     }
        
       
   
    	}
      
    	


        
        



