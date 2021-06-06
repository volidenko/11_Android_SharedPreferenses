package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private final static String FILE_NAME="content.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void saveText(View view){
        FileOutputStream fos=null;
        try{
            EditText textBox=(EditText) findViewById(R.id.editor);
            String text=textBox.getText().toString();
            fos=openFileOutput(FILE_NAME,MODE_PRIVATE);
            fos.write(text.getBytes());
            Toast.makeText(this,"Файл сохранен",Toast.LENGTH_SHORT).show();

        }catch (IOException ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fos!=null){
                    fos.close();
                }
            }catch (IOException ex){
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void openText(View view){
        FileInputStream fin=null;
        TextView textView=(TextView)findViewById(R.id.text);
        try {
            fin=openFileInput(FILE_NAME);
            byte[]bytes=new byte[fin.available()];
            fin.read(bytes);
            String text=new String(bytes);
            textView.setText(text);
        }catch (IOException ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            try {
                if(fin!=null){
                    fin.close();
                }
            }catch (IOException ex){
                Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

    }
}