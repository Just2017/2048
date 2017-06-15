package com.example.administrator.a2048;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuAdapter;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.Math;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private boolean newGame = true;
    private int Score = 0;
    private int Score_Recode=0;
    private int MaxScore = 0;
//    当随机数为四时，随机出现的value为4，否则为2. （0-9 随机数）
    private static final int VALUE_FOUR = 4;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN =3;
    private static final int _LEFT = 4;

    private TextView tv_Score;
    private TextView tv_BestScore;
    private RelativeLayout layout;

    private TextView cell_0_0;
    private TextView cell_0_1;
    private TextView cell_0_2;
    private TextView cell_0_3;

    private TextView cell_1_0;
    private TextView cell_1_1;
    private TextView cell_1_2;
    private TextView cell_1_3;

    private TextView cell_2_0;
    private TextView cell_2_1;
    private TextView cell_2_2;
    private TextView cell_2_3;

    private TextView cell_3_0;
    private TextView cell_3_1;
    private TextView cell_3_2;
    private TextView cell_3_3;



    private int [][]cell=new int[4][4];
    private int [][]Has_Impact =new int[4][4];
    private int [][]cellRecode = new int[4][4];

    private Map map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout = (RelativeLayout)findViewById(R.id.relLayout1);
        gestureListener gListen = new gestureListener();

        layout.setOnTouchListener(gListen);

