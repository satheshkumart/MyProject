
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
    public class viewinbox extends Activity {
    	
    public ListView list;; 
    	EditText byear;   // To take birthyear as input from user
    	String name="";
    	String question;
    	String question1;
    	EditText questions;
    	Button postanswer; 
String test="";
     String[] strArray;
     TextView tv;      // TextView to show the result of MySQL query 
    //String test={"one","two","three"};
     String  username;
     String loginemail;
     String[] countries; 
    String[] arrayOfObjects;
    String[]  items =  {"Item 1", "Item 2", "Item 3"};
    //String name="h";
     String returnString; 
     String returnString1;// to store the result of MySQL query after decoding JSON
     
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

           
          setContentView(R.layout.viewinbox);
          TextView tview = (TextView)findViewById(R.id.loginlabel);
          TextView tview1 = (TextView)findViewById(R.id.answerlabel);
          TextView tview2 = (TextView)findViewById(R.id.answerlabel1);
          EditText editview=(EditText)findViewById(R.id.answerfield);
          Bundle extras = getIntent().getExtras();
          if (extras != null) {
              question = extras.getString("new_variable_name");
              question1 = extras.getString("new_variable_name1");
              tview.setText(question);
             // tview1.setText(question1);
          }
             
         // questions = (EditText)findViewById(R.id.answerfield);
         
         
         
          
    

            ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();


              


              // define the parameter


            postParameters.add(new BasicNameValuePair("name",


            		question));
            postParameters.add(new BasicNameValuePair("question",


            		question1));
          
              String response = null;


              


              // call executeHttpPost method passing necessary parameters 


              try {


         response = CustomHttpClient.executeHttpPost(


           //"http://129.107.187.135/CSE5324/jsonscript.php", // your ip address if using localhost server


           "http://10.0.2.2/sathesh/viewanswer.php",  // in case of a remote server


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


                             ", loginemail: "+json_data.getString("loginemail")+
                             ",answers: "+json_data.getString("answers")
                             




                     );


                     //Get an output to the screen


                     returnString = json_data.getString("answers");
                    // returnString1 = json_data.getString("answers");

                      loginemail=json_data.getString("loginemail");
                    // content.add(returnString);
                    // tview1.setText(returnString);
                    // int j=1;
                   
                    test="No";
                    	name=name+returnString+"\n"+"\n"+"\t"+"\t"+"\t"+"\t"+"\t"+"Answered By"+"\n"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+"\t"+loginemail+"\n"+"------------------"+"\n"+"\n"; 
                   
                   
             }
             //name="No Answer For This Question";
             
            	//tview1.setText(name);
            	//tview2.setText(loginemail);
            
             if(name!="")
             {
            	editview.setText(name);
             }
            
if(test=="")
{
	test="No";
	editview.setText(test);
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
            
                
     }
        
       
   
    	
      
    	


        
        



