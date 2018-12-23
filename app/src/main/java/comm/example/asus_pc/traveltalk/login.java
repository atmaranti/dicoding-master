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

public class login extends AppCompatActivity implements View.OnClickListener{
    EditText email, password;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        password = (AutoCompleteTextView) findViewById(R.id.password);
        email = (AutoCompleteTextView) findViewById(R.id.email);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.login).setOnClickListener(this);

    }
    private void userLogin(){
        String password1 = password.getText().toString().trim();
        String email1 = email.getText().toString().trim();


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

        progressBar.setVisibility(View.VISIBLE);


        mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Intent intent = new Intent(login.this , menu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:

                startActivity(new Intent(login.this, register.class));

                break;
            case R.id.login:
                userLogin();
                break;
        }
    }
}
