package org.dfir.harita.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Firsat;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class FirsatEkle extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsat_ekle);

        final Button btn_ekle = (Button) findViewById(R.id.btnEkle);
        final EditText et_baslangic = (EditText) findViewById(R.id.edittext1);
        final EditText et_sure = (EditText) findViewById(R.id.edittext2);
        final EditText et_kac_kisi = (EditText)  findViewById(R.id.edittext3);
        final EditText et_aciklama = (EditText)  findViewById(R.id.edittext4);
        final EditText et_firsat_turu = (EditText)  findViewById(R.id.edittext5);
        final EditText et_firsat_kodu = (EditText)  findViewById(R.id.edittext6);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner_kategori);

        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date_baslangic = Date.valueOf(et_baslangic.getText().toString());
                String str_sure = et_sure.getText().toString();
                String str_kac_kisi=et_kac_kisi.getText().toString();
                String str_aciklama=et_aciklama.getText().toString();
                String str_fırsat_turu=et_firsat_turu.getText().toString();
                String str_fırsat_kodu=et_firsat_kodu.getText().toString();
                String str_kategori = String.valueOf(spinner.getSelectedItem());


                DaoAccess dao= DaoAccess.getSingletonObject(FirsatEkle.this);
                FirsatDao firsat_dao= dao.getFirsatDao();
                Firsat firsat = new Firsat();

                firsat.setBaslangic(date_baslangic);
                firsat.setSure(Integer.parseInt(str_sure));
                firsat.setKac_kisi(Integer.parseInt(str_kac_kisi));
                firsat.setAciklama(str_aciklama);
                firsat.setFirsat_turu(str_fırsat_turu);
                firsat.setFirsat_kodu(str_fırsat_kodu);
                firsat.setKategori(str_kategori);
                firsat.setAktif_mi(true);
                firsat.setIsletme_id(MapsActivity.isletme.getId());

                try
                {
                    firsat_dao.insert(firsat);
                    firsat_dao.refresh(firsat);
                    double latitude = MapsActivity.isletme.getEnlem();
                    double longitude =  MapsActivity.isletme.getBoylam();
                    LatLng position = new LatLng(latitude, longitude);
                    MapsActivity.mMap.addMarker(new MarkerOptions().position(position).title(MapsActivity.isletme.getAd()+":"+str_aciklama));
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Fırsat Eklenemedi!",Toast.LENGTH_LONG).show();
                }

                Intent firsat_sayfasi = new Intent(FirsatEkle.this, FirsatSayfasi.class);
                startActivity(firsat_sayfasi);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            default:
                break;
        }
        return false;
    }
}
