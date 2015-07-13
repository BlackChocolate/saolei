package com.example.sukurax.saolei2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by sukurax on 15/7/8.
 */
public class RankActivity extends Activity{
    public String[] s=new String[5];
    public int[] sss=new int[5];
    public int num;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rank_layout);
        ListView scoreList=(ListView)findViewById(R.id.list_view);

        SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
        num= pref.getInt("score", 0);


        SharedPreferences pref2 = getSharedPreferences("data2", MODE_PRIVATE);
        String b= pref2.getString("score2", "");
        if(b.length()>0){
            String[] c=b.split(";");
            for(int i=0;i<c.length;i++){
                sss[i]=Integer.parseInt(c[i]);
            }
        }

        if(num==sss[0]||num==sss[1]||num==sss[2]||num==sss[3]||num==sss[4]){
            num=0;
        }

        int[] na = new int[6];
        for(int i=0;i<5;i++){
            na[i]=sss[i];
        }
        na[5]=num;


        for(int i=0; i<na.length; i++) {
            for(int j=i+1; j<=na.length-1; j++) {
                if(na[i] < na[j]) {
                    int temp = na[i];
                    na[i] = na[j];
                    na[j] = temp;
                }
            }
        }

        for(int i=0;i<5;i++){
            sss[i]=na[i];
        }



        for(int i=0;i<5;i++){
            s[i]=(i+1)+":"+sss[i];
        }

        String a=sss[0]+";"+sss[1]+";"+sss[2]+";"+sss[3]+";"+sss[4];
        SharedPreferences.Editor editor = getSharedPreferences("data2", MODE_PRIVATE).edit();
        editor.putString("score2", a);
        editor.commit();

        ArrayAdapter<String> scoreAdapter=new ArrayAdapter<String>(RankActivity.this,android.R.layout.simple_list_item_1, s);
        scoreList.setAdapter(scoreAdapter);

     }
    }

