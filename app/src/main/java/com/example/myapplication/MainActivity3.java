package com.example.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity3 extends Activity {
    private Button bt_Hoimau,bt_Tancong,bt_Tangmau,bt_Xacnhan;
    private Context context;
    private TextView tv_Manhientai,tv_Tien,tv_tancong,tv_mau,tv_HienTien_tancong,tv_HienTien_mau;
    private int Man, mau_Player_chuyen, mau_Player_ht_chuyen, tancong_player_chuyen,Tien_chuyen,x,bn=0;
    private Button[] buttons = new Button[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);

        hamgoiid();
        laydulieu();
        capnhatdulieu();

        tv_HienTien_mau.setText("Tăng máu \n"+"Giá tiền 1");
        tv_HienTien_tancong.setText("Tăng tấn công \n"+"Giá tiền 1");

        for(int i=1;i<4;i++){
            String buttonID="button_"+i;
            int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = findViewById(resID);
            buttons[i].setOnClickListener(this::kt);
        }

        bt_Xacnhan.setOnClickListener(v -> {
            chuyendulieuvemaintest();
        });


    }

    private void kt(View v){
        if((((Button) v).getText().toString().equals("Tăng tấn công"))){
            x=1;
            bn = (int) (tancong_player_chuyen-1)/10;
        } else if ((((Button) v).getText().toString().equals("Tăng máu"))) {
            x=2;
            bn = (int) (mau_Player_chuyen-3)/10;
        }else {
            x=3;
        }

        if(Tien_chuyen>0){
            if(x==1){
                if((tancong_player_chuyen-1)<10) {
                    Tien_chuyen = Tien_chuyen - 1;
                    tv_HienTien_tancong.setText("Tăng tấn công \n"+"Giá tiền "+(bn+1));
                    tancong_player_chuyen = tancong_player_chuyen + 1;
                    capnhatdulieu();
                }else if(bn>0 && Tien_chuyen>=(bn)){
                    Tien_chuyen = Tien_chuyen - (bn);
                    tv_HienTien_tancong.setText("Tăng tấn công \n"+"Giá tiền "+(bn+1));
                    tancong_player_chuyen = tancong_player_chuyen + (int) ((bn/2)+1);
                    capnhatdulieu();
                }else {
                    Toast.makeText(MainActivity3.this,"Tiền không đủ để nâng cấp! \n Cần "
                            +(bn+1)+" tiền để nâng cấp!", Toast.LENGTH_SHORT).show();
                }
            }else if(x==2){
                if((mau_Player_chuyen-3)<10){
                Tien_chuyen=Tien_chuyen-1;
                tv_HienTien_mau.setText("Tăng máu \n"+"Giá tiền "+(bn+1));
                mau_Player_chuyen=mau_Player_chuyen+1;
                mau_Player_ht_chuyen=mau_Player_ht_chuyen+1;
                capnhatdulieu();
            }else if(bn>0 && Tien_chuyen>=(bn)){
                Tien_chuyen = Tien_chuyen - (bn);
                tv_HienTien_mau.setText("Tăng máu \n"+"Giá tiền "+(bn+1));
                mau_Player_chuyen=mau_Player_chuyen+(int) ((bn/2)+1);
                mau_Player_ht_chuyen=mau_Player_ht_chuyen+(int) ((bn/2)+1);
                capnhatdulieu();
            }else {
                Toast.makeText(MainActivity3.this,"Tiền không đủ để nâng cấp! \n Cần "
                        +(bn+1)+" tiền để nâng cấp!", Toast.LENGTH_SHORT).show();
            }
            }else if(x==3){
                if(mau_Player_ht_chuyen<mau_Player_chuyen) {
                    Tien_chuyen = Tien_chuyen - 1;
                    mau_Player_ht_chuyen = mau_Player_chuyen;
                    capnhatdulieu();
                }else {
                    Toast.makeText(MainActivity3.this,"Máu đã đầy không cần hối máu! ", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    private void hamgoiid(){
        bt_Hoimau=findViewById(R.id.button_3);
        bt_Tancong=findViewById(R.id.button_1);
        bt_Tangmau=findViewById(R.id.button_2);
        tv_Manhientai=findViewById(R.id.man);
        tv_Tien=findViewById(R.id.tv_tien);
        bt_Xacnhan=findViewById(R.id.button_Xacnhan);
        tv_tancong=findViewById(R.id.tv_tancong);
        tv_mau=findViewById(R.id.tv_mau);
        tv_HienTien_tancong=findViewById(R.id.textView_TangCong);
        tv_HienTien_mau=findViewById(R.id.textView_TangMau);

    }

    private void laydulieu(){
        Intent intent = this.getIntent();
        Man = intent.getExtras().getInt("Man");
        mau_Player_chuyen =intent.getExtras().getInt("Mauplayer");
        mau_Player_ht_chuyen =intent.getExtras().getInt("Mauplayer_ht");
        tancong_player_chuyen =intent.getExtras().getInt("Tancong");
        Tien_chuyen=intent.getExtras().getInt("Tien");
        bn = (int) (tancong_player_chuyen-1)/10;
        tv_HienTien_tancong.setText("Tăng tấn công \n"+"Giá tiền "+(bn+1));
        bn = (int) (mau_Player_chuyen-3)/10;
        tv_HienTien_mau.setText("Tăng máu \n"+"Giá tiền "+(bn+1));
    }
    private void capnhatdulieu(){
        tv_Manhientai.setText("Màn "+Man);
        tv_Tien.setText("Tiền: "+Tien_chuyen);
        tv_tancong.setText("Tấn công: "+ tancong_player_chuyen);
        tv_mau.setText("Máu: "+ mau_Player_ht_chuyen +"/"+ mau_Player_chuyen);
    }


    private void chuyendulieuvemaintest(){
        Intent intent = new Intent();
        intent.putExtra("Mau_capnhat_test",mau_Player_chuyen);
        intent.putExtra("Mauplayer_ht_capnhat_test",mau_Player_ht_chuyen);
        intent.putExtra("Tancong_capnhat_test",tancong_player_chuyen);
        intent.putExtra("Tien_capnhat_test",Tien_chuyen);
        intent.putExtra("Man_test",Man);
        setResult(RESULT_OK,intent);
        finish();
    }


}

