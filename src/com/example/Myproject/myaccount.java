package com.example.Myproject;



import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.ListActivity;
import android.content.Intent;
import android.widget.Toast;

public class myaccount extends ListActivity  {
	String[] presidents = {
			"Ask Question/View Answer",	"Respond Answer","Suggestion Corner","Search Answers","Favorite Area","Message"
			
	};
	String value;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, presidents));
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getString("new_variable_name");
            
           
        }
    }
	
	
	public void onListItemClick(ListView parent, View v, int position, long id)
			{
			Toast.makeText(this,"You have selected " + presidents[position],
			Toast.LENGTH_SHORT).show();
			if( presidents[position]=="Ask Question/View Answer")
			{
				  //Intent activity passing username with this intent to Question
				 Intent i = new Intent(getApplicationContext(), ques.class);
	              
	                i.putExtra("new_variable_name",value);
	                startActivity(i);
			}
			else if(presidents[position]=="Respond Answer")
			{
				Intent i = new Intent(getApplicationContext(), second.class);
	              
                i.putExtra("new_variable_name",value);
                startActivity(i);
			}
			else if(presidents[position]=="Suggestion Corner")
			{
				Intent intent = new Intent(myaccount.this,suggestion.class);  
			    startActivity(intent );  
			}
			else if(presidents[position]=="Search Answers")
			{
				Intent intent = new Intent(myaccount.this,QAbank.class);  
			    startActivity(intent );  
			}
			else if(presidents[position]=="Message")
			{
				Intent intent = new Intent(myaccount.this,Message.class);  
			    startActivity(intent);  
			}
			else 
			{
				Intent intent = new Intent(myaccount.this,Addfavorite.class);  
			    startActivity(intent );  
			}
			
			
			
			           
			}
}

    

