package gr.dasoftware.trainyourbrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();

    int score = 0;
    int tries = 0;

    ArrayList<String> math;
    ArrayList<Integer> answers;
    int locationOfCorrectAnswer;
    int a;
    int b;

    private Button btnStartGame;
    private Button btnPlayAgain;
    private TextView timerTextView;
    private TextView pointsTextView;
    private TextView sumTextView;
    private TextView resultTextView;

    private Button btnAnswer1;
    private Button btnAnswer2;
    private Button btnAnswer3;
    private Button btnAnswer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views.
        btnPlayAgain = (Button) findViewById(R.id.playAgainBtn);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        pointsTextView = (TextView) findViewById(R.id.pointsTextView);
        sumTextView = (TextView) findViewById(R.id.sumTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        btnAnswer1 = (Button) findViewById(R.id.button);
        btnAnswer2 = (Button) findViewById(R.id.button1);
        btnAnswer3 = (Button) findViewById(R.id.button2);
        btnAnswer4 = (Button) findViewById(R.id.button3);

        // Generate first question on app startup.
        generateQuestion();

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(tries));
                btnPlayAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void btnGameStart(View view) {
        btnStartGame.setVisibility(View.INVISIBLE);
    }

    public void chooseAnswer(View view) {
        tries++;
        int chosenAnswer = Integer.parseInt(view.getTag().toString());

        if (chosenAnswer == locationOfCorrectAnswer) {
            score++;
            resultTextView.setText("Correct answer!!");
        } else {
            resultTextView.setText("Incorrect answer!!");
        }

        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(tries));

        // Generate next question.
        generateQuestion();
    }

    private void generateQuestion() {
        answers = new ArrayList<Integer>();
        math = new ArrayList<String>();

        a = rand.nextInt(21);
        b = rand.nextInt(21);
        // location of correct answer 1-4.
        locationOfCorrectAnswer = rand.nextInt(4);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        for (int i = 0; i < 4; i++){
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int incorrectAnswer = rand.nextInt(50);

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(50);
                }

                answers.add(incorrectAnswer);
            }
        }

        btnAnswer1.setText(Integer.toString(answers.get(0)));
        btnAnswer2.setText(Integer.toString(answers.get(1)));
        btnAnswer3.setText(Integer.toString(answers.get(2)));
        btnAnswer4.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view) {
        view.setVisibility(View.GONE);

        answers = new ArrayList<Integer>();
        math = new ArrayList<String>();

        score = 0;
        tries = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");

        resultTextView.setText("");

        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(tries));
            }
        }.start();
    }
}
