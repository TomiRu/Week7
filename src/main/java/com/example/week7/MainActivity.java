package com.example.week7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textEditor;
    EditText fileN;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        fileN = (EditText) findViewById(R.id.fileName);
        textEditor = (EditText) findViewById(R.id.textEditor);

        System.out.println("Sijainti kirjastossa: " + context.getFilesDir());

/*
        text = (TextView) findViewById(R.id.textView);
        input = (EditText) findViewById(R.id.inputText);


        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                text.setText(input.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

         */
    }

    public void readFile(View v){

        try{
            InputStream ins = context.openFileInput(fileN.getText().toString()); //TODO Tälle arvo

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";
            String x = "";

            while ((s = br.readLine()) != null){
                x = x + s + "\n";
                System.out.println(s);
            }
            textEditor.setText(x);

            ins.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        } finally {
            System.out.println("Luettu");
        }

    }

    public void writeFile(View v){
        fileN.setText(fileN.getText().toString());

        textEditor.setText(textEditor.getText().toString());

        try{
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(fileN.getText().toString(), Context.MODE_PRIVATE));

            String s = textEditor.getText().toString();

            osw.write(s);

            osw.close();

        } catch (IOException e) {
            Log.e("IOException", "Virhe syötteessä");
        }  finally {
            System.out.println("Kirjoitettu");
        }

    }


    /*
    public void setText (View v){

        teksti.setText(Input.getText().toString());
    }
     */
}