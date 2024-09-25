package com.example.androidassignments;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RatePizza extends AppCompatActivity {

    private RatingBar ratingBar;
    private TextView selectedRatingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_pizza);

        ratingBar = findViewById(R.id.rating_bar);
        selectedRatingText = findViewById(R.id.selected_rating);

        // Set up listener for RatingBar
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // Display the selected rating in the TextView
                selectedRatingText.setText("Your rating: " + rating);

                // Optional: Show a Toast message with the rating
                Toast.makeText(getApplicationContext(), "You rated: " + rating,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}