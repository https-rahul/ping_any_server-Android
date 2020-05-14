package application.rahul.internetping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Criar variaveis
    Button btnIp;
    ListView listaPing;
    EditText edtIP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnIp = (Button) findViewById(R.id.btn_ping);
        listaPing = (ListView) findViewById(R.id.listView_ping);
        edtIP = (EditText) findViewById(R.id.edit_ip);

    }

    public void fExecuterPing(View view){
        Editable host = edtIP.getText();
        List<String> listsRespondedPing = new ArrayList<String>();
        //Creates the adapter for Listview
        ArrayAdapter<String> adapterList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                listsRespondedPing);

        try {
            String cmdPing = "ping -c 4 "+host;
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(cmdPing);
            BufferedReader in = new BufferedReader(	new InputStreamReader(p.getInputStream()));
            String inputLinhe;

            while((inputLinhe = in.readLine())!= null){
                listsRespondedPing.add(inputLinhe);
                //add for each row
                listaPing.setAdapter(adapterList);
            }

            Toast.makeText(this, "Command executed successfully!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Error: "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }


    }
}