package com.example.abe.mobiletrabalho.Mic;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abe.mobiletrabalho.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

public class MicActivity extends AppCompatActivity {
    private Button micButton;
    private TextView showText;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);

        micButton = (Button) findViewById(R.id.talkButton);
        showText = (TextView) findViewById(R.id.auxTextView);

    }

    private void openMic(){
        Intent intentMic = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH); //Cria uma nova intent para ouvirr
        intentMic.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); //Qual tipo de linguagem será ouvida

        intentMic.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault()); //Pega a língua padrão do telefone
        intentMic.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale agora!!!"); //Abre o microfone do telefone

        try{
            startActivityForResult(intentMic, REQ_CODE_SPEECH_OUTPUT);

        }catch (Exception e){
            Log.i("Deu ruim", "Microfone morreu");
        }
    }

    public void listenOnClick(View view) {
        openMic();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQ_CODE_SPEECH_OUTPUT:{
                if(resultCode == RESULT_OK && data != null){
                    ArrayList<String> voiceIntent = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //Pega os textos
                    showText.setText(voiceIntent.get(0)); //Coloca o texto na textView
                }
            }
            break;
        }
    }
}
