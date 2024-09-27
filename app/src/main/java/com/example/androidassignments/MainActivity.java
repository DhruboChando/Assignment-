package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Switch veg_switch, cutlery_switch;
    private CheckBox check1, check2, check3, check4, top1, top2, top3, top4;
    private RadioGroup pizza_size;
    private SeekBar spice_level;
    private LinearLayout non_veg_layout1, non_veg_layout2, coupon_layout;
    private Button confirm_order;
    private TextView m1, m2, m3, m4, p1, p2, p3, p4, tv1, tv2, tv3, tv4;
    private Spinner location;
    private String size = "";
    private ArrayList<String> toppings = new ArrayList<>();
    private String spiceLevel = "";
    private ArrayList<String> selectedPizzas = new ArrayList<>();
    private String selectedLocation = "";
    private AlertDialog.Builder builder;
    private HashMap<String, Integer> name_quantity_pair = new HashMap<>();
    TextView pizza1;
    TextView pizza2;
    TextView pizza3;
    TextView pizza4;
    int[] price = {300, 200, 300, 100};
    Button rate;

    // Initialize quantity globally
    private int quantity1 = 0;
    private int quantity2 = 0;
    private int quantity3 = 0;
    private int quantity4 = 0;
    private int totalPrice = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views
        veg_switch = findViewById(R.id.veg_only);
        cutlery_switch = findViewById(R.id.cutlery);

        check1 = findViewById(R.id.checkbox1);
        check2 = findViewById(R.id.checkbox2);
        check3 = findViewById(R.id.checkbox3);
        check4 = findViewById(R.id.checkbox4);

        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);
        top4 = findViewById(R.id.top4);

        pizza_size = findViewById(R.id.radio_group);

        spice_level = findViewById(R.id.seek_bar);

        non_veg_layout1 = findViewById(R.id.pizza1_layout);
        non_veg_layout2 = findViewById(R.id.pizza3_layout);

        coupon_layout = findViewById(R.id.cupoun);

        confirm_order = findViewById(R.id.btn_confirm_order);

        m1 = findViewById(R.id.minus_1);
        m2 = findViewById(R.id.minus_2);
        m3 = findViewById(R.id.minus_3);
        m4 = findViewById(R.id.minus_4);

        p1 = findViewById(R.id.plus_1);
        p2 = findViewById(R.id.plus_2);
        p3 = findViewById(R.id.plus_3);
        p4 = findViewById(R.id.plus_4);

        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        tv3 = findViewById(R.id.tv_3);
        tv4 = findViewById(R.id.tv_4);

        location = findViewById(R.id.location_spinner);

        pizza1 = findViewById(R.id.pizza1_name);
        pizza2 = findViewById(R.id.pizza2_name);
        pizza3 = findViewById(R.id.pizza3_name);
        pizza4 = findViewById(R.id.pizza4_name);

        builder = new AlertDialog.Builder(this);

        coupon_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sorry!!!\nWe currently don't accept any coupon\nYou can use your coupon leter.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        cutlery_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, "Cutlery added :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Veg Switch functionality
        veg_switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(MainActivity.this, "Only Veg pizzas enabled", Toast.LENGTH_SHORT).show();
                non_veg_layout1.setVisibility(View.GONE);
                non_veg_layout2.setVisibility(View.GONE);
            } else {
                non_veg_layout1.setVisibility(View.VISIBLE);
                non_veg_layout2.setVisibility(View.VISIBLE);
            }
        });

        // Quantity handling for pizza1
        p1.setOnClickListener(v -> updateQuantity(pizza1.getText().toString(), check1.isChecked(), tv1, 1));
        m1.setOnClickListener(v -> decreaseQuantity(pizza1.getText().toString(), check1.isChecked(), tv1, 1));

        // Quantity handling for pizza2
        p2.setOnClickListener(v -> updateQuantity(pizza2.getText().toString(), check2.isChecked(), tv2, 2));
        m2.setOnClickListener(v -> decreaseQuantity(pizza2.getText().toString(), check2.isChecked(), tv2, 2));

        // Quantity handling for pizza3
        p3.setOnClickListener(v -> updateQuantity(pizza3.getText().toString(), check3.isChecked(), tv3, 3));
        m3.setOnClickListener(v -> decreaseQuantity(pizza3.getText().toString(), check3.isChecked(), tv3, 3));

        // Quantity handling for pizza4
        p4.setOnClickListener(v -> updateQuantity(pizza4.getText().toString(), check4.isChecked(), tv4, 4));
        m4.setOnClickListener(v -> decreaseQuantity(pizza4.getText().toString(), check4.isChecked(), tv4, 4));

        // Pizza size selection
        RadioGroup pizza_size = findViewById(R.id.radio_group);
        int selectedId = pizza_size.getCheckedRadioButtonId(); // This will return -1 if no selection is made
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedSize = selectedRadioButton.getText().toString();
            // Use selectedSize in your logic
        } else {
            Log.e("MainActivity", "No radio button selected!");
            // Handle no selection case here
        }

        // Seek bar for spice level
        spice_level.setMax(5);
        spice_level.setProgress(1);
        spice_level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String[] spiceLevels = {"Plain", "Medium", "Hot", "Super spicy", "Super super spicy"};
                spiceLevel = spiceLevels[progress];
                Toast.makeText(MainActivity.this, "Spice level  " + spiceLevel, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Location Spinner setup
        String[] locations = {"-none-", "Modina Market", "Pathantula", "Sust Gate", "Subid Bazar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        location.setAdapter(adapter);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = adapter.getItem(position);
                if (!selectedLocation.equals("-none-")) {
                    Toast.makeText(MainActivity.this, "Selected location " + selectedLocation, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Confirm order button
        confirm_order.setOnClickListener(v -> confirmOrder());

        // Rate button
        rate = findViewById(R.id.btn_rate);
        rate.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), RatePizza.class)));
    }

    private void updateQuantity(String name, boolean pizza_checking, TextView tv, int pizzaNumber) {
        if (pizza_checking) {
            int quantity = getQuantityByNumber(pizzaNumber);
            quantity++;
            tv.setText(String.valueOf(quantity));
            name_quantity_pair.put(name, quantity);
        } else {
            Toast.makeText(this, "Check desired pizza first", Toast.LENGTH_SHORT).show();
        }
    }

    private void decreaseQuantity(String name, boolean pizza_checking, TextView tv, int pizzaNumber) {
        if (pizza_checking) {
            int quantity = getQuantityByNumber(pizzaNumber);
            if (quantity > 0) {
                quantity--;
                tv.setText(String.valueOf(quantity));
                name_quantity_pair.put(name, quantity);
            }
        } else {
            Toast.makeText(this, "Check desired pizza first", Toast.LENGTH_SHORT).show();
        }
    }

    private int getQuantityByNumber(int pizzaNumber) {
        switch (pizzaNumber) {
            case 1:
                return quantity1++;
            case 2:
                return quantity2++;
            case 3:
                return quantity3++;
            case 4:
                return quantity4++;
            default:
                return 0;
        }
    }

    private void confirmOrder() {
        int totalPrice = 0;

        // Calculate the total price based on the quantity of each pizza
        if (check1.isChecked()) {
            totalPrice += quantity1 * price[0]; // price[0] corresponds to pizza1
        }
        if (check2.isChecked()) {
            totalPrice += quantity2 * price[1]; // price[1] corresponds to pizza2
        }
        if (check3.isChecked()) {
            totalPrice += quantity3 * price[2]; // price[2] corresponds to pizza3
        }
        if (check4.isChecked()) {
            totalPrice += quantity4 * price[3]; // price[3] corresponds to pizza4
        }

        final int total_price = totalPrice;

        //confirm dialog
        builder.setMessage("Your total order price is " + total_price + " TK.\nAre you sure you want to confirm your order?")
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {

                    //payment dialog after the first confirmation
                    AlertDialog.Builder paymentDialog = new AlertDialog.Builder(this);
                    paymentDialog.setTitle("Payment")
                            .setMessage("Send " + total_price + " TK to Pizza Hut")
                            .setCancelable(false)
                            .setPositiveButton("Confirm", (paymentDialogInterface, which) -> {
                                paymentDialogInterface.cancel();

                                //resetting the checkboxes, radio buttons
                                check1.setChecked(false);
                                check2.setChecked(false);
                                check3.setChecked(false);
                                check4.setChecked(false);

                                top1.setChecked(false);
                                top2.setChecked(false);
                                top3.setChecked(false);
                                top4.setChecked(false);

                                pizza_size.clearCheck();

                                location.setSelection(0);
                                cutlery_switch.setChecked(false);

                                Toast.makeText(this, "Payment received!!!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, "Your pizza will be delivered in 30 minutes.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(this, "\"Do rate our pizza,\nIt will help us improve our service\"", Toast.LENGTH_SHORT).show();
                            })
                            .show();
                })
                .setNegativeButton("No", (dialog, id) ->{
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);

                    top1.setChecked(false);
                    top2.setChecked(false);
                    top3.setChecked(false);
                    top4.setChecked(false);

                    pizza_size.clearCheck();

                    location.setSelection(0);
                    cutlery_switch.setChecked(false);

                    Toast.makeText(this, "Order cancelled!!!", Toast.LENGTH_SHORT).show();

                    dialog.cancel();
                });

        //show confirm dialog
        AlertDialog alert = builder.create();
        alert.setTitle("Confirm your order");
        alert.show();

    }

}
