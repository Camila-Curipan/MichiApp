package com.michi.michislifever2;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;



import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

public class Mapa extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveIntanceState){
        super.onCreate(saveIntanceState);
        setContentView(R.layout.activity_mapa);

        //configuración de mapa de zoom con los dedos y sus controle
        Configuration.getInstance().load(getApplicationContext(), PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));


        MapView mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);


        //coordenadas de las ubicaciones que deseamos colocar
        double santiagoLatitud = -33.4489;
        double santiagoLongitud= -70.6180072;

        double sanjoaquinLatitud = -33.4989778;
        double sanjoaquinLongitud = -70.6180072;


        // creación de marcadores
        GeoPoint santiagoPoint = new GeoPoint(santiagoLatitud,santiagoLongitud);
        GeoPoint sanjoaquinPoint = new GeoPoint(sanjoaquinLatitud, sanjoaquinLongitud);


        //marcadores
        Marker santiagoMarker=new Marker(mapView);
        santiagoMarker.setPosition(santiagoPoint);
        santiagoMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        santiagoMarker.setTitle("Santiago de chile");
        santiagoMarker.setSnippet("Comuna");

        Marker sanjoaquinMarker = new Marker(mapView);
        sanjoaquinMarker.setPosition(sanjoaquinPoint);
        sanjoaquinMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        sanjoaquinMarker.setTitle("San joaquin de Chile");
        sanjoaquinMarker.setSnippet("Comuna de Santiago de Chile");


        // marcadores agregados
        mapView.getOverlays().add(santiagoMarker);
        mapView.getOverlays().add(sanjoaquinMarker);


        // centrar mapa
        IMapController mapController = mapView.getController();
        mapController.setCenter(santiagoPoint);
        mapController.setZoom(14);


        //agregando linea de conexión
        Polyline polyline = new Polyline();
        polyline.addPoint(santiagoPoint);
        polyline.addPoint(sanjoaquinPoint);
        polyline.setColor(0xFF0000FF);
        polyline.setWidth(5);
        mapView.getOverlays().add(polyline);
    }

}
