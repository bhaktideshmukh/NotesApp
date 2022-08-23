package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private EditText forgotmailaddress;
    private Button recoverbutton;
    private TextView backtologin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        getSupportActionBar().hide();

        forgotmailaddress=findViewById(R.id.editTextmailAddress);
        recoverbutton=findViewById(R.id.forgotpassword_button);
        backtologin=findViewById(R.id.backtologin);

        firebaseAuth=FirebaseAuth.getInstance();

        backtologin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forgotpassword.this,MainActivity.class);
                startActivity(intent);
            }
        });

        recoverbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = forgotmailaddress.getText().toString().trim();
                if(mail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter your mail first",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Mail is sent, you can recover your password",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotpassword.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"email is wrong or account does not exist",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}