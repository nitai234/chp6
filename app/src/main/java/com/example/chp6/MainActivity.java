package com.example.chp6;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button[][] btArr;
    TextView tvWiner;
    Button btRestart;
    LinearLayout ll;
    int counter;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btArr = new Button[3][3];
        ll = findViewById(R.id.ll1);
        btRestart = findViewById(R.id.btRestart);
        tvWiner = findViewById(R.id.tvWiner);
        btRestart.setOnClickListener(this);
        counter = 0;

        create(btArr, ll);
    }

    public void create(Button[][] btArr, LinearLayout ll)
    {
        for (int i = 0; i < 3; i++)
        {
            LinearLayout linearLayoutL = new LinearLayout(this);
            LinearLayout.LayoutParams layoutParamsL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
                    , LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayoutL.setLayoutParams(layoutParamsL);
            linearLayoutL.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < 3; j++)
            {
                LinearLayout.LayoutParams layoutParamsB = new LinearLayout.LayoutParams(300, 300);
                btArr[i][j].setLayoutParams(layoutParamsB);
                btArr[i][j].setOnClickListener(this);
                linearLayoutL.addView(btArr[i][j]);
            }
            ll.addView(linearLayoutL);
        }

    }

    @Override
    public void onClick(View view)
    {
        if(view == btRestart)
        {
            restart();
        }
        else
        {
            userTurn(view);
            computerTurn();
        }
    }

    public void restart()
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                btArr[i][j].setText("");
            }
        }
    }

    public void userTurn(View view)
    {
        Button bt = (Button) view;
        while(bt.getText() != "")
        {
            Toast.makeText(this,"נא ללחוץ על משבצת פנויה", Toast.LENGTH_SHORT).show();
        }
        bt.setText("X");
        counter++;
        isWin("X");
    }

    public void computerTurn()
    {
        int i = (int)(Math.random()*4);
        int j = (int)(Math.random()*4);
        while (btArr[i][j].getText() != "")
        {
            i = (int)(Math.random()*4);
            j = (int)(Math.random()*4);
        }
        btArr[i][j].setText("O");
        counter++;
        isWin("O");
    }

    @SuppressLint("SetTextI18n")
    public void isWin(String XorO)
    {
        if (counter > 4)
        {
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (btArr[i][j].getText() == btArr[i + 1][j].getText() && btArr[i + 1][j].getText() == btArr[i + 2][j].getText()
                            || btArr[i][j].getText() == btArr[i][j + 1].getText() && btArr[i][j + 1].getText() == btArr[i][j + 2].getText()
                            || btArr[i][j].getText() == btArr[i + 1][j + 1].getText() && btArr[i + 1][j + 1].getText() == btArr[i + 2][j + 2].getText()
                            || btArr[i][j + 2].getText() == btArr[i + 1][j + 1].getText() && btArr[i + 1][j + 1].getText() == btArr[i + 2][j].getText())
                    {
                        tvWiner.setText("המנצח הוא: " + XorO);
                        return;
                    }
                }
            }
        }

        else if(counter >= 9)
        {
            tvWiner.setText("תיקו");
        }
    }
}