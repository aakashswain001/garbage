package xyz.sleepygamers.garbage;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText email,password;
    public TextView sign;
    public Button register;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,welcome.class));
        }
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        sign=findViewById(R.id.signin);
        register=findViewById(R.id.register);

        register.setOnClickListener(this);
        sign.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
  if(v==register){
      register();
   }
   if(v==sign){
      finish();
      startActivity(new Intent(this,LoginActivity.class));
   }
    }

    private void register() {
        String email=findViewById(R.id.email).toString().trim();
        String password=findViewById(R.id.password).toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Enter the email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter the password",Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Toast.makeText(MainActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                   finish();
                   startActivity(new Intent(MainActivity.this,welcome.class));

               }else{
                   Toast.makeText(MainActivity.this,"Couldn't register",Toast.LENGTH_SHORT).show();
               }
            }
        });


    }


}