//        初始化 TextView
        initializeTextView();
        initializeCell();
        initMap();



        bt_newGame_Clicked();
        bt_reg_Clicked();

    }

    private void initMap(){
        map=new HashMap();
        map.put("0",(int)R.drawable.bg);
        map.put("2",(int)R.drawable.bg_2);
        map.put("4",(int)R.drawable.bg_4);
        map.put("8",R.drawable.bg_8);
        map.put("16",R.drawable.bg_16);
        map.put("32",R.drawable.bg_32);
        map.put("64",R.drawable.bg_64);
        map.put("128",R.drawable.bg_128);
        map.put("256",R.drawable.bg_256);
        map.put("512",R.drawable.bg_512);
        map.put("1024",R.drawable.bg_1024);
        map.put("2048",R.drawable.bg_2048);
        map.put("4096",R.drawable.bg_4096);
        map.put("8192",R.drawable.bg_8192);
        map.put("16348",R.drawable.bg_16348);
        map.put("32678",R.drawable.bg_32678);

    }

    private void initializeTextView(){
        tv_Score = (TextView) findViewById(R.id.ScoreText);
        tv_BestScore = (TextView) findViewById(R.id.bestScoreText);

        cell_0_0 = (TextView) findViewById(R.id.textView_0_0);
        cell_0_1 = (TextView) findViewById(R.id.textView_0_1);
        cell_0_2 = (TextView) findViewById(R.id.textView_0_2);
        cell_0_3 = (TextView) findViewById(R.id.textView_0_3);

        cell_1_0 = (TextView) findViewById(R.id.textView_1_0);
        cell_1_1 = (TextView) findViewById(R.id.textView_1_1);
        cell_1_2 = (TextView) findViewById(R.id.textView_1_2);
        cell_1_3 = (TextView) findViewById(R.id.textView_1_3);

        cell_2_0 = (TextView) findViewById(R.id.textView_2_0);
        cell_2_1 = (TextView) findViewById(R.id.textView_2_1);
        cell_2_2 = (TextView) findViewById(R.id.textView_2_2);
        cell_2_3 = (TextView) findViewById(R.id.textView_2_3);

        cell_3_0 = (TextView) findViewById(R.id.textView_3_0);
        cell_3_1 = (TextView) findViewById(R.id.textView_3_1);
        cell_3_2 = (TextView) findViewById(R.id.textView_3_2);
        cell_3_3 = (TextView) findViewById(R.id.textView_3_3);
    }

    private void initializeCell(){
        int seedValue1=new Random().nextInt(10);
        int seedValue2=new Random().nextInt(10);

        int seed1 = new Random().nextInt(16);
        int seed2 = new Random().nextInt(16);
        while (seed1 == seed2){
            seed2 = new Random().nextInt(16);
        }

        for(int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {
                cell[i][j]=0;
            }
        }
        cell[seed1/4][seed1%4] = (seedValue1 == VALUE_FOUR ? 4:2);
        cell[seed2/4][seed2%4] = (seedValue2 == VALUE_FOUR ? 4:2);

//        将 cell与 TextView 一一对应
        TextView_with_Cell();
    }

    private void TextView_with_Cell() {
        if (cell[0][0] == 0) {cell_0_0.setText("");Bk_twc(cell_0_0);} else {cell_0_0.setText(Integer.toString(cell[0][0]));Bk_TextView_with_Cell(cell[0][0],cell_0_0);}
        if (cell[0][1] == 0) {cell_0_1.setText("");Bk_twc(cell_0_1);} else {cell_0_1.setText(Integer.toString(cell[0][1]));Bk_TextView_with_Cell(cell[0][1],cell_0_1);}
        if (cell[0][2] == 0) {cell_0_2.setText("");Bk_twc(cell_0_2);} else {cell_0_2.setText(Integer.toString(cell[0][2]));Bk_TextView_with_Cell(cell[0][2],cell_0_2);}
        if (cell[0][3] == 0) {cell_0_3.setText("");Bk_twc(cell_0_3);} else {cell_0_3.setText(Integer.toString(cell[0][3]));Bk_TextView_with_Cell(cell[0][3],cell_0_3);}

        if (cell[1][0] == 0) {cell_1_0.setText("");Bk_twc(cell_1_0);} else {cell_1_0.setText(Integer.toString(cell[1][0]));Bk_TextView_with_Cell(cell[1][0],cell_1_0);}
        if (cell[1][1] == 0) {cell_1_1.setText("");Bk_twc(cell_1_1);} else {cell_1_1.setText(Integer.toString(cell[1][1]));Bk_TextView_with_Cell(cell[1][1],cell_1_1);}
        if (cell[1][2] == 0) {cell_1_2.setText("");Bk_twc(cell_1_2);} else {cell_1_2.setText(Integer.toString(cell[1][2]));Bk_TextView_with_Cell(cell[1][2],cell_1_2);}
        if (cell[1][3] == 0) {cell_1_3.setText("");Bk_twc(cell_1_3);} else {cell_1_3.setText(Integer.toString(cell[1][3]));Bk_TextView_with_Cell(cell[1][3],cell_1_3);}

        if (cell[2][0] == 0) {cell_2_0.setText("");Bk_twc(cell_2_0);} else {cell_2_0.setText(Integer.toString(cell[2][0]));Bk_TextView_with_Cell(cell[2][0],cell_2_0);}
        if (cell[2][1] == 0) {cell_2_1.setText("");Bk_twc(cell_2_1);} else {cell_2_1.setText(Integer.toString(cell[2][1]));Bk_TextView_with_Cell(cell[2][1],cell_2_1);}
        if (cell[2][2] == 0) {cell_2_2.setText("");Bk_twc(cell_2_2);} else {cell_2_2.setText(Integer.toString(cell[2][2]));Bk_TextView_with_Cell(cell[2][2],cell_2_2);}
        if (cell[2][3] == 0) {cell_2_3.setText("");Bk_twc(cell_2_3);} else {cell_2_3.setText(Integer.toString(cell[2][3]));Bk_TextView_with_Cell(cell[2][3],cell_2_3);}

        if (cell[3][0] == 0) {cell_3_0.setText("");Bk_twc(cell_3_0);} else {cell_3_0.setText(Integer.toString(cell[3][0]));Bk_TextView_with_Cell(cell[3][0],cell_3_0);}
        if (cell[3][1] == 0) {cell_3_1.setText("");Bk_twc(cell_3_1);} else {cell_3_1.setText(Integer.toString(cell[3][1]));Bk_TextView_with_Cell(cell[3][1],cell_3_1);}
        if (cell[3][2] == 0) {cell_3_2.setText("");Bk_twc(cell_3_2);} else {cell_3_2.setText(Integer.toString(cell[3][2]));Bk_TextView_with_Cell(cell[3][2],cell_3_2);}
        if (cell[3][3] == 0) {cell_3_3.setText("");Bk_twc(cell_3_3);} else {cell_3_3.setText(Integer.toString(cell[3][3]));Bk_TextView_with_Cell(cell[3][3],cell_3_3);}
//
//        添加样式
        //TextView_with_Bk();

//        cell_0_0.setBackgroundResource((int)map.get(Integer.toString(cell[0][0])));

        tv_Score.setText(Integer.toString(CurrentScore()));
        tv_BestScore.setText(Integer.toString(Max()));
    }

    private void Bk_TextView_with_Cell(int value,TextView cell){
        switch (value){
            case 0:
                cell.setBackgroundResource(R.drawable.bg);
                break;
            case 2:
                cell.setBackgroundResource(R.drawable.bg_2);
                cell.setTextSize(40f);
                break;
            case 4:
                cell.setBackgroundResource(R.drawable.bg_4);
                cell.setTextSize(40f);
                break;
            case 8:
                cell.setBackgroundResource(R.drawable.bg_8);
                cell.setTextSize(40f);
                break;
            case 16:
                cell.setBackgroundResource(R.drawable.bg_16);
                cell.setTextSize(40f);
                break;
            case 32:
                cell.setBackgroundResource(R.drawable.bg_32);
                cell.setTextSize(40f);
                break;
            case 64:
                cell.setBackgroundResource(R.drawable.bg_64);
                cell.setTextSize(40f);
                break;
            case 128:
                cell.setBackgroundResource(R.drawable.bg_128);
                cell.setTextSize(25f);
                break;
            case 256:
                cell.setBackgroundResource(R.drawable.bg_256);
                cell.setTextSize(25f);
                break;
            case 512:
                cell.setBackgroundResource(R.drawable.bg_512);
                cell.setTextSize(25f);
                break;
            case 1024:
                cell.setBackgroundResource(R.drawable.bg_1024);
                cell.setTextSize(20f);
                break;
            case 2048:
                cell.setBackgroundResource(R.drawable.bg_2048);
                cell.setTextSize(20f);
                break;
            case 4096:
                cell.setBackgroundResource(R.drawable.bg_4096);
                cell.setTextSize(20f);
                break;
            case 8192:
                cell.setBackgroundResource(R.drawable.bg_8192);
                cell.setTextSize(20f);
                break;
            case 16348:
                cell.setBackgroundResource(R.drawable.bg_16348);
                cell.setTextSize(15f);
                break;
            case 32678:
                cell.setBackgroundResource(R.drawable.bg_32678);
                cell.setTextSize(15f);
                break;

        }
    }

    private void Bk_twc(TextView cell){
//        设置放个为空时的颜色
        cell.setBackgroundResource(R.drawable.bg);
    }

    private void TextView_with_Bk(){
//        cell_0_0.setBackgroundResource((int)map.get(Integer.toString(cell[0][0])));
//        cell_0_1.setBackgroundResource((int)map.get(Integer.toString(cell[0][1])));
//        cell_0_2.setBackgroundResource((int)map.get(Integer.toString(cell[0][2])));
//        cell_0_3.setBackgroundResource((int)map.get(Integer.toString(cell[0][3])));
//
//        cell_1_0.setBackgroundResource((int)map.get(Integer.toString(cell[1][0])));
//        cell_1_1.setBackgroundResource((int)map.get(Integer.toString(cell[1][1])));
//        cell_1_2.setBackgroundResource((int)map.get(Integer.toString(cell[1][2])));
//        cell_1_3.setBackgroundResource((int)map.get(Integer.toString(cell[1][3])));
//
//        cell_2_0.setBackgroundResource((int)map.get(Integer.toString(cell[2][0])));
//        cell_2_1.setBackgroundResource((int)map.get(Integer.toString(cell[2][1])));
//        cell_2_2.setBackgroundResource((int)map.get(Integer.toString(cell[2][2])));
//        cell_2_3.setBackgroundResource((int)map.get(Integer.toString(cell[2][3])));
//
//        cell_3_0.setBackgroundResource((int)map.get(Integer.toString(cell[3][0])));
//        cell_3_1.setBackgroundResource((int)map.get(Integer.toString(cell[3][1])));
//        cell_3_2.setBackgroundResource((int)map.get(Integer.toString(cell[3][2])));
//        cell_3_3.setBackgroundResource((int)map.get(Integer.toString(cell[3][3])));
    }

    private void bt_newGame_Clicked() {
        Button bt_new_Game = (Button) findViewById(R.id.ButtonNewGame);

//     监听   bt_new_game ，如果单击了，游戏重新开始。
        bt_new_Game.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                newGame = true;
                Score = 0;
                MaxScore = 0;
                initializeCell();
            }
        });

    }

    private void bt_reg_Clicked(){
        Button bt_reg =(Button) findViewById(R.id.ButtonRegret);

//
        bt_reg.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<4;i++){
                    for (int j=0;j<4;j++){
                        cell[i][j] = cellRecode[i][j];
                    }
                }
                TextView_with_Cell();
            }
        });
    }

    private int CurrentScore(){
        return Score;
    }

    private void MoveCell(int dir){
        if(IsGameOver()) { Toast.makeText(layout.getContext(),"Game over",Toast.LENGTH_SHORT).show();return;}
        switch (dir){
//            case _LEFT:
//                // (0,0) --> (0,1),(0,2),(0,3)  | (0,1) --> (0,2),(0,3) | (0,2) -->(0,3)
//                for(int k=0;k<4;k++){
////                    a[0] 与 a[1] 先碰撞
//                    if (cell[k][1]!=0) {
//                        if (cell[k][0] == cell[k][1]) {
//                            cell[k][0] += cell[k][1];
//                            cell[k][1] = 0;
//                            Has_Impact[k][0] = 1;
//                        } else if (cell[k][0] == 0) {
//                            cell[k][0] += cell[k][1];
//                            cell[k][1] = 0;
//                        }
//                    }
////                    a[2] yu a[0] 进行碰撞
//                    if (cell[k][2]!=0){
////                        先将其与cell[k][1] 碰撞
//                        if (Has_Impact[k][0]==1){
//                            cell[k][1] =cell[k][2];
//                            cell[k][2] =0;
//                        }else {
//                            if(cell[k][2]==cell[k][1]){
//                                cell[k][1]+=cell[k][2];
//                                cell[k][2]=0;
//                                Has_Impact[k][1]=1;
//                            }else if(cell[k][1]==0){
////                                cell[k][2] 与 cell[k][0] 碰撞
//                                if (cell[k][0] == cell[k][2]) {
//                                    cell[k][0] += cell[k][2];
//                                    cell[k][2] = 0;
//                                    Has_Impact[k][0] = 1;
//                                } else if (cell[k][0] == 0) {
//                                    cell[k][0] += cell[k][2];
//                                    cell[k][2] = 0;
//                                }
//                            }
//                        }
//                    }
////                    a[3] 与 a[0] 碰撞
//                    if(cell[k][3]!=0){
////                        cell[k][3] 与 cell[k][2] 碰撞
//                        if(cell[k][3] == cell[k][2]){
//                            cell[k][2] += cell[k][3];
//                            cell[k][3]=0;
//                        }else if(cell[k][2] == 0){
////                            cell[k][3] yu cell[k][1]碰撞
//                            if (Has_Impact[k][1] == 1){
//                                cell[k][1] += cell[k][3];
//                                cell[k][3]=0;
//                            }else{
//                                if (cell[k][3] == cell[k][1]){
//                                    cell[k][1] += cell[k][3];
//                                    cell[k][3]=0;
//                                }else if(cell[k][1] == 0){
////                                    cell[k][3] yu cell[k][0] 比较
//                                    if (Has_Impact[k][0] == 1){
//                                        cell[k][0]+=cell[k][3];
//                                        cell[k][3]=0;
//                                    }else{
//                                        if(cell[k][3] == cell[k][0]){
//                                            cell[k][0]+=cell[k][3];
//                                            cell[k][3]=0;
//                                        }else if(cell[k][0]==0){
//                                            cell[k][0]+=cell[k][3];
//                                            cell[k][3]=0;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }


//                    先将 a[1] 与 a[0] 进行碰撞
//                    if(cell[k][0] == cell[k][1] && cell[k][0] !=0 && cell[k][1] !=0){
//                        cell[k][0] += cell[k][1];
//                        cell[k][1] = 0;
//                        Has_Impact[k][0] = 1;
//                    }else if(cell[k][0]==0 || cell[k][1]==0){
//                        cell[k][0] += cell[k][1];
//                        cell[k][1] = 0;
//                    }
////                    接着将 a[2] 与 a[0]位置进行碰撞,碰撞之前与 a[1] 进行碰撞
//                    if(cell[k][2] == cell[k][1] && cell[k][1] !=0 && cell[k][2] !=0){
////                        cell[k][2] yu cell[k][1] 碰撞
//                        cell[k][1] += cell[k][2];
//                        cell[k][2] = 0;
//                        Has_Impact[k][1]=1;
//                    }else if(cell[k][1] == 0 && Has_Impact[k][0] ==1){
//                        cell[k][1] += cell[k][2];
//                        cell[k][2] = 0;
//                    }else if(cell[k][1] == 0 && Has_Impact[k][0] !=1){
////                       将 cell[k][2] 和 cell[k][0] 进行碰撞
//                        if(cell[k][0] == cell[k][2] && cell[k][0] !=0 && cell[k][2] !=0){
//                            cell[k][0] += cell[k][2];
//                            cell[k][2] = 0;
//                            Has_Impact[k][0] = 1;
//                        }else if(cell[k][0]==0 || cell[k][2]==0){
//                            cell[k][0] += cell[k][2];
//                            cell[k][2] = 0;
//                        }
////                        a[3] 对前面进行碰撞
//                        if (cell[k][3]!=0){
//
//                        }
            case LEFT:
                left();
                TextView_with_Cell();
                break;

            case RIGHT:
                right();
                TextView_with_Cell();
                break;
            case DOWN:
                down();
                TextView_with_Cell();

                break;
            case UP:
                up();
                TextView_with_Cell();
                break;
        }
        if (IsEquals()) return;
//        创建 种子

        TextView_with_Cell();
        try {
            Thread.sleep(100);
        }catch (Exception e){
        }
        CreateSeed();
        TextView_with_Cell();
    }

    private void initizeImpact(){
        for(int i = 0; i <4; i++){
            for(int j=0;j<4;j++){
                Has_Impact[i][j] = 0;
            }
        }
    }

    private void left(){
        initizeImpact();
        fuzhi();
        Score_Recode=Score;
        for(int k = 0;k<4;k++){
//            第一次碰撞
            compare(k,0,1);
            compare(k,1,2);
            compare(k,2,3);
//            if(k == 3)  TextView_with_Cell();
//            第二次碰撞
            compare(k,0,1);
            compare(k,1,2);
//            if(k == 3) TextView_with_Cell();
//            第三次碰撞
            compare(k,0,1);
        }
    }

    private void right(){
        initizeImpact();
        fuzhi();
        Score_Recode=Score;
        for(int k=0;k<4;k++){
//            第一次碰撞
            compare(k,3,2);
            compare(k,2,1);
            compare(k,1,0);
//            第二次碰撞
            compare(k,3,2);
            compare(k,2,1);
//            第三次碰撞
            compare(k,3,2);
        }
    }

    private void up(){
        initizeImpact();
        fuzhi();
        Score_Recode=Score;
        for (int k=0;k<4;k++) {
//            第一次比较
            compare1(0, 1, k);
            compare1(1, 2, k);
            compare1(2, 3, k);
//            第二次比较
            compare1(0, 1, k);
            compare1(1, 2, k);
//            第三次比较
            compare1(0, 1, k);
        }
    }

    private void down(){
        initizeImpact();
        fuzhi();
        Score_Recode=Score;
        for (int k=0;k<4;k++){
//            第一次比较
            compare1(3,2,k);
            compare1(2,1,k);
            compare1(1,0,k);
//            第二次比较
            compare1(3,2,k);
            compare1(2,1,k);
//            第三次比较
            compare1(3,2,k);
        }
    }

    private void compare(int k,int posx1,int posx2){
        if(Has_Impact[k][posx1]==1 || Has_Impact[k][posx2]==1) return;
        else if(cell[k][posx1] == cell[k][posx2] && (cell[k][posx1]!=0&&cell[k][posx2]!=0)){
            Score += cell[k][posx2];
            cell[k][posx1]+=cell[k][posx2];
            cell[k][posx2]=0;
            Has_Impact[k][posx1]=1;
            Log.i("log",Score+"");
            return;
        }
        else if(cell[k][posx1]==0 || cell[k][posx2]==0){
            cell[k][posx1] += cell[k][posx2];
            cell[k][posx2] =0;
            return;
        }

    }

    private  void compare1(int posx1,int posx2,int k){
        if(Has_Impact[posx1][k] == 1 || Has_Impact[posx2][k] == 1) return;
        else if(cell[posx1][k] == cell[posx2][k] && (cell[posx1][k]!=0 && cell[posx2][k]!=0)){
            Score += cell[posx2][k];
            cell[posx1][k] += cell[posx2][k];
            cell[posx2][k] = 0;
            Has_Impact[posx1][k] =1;
            return;
        }
        else if(cell[posx1][k] == 0 || cell[posx2][k] == 0){
            cell[posx1][k] += cell[posx2][k];
            cell[posx2][k] = 0;
            return;
        }
    }

    private void CreateSeed(){
//        随机产生一个种子
        if(IsGameOver()) {
            Toast.makeText(layout.getContext(),"游戏结束",Toast.LENGTH_SHORT).show();
            return;
        }
        int seed = new Random().nextInt(16);
        if (cell[seed/4][seed%4]==0){
            cell[seed/4][seed%4]= (new Random().nextInt(10)) == VALUE_FOUR ? 4:2;
        }else{
            seed=ValidSeed(seed);
            cell[seed/4][seed%4]= (new Random().nextInt(10)) == VALUE_FOUR ? 4:2;
        }
    }

    private int ValidSeed(int seed){
        boolean valid = cell[seed/4][seed%4] == 0?true:false;
        if(valid) return seed;
        else{
            while(!valid){
                seed = (seed+3)%16;
                valid = cell[seed/4][seed%4] == 0?true:false;
            }
        }
        return seed;
    }

    private boolean IsGameOver(){
        for(int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if (cell[i][j] ==0){
                    return false;
                }
            }
        }
        for (int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if (j<3){
                    if(cell[i][j] == cell[i][j+1]) return false;
                }
                if(i<3) {
                    if (cell[i][j] == cell[i + 1][j]) return false;
                }
            }
        }
        return true;
    }

    private void fuzhi(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                cellRecode[i][j]=cell[i][j];
            }
        }
    }

    private boolean IsEquals(){
        for(int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                if(cell[i][j] == cellRecode[i][j]) continue;
                else return false;
            }
        }

        return true;
    }

    private int Max(){
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                MaxScore = MaxScore >= cell[i][j] ? MaxScore : cell[i][j];
            }
        }
        return MaxScore;
    }




    public class gestureListener implements GestureDetector.OnGestureListener, View.OnTouchListener{

        private  GestureDetector mGestureDetector = new GestureDetector(this);

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return mGestureDetector.onTouchEvent(event);
        }

        //
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //根据坐标识别 其方向

            float beginX = e1.getX();
            float endX = e2.getX();
            float beginY = e1.getY();
            float endY = e2.getY();
            float minMoveLen = 100;
            Log.i("beginX`",beginX+"");
            Log.i("endX",endX+"");
            Log.i("beginY",beginY+"");
            Log.i("endY",endY+"");
            Log.i("VX",String.valueOf(velocityX));
            Log.i("VY",velocityY+"");
            float v = Math.abs(velocityX)>=Math.abs(velocityY) ? Math.abs(velocityX):Math.abs(velocityY);
            if(beginX-endX>=minMoveLen && v== Math.abs(velocityX)){
//              Left

                Log.i("log","left");
//                Toast.makeText(layout.getContext(),"left",Toast.LENGTH_SHORT).show();
                MoveCell(LEFT);

            }else if(endX - beginX >=minMoveLen && v == Math.abs(velocityX)){
//              Right
                Log.i("log","right");
//                Toast.makeText(layout.getContext(),"right",Toast.LENGTH_SHORT).show();
                MoveCell(RIGHT);

            }else if (beginY - endY >minMoveLen && v == Math.abs(velocityY)){
//              Up
                Log.i("log","up");
//                Toast.makeText(layout.getContext(),"up",Toast.LENGTH_SHORT).show();
                MoveCell(UP);

            }else if(endY - beginY >minMoveLen && v == Math.abs(velocityY)){
//              Down
                Log.i("log","down");
//                Toast.makeText(layout.getContext(),"down",Toast.LENGTH_SHORT).show();
                MoveCell(DOWN);

            }



            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.i("touch","onLongPress");
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            return true;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.i("touch","onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.i("touch","onSingleTapUp");
            return false;
        }
    }
}


