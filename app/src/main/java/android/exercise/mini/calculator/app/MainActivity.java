package android.exercise.mini.calculator.app;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @VisibleForTesting
    public SimpleCalculator calculator;
    private TextView textViewCalculatorOutput;
    private static final String CalculatorStateKey = "CalculatorStateKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (calculator == null) {
            calculator = new SimpleCalculatorImpl();
        }

        // finding all views
        textViewCalculatorOutput = findViewById(R.id.textViewCalculatorOutput);

        // render output text view
        renderTextViewCalculatorOutput();

        // set buttons
        // =
        findViewById(R.id.buttonEquals).setOnClickListener(v -> {
            calculator.insertEquals();
            renderTextViewCalculatorOutput();
        });
        // +
        findViewById(R.id.buttonPlus).setOnClickListener(v -> {
            calculator.insertPlus();
            renderTextViewCalculatorOutput();
        });
        // -
        findViewById(R.id.buttonMinus).setOnClickListener(v -> {
            calculator.insertMinus();
            renderTextViewCalculatorOutput();
        });
        // clear
        findViewById(R.id.buttonClear).setOnClickListener(v -> {
            calculator.clear();
            renderTextViewCalculatorOutput();
        });
        handleDigitButtonClick(R.id.button0, 0);
        handleDigitButtonClick(R.id.button1, 1);
        handleDigitButtonClick(R.id.button2, 2);
        handleDigitButtonClick(R.id.button3, 3);
        handleDigitButtonClick(R.id.button4, 4);
        handleDigitButtonClick(R.id.button5, 5);
        handleDigitButtonClick(R.id.button6, 6);
        handleDigitButtonClick(R.id.button7, 7);
        handleDigitButtonClick(R.id.button8, 8);
        handleDigitButtonClick(R.id.button9, 9);
//
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // todo: save calculator state into the bundle
        outState.putSerializable(MainActivity.CalculatorStateKey, calculator.saveState());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculator.loadState(savedInstanceState.getSerializable(MainActivity.CalculatorStateKey));

    }


    private void renderTextViewCalculatorOutput() {
        if (textViewCalculatorOutput != null) {
            textViewCalculatorOutput.setText(calculator.output());
        }
    }

    private void handleDigitButtonClick(int button, int digit) {
        findViewById(button).setOnClickListener(v -> {
            calculator.insertDigit(digit);
            renderTextViewCalculatorOutput();
        });
    }

}