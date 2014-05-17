package org.dfir.harita.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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



}
