package com.foodie.foodographer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firsteverapp.auth.R;

public class Log_inActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginBut;
    private EditText user_input_email;
    private EditText user_input_password;
    private TextView loginText;
    private ProgressDialog progressDialog2;
    private FirebaseAuth mAuth2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth2=FirebaseAuth.getInstance();
        if(mAuth2.getCurrentUser()!= null){
            //go into to profile page
            finish();

            startActivity(new Intent(getApplicationContext(), profileforuser.class));
        }
        progressDialog2= new ProgressDialog(this);
        user_input_email = (EditText) findViewById(R.id.user_enter_email);
        user_input_password = (EditText) findViewById(R.id.user_enter_password);
        loginBut= (Button) findViewById(R.id.loginRegister);
        loginText=(TextView) findViewById(R.id.hint_signup);
        loginBut.setOnClickListener(this);
        loginText.setOnClickListener(this);
    }

    private void userLogin(){
        String email2=user_input_email.getText().toString().trim();
        String password2=user_input_password.getText().toString().trim();
        if(TextUtils.isEmpty(email2)){
            Toast.makeText(this,"Please enter an email !",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this,"Please enter password !",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog2.setMessage("Login Account...");
        progressDialog2.show();
        mAuth2.signInWithEmailAndPassword(email2,password2)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //progressDialog2.dismiss();
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), profileforuser.class));
                        }else {
                            // If sign in fails, display a message to the user.

                        }
                        progressDialog2.dismiss();

                    }
                });
    }
    @Override
    public void onClick(View view){
        if(view==loginBut){
            userLogin();
        }
        if(view ==loginText){
            finish();
            startActivity(new Intent(this,SignupActivity.class));
        }
    }
}
