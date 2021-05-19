package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button playAgainButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView resultTextView;
    TextView correctTextView;
    TextView answerTextView;
    TextView timerTextView;
    TextView welcomeTextView;
    TextView visualTextView;
    TextView compTextView;
    ConstraintLayout gameLayout;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions;

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("45s");
        resultTextView.setText("0/0");
        correctTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);

        generateQuestions();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");

            }

            @Override
            public void onFinish() {

                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);

                timerTextView.setText("00s");
                correctTextView.setText("Your Score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(View.VISIBLE);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                mediaPlayer.start();

            }
        }.start();

    }
    public void generateQuestions () {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        answerTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {

            if (i == locationOfCorrectAnswer) {

                answer.add(a + b);

            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {

                    incorrectAnswer = rand.nextInt(41);

                }

                answer.add(incorrectAnswer);

            }

        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void start(View view) {

        startButton.setVisibility(View.INVISIBLE);

        welcomeTextView.setVisibility(View.INVISIBLE);
        visualTextView.setVisibility(View.INVISIBLE);
        compTextView.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(correctTextView);

    }

    public void chooseAnswer (View view) {

        if (view.getTag().equals(Integer.toString(locationOfCorrectAnswer))) {

            score++;
            correctTextView.setText("Correct Answer!!");

        } else {

            correctTextView.setText("Wrong Answer!!");

        }

        numberOfQuestions++;
        resultTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestions();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        correctTextView = findViewById(R.id.correctTextView);
        resultTextView = findViewById(R.id.resultTextView);
        answerTextView = findViewById(R.id.answerTextView);
        timerTextView = findViewById(R.id.timerTextView);
        welcomeTextView = findViewById(R.id.welcomeTextView);
        visualTextView = findViewById(R.id.visualTextView);
        compTextView = findViewById(R.id.compTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

    }
}