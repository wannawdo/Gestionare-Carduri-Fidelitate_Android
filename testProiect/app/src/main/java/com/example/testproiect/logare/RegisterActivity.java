package com.example.testproiect.logare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.MainActivity;
import com.example.testproiect.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterActivity extends AppCompatActivity {
    EditText etUser, etNume, etEmail,  etParola;
    Button btnRegister;
    TextView tvLogIn;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializareComponente();
        mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user = etUser.getText().toString();
                final String nume = etNume.getText().toString();
                final String email = etEmail.getText().toString();
                final String parola = etParola.getText().toString();

                if (user.isEmpty()) {
                    etUser.setError(getString(R.string.errUsername));
                    etUser.requestFocus();
                } else if (nume.isEmpty()) {
                    etNume.setError(getString(R.string.errNume));
                    etNume.requestFocus();
                } else if (email.isEmpty()) {
                    etEmail.setError(getString(R.string.errMail));
                    etEmail.requestFocus();
                } else if (parola.isEmpty()) {
                    etParola.setError(getString(R.string.errParola));
                    etParola.requestFocus();
                } else if (!(email.isEmpty() && parola.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, parola)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, R.string.errInregistrare, LENGTH_LONG).show();

                                    } else {
                                        User utilizator = new User(user, nume);
                                        String uid = task.getResult().getUser().getUid();
                                        firebaseDatabase.getReference(uid).setValue(utilizator)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Intent intent = new Intent(RegisterActivity.this,
                                                                LogInActivity.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        intent.putExtra("nume", nume);
                                                        startActivity(intent);
                                                    }
                                                });
                                    }

                                }
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, R.string.errInregistrare, LENGTH_LONG).show();
                }
            }
        });


        tvLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LogInActivity.class);
                startActivity(i);
            }
        });


    }
    public void initializareComponente(){
        etUser=findViewById(R.id.etUser);
        etEmail=findViewById(R.id.etEMail);
        etNume=findViewById(R.id.etNume);
        etParola=findViewById(R.id.etParola);

        btnRegister = (Button) findViewById(R.id.buttonRegister);
        tvLogIn=(TextView)findViewById(R.id.tvRegister);
    }
}
