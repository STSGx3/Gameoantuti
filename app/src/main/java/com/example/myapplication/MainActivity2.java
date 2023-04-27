package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends Activity {
    private int mau_Player,mau_Player_ht,tancong_player,tancong_bot,mau_Bot_ht,mau_Bot,Tien,x,y,bn,kt=0;
    private Context context;
    private ProgressBar thanhmau1,thanhmau2;
    private Button[] buttons = new Button[100];
    private int Man;
    int REQUEST_CODE_EDIT=123;
    private TextView tvMan,tvTien,dmgB,dmgP;
    int max=5;
    int min=2;
    String code;
    //Intent intent;

    ArrayList<Integer> listImg ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        context = this;
        hamgoiid();
        Man=1;
        if(Man==1) {
            mau_Bot = mau_Bot_ht = mau_Player_ht = mau_Player = 3;
            tancong_bot = tancong_player = 1;
        }
        tvMan.setText("Màn "+Man);
        listImg = new ArrayList<>();

        listImg.add(R.drawable.anh_1);
        listImg.add(R.drawable.anh_2);
        listImg.add(R.drawable.anh_3);

        hamxetanhvemachdinh();
        capnhatdulieuVeMau();
        for(int i=1;i<4;i++){
            String buttonID="button_"+i;
            int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this::kt);
        }

    }


    private void kt(View v){

        if((((Button) v).getText().toString().equals("Kéo"))){
            x=1;
        } else if ((((Button) v).getText().toString().equals("Búa"))) {
            x=2;
        }else {
            x=3;
        }
        y  = (int) (Math.random()*((3-1)+1))+1;
        // y=3;

        if(Man%4==0 && kt==0){
            mau_Bot=mau_Bot+(int) Math.ceil(Man/4);
            tancong_bot=tancong_bot+(int) Math.ceil(Man/4);
            kt=1;
        }

        ((ImageView) findViewById(R.id.imageView)).setImageResource(listImg.get(x-1));
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(listImg.get(y-1));
        capnhatdulieuVeMau();

        if(x==1){
            if(y==2){
                thua();
            }if(y==3){
                thang();
            }
        }
        if(x==2){
            if(y==3){
                thua();
            }if(y==1){
                thang();
            }
        }
        if (x==3){
            if(y==1){
                thua();
            }if(y==2){
                thang();
            }
        }
        if(x==y){
            hoa();
        }
    }

    private void hoa(){
        Toast.makeText(MainActivity2.this,"Hòa.", Toast.LENGTH_SHORT).show();
        CountDownTimer countDownTimer = new CountDownTimer(3000,1500) {
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                hamxetanhvemachdinh();
            }
        };
        countDownTimer.start();
    }
    private void thua(){
        mau_Player_ht=mau_Player_ht-tancong_bot;
        dmgP.setText("-"+tancong_bot);
        CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                hamxetanhvemachdinh();
                dmgP.setText("");
            }
        };
        countDownTimer.start();
        capnhatdulieuVeMau();
        if(mau_Player_ht<=0){
            resetGame();
        }
    }
    private void thang(){
        mau_Bot_ht=mau_Bot_ht-tancong_player;
        dmgB.setText("-"+tancong_player);
          CountDownTimer countDownTimer = new CountDownTimer(3000,1500) {
            @Override
            public void onTick(long millisUntilFinished) {}
            @Override
            public void onFinish() {
                hamxetanhvemachdinh();
                dmgB.setText("");
            }
        };
        countDownTimer.start();
        capnhatdulieuVeMau();

        if(Man%4==0){
            max=max+Man/4;
            min=min+Man/4;
        }

        if(mau_Bot_ht<=0){
            bn = (int) (Math.random()*((max-min)+1))+min;
            Tien = Tien+bn;
            Man++;
            tvTien.setText("Tiền "+Tien);
            tvMan.setText("Màn "+Man);
            mau_Bot_ht=mau_Bot;
            capnhatdulieuVeMau();
            chuyenman();
            kt=0;
        }
    }
    private void capnhatdulieuVeMau(){
        thanhmau1.setMax(mau_Player);
        thanhmau2.setMax(mau_Bot);
        thanhmau1.setProgress(mau_Player_ht);
        thanhmau2.setProgress(mau_Bot_ht);
    }
    private void resetGame(){
        Man=1;
        mau_Bot=mau_Bot_ht=mau_Player_ht=mau_Player=3;
        tancong_bot=tancong_player=1;
        Tien=0;
        Toast.makeText(MainActivity2.this,"Bạn đã thua.", Toast.LENGTH_SHORT).show();
        capnhatdulieuVeMau();
        hamxetanhvemachdinh();
        tvTien.setText("Tiền 0");
        tvMan.setText("Màn 1");
        kt=0;
    }


    private void hamgoiid(){
        thanhmau1= findViewById(R.id.progressBar);
        thanhmau2= findViewById(R.id.progressBar2);
        tvTien = findViewById(R.id.tienGame);
        tvMan=findViewById(R.id.man);
        dmgB=findViewById(R.id.tv_dmb);
        dmgP=findViewById(R.id.tv_dmgP);
    }
    private void hamxetanhvemachdinh(){
        ((ImageView) findViewById(R.id.imageView)).setImageResource(R.drawable.anhnhanvat);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(R.drawable.anhnhanvat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==REQUEST_CODE_EDIT&& resultCode==RESULT_OK&& data!=null){
            int bn;
            bn=data.getIntExtra("Mau_capnhat_test",1);
            mau_Player=bn;
            bn=data.getIntExtra("Mauplayer_ht_capnhat_test",1);
            mau_Player_ht=bn;
            capnhatdulieuVeMau();
            bn=data.getIntExtra("Tancong_capnhat_test",1);
            tancong_player=bn;
            bn=data.getIntExtra("Tien_capnhat_test",1);
            Tien=bn;
            tvTien.setText("Tiền "+Tien);
            tvMan.setText("Màn "+Man);

        }


    }
    private void chuyenman(){
        Intent intent = new Intent(context,MainActivity3.class);
        intent.putExtra("Man",Man);
        intent.putExtra("Mauplayer",mau_Player);
        intent.putExtra("Mauplayer_ht",mau_Player_ht);
        intent.putExtra("Tancong",tancong_player);
        intent.putExtra("Tien",Tien);
        overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
        startActivityForResult(intent,REQUEST_CODE_EDIT);
    }

}

