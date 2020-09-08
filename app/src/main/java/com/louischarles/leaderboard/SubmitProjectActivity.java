package com.louischarles.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class SubmitProjectActivity extends AppCompatActivity {
    Dialog mConfirmationPopUp;
    Dialog mConfirmationSuccessPopUp;
    Dialog mConfirmationFailurePopUp;
    Button mSubmitButton;
    Button mConfirmButton;
    ImageView mClosePopUp;
    EditText mFirstNameEditText, mLastNameEditText, mEmailEditText, mGithubLinkEditText;

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

//        LayoutInflater inflater = getLayoutInflater();
//        View dialogLayout = inflater.inflate(R.layout.confirmation_popup, null);
        mConfirmationPopUp = new Dialog(this);
        mConfirmationSuccessPopUp = new Dialog(this);
        mConfirmationFailurePopUp = new Dialog(this);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createConfirmationPopUp();
            }
        });

        mFirstNameEditText = findViewById(R.id.first_name_text);
        mLastNameEditText = findViewById(R.id.last_name_text);
        mEmailEditText = findViewById(R.id.email_text);
        mGithubLinkEditText = findViewById(R.id.github_link);
    }

    private FormData createFormData(){
        FormData formData = new FormData();
        formData.setFirstName(String.valueOf(mFirstNameEditText.getText()));
        formData.setLastName(String.valueOf(mLastNameEditText.getText()));
        formData.setEmailAddress(String.valueOf(mEmailEditText.getText()));
        formData.setProjectLink(String.valueOf(mGithubLinkEditText.getText()));
        return formData;
    }

    private void submitFormData(FormData formData) {
        String fName = formData.getFirstName();
        String lName = formData.getLastName();
        String email = formData.getEmailAddress();
        String githubLink = formData.getProjectLink();

        Call<String> submitForm = ApiClient.getGoogleFormApi().submitFormData(fName, lName, email, githubLink);
        submitForm.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                    showConfirmationSuccessfulPopUp();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("-----CHECKING------ ", "Res: " + t.getMessage());
                showConfirmationFailurePopUp();
            }
        });
    }

    public void showConfirmationSuccessfulPopUp() {
        mConfirmationPopUp.dismiss();
        mConfirmationSuccessPopUp.setContentView(R.layout.success_popup);
        mConfirmationSuccessPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationSuccessPopUp.show();
    }

    public void dismissConfirmationPopUp(View view){
        mConfirmationPopUp.dismiss();
    }

    private void showConfirmationFailurePopUp() {
        mConfirmationFailurePopUp.setContentView(R.layout.failure_popup);
        mConfirmationFailurePopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationFailurePopUp.show();
    }
//    https://github.com/louischarles2000/LeaderBoard.git
    private void createConfirmationPopUp() {
        mConfirmationPopUp.setContentView(R.layout.confirmation_popup);
        mClosePopUp = (ImageView) findViewById(R.id.close_confirmation_popup);
        mConfirmationPopUp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mConfirmationPopUp.show();
        mConfirmButton = (Button) mConfirmationPopUp.findViewById(R.id.confirm_submission_button);
        mConfirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mConfirmationPopUp.dismiss();
                submitFormData(createFormData());
            }
        });
    }
}
