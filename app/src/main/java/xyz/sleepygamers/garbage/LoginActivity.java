package xyz.sleepygamers.garbage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
 EditText EtEmail,EtPassword;
 private Button login;
 private TextView reg;
 FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,welcome.class));
        }

        EtEmail = findViewById(R.id.email);
        EtPassword = findViewById(R.id.password);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);

        reg.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    public void onClick(View v) {
      if(v==reg){
          finish();
          startActivity(new Intent(this,MainActivity.class));
      }
      if(v==login){
          Login();
      }
    }

    private void Login() {
        String email= EtEmail.getText().toString().trim();
        String password=EtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter the email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter the password",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()) {
                     finish();
                     startActivity(new Intent(LoginActivity.this,welcome.class));
                 }
            }
        });
    }


}
