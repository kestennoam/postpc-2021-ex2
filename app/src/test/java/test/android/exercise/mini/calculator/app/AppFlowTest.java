package test.android.exercise.mini.calculator.app;

import android.exercise.mini.calculator.app.MainActivity;
import android.exercise.mini.calculator.app.R;
import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {28})
public class AppFlowTest {

    private ActivityController<MainActivity> activityController;
    private MainActivity activityUnderTest;
    private View button0;
    private View button1;
    private View button2;
    private View button3;
    private View button4;
    private View button5;
    private View button6;
    private View button7;
    private View button8;
    private View button9;
    private View buttonBackspace;
    private View buttonClear;
    private View buttonPlus;
    private View buttonMinus;
    private View buttonEquals;
    private TextView textViewOutput;

    /**
     * initialize main activity with a real calculator
     */
    @Before
    public void setup() {
        activityController = Robolectric.buildActivity(MainActivity.class);
        activityUnderTest = activityController.get();
        activityController.create().start().resume();
        button0 = activityUnderTest.findViewById(R.id.button0);
        button1 = activityUnderTest.findViewById(R.id.button1);
        button2 = activityUnderTest.findViewById(R.id.button2);
        button3 = activityUnderTest.findViewById(R.id.button3);
        button4 = activityUnderTest.findViewById(R.id.button4);
        button5 = activityUnderTest.findViewById(R.id.button5);
        button6 = activityUnderTest.findViewById(R.id.button6);
        button7 = activityUnderTest.findViewById(R.id.button7);
        button8 = activityUnderTest.findViewById(R.id.button8);
        button9 = activityUnderTest.findViewById(R.id.button9);
        buttonBackspace = activityUnderTest.findViewById(R.id.buttonBackSpace);
        buttonClear = activityUnderTest.findViewById(R.id.buttonClear);
        buttonPlus = activityUnderTest.findViewById(R.id.buttonPlus);
        buttonMinus = activityUnderTest.findViewById(R.id.buttonMinus);
        buttonEquals = activityUnderTest.findViewById(R.id.buttonEquals);
        textViewOutput = activityUnderTest.findViewById(R.id.textViewCalculatorOutput);
    }

    @Test
    public void flowTest1() {
        // run clicks on "13+5"
        for (View button : Arrays.asList(
                button1, button3, buttonPlus, button5
        )) {
            button.performClick();
        }

        assertEquals("13+5", textViewOutput.getText().toString());
    }


    @Test
    public void flowTest2() {
        // run clicks on "7+5<backspace>4="
        for (View button : Arrays.asList(
                button7, buttonPlus, button5, buttonBackspace, button4, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("11", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest3() {
        // run clicks on "7-5<backspace>+4="
        for (View button : Arrays.asList(
                button7, buttonMinus, button5, buttonBackspace, buttonPlus, button4, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("3", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest4() {
        // run clicks on "<clear>2<clear>3<backspace>+4="
        for (View button : Arrays.asList(
                buttonClear, button2, buttonClear, button3, buttonBackspace, buttonPlus, button4, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("4", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest5() {
        // run clicks on "30<backspace>-2="
        for (View button : Arrays.asList(
                button3, button0, buttonBackspace, buttonMinus, button2, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("1", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest6() {
        // run clicks on "1++1="
        for (View button : Arrays.asList(
                button1, buttonPlus, buttonPlus, button1, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("2", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest7() {
        // run clicks on "000="
        for (View button : Arrays.asList(
                button0, button0, button0, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("0", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest8() {
        // run clicks on "1+1=+1="
        for (View button : Arrays.asList(
                button1, buttonPlus, button1, buttonEquals, buttonPlus, button1, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("3", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest9() {
        // run clicks on "1+1=-1="
        for (View button : Arrays.asList(
                button1, buttonPlus, button1, buttonEquals, buttonMinus, button1, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("1", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest10() {
        // run clicks on "1+1=<clear>-1="
        for (View button : Arrays.asList(
                button1, buttonPlus, button1, buttonEquals, buttonClear,buttonMinus, button1, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("-1", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest11() {
        // run clicks on "1+1=<backspace>"
        for (View button : Arrays.asList(
                button1, buttonPlus, button1, buttonEquals,buttonBackspace ,buttonMinus, button1, buttonEquals
        )) {
            button.performClick();
        }

        assertEquals("-1", textViewOutput.getText().toString());
    }

    @Test
    public void flowTest12() {
        // run clicks on "<backspace>"
        for (View button : Arrays.asList(
                buttonBackspace
        )) {
            button.performClick();
        }

        assertEquals("0", textViewOutput.getText().toString());
    }
}
