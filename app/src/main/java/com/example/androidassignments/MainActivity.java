package com.example.androidassignments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Switch veg_switch, cutlary_switch;
    private CheckBox check1, check2, check3, check4, top1, top2, top3, top4;
    private RadioGroup pizza_size;
    private SeekBar spice_level;
    private LinearLayout non_veg_layout1, non_veg_layout2, coupon_layout;
    private Button confirm_order;
    private TextView m1, m2, m3, m4, p1, p2, p3, p4, tv1, tv2, tv3, tv4;
    private Spinner location;
    private String size = "";
    private ArrayList<String> toppings = new ArrayList<>();
    private ArrayList<String> spiceLevel = new ArrayList<>();
    private ArrayList<String> selectedPizzas = new ArrayList<>();
    private String selectedLocation = "";
    private AlertDialog.Builder builder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //initiating all the views
        veg_switch = findViewById(R.id.veg_only);
        cutlary_switch = findViewById(R.id.cutlery);

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

        TextView pizza1 = findViewById(R.id.pizza1_name);
        TextView pizza2 = findViewById(R.id.pizza2_name);
        TextView pizza3 = findViewById(R.id.pizza3_name);
        TextView pizza4 = findViewById(R.id.pizza4_name);

        //implementing codes
        veg_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    Toast.makeText(MainActivity.this, "Only Veg pizzas enabled",
                            Toast.LENGTH_SHORT).show();

                    non_veg_layout1.setVisibility(View.GONE);
                    non_veg_layout2.setVisibility(View.GONE);
                }
                else{
                    non_veg_layout1.setVisibility(View.VISIBLE);
                    non_veg_layout2.setVisibility(View.VISIBLE);
                }
            }
        });


        if(check1.isChecked()){
            selectedPizzas.add(pizza1.getText().toString());
        }
        else if(check2.isChecked()){
            selectedPizzas.add(pizza2.getText().toString());
        }
        else if(check3.isChecked()){
            selectedPizzas.add(pizza3.getText().toString());
        }
        else if(check4.isChecked()){
            selectedPizzas.add(pizza4.getText().toString());
        }

        int[] quantity1 = {0};
        int[] quantity2 = {0};
        int[] quantity3 = {0};
        int[] quantity4 = {0};

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(true, check1, tv1, quantity1);
            }
        });

        m1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(false, check1, tv1, quantity1);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(true, check2, tv2, quantity2);
            }
        });

        m2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(false, check2, tv2, quantity2);
            }
        });

        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(true, check3, tv3, quantity3);
            }
        });

        m3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(false, check3, tv3, quantity3);
            }
        });

        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(true, check4, tv4, quantity4);
            }
        });

        m4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(false, check4, tv4, quantity4);
            }
        });


        if(top1.isChecked()) {
            toppings.add(top1.getText().toString());
        }
        else {
            toppings.remove(top1.getText().toString());
        }
        if(top2.isChecked()) {
            toppings.add(top2.getText().toString());
        }
        else {
            toppings.remove(top2.getText().toString());
        }
        if(top3.isChecked()) {
            toppings.add(top3.getText().toString());
        }
        else {
            toppings.remove(top3.getText().toString());
        }
        if(top4.isChecked()) {
            toppings.add(top4.getText().toString());
        }
        else {
            toppings.remove(top4.getText().toString());
        }

        pizza_size.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedRadioButton = findViewById(checkedId);

                size = selectedRadioButton.getText().toString();

                Toast.makeText(getApplicationContext(), "Selected Size: " + size,
                        Toast.LENGTH_SHORT).show();
            }
        });

        spice_level.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == spice_level.getMax()) {
                    spiceLevel.add("Super super spicy");
                    Toast.makeText(MainActivity.this, "O Maa go\nato besi JHALLL :'''",
                            Toast.LENGTH_SHORT).show();
                }
                else if(progress >= spice_level.getMax()/2 || progress <= (spice_level.getMax()/4) * 3) {
                    spiceLevel.add("Hot");
                    Toast.makeText(MainActivity.this, "Mutamuti JHAL",
                            Toast.LENGTH_SHORT).show();
                }
                else if(progress >= (spice_level.getMax()/4) * 3 || progress == spice_level.getMax()) {
                    spiceLevel.add("Super spicy");
                    Toast.makeText(MainActivity.this, "valoi JHALL",
                            Toast.LENGTH_SHORT).show();
                }
                else if(progress >= spice_level.getMax()/4 || progress <= (spice_level.getMax()/2)) {
                    spiceLevel.add("Medium");
                    Toast.makeText(MainActivity.this, "ato JHAL na ",
                            Toast.LENGTH_SHORT).show();
                }
                else if(progress >= 0 || progress <= spice_level.getMax()/4) {
                    spiceLevel.add("Plain");
                    Toast.makeText(MainActivity.this, "JHAL nai oi",
                            Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cutlary_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(MainActivity.this, "Cutlary added :)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        coupon_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sorry!!!\nWe currently don't accept any coupon\nYou can use your coupon leter.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        String[] locations = {"-none-", "Modina Market", "Pathantula", "Sust Gate", "Subid Bazar"};

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locations);

        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

        location.setAdapter(adapter);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLocation = adapter.getItem(position).toString();

                if(!selectedLocation.equals("-none-")) {
                    Toast.makeText(MainActivity.this, "Selected location " + selectedLocation,
                            Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button rate = findViewById(R.id.btn_rate);

        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RatePizza.class);
                startActivity(intent);
            }
        });

        final int[] totalprice = {0};

        confirm_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quantity1[0]==0 && quantity2[0]==0 && quantity3[0]==0 && quantity4[0]==0) {
                    Toast.makeText(MainActivity.this, "Please add quantity",
                            Toast.LENGTH_SHORT).show();
                }
                else if(selectedLocation.equals("-none-") || selectedLocation.equals("")) {
                    Toast.makeText(MainActivity.this, "Please select location",
                            Toast.LENGTH_SHORT).show();
                }
                else if(size.equals("")) {
                    Toast.makeText(MainActivity.this, "Please select size",
                            Toast.LENGTH_SHORT).show();
                }
                else if(toppings.get(0).isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please select toppings",
                            Toast.LENGTH_SHORT).show();
                }
                else if(spiceLevel.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You didnot specify spice level",
                            Toast.LENGTH_SHORT).show();
                }
                else if(selectedPizzas.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Pizza not selected",
                            Toast.LENGTH_SHORT).show();
                }
