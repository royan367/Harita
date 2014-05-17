package org.dfir.harita.app;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Firsat;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;
import org.dfir.harita.app.model.dao.Musteri;
import org.dfir.harita.app.model.dao.MusteriDao;

import java.util.List;

public class MapsActivity extends MyActionBarActivity {

    public static GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public static Isletme isletme;
    public static Isletme isletme1;

    private DaoAccess mDaoAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // TODO NullPointerException a neden oluyor
        //setUpMapIfNeeded();
        isletme1 = new Isletme();
        mDaoAccess = DaoAccess.getSingletonObject(MapsActivity.this);
        FirsatDao firsat_dao= mDaoAccess.getFirsatDao();
        List<Firsat> firsatlar = firsat_dao.queryBuilder().list();
        double latitude;
        double longitude;

        for (int i = 0; i < firsatlar.size(); i++)
        {
            long l_isletme = firsatlar.get(i).getIsletme_id();

            IsletmeDao isletme_dao= mDaoAccess.getIsletmeDao();
            try {
                List<Isletme> isletme = isletme_dao.queryBuilder().where(IsletmeDao.Properties.Id.eq(l_isletme)).list();
                if(isletme != null)
                {
                    isletme1=isletme.get(0);
                    latitude = isletme1.getEnlem();
                    longitude = isletme1.getBoylam();
                    LatLng position = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(position).title(MapsActivity.isletme1.getAd()+":"+firsatlar.get(i).getAciklama()));
                }
            }
            catch(Exception e)
            {
            }
        }


    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DaoAccess.nullify();
        isletme=null;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Yönetici Girişi");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 0:
                Intent login = new Intent(this,LoginActivity.class);
                startActivity(login);
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if(location!=null)
        {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LatLng current_position = new LatLng(latitude, longitude);
            if (isletme != null) {    // yani bir işletme giriş yapmış
                mMap.addMarker(new MarkerOptions()
                        .position(current_position)
                        .title(isletme.getAd())
                        .snippet(isletme.getAciklama())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.triangle_blue_isletme)));
            } else {    // yani bir işletme giriş yapmamış
                mMap.addMarker(new MarkerOptions()
                        .position(current_position)
                        .title("Buradasınız")
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.triangle_blue_musteri)));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15));

            // add işletme markers
            addIsletmeMarkers(mMap);
            // add müşteri markers
            addMusteriMarkers(mMap);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Konum Algılanamadı", Toast.LENGTH_LONG).show();
        }
    }

    private void addIsletmeMarkers(GoogleMap map) {
        IsletmeDao isletmeDao = mDaoAccess.getIsletmeDao();

        List<Isletme> isletmeler = isletmeDao.loadAll();
        for (Isletme isl : isletmeler) {
            // find fırsatlar for this isletme
            List<Firsat> firsatlar = isl.getFirsatlar();
            // if there is at least one firsat for this isletme, the display this isletme
            if (firsatlar.size() >= 1) {
                map.addMarker(new MarkerOptions()
                        .position(new LatLng(isl.getEnlem(), isl.getBoylam()))
                        .title(isl.getAd())
                        .snippet(isl.getAciklama())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.triangle_green_isletme)));
            }
        }
    }

    private void addMusteriMarkers(GoogleMap map) {
        MusteriDao musteriDao = mDaoAccess.getMusteriDao();

        List<Musteri> musteriler = musteriDao.loadAll();
        for (Musteri musteri : musteriler) {
            // add this musteri to the map
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(musteri.getEnlem(), musteri.getBoylam()))
                    .title("Bir müşteri")
                    .icon(BitmapDescriptorFactory
                            .fromResource(R.drawable.triangle_green_musteri)));
        }
    }
}
