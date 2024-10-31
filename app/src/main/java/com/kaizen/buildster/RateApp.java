package com.kaizen.buildster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateApp extends AppCompatActivity {

    RatingBar ratingBar;
    TextView rateMessage;
    String ratedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_app);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        rateMessage = (TextView)findViewById(R.id.rateMessage);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                ratedValue = String.valueOf(ratingBar.getRating());
                rateMessage.setText("Thanks for rating us at : " + ratedValue + " out of 6");
            }
        });
    }
}
