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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogInActivity extends AppCompatActivity {
    EditText user, parola;
    Button btnLogIn;
    TextView tvRegister;

    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializareComponente();

        firebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = firebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    moveToHomeActivity(mFirebaseUser);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.te_rugam_sa_te_loggezi, Toast.LENGTH_LONG).show();
                }
            }

        };


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = LogInActivity.this.user.getText().toString();
                String parola = LogInActivity.this.parola.getText().toString();
                if (user.isEmpty()) {
                    LogInActivity.this.user.setError(getString(R.string.validare_user));
                    LogInActivity.this.user.requestFocus();
                } else if (parola.isEmpty()) {
                    LogInActivity.this.parola.setError(getString(R.string.validare_parola));
                    LogInActivity.this.parola.requestFocus();
                } else if (user.isEmpty() && parola.isEmpty()) {
                    Toast.makeText(LogInActivity.this, R.string.validare_campuri, Toast.LENGTH_SHORT).show();
                } else if (!(user.isEmpty() && parola.isEmpty())) {
//                    mFirebaseAuth.signInWithEmailAndPassword(user, parola).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (!task.isSuccessful()) {
//                                Toast.makeText(LogInActivity.this, R.string.eroare_autentificare, Toast.LENGTH_SHORT).show();
//                            } else {
//                                moveToHomeActivity(task.getResult().getUser());
//                            }
//                        }
//                    });
                    mFirebaseAuth.signInWithEmailAndPassword(user, parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                startActivity(new Intent(LogInActivity.this, MainActivity.class));

                            } else {

                                Toast.makeText(LogInActivity.this, R.string.nume_parola_gresita, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(LogInActivity.this, R.string.eroare, Toast.LENGTH_SHORT).show();

                }

            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(LogInActivity.this, RegisterActivity.class);
                startActivity(intSignUp);
            }
        });
    }


    public void initializareComponente(){
        user = findViewById(R.id.etUser);
        parola = findViewById(R.id.etParola);
        btnLogIn = findViewById(R.id.buttonLogIn);
        tvRegister = findViewById(R.id.tvCreeazaCont);
    }

    private void moveToHomeActivity(FirebaseUser mFirebaseUser) {

        firebaseDatabase.getReference().child(mFirebaseUser.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User userDetail = snapshot.getValue(User.class);
                        String name = userDetail.getNume();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(LogInActivity.this,"V-ati logat cu succes!", Toast.LENGTH_SHORT).show();
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.putExtra("name", name);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}