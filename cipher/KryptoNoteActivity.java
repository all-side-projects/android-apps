package ca.yorku.eecs.kryptonote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.nio.CharBuffer;

public class KryptoNoteActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kryptonote_layout);
    }

    public void onEncrypt(View v)
    {
        try
        {
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            Cipher cipher = new Cipher(key);

            String data = ((EditText) findViewById(R.id.data)).getText().toString();
            String encryptedData = cipher.encrypt(data);

            ((EditText) findViewById(R.id.data)).setText(encryptedData);


        } catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onDecrypt(View v)
    {
        try
        {
            String key = ((EditText) findViewById(R.id.key)).getText().toString();
            Cipher cipher = new Cipher(key);

            String data = ((EditText) findViewById(R.id.data)).getText().toString();
            String encryptedData = cipher.decrypt(data);

            ((EditText) findViewById(R.id.data)).setText(encryptedData);


        } catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onSave(View v)
    {
        try
        {
            String name = ((EditText) findViewById(R.id.file)).getText().toString();
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileWriter fw = new FileWriter(file);
            fw.write(((EditText) findViewById(R.id.data)).getText().toString());
            fw.close();
            Toast label = Toast.makeText(this, "Note Saved", Toast.LENGTH_LONG);
            label.show();
        } catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }
    }

    public void onLoad(View v)
    {
        try
        {
            String name = ((EditText)findViewById(R.id.file)).getText().toString();;
            File dir = this.getFilesDir();
            File file = new File(dir, name);
            FileReader fr = new FileReader(file);
            String show = "";
            for (int c = fr.read(); c != -1; c = fr.read())
            {
                show += (char) c;
            }
            fr.close();
            ((EditText) findViewById(R.id.data)).setText(show);

        } catch (Exception e)
        {
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }

    }
}


