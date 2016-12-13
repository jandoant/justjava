package com.gmail.jandoant.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numberOfCoffees, pricePerCoffee, price;
    TextView txt_quantity, txt_price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initial Values
        numberOfCoffees = 2;
        pricePerCoffee = 5;

        //initUI
        txt_quantity = (TextView) findViewById(R.id.txt_quantity);
        txt_price = (TextView) findViewById(R.id.txt_price);
        updateOrder();
    }

    public void submitOrder(View view) {
        updateOrder();
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

    private void updatePrice() {
        price = numberOfCoffees * pricePerCoffee;
    }

    private void displayPrice() {
        txt_price.setText("$" + price);
    }

    private void displayQuantity() {
        txt_quantity.setText("" + numberOfCoffees);
    }

    private void updateOrder() {
        displayQuantity();
        updatePrice();
        displayPrice();
    }


}
