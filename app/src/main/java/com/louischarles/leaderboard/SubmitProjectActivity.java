package com.louischarles.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class SubmitProjectActivity extends AppCompatActivity {
    Dialog mConfirmationPopUp;
    Dialog mConfirmationSuccessPopUp;
    Dialog mConfirmationFailurePopUp;
    Button mSubmitButton;
    Button mConfirmButton;
    ImageView mClosePopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_project);
        Toolbar toolbar = (Toolbar) findViewById(R.id.submit_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SubmitProjectActivity.this, HomeActivity.class));
                finish();
            }
        });
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        mConfirmationPopUp = new Dialog(this);
        mConfirmationSuccessPopUp = new Dialog(this);
        mConfirmationFailurePopUp = new Dialog(this);

//        mConfirmButton = (Button) findViewById(R.id.confirm_submission_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createConfirmationPopUp();
            }
        });
//        mConfirmButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showConfirmationSuccessfulPopUp();
//            }
//        });
    }

    public void showConfirmationSuccessfulPopUp() {
        mConfirmationPopUp.dismiss();
        mConfirmationSuccessPopUp.setContentView(R.layout.success_popup);
        mConfirmationSuccessPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationSuccessPopUp.show();
    }

    public void dismissConfirmationPopUp(View view){
        mConfirmationPopUp.dismiss();
        showConfirmationSuccessfulPopUp();
//        showConfirmationFailurePopUp();
    }

    private void showConfirmationFailurePopUp() {
        mConfirmationFailurePopUp.setContentView(R.layout.failure_popup);
        mConfirmationFailurePopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationFailurePopUp.show();
    }

    private void createConfirmationPopUp() {
        mConfirmationPopUp.setContentView(R.layout.confirmation_popup);
        mClosePopUp = (ImageView) findViewById(R.id.close_confirmation_popup);
        mConfirmationPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationPopUp.show();
    }
}
