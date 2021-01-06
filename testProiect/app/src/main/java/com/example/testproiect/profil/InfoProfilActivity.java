package com.example.testproiect.profil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproiect.R;
import com.example.testproiect.logare.LogInActivity;
import com.example.testproiect.logare.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InfoProfilActivity extends AppCompatActivity {
    private TextView tvUser, tvNume;
    private Button btnLogOut;

    private FirebaseUser user;
    private DatabaseReference referinta;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_profil);

        btnLogOut = (Button) findViewById(R.id.buttonLogOut);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(InfoProfilActivity.this, LogInActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
 //       referinta = FirebaseDatabase.getInstance().getReference("Profil");
        userId = user.getUid();

        tvUser = findViewById(R.id.tvgetUserCont);
        tvNume = findViewById(R.id.tvgetNumeCont);

//        referinta.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                User profilUser = snapshot.getValue(User.class);
//
//                if (profilUser != null) {
//
//                    String nume = profilUser.getNume();
//                    String user = profilUser.getUsername();
//
//                    tvNume.setText(user);
//                    tvUser.setText(nume);
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(InfoProfilActivity.this, R.string.eroare, Toast.LENGTH_SHORT).show();
//            }
//        });

   }
}
