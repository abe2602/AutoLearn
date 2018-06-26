package com.example.abe.mobiletrabalho;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public class CadastroActivity extends AppCompatActivity {
    private EditText usernameView;
    private EditText passwordView;
    private EditText passwordAgainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Parse.initialize(this);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "Your GCM Sender ID here");
        installation.saveInBackground();

        usernameView = (EditText) findViewById(R.id.username);
        passwordView = (EditText) findViewById(R.id.password);
        passwordAgainView = (EditText) findViewById(R.id.passwordAgain);
    }

    public void doneClick(View view) {
        //Validating the log in data
        boolean validationError = false;

        StringBuilder validationErrorMessage = new StringBuilder("Por favor, preencher");
        if (isEmpty(usernameView)) {
            validationError = true;
            validationErrorMessage.append("o campo Usuário");
        }
        if (isEmpty(passwordView)) {
            if (validationError) {
                validationErrorMessage.append(" e ");
            }
            validationError = true;
            validationErrorMessage.append("o campo Senha");
        }
        if (isEmpty(passwordAgainView)) {
            if (validationError) {
                validationErrorMessage.append(" e ");
            }
            validationError = true;
            validationErrorMessage.append("a senha novamente");
        }
        else {
            if (!isMatching(passwordView, passwordAgainView)) {
                if (validationError) {
                    validationErrorMessage.append(" e ");
                }
                validationError = true;
                validationErrorMessage.append("digite a senha duas vezes");
            }
        }
        validationErrorMessage.append(".");

        if (validationError) {
          //  Toast.makeText(CadastroActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG).show();
            return;
        }

        /*
         * Aqui usamos o Parse para cadastrar o usuário!
         * Quando o usuário se cadastrar, ele será redirecionado para a tela de login, onde poderá
         * se logar.
         *
         * */
        final ProgressDialog dlg = new ProgressDialog(CadastroActivity.this);
        dlg.setTitle("Aguarde um momento");
        dlg.setMessage("Cadastrando...");
        dlg.show();

        //Cria o novo usuário a partir do Parser
        ParseUser user = new ParseUser();
        user.setUsername(usernameView.getText().toString());
        user.setPassword(passwordView.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    dlg.dismiss();
                    ParseObject entrance = new ParseObject("UserRating");

                    entrance.put("userId", ParseUser.getCurrentUser().getObjectId());
                    entrance.put("userName", usernameView.getText().toString());
                    entrance.put("micEntrance", 0);
                    entrance.put("emotionEntrance", 0);
                    entrance.put("vibraEntrance", 0);
                    entrance.put("orderEntrance", 0);
                    entrance.put("dangerEntrance", 0);
                    entrance.put("appEntrance", 1);
                    entrance.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                Log.d("entrance", "deu bom");}
                            else{
                                Log.d("ruim", e.getMessage());
                            }
                        }
                    });
                    alertDisplayer("Cadastrado com sucesso!");
                } else {
                    dlg.dismiss();
                    ParseUser.logOut();
                    Toast.makeText(CadastroActivity.this, "Não consegui cadastrar", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isEmpty(EditText text) {
        if (text.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isMatching(EditText text1, EditText text2){
        if(text1.getText().toString().equals(text2.getText().toString())){
            return true;
        }
        else{
            return false;
        }
    }

    private void alertDisplayer(String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    public void backToLogin(View view) {
        Intent backIntent = new Intent(CadastroActivity.this, LoginActivity.class);
        startActivity(backIntent);
    }
}
