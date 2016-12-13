package com.gmail.jandoant.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees, pricePerCoffee, price;
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
        numberOfCoffees = 2;
        pricePerCoffee = 5;
        clientName = null;

        //initUI
        editText_clientName = (EditText) findViewById(R.id.editText_clientName);
        txt_quantity = (TextView) findViewById(R.id.txt_quantity);
        txt_order = (TextView) findViewById(R.id.txt_order_summary);
        chkBox_cream = (CheckBox) findViewById(R.id.chkBox_cream);
        chkBox_chocolate = (CheckBox) findViewById(R.id.chkBox_chocolate);
        displayQuantity();
    }

    public void submitOrder(View view) {
        assembleOrder();
        displayOrderSummary();
    }

    private void assembleOrder() {
        hasWhippedCream = chkBox_cream.isChecked();
        hasChocolate = chkBox_chocolate.isChecked();
        clientName = String.valueOf(editText_clientName.getText());
        calulatePrice();
    }

    public void addCoffee(View view) {
        numberOfCoffees++;
        displayQuantity();
    }

    public void removeCoffee(View view) {

        //keine negativen Kaffeemengen
        if (numberOfCoffees <= 1) {
            numberOfCoffees = 0;
        } else {
            numberOfCoffees--;
        }

        displayQuantity();
    }

    private void calulatePrice() {
        //Pure Coffee Price
        price = numberOfCoffees * pricePerCoffee;
        //Extra Charge
        if (hasWhippedCream) {
            price += 1;
        }
        if (hasChocolate) {
            price += 2;
        }
    }

    private void displayOrderSummary() {

        txt_order.setText(
                "Name: " + clientName + "\n" +
                        "Add Whipped Cream? " + hasWhippedCream + "\n" +
                        "Add Chocolate? " + hasChocolate + "\n" +
                        "Quantity: " + numberOfCoffees + "\n" +
                        "Total: " + price + "$\n" +
                        "Thank you!");
    }

    private void displayQuantity() {
        txt_quantity.setText("" + numberOfCoffees);
    }


}
