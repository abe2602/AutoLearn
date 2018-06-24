package com.example.abe.mobiletrabalho;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends AppCompatActivity {

    private Button cadastro;
    private EditText user, senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*Parser para realizar o login*/
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        user = (EditText) findViewById(R.id.editTextEmail);
        senha = (EditText) findViewById(R.id.editTextPassword);

        /*Resgata o texto salvo via SharedPreferences!!*/
        SharedPreferences preferences = getSharedPreferences("name_shared_preferences", MODE_PRIVATE);
        if(preferences.getString("stringKey", "") != null){
            String putText = preferences.getString("userName", "");
            user.setText(putText);

            putText = preferences.getString("password", "");
            senha.setText(putText);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void signIn(View view) {
        SharedPreferences preferences = getSharedPreferences("name_shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("userName", user.getText().toString());
        editor.apply();

        editor.putString("password", senha.getText().toString());
        editor.apply();

        //Setting up a progress dialog
        final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
        dlg.setTitle("Aguarde, por favor");
        dlg.setMessage("Logando...");
        dlg.show();

        ParseUser.logInInBackground(user.getText().toString(), senha.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    dlg.dismiss();
                    alertDisplayer("Login efetuado com sucesso!","Bem-vindo(a) " + user.getText().toString() + "!");

                } else {
                    dlg.dismiss();
                    ParseUser.logOut();
                    Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void signUp(View view) {
        Intent cadastroIntent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(cadastroIntent);
    }

}


