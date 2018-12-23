package comm.example.asus_pc.traveltalk;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class register extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText username, password, email, alamat;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = (AutoCompleteTextView) findViewById(R.id.username);
        password = (AutoCompleteTextView) findViewById(R.id.password);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        alamat = (AutoCompleteTextView) findViewById(R.id.alamat);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);
    }
    private void registerUser(){
        String username1 = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String email1 = email.getText().toString().trim();
        String alamat1 = alamat.getText().toString().trim();

        if (username1.isEmpty()){
            username.setError("username belum diisi");
            username.requestFocus();
            return;
        }

        if (password1.isEmpty()){
            password.setError("password belum diisi");
            password.requestFocus();
            return;
        }

        if (password1.length() < 6){
            password.setError("Minimum password is 6");
            password.requestFocus();
            return;
        }

        if (email1.isEmpty()){
            email.setError("email belum diisi");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches()){
            email.setError("Please Enter Valid email");
            email.requestFocus();
            return;
        }

        if (alamat1.isEmpty()){
            alamat.setError("alamat belum diisi");
            alamat.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(register.this , menu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    if (task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "kamu sudah terdaftar", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    // Toast.makeText(getApplicationContext(), "Terjadi Error, coba lagi" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                registerUser();
                break;

            case R.id.login:
                startActivity(new Intent(register.this, login.class));
                break;
        }
    }
}
