package com.example.sukurax.saolei2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sukurax on 15/Image7/Image7.
 */
public class GameActivity extends Activity {

    public TextView score;
    public int sco=0;
    public int count;
    int[] bomb=new int[5];

    GridView grid;
    int[] imagesIds=new int[]{
       R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,
            R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,
            R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,
            R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,
            R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,R.drawable.flag2,

    };


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        score=(TextView)findViewById(R.id.score);

        final ArrayList<Integer> list = new ArrayList<Integer>();

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.bgm);


        //5个炸弹
        for(int i=0;i<5;i++){
            bomb[i]=(int) (Math.random() * 25);
       }



        grid=(GridView)findViewById(R.id.grid01);
        final GridViewAdapter gridViewAdapter = new GridViewAdapter();
        grid.setAdapter(gridViewAdapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (position == bomb[0] || position == bomb[1] || position == bomb[2] || position == bomb[3] || position == bomb[4]) {
                    imagesIds[position] = R.drawable.bomb0;
                    gridViewAdapter.notifyDataSetChanged();
                    SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                    editor.putInt("score", sco * 10);
                    editor.commit();

                    mediaPlayer.start();
                    AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
                    dialog.setTitle("Boom!");
                    dialog.setMessage("你选择了死亡");
                    dialog.setCancelable(false);

                    dialog.setPositiveButton("确认并返回", new DialogInterface.OnClickListener() {
                        @Deprecated
                        public void onClick(DialogInterface dialog, int which) {
                            //分数逻辑

                            finish();
                        }
                    });
                    dialog.show();

                }
                else{


                    //计算周围雷数
                    switch (count(position)){
                        case 0:
                            imagesIds[position]=R.drawable.image0;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 1:
                            imagesIds[position]=R.drawable.image1;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 2:
                            imagesIds[position]=R.drawable.image2;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 3:
                            imagesIds[position]=R.drawable.image3;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 4:
                            imagesIds[position]=R.drawable.image4;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 5:
                            imagesIds[position]=R.drawable.image5;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 6:
                            imagesIds[position]=R.drawable.image6;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 7:
                            imagesIds[position]=R.drawable.image7;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        case 8:
                            imagesIds[position]=R.drawable.image8;
                            gridViewAdapter.notifyDataSetChanged();
                            break;
                        default:
                            Toast.makeText(GameActivity.this,"雷数计算错误！", Toast.LENGTH_SHORT).show();
                            break;
                   }
                    if(list.contains(position))
                    {
                        Toast.makeText(GameActivity.this,"多次点击无效",Toast.LENGTH_SHORT).show();
                    }else {
                        list.add(position);
                        sco++;
                        score.setText("当前得分："+sco*10);
                    }
                }
            }
        });


    }

    public int count(int pos){
        count=0;
        if((pos>=6&&pos<=8)||(pos>=11&&pos<=13)||(pos>=16&&pos<=18))
        {
            if((pos-6>=0)&&((pos-6) == bomb[0] || (pos-6) == bomb[1] || (pos-6) == bomb[2] || (pos-6) == bomb[3] || (pos-6) == bomb[4])){count++;}
            if((pos-5>=0)&&((pos-5) == bomb[0] || (pos-5) == bomb[1] || (pos-5) == bomb[2] || (pos-5) == bomb[3] || (pos-5) == bomb[4])){count++;}
            if((pos-4>=0)&&((pos-4) == bomb[0] || (pos-4) == bomb[1] || (pos-4) == bomb[2] || (pos-4) == bomb[3] || (pos-4) == bomb[4])){count++;}
            if((pos-1>=0)&&((pos-1) == bomb[0] || (pos-1) == bomb[1] || (pos-1) == bomb[2] || (pos-1) == bomb[3] || (pos-1) == bomb[4])){count++;}
            if((pos+1<=24)&&((pos+1) == bomb[0] || (pos+1) == bomb[1] || (pos+1) == bomb[2] || (pos+1) == bomb[3] || (pos+1) == bomb[4])){count++;}
            if((pos+4<=24)&&((pos+4) == bomb[0] || (pos+4) == bomb[1] || (pos+4) == bomb[2] || (pos+4) == bomb[3] || (pos+4) == bomb[4])){count++;}
            if((pos+5<=24)&&((pos+5) == bomb[0] || (pos+5) == bomb[1] || (pos+5) == bomb[2] || (pos+5) == bomb[3] || (pos+5) == bomb[4])){count++;}
            if((pos+6<=24)&&((pos+6) == bomb[0] || (pos+6) == bomb[1] || (pos+6) == bomb[2] || (pos+6) == bomb[3] || (pos+6) == bomb[4])){count++;}
        }
        if(pos==0||pos==5||pos==10||pos==15||pos==20) {
            if ((pos-5>=0) && ((pos - 5) == bomb[0] || (pos - 5) == bomb[1] || (pos - 5) == bomb[2] || (pos - 5) == bomb[3] || (pos - 5) == bomb[4])) {count++;}
            if ((pos-4>=0)&&((pos-4) == bomb[0] || (pos-4) == bomb[1] || (pos-4) == bomb[2] || (pos-4) == bomb[3] || (pos-4) == bomb[4])){count++;}
            if ((pos+1<=24) && ((pos + 1) == bomb[0] || (pos + 1) == bomb[1] || (pos + 1) == bomb[2] || (pos + 1) == bomb[3] || (pos + 1) == bomb[4])) {count++;}
            if ((pos+5<=24) && ((pos + 5) == bomb[0] || (pos + 5) == bomb[1] || (pos + 5) == bomb[2] || (pos + 5) == bomb[3] || (pos + 5) == bomb[4])) {count++;}
            if((pos+6<=24)&&((pos+6) == bomb[0] || (pos+6) == bomb[1] || (pos+6) == bomb[2] || (pos+6) == bomb[3] || (pos+6) == bomb[4])){count++;}
        }
        if(pos==4||pos==9||pos==14||pos==19||pos==24){
            if((pos-6>=0)&&((pos-6) == bomb[0] || (pos-6) == bomb[1] || (pos-6) == bomb[2] || (pos-6) == bomb[3] || (pos-6) == bomb[4])){count++;}
            if((pos-5>=0)&&((pos-5) == bomb[0] || (pos-5) == bomb[1] || (pos-5) == bomb[2] || (pos-5) == bomb[3] || (pos-5) == bomb[4])){count++;}
            if((pos-1>=0)&&((pos-1) == bomb[0] || (pos-1) == bomb[1] || (pos-1) == bomb[2] || (pos-1) == bomb[3] || (pos-1) == bomb[4])){count++;}
            if((pos+4<=24)&&((pos+4) == bomb[0] || (pos+4) == bomb[1] || (pos+4) == bomb[2] || (pos+4) == bomb[3] || (pos+4) == bomb[4])){count++;}
            if((pos+5<=24)&&((pos+5) == bomb[0] || (pos+5) == bomb[1] || (pos+5) == bomb[2] || (pos+5) == bomb[3] || (pos+5) == bomb[4])){count++;}
        }
        if(pos==1||pos==2||pos==3){
            if((pos-1>=0)&&((pos-1) == bomb[0] || (pos-1) == bomb[1] || (pos-1) == bomb[2] || (pos-1) == bomb[3] || (pos-1) == bomb[4])){count++;}
            if((pos+1<=24)&&((pos+1) == bomb[0] || (pos+1) == bomb[1] || (pos+1) == bomb[2] || (pos+1) == bomb[3] || (pos+1) == bomb[4])){count++;}
            if((pos+5<=24)&&((pos+5) == bomb[0] || (pos+5) == bomb[1] || (pos+5) == bomb[2] || (pos+5) == bomb[3] || (pos+5) == bomb[4])){count++;}
        }
        if(pos==21||pos==22||pos==23){
            if((pos-1>=0)&&((pos-1) == bomb[0] || (pos-1) == bomb[1] || (pos-1) == bomb[2] || (pos-1) == bomb[3] || (pos-1) == bomb[4])){count++;}
            if((pos+1<=24)&&((pos+1) == bomb[0] || (pos+1) == bomb[1] || (pos+1) == bomb[2] || (pos+1) == bomb[3] || (pos+1) == bomb[4])){count++;}
            if((pos-5>=0)&&((pos-5) == bomb[0] || (pos-5) == bomb[1] || (pos-5) == bomb[2] || (pos-5) == bomb[3] || (pos-5) == bomb[4])){count++;}
        }

        return count;

    }


    private class GridViewAdapter extends BaseAdapter{
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview; // 声明ImageView的对象
            if (convertView == null) {
                imageview = new ImageView(GameActivity.this); // 实例化ImageView的对象
                imageview.setMaxWidth(90);
                imageview.setMaxHeight(90);
                imageview.setMinimumHeight(90);
                imageview.setMinimumWidth(90);
            } else {
                imageview = (ImageView) convertView;
            }
            imageview.setImageResource(imagesIds[position]); // 为ImageView设置要显示的图片
            return imageview; // 返回ImageView
        }

        /*
         * 功能：获得当前选项的ID
         *
         * @see android.widget.Adapter#getItemId(int)
         */
        @Override
        public long getItemId(int position) {
            //System.out.println("getItemId = " + position);
            return position;
        }

        /*
         * 功能：获得当前选项
         *
         * @see android.widget.Adapter#getItem(int)
         */
        @Override
        public Object getItem(int position) {
            return position;
        }

        /*
         * 获得数量
         *
         * @see android.widget.Adapter#getCount()
         */
        @Override
        public int getCount() {
            return imagesIds.length;
        }
    }


}

