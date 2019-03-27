package com.teashop.teacharge;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ActivityItemCalculator extends AppCompatActivity {

    TextView numberDisplay,operationsDisplay;
    HorizontalScrollView scrollerDisplayNumber, scrollerDisplayOperations;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonCE, buttonRoot, buttonSin, buttonCos, buttonBracketsOpen, buttonBracketsClose, buttonTan, buttonPI, buttonExponentation, buttonSum, buttonSubtraction, buttonMultiplication, buttonDivision;
    String stringNumber, stringSpecial;
    ImageButton buttonEqual;
    Expression expression;
    int notificationId=001;
    double value=0;
    boolean numberClicked=false;
    //android.support.v7.app.NotificationCompat.Builder resultNotification;
    ClipboardManager clipboard;
    ClipData clip;
    int charBracketOpenCount=0, charBracketCloseCount=0, charInExceed=0, dotCount=0;
    char bracketOpen='(';
    char bracketClose=')';
    Button button;
   //DialogFragment dialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_itemcalulation);

//        dialogFragment = (DialogFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.godLayout);

      //  numberDisplay = (TextView) findViewById(R.id.displayNumber);
        //operationsDisplay = (TextView) findViewById(R.id.displayOperationNumber);
        // scrollerDisplayNumber = (HorizontalScrollView) findViewById(R.id.displayNumberScroller);
        // scrollerDisplayOperations = (HorizontalScrollView) findViewById(R.id.displayOperationNumberScroller);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button0 = (Button) findViewById(R.id.button0);
//        buttonCE = (Button) findViewById(R.id.buttonCE);
//        buttonRoot = (Button) findViewById(R.id.buttonRoot);
//        buttonSin = (Button) findViewById(R.id.buttonSin);
//        buttonCos = (Button) findViewById(R.id.buttonCos);
//        buttonBracketsOpen = (Button) findViewById(R.id.buttonBracketsOpen);
//        buttonBracketsClose = (Button) findViewById(R.id.buttonBracketsClose);
//        buttonTan = (Button) findViewById(R.id.buttonTan);
//        buttonPI = (Button) findViewById(R.id.buttonPI);
//        buttonExponentation = (Button) findViewById(R.id.buttonExponentation);
//        buttonSum = (Button) findViewById(R.id.buttonSum);
//        buttonSubtraction = (Button) findViewById(R.id.buttonSubtraction);
//        buttonMultiplication = (Button) findViewById(R.id.buttonMultiplication);
//        buttonDivision = (Button) findViewById(R.id.buttonDivision);


       // buttonEqual = (ImageButton) findViewById(R.id.buttonEqaul);
//        resultNotification = new android.support.v7.app.NotificationCompat.Builder(this);

    //    getSupportActionBar().setElevation(0);
//        clickButtonCE();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        stringSpecial = numberDisplay.getText().toString();
        outState.putString("numberDisplay", stringSpecial);

//        stringSpecial = operationsDisplay.getText().toString();
//        outState.putString("operationsDisplay", stringSpecial);

        outState.putDouble("resultValue", value);

        outState.putBoolean("numberClicked", numberClicked);

        outState.putInt("bracketOpenCount", charBracketOpenCount);
        outState.putInt("bracketCloseCOunt", charBracketCloseCount);
        outState.putInt("charInExceed", charInExceed);
        outState.putInt("dotCount", dotCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        numberDisplay.setText(savedInstanceState.getString("numberDisplay"));
        //operationsDisplay.setText(savedInstanceState.getString("operationsDisplay"));

        value = savedInstanceState.getDouble("resultValue");

        numberClicked = savedInstanceState.getBoolean("numberClicked");

        charBracketOpenCount = savedInstanceState.getInt("bracketOpenCount");
        charBracketCloseCount = savedInstanceState.getInt("bracketCloseCount");
        charInExceed = savedInstanceState.getInt("charInExceed");
        dotCount = savedInstanceState.getInt("dotCount");
    }

    public void clickButton1(View v) {

        numberDisplay.setText(numberDisplay.getText()+"1");
        // operationsDisplay.setText(operationsDisplay.getText()+"1");
        numberClicked=true;
    }

    public void clickButton2(View v) {

        numberDisplay.setText(numberDisplay.getText()+"2");
        //operationsDisplay.setText(operationsDisplay.getText()+"2");
        numberClicked=true;
    }

    public void clickButton3(View v) {

        numberDisplay.setText(numberDisplay.getText()+"3");
        //  operationsDisplay.setText(operationsDisplay.getText()+"3");
        numberClicked=true;
    }

    public void clickButton4(View v) {

        numberDisplay.setText(numberDisplay.getText()+"4");
        //   operationsDisplay.setText(operationsDisplay.getText()+"4");
        numberClicked=true;
    }

    public void clickButton5(View v) {

        numberDisplay.setText(numberDisplay.getText()+"5");
        //  operationsDisplay.setText(operationsDisplay.getText()+"5");
        numberClicked=true;
    }

    public void clickButton6(View v) {

        numberDisplay.setText(numberDisplay.getText()+"6");
        //  operationsDisplay.setText(operationsDisplay.getText()+"6");
        numberClicked=true;
    }

    public void clickButton7(View v) {

        numberDisplay.setText(numberDisplay.getText()+"7");
        //operationsDisplay.setText(operationsDisplay.getText()+"7");
        numberClicked=true;
    }

    public void clickButton8(View v) {

        numberDisplay.setText(numberDisplay.getText()+"8");
        // operationsDisplay.setText(operationsDisplay.getText()+"8");
        numberClicked=true;
    }

    public void clickButton9(View v) {

        numberDisplay.setText(numberDisplay.getText()+"9");
        //  operationsDisplay.setText(operationsDisplay.getText()+"9");
        numberClicked=true;
    }

    public void clickButton0(View v) {

        numberDisplay.setText(numberDisplay.getText()+"0");
        //operationsDisplay.setText(operationsDisplay.getText()+"0");
        numberClicked=true;
    }

    public void clickButtonDot(View v) {

        //   stringSpecial = operationsDisplay.getText().toString();

        if (numberClicked == false) {

        }

        else if(stringSpecial.endsWith("(") || stringSpecial.endsWith("+") || stringSpecial.endsWith("-") || stringSpecial.endsWith("*") || stringSpecial.endsWith("/")) {

        }

        else {
            if (dotCount == 1) {

            } else {
                dotCount++;
                //   operationsDisplay.setText(operationsDisplay.getText() + ".");
                stringSpecial = numberDisplay.getText().toString();
                if(!stringSpecial.contains(".")) {
                    numberDisplay.setText(numberDisplay.getText() + "."); }
            }
        }
    }





    public void checkBracketsNumber() {

        charBracketCloseCount=0;
        charBracketOpenCount=0;
        charInExceed=0;

        for (int i = 0; i < stringNumber.length(); i++) {
            if (stringNumber.charAt(i) == bracketOpen)
                charBracketOpenCount++;

            else if (stringNumber.charAt(i) == bracketClose)
                charBracketCloseCount++;
        }

        if (charBracketOpenCount == charBracketCloseCount) {

        }

        else if (charBracketOpenCount > charBracketCloseCount) {
            charInExceed = charBracketOpenCount - charBracketCloseCount;

            for (int j = 0; j < charInExceed; j++) {
                stringNumber = stringNumber + ")";
            }
        }
    }

    public void clickButtonEqual(View v) {

        if(numberClicked==false) {

        }

        else {

            //stringNumber = operationsDisplay.getText().toString();

            checkBracketsNumber();

            if(charBracketCloseCount > charBracketOpenCount) {
                numberDisplay.setText("Invalid expression");
            }

            else if(stringNumber.contains("Infinity")) {
                numberDisplay.setText("Infinity");
            }

            else {

                expression = new ExpressionBuilder(stringNumber).build();

                try {
                    value = expression.evaluate();
                    numberDisplay.setText(Double.toString(value));
                }

                catch (ArithmeticException e) {
                    numberDisplay.setText("Can't divide by 0");
                }

                buttonCE.setText("CLR");
//
//                NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//
//                Intent intent = new Intent(this, ActivityCalculator.class);
//                PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//
//                resultNotification.setSmallIcon(R.drawable.ic_stat_name);
//                resultNotification.setContentTitle("Last equation solved");
//                resultNotification.setContentText(stringNumber + " = " + Double.toString(value));
//                resultNotification.setWhen(System.currentTimeMillis());
//                resultNotification.setColor(rgb(165, 0, 229));
//                resultNotification.setContentIntent(pIntent);
//
//                notifyManager.notify(notificationId, resultNotification.build());
            }
        }

    }

    public void clickNumberDisplay(View v) {

        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clip = ClipData.newPlainText("number", numberDisplay.getText().toString());
        clipboard.setPrimaryClip(clip);
      //  Toast.makeText(ActivityItemCalculator.this, "Copied result to clipboard", Toast.LENGTH_SHORT).show();
    }



}

