package com.foodie.foodographer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.util.Log;
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
public class ResetPassword extends AppCompatActivity {
    private Button goto_email;
    private EditText user_reset_enter_email;
    private FirebaseAuth checkAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        checkAuth=FirebaseAuth.getInstance();
        goto_email = (Button) findViewById(R.id.send_email);
        user_reset_enter_email=(EditText)findViewById(R.id.email_box);

        goto_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_input=user_reset_enter_email.getText().toString();
                if(TextUtils.isEmpty(email_input)){
                    Toast.makeText(ResetPassword.this, "Enter the email please",Toast.LENGTH_SHORT).show();
                }else{
                    checkAuth.sendPasswordResetEmail(email_input).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetPassword.this, "Success, Check your email",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPassword.this,LogIn.class));
                            }else{
                                String bug_error=task.getException().getMessage();
                                Toast.makeText(ResetPassword.this, "Error Something went wrong!"+bug_error,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}

