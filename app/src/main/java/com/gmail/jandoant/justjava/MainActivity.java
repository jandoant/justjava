package com.gmail.jandoant.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees, pricePerCoffee, pricePerCream, pricePerChocolate, price;
    private EditText editText_clientName;
    private TextView txt_quantity, txt_order;
    private CheckBox chkBox_cream, chkBox_chocolate;
    private String clientName;
    private boolean hasWhippedCream, hasChocolate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial Values
        numberOfCoffees = 0;
        pricePerCoffee = 5;
        pricePerCream = 1;
        pricePerChocolate = 2;
        clientName = null;

        //initUI
        editText_clientName = (EditText) findViewById(R.id.editText_clientName);
        txt_quantity = (TextView) findViewById(R.id.txt_quantity);
        txt_order = (TextView) findViewById(R.id.txt_order_summary);
        chkBox_cream = (CheckBox) findViewById(R.id.chkBox_cream);
        chkBox_chocolate = (CheckBox) findViewById(R.id.chkBox_chocolate);
        displayQuantity();
    }

    public void OnSubmitOrder(View view) {
        assembleOrder();
        //displayOrderSummary();
        sendOrderMail();
    }

    private void sendOrderMail() {
        String orderMessage = "<h1>" + createOrderMessage() + "</h1>";

        //Impliziter Intent um Bestellung als Mail zu verschicken
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        sendIntent.putExtra(Intent.EXTRA_TEXT, orderMessage);
        sendIntent.setType("text/html");
        startActivity(sendIntent);

    }

    private void assembleOrder() {
        hasWhippedCream = chkBox_cream.isChecked();
        hasChocolate = chkBox_chocolate.isChecked();
        clientName = String.valueOf(editText_clientName.getText());
        calulatePrice();
    }

    private String createOrderMessage() {

        String orderMessage = "";

        orderMessage += "Name: " + clientName + "\n";
        orderMessage += "Add Whipped Cream? " + hasWhippedCream + "\n";
        orderMessage += "Add Chocolate? " + hasChocolate + "\n";
        orderMessage += "Quantity: " + numberOfCoffees + "\n";
        orderMessage += "Total: " + price + "$\n";
        orderMessage += "Thank you!";

        return orderMessage;
    }

    public void OnAddCoffee(View view) {
        numberOfCoffees++;
        displayQuantity();
    }

    public void OnRemoveCoffee(View view) {

        //keine negativen Kaffeemengen
        if (numberOfCoffees <= 1) {
            numberOfCoffees = 0;
        } else {
            numberOfCoffees--;
        }

        displayQuantity();
    }

    private void calulatePrice() {
        //Extra Charge
        if (hasWhippedCream) {
            pricePerCoffee += pricePerCream;
        }
        if (hasChocolate) {
            pricePerCoffee += pricePerChocolate;
        }
        //Complete Coffee Price
        price = numberOfCoffees * pricePerCoffee;
    }

    private void displayOrderSummary() {
        txt_order.setText(createOrderMessage());
    }

    private void displayQuantity() {
        txt_quantity.setText("" + numberOfCoffees);
    }


}
