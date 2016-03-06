package com.xosel.coffeechain;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

import static android.app.PendingIntent.getActivity;

public class Buy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Que la pases muy contenta en este día tan feliz y que cumplas muchos años más!!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    /*
    This method is called when teh order button is clicked
    * */
    public void submitOrder(View view){

        //calculateOrder();

        openAlert(view);
    }

    public void calculateOrder(){
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);

        TextView priceTextView = (TextView) findViewById(
                R.id.price_text_view);


        int total = Integer.parseInt(quantityTextView.getText().toString()) *
                Integer.parseInt(priceTextView.getText().toString());

        display(total);
    }

    public void increment(View view){
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);

        int qty = Integer.parseInt(quantityTextView.getText().toString());
        qty = qty + 1;

        displayQuantity(qty);
        calculateOrder();
    }

    public void decrement(View view){
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);

        int qty = Integer.parseInt(quantityTextView.getText().toString());

        if (qty!=0)
            qty = qty -1;

        displayQuantity(qty);
        calculateOrder();
    }


    /*
   This method displays the given quantity value on the screen
   * */
    private void displayQuantity(int number){
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText(""+number);
    }

    /*
    This method displays the given total value on the screen
    * */
    private void display(int number){

        TextView quantityTextView = (TextView) findViewById(
                R.id.total_text_view);

        quantityTextView.setText("" + NumberFormat.getCurrencyInstance().format(number));

    }

    private void openAlert(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Buy.this);
        alertDialogBuilder.setTitle("Thank you!");

        // set positive button: Yes message
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // go to a new activity of the app
                //Intent positveActivity = new Intent(getApplicationContext(),
                //        PositiveActivity.class);
                //startActivity(positveActivity);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();

        // show alert
        alertDialog.show();
    }


}
