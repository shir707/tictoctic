package com.example.tictoctic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Looper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int turn=1;
    int [][] mat={{2,2,2,},{2,2,2},{2,2,2}};
    boolean wins=false;
    char winC;
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
        Button btReset=(Button) this.findViewById(R.id.main_reset);
        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    public void checkWin(){
        for(int i=0; i<3; i++){
            if(mat[i][0]==0&&mat[i][1]==0&&mat[i][2]==0){
                wins=true;
                winC= 'o';
                drawLine(i);
            }
            else if(mat[0][i]==0&&mat[1][i]==0&&mat[2][i]==0){
                wins=true;
                winC= 'o';
                drawCol(i);
            }
            else if(mat[0][0]==0&&mat[1][1]==0&&mat[2][2]==0){
                wins=true;
                winC= 'o';
                drawLtR();
            }else if(mat[0][2]==0&&mat[1][1]==0&&mat[2][0]==0){
                wins=true;
                winC= 'o';
                drawRtL();
            }
            else if(mat[i][0]==1&&mat[i][1]==1&&mat[i][2]==1){
                wins=true;
                winC= 'x';
                drawLine(i);
            }
            else if(mat[0][i]==1&&mat[1][i]==1&&mat[2][i]==1){
                wins=true;
                winC= 'x';
                drawCol(i);
            }
            else if(mat[0][0]==1&&mat[1][1]==1&&mat[2][2]==1){
                wins=true;
                winC= 'x';
                drawLtR();
            }
            else if(mat[0][2]==1&&mat[1][1]==1&&mat[2][0]==1){
                wins=true;
                winC= 'x';
                drawRtL();
            }
        }
    }
    void drawLine(int i) {
        int j=i*3+1;
        int lim=j+3;
        String text="button";
        for(;j<lim;j++){
            String bText=text.concat(String.valueOf(j));
            int resID = getResources().getIdentifier(bText, "id",getPackageName());
            Button bt=(Button) this.findViewById(resID);
            bt.setText("➙");
            bt.setTextSize(60);
        }
    }
    public  void drawCol(int i) {
        i+=1;
        String text="button";
        for( int time=0;time<3;i+=3,time++){
            String bText=text.concat(String.valueOf(i));
            int resID = getResources().getIdentifier(bText, "id",getPackageName());
            Button bt=(Button) this.findViewById(resID);
            bt.setText("↟");
            bt.setTextSize(50);
        }
    }
    public void drawLtR() {
        Button bt;
        bt=(Button) this.findViewById(R.id.button1);
        bt.setText("➘"  );
        bt.setTextSize(50);
        bt=(Button) this.findViewById(R.id.button5);
        bt.setText("➘"  );
        bt.setTextSize(50);
        bt=(Button) this.findViewById(R.id.button9);
        bt.setText("➘"  );
        bt.setTextSize(50);
    }
    public void drawRtL() {
        Button bt;
        bt=(Button) this.findViewById(R.id.button3);
        bt.setText("➚"  );
        bt.setTextSize(50);
        bt=(Button) this.findViewById(R.id.button5);
        bt.setText("➚"  );
        bt.setTextSize(50);
        bt=(Button) this.findViewById(R.id.button7);
        bt.setText("➚"  );
        bt.setTextSize(50);
    }

    public void resetGame(){
        String text="button";
        TextView t=(TextView) this.findViewById(R.id.textView);
        t.setText("X is first, good luck :) ");
        for(int i=1; i<10; i++){
            String bText=text.concat(String.valueOf(i));
            //search the id of the text
            int resID=getResources().getIdentifier(bText,"id",getPackageName());
            Button btn=(Button)this.findViewById(resID);
            btn.setText("Click");
            btn.setBackgroundColor(0);
            btn.setTextSize(14);
            turn=1;
            for(int z=0;z<3; z++){
                for(int q=0; q<3;q++){
                    mat[q][z]=2;
                }
            }
            wins=false;
            winC= 's';


        }

    }

    @Override
    public void onClick(View v) {
      Button b=(Button)v;
        TextView t=(TextView) this.findViewById(R.id.textView);
      if(b.getText()!="") {
          switch(b.getId()){
              case R.id.button1:
                  mat[0][0]=turn%2;
                  break;
              case R.id.button2:
                  mat[0][1]=turn%2;
                  break;
              case R.id.button3:
                  mat[0][2]=turn%2;
                  break;
              case R.id.button4:
                  mat[1][0]=turn%2;
                  break;
              case R.id.button5:
                  mat[1][1]=turn%2;
                  break;
              case R.id.button6:
                  mat[1][2]=turn%2;
                  break;
              case R.id.button7:
                  mat[2][0]=turn%2;
                  break;
              case R.id.button8:
                  mat[2][1]=turn%2;
                  break;
              case R.id.button9:
                  mat[2][2]=turn%2;
                  break;
          }
          if (turn % 2 == 1) { //now it's x turn
              b.setBackgroundResource(R.drawable.x);
              b.setText("");
              t.setText("Now is X, circle is next");


          } else {
              b.setBackgroundResource(R.drawable.c);
              b.setText("");
              t.setText("Now is circle, X is next");
          }
          turn++;
          if(turn>3){
              checkWin();
              if(wins){
                  t.setText("The winner is: "+ winC + " :) new round in 2 seconds");
                  new android.os.Handler(Looper.getMainLooper()).postDelayed(
                          new Runnable() {
                              public void run() {
                                  resetGame();
                              }
                          },
                          2000);

              }
              else if(turn==9 && !wins){
                  Toast.makeText(getApplicationContext(), "It's tie!", Toast.LENGTH_LONG).show();
                  new android.os.Handler(Looper.getMainLooper()).postDelayed(
                          new Runnable() {
                              public void run() {
                                  resetGame();
                              }
                          },
                          2000);
              }
          }
      }
      else{
          Toast.makeText(getApplicationContext(),"Please select another button",Toast.LENGTH_LONG).show();


      }
    }
}