package com.example.tictoctic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int turn=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String text="button";
        for(int i=1; i<10; i++){
           String bText=text.concat(String.valueOf(i));
            //search the id of the text
            int resID=getResources().getIdentifier(bText,"id",getPackageName());
            Button btn=(Button)this.findViewById(resID);
            btn.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View v) {
      Button b=(Button)v;
        TextView t=(TextView) this.findViewById(R.id.textView);
      if(b.getText()!="") {
          if (turn % 2 == 1) {
              b.setBackgroundResource(R.drawable.x);
              b.setText("");
              t.setText("Now is X, circle is next");


          } else {
              b.setBackgroundResource(R.drawable.c);
              b.setText("");
              t.setText("Now is circle, X is next");
          }
          turn++;
      }
      else{
          Toast.makeText(getApplicationContext(),"Please select another button",Toast.LENGTH_LONG).show();

      }
    }
}