//                else if(!selectedPizzas.isEmpty()) {
//                    if(selectedPizzas.get(0).equals(pizza1.getText().toString())){
//                        totalprice[0] = 300*quantity1[0];
//                    }
//                    else if(selectedPizzas.get(0).equals(pizza2.getText().toString())){
//                        totalprice[0] = 200*quantity2[0];
//                    }
//                    else if(selectedPizzas.get(0).equals(pizza3.getText().toString())){
//                        totalprice[0] = 300*quantity3[0];
//                    }
//                    else if(selectedPizzas.get(0).equals(pizza4.getText().toString())){
//                        totalprice[0] = 100*quantity4[0];
//                    }
//                }
                else {
                    builder.setTitle("Confirm order")
                            .setMessage("Oredr summary:\nSelected pizza:" + selectedPizzas.get(0) +
                                    "\nSize:" + size + "\nToppings:" + toppings.get(0) + "\nSpice level:" + spiceLevel +
                                    "\nDelivery location:" + selectedLocation + "\n\tTotal price:" )

                            .setCancelable(false)
                            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();

                                    check1.setChecked(false);
                                    check2.setChecked(false);
                                    check3.setChecked(false);
                                    check4.setChecked(false);

                                    top1.setChecked(false);
                                    top2.setChecked(false);
                                    top3.setChecked(false);
                                    top4.setChecked(false);

                                    pizza_size.clearCheck();
                                }
                            }).show();
                }
            }
        });
    }

    private void updateQuantity(boolean isIncrement, CheckBox checkBox, TextView textView, int[] quantity) {
        if (checkBox.isChecked()) {
            if (isIncrement) {
                quantity[0]++;
            } else {
                if (quantity[0] > 0) {
                    quantity[0]--;
                }
            }
            textView.setText(String.valueOf(quantity[0]));
        } else {
            Toast.makeText(MainActivity.this, "Please select pizza first",
                    Toast.LENGTH_SHORT).show();
        }
    }
}