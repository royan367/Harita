package org.dfir.harita.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by userr on 16.5.2014.
 */
public class ProfilSayfasi extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yonetici_ekrani);

        // Find the ListView resource.
        ListView list_view = (ListView) findViewById( R.id.list_view );

        // Create and populate a List of planet names.
        String[] tercihler = new String[] { "Fırsatlar","Ayarlar","Kategori","Oy"};
        ArrayList<String> profil_tercihleri = new ArrayList<String>();
        profil_tercihleri.addAll( Arrays.asList(tercihler) );

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, profil_tercihleri);
        list_view.setAdapter(adapter);


        final Intent firsat_sayfasi = new Intent(this, FirsatSayfasi.class);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        startActivity(firsat_sayfasi);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Toast.makeText(ProfilSayfasi.this, "Ortalama Puan:" + Float.toString(MapsActivity.isletme.getOy()), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;

                }

            }
        });

    }

    private void Ayarlar_PopUp()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Ayarlar");
        alert.setMessage("Message");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }
}
