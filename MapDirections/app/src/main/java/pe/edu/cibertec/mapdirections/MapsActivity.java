package pe.edu.cibertec.mapdirections;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import pe.edu.cibertec.mapdirections.directionhelpers.FetchURL;
import pe.edu.cibertec.mapdirections.directionhelpers.TaskLoadedCallback;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, TaskLoadedCallback {

    private GoogleMap mMap;

    //Traza la ruta entre dos localizacionbes
    private Polyline polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        LatLng cibertecSanIsidro = new LatLng(-12.1041327,-77.0479078);
        LatLng cibertecMiraflores = new LatLng(-12.1222595,-77.0304797);

        mMap.addMarker(new MarkerOptions().position(cibertecSanIsidro).title("Cibertec").snippet("San Isidro"));

        mMap.addMarker(new MarkerOptions().position(cibertecMiraflores).title("Cibertec").snippet("San Miraflores"));

        new FetchURL(this).execute(getUrl(cibertecSanIsidro,cibertecMiraflores,"driving"),"driving");


        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


    }

    private String getUrl(LatLng origin, LatLng destino, String mode) {
        //Origen

        String originString = "origin="+origin.latitude+","+origin.longitude;


        //Destino
        String destioString = "destination="+destino.latitude+","+destino.longitude;


        //Modo
        String modoString = "mode="+mode;

        //Formato

        String outputFormat = "json";

        //Parametro

        String parameters = originString+"&"
                +destioString+"&"
                +modoString;


        //Url

        String Url = "https://maps.googleapis.com/maps/api/directions/"+outputFormat+"?"+parameters+"&key="+getString(R.string.google_maps_key);

        Log.d("myLoc",Url);

        return  Url;

    }

    @Override
    public void onTaskDone(Object... values) {
        if(polyline!=null)
        {
            polyline.remove();
        }
        else {
            polyline = mMap.addPolyline((PolylineOptions) values[0]);
        }
    }
}
