
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
    public class Message extends Activity implements OnItemClickListener{
    	
    public ListView list;; 
    	EditText byear;   // To take birthyear as input from user
    	String[] Knowledgeare;
    	String username;
    	 String knownArea;
    	Button post;
    	EditText quesion;
    	String question;
    	EditText questions;
    	Button postanswer; 
        String[] strArray;
     TextView tv;      // TextView to show the result of MySQL query 
       
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

           
          setContentView(R.layout.message);
       //   TextView tview = (TextView)findViewById(R.id.loginlabel);
          Bundle extras = getIntent().getExtras();
          if (extras != null) {
              username = extras.getString("new_variable_name");
              
             // tview.setText(username);
          }
         

        
        
         
          post = (Button)findViewById(R.id.message); 
         
          post.setOnClickListener(new View.OnClickListener(){        
        	  
            


           public void onClick(View v) {
         
         Intent intent=new Intent(Message.this,SendMessage.class);
         startActivity(intent);
           }
          });
          
    

          list=(ListView) findViewById(R.id.listview);
          list.setAdapter(new ArrayAdapter<String>(this,
                  android.R.layout.simple_list_item_1, content));
          list.setOnItemClickListener(this);
    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();


          


          // define the parameter


        postParameters.add(new BasicNameValuePair("name",


     username));


          String response = null;

     


          // call executeHttpPost method passing necessary parameters 


          try {


     response = CustomHttpClient.executeHttpPost(


       //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server


       "http://10.0.2.2/sathesh/question1.php",  // in case of a remote server


       postParameters);


     


     // store the result returned by PHP script that runs MySQL query


     String result = response.toString();  


              


      //parse json data


         try{


                 returnString = "";


           JSONArray jArray = new JSONArray(result);


                 for(int i=0;i<jArray.length();i++){


                         JSONObject json_data = jArray.getJSONObject(i);


                         Log.i("log_tag",


                                 ", questions: "+json_data.getString("questions")


                                

                         );


                         //Get an output to the screen


                         returnString =  json_data.getString("questions");
                         content.add(returnString);

                 }


         }


         catch(JSONException e){


                 Log.e("log_tag", "Error parsing data "+e.toString());


         }


     




         catch(Exception e){


          Log.e("log_tag","Error in Display!" + e.toString());;          


         }  


    }


          catch (Exception e) {


     Log.e("log_tag","Error in http connection!!" + e.toString());     


    }
 
          
          
          
          
           
            
                
     }
        public void onItemClick(AdapterView<?> a, View v, int position, long id) {
       	 String s=  (String) ((TextView) v).getText();     	   
       	
               Intent i = new Intent(Message.this, viewinbox.class);
               i.putExtra("new_variable_name",s);
               i.putExtra("new_variable_name1",username);
               startActivity(i);
             
              //Toast.makeText(this, "You pressed the first item in the list",
                  //  Toast.LENGTH_SHORT).show();
         
       }
   		
        
       
   
    	}
      
    	


        
        



