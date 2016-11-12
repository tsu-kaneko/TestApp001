package com.example.tsubasa.testapp001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    //mainメソッド
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(buttonListener);

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
    }


    int recentOperator = R.id.button_equal;   //最近押された計算キー
    double result;   //計算結果
    boolean isOperatorPushed;   //計算キーが押されたことを記憶

    //入力部分をクリアするメソッド
    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            recentOperator = R.id.button_equal;
            result = 0;
            isOperatorPushed = false;

            textView.setText("");
            editText.setText("");
        }
    };

    //演算子が押される前なら文字を追加、後なら文字をセットするメソッド
    View.OnClickListener buttonNumberListener = new View.OnClickListener(){
      @Override
        public void onClick(View view){
          Button button = (Button) view;

          if(isOperatorPushed == true){
              editText.setText(button.getText());
          }else{
          editText.append(button.getText());
          }

          isOperatorPushed = false;
      }
    };

    //演算子が押された時に呼ばれるメソッド
    View.OnClickListener buttonOperatorListener = new View.OnClickListener(){
      @Override
        public void onClick(View view){
          Button operatorButton = (Button)view;
          double value = Double.parseDouble(editText.getText().toString());

          if (recentOperator == R.id.button_equal){
              result = value;
          }else{
              result = calc(recentOperator, result, value);
              editText.setText(String.valueOf(result));
          }

          //android:idを取得
          recentOperator = operatorButton.getId();
          //android:textを取得
          textView.setText(operatorButton.getText());
          isOperatorPushed = true;
      }
    };

    //計算処理を行うメソッド
    double calc(int operator, double value1, double value2){
      switch(operator){
          case R.id.button_add:
              return value1 + value2;
          case R.id.button_subtract:
              return value1 - value2;
          case R.id.button_multiply:
              return value1 * value2;
          case R.id.button_divide:
              return value1 / value2;
          default:
              return value1;
      }
    }

}
