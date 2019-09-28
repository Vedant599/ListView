package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button Register;
    private ImageView User;
    int id=R.drawable.emo1;
    private EditText email;
    private EditText password;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Register=(Button)findViewById(R.id.btnRegister);
        User=(ImageView)findViewById(R.id.imgview);
        User.setOnClickListener(this);
        Register.setOnClickListener(this);
        email=(EditText)findViewById(R.id.edtName);
        password=(EditText)findViewById(R.id.edtPassword);

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==User.getId())
        {

            if(id==R.drawable.emo1)
            {
                id=R.drawable.emo2;
            }
            else
            {
                id=R.drawable.emo1;
            }
            User.setImageResource(id);
            Toast.makeText(this, "Image View Clicked", Toast.LENGTH_SHORT).show();


        }
        else
        {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
            if(check())
            {
                Toast.makeText(this, "Appropriate", Toast.LENGTH_SHORT).show();
                intent =new Intent(LoginActivity.this,RecyclerViewActivity.class);
                intent.putExtra("Email",email.getText().toString().trim());
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Not Appropriate", Toast.LENGTH_SHORT).show();

            }
        }
        Toast.makeText(this, "On click Listner", Toast.LENGTH_SHORT).show();
    }

    private boolean check() {
        String Email =email.getText().toString().trim();
        String Password =password.getText().toString().trim();
        if(Email.length()<=8||Password.length()<=8)
        {



                if(Email.length()<=8&&Password.length()<=8)
                {
                    email.setError("Email min 7 charecters");
                    password.setError("Password min 7 charecters");
                }
                else
                if(Email.length()<=8)
                {
                    email.setError("Email min 7 charecters");
                }
                else
                    password.setError("Password min 7 charecters");

            return false;
        }
        else
        {
            return true;
        }


    }
}
