package android.exercise.mini.calculator.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SimpleCalculatorImpl implements SimpleCalculator {

    public List<String> currentState = new LinkedList<String>() {{
        add("0");
    }};

    private static final Set<String> OPERATORS = new HashSet<String>() {{
        add("-");
        add("+");
    }};


    @Override
    public String output() {
        // O(n)
        StringBuilder builder = new StringBuilder();
        for (String s : currentState)
            builder.append(s);
        return builder.toString();
    }

    @Override
    public void insertDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("digit must be in range 0-9");
        }
        // check if the array is empty- requires deleting of 0
        if (currentState.size() == 1 && currentState.get(0).equals("0")) {
            currentState.remove(0);
        }
        insertToCurrentState(Integer.toString(digit));
    }

    @Override
    public void insertPlus() {
        if (!SimpleCalculatorImpl.OPERATORS.contains(currentState.get(currentState.size() - 1))) {
            insertToCurrentState("+");
        }
    }

    @Override
    public void insertMinus() {
        if (!SimpleCalculatorImpl.OPERATORS.contains(currentState.get(currentState.size() - 1))) {
            insertToCurrentState("-");
        }
    }

    @Override
    public void insertEquals() {
        // todo: calculate the equation. after calling `insertEquals()`, the output should be the result
        //  e.g. given input "14+3", calling `insertEquals()`, and calling `output()`, output should be "17"
        String result = "";
        // check if last char is operator then needs to add 0
        if (SimpleCalculatorImpl.OPERATORS.contains(currentState.get(currentState.size() - 1))) {
            currentState.add("0");
        }

        // init vars
        int i, total;
        i = total = 0;
        String operator = "+";

        // parsing it
        while (i < currentState.size()) {
            // operator
            if (SimpleCalculatorImpl.OPERATORS.contains(currentState.get(i))) {
                operator = currentState.get(i);
                ++i;
            }
            // number
            else {
                int j = parseTokenNumber(i);
                // build string
                StringBuilder builder = new StringBuilder();
                for (String s : currentState.subList(i, j))
                    builder.append(s);
                int token = Integer.parseInt(String.valueOf(builder));
                total = operator.equals("+") ? total + token : total - token;

                i = j;
            }
        }
        // change current state
        clear();
        currentState.set(0, Integer.toString(total));
    }


    /*
     *
     * @param index
     * @return
     */
    private int parseTokenNumber(int index) {
        while (index < currentState.size() && !SimpleCalculatorImpl.OPERATORS.contains(currentState.get(index))) {
            ++index;
        }
        return index;
    }


    @Override

    public void deleteLast() {
        // todo: delete the last input (digit, plus or minus)
        //  e.g.
        //  if input was "12+3" and called `deleteLast()`, then delete the "3"
        //  if input was "12+" and called `deleteLast()`, then delete the "+"
        //  if no input was given, then there is nothing to do here

        // never could be empty
        currentState.remove(currentState.size() - 1);
        // if empty requires inserting 0
        if (currentState.isEmpty()) {
            currentState.add("0");
        }

    }

    @Override
    public void clear() {
        currentState = new ArrayList<String>() {{
            add("0");
        }};
    }

    @Override
    public Serializable saveState() {
        CalculatorState state = new CalculatorState();
        // todo: insert all data to the state, so in the future we can load from this state
        state.currentState = new ArrayList<>(currentState);
        return state;
    }

    @Override
    public void loadState(Serializable prevState) {
        if (!(prevState instanceof CalculatorState)) {
            return; // ignore
        }
        CalculatorState casted = (CalculatorState) prevState;
        // todo: use the CalculatorState to load
        currentState = new ArrayList<>(((CalculatorState) prevState).currentState);
    }

    private static class CalculatorState implements Serializable {
        /*
        TODO: add fields to this class that will store the calculator state
        all fields must only be from the types:
        - primitives (e.g. int, boolean, etc)
        - String
        - ArrayList<> where the type is a primitive or a String
        - HashMap<> where the types are primitives or a String
         */
        private ArrayList<String> currentState = new ArrayList<String>() {{
            add("0");
        }};
    }

    private void insertToCurrentState(String string) {
        currentState.add(string);
    }


}
