package org.dfir.harita.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Firsat;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FirsatSayfasi extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsat_sayfasi);

        ListView list_view = (ListView) findViewById(R.id.list_view);

        DaoAccess dao = DaoAccess.getSingletonObject(FirsatSayfasi.this);
        FirsatDao firsat_dao = dao.getFirsatDao();

        List<Firsat> firsatlar = firsat_dao.queryBuilder().list();

        ArrayList<String> liste_firsat = new ArrayList<String>();

        for (int i = 0; i < firsatlar.size(); i++) {
            String str = "Fırsat Kodu:" +firsatlar.get(i).getFirsat_kodu() + "\n" + "Kategori:"+firsatlar.get(i).getKategori() + "\n" +
                    "Açıklama:"+firsatlar.get(i).getAciklama() + "\n" + "Başlangıç Tarihi:"+firsatlar.get(i).getBaslangic().toString() + "\n" +
                    "Süre:"+Integer.toString(firsatlar.get(i).getSure()) + "\n" + "Kişi Sayısı:"+Integer.toString(firsatlar.get(i).getKac_kisi());
            liste_firsat.add(i, str);
        }

        ArrayList<String> profil_tercihleri = new ArrayList<String>();
        profil_tercihleri.addAll(liste_firsat);

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, profil_tercihleri);
        list_view.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Yeni Fırsat Ekle");
        menu.add(0, 0, 1, "Haritaya Dön");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                Intent firsat_ekle = new Intent(this, FirsatEkle.class);
                startActivity(firsat_ekle);
                break;
            case 1:
                Intent harita = new Intent(this, MapsActivity.class);
                startActivity(harita);
                break;
            default:
                break;
        }
        return false;
    }
}

