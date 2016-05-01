package com.vargas.carlos.mapa.fragment;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.vargas.carlos.mapa.R;
import com.vargas.carlos.mapa.models.Cidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TwoFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.tab_fragment_2, container, false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        setMapView();

        return v;
    }

    private void setMapView() {
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        /* do something with the map */
        googleMap = map;





        List<Cidades> cidadesList = new ArrayList<>();
        Cidades passoFundo = new Cidades("Passo Fundo", "RS");
        Cidades carazinho = new Cidades("Carazinho", "RS");
        Cidades marau = new Cidades("Carazinho", "RS");
        Cidades portoAlegre = new Cidades("Porto Alegre", "RS");
        Cidades cachoeira = new Cidades("Cachoeira", "RS");
        Cidades santaMaria = new Cidades("Santa Maria", "RS");
        Cidades erechim = new Cidades("Erechim", "RS");
        Cidades getulioVargas = new Cidades("Getulio Vargas", "RS");
        Cidades colorado = new Cidades("Colorado", "RS");

        cidadesList.add(passoFundo);
        cidadesList.add(carazinho);
        cidadesList.add(marau);
        cidadesList.add(portoAlegre);
        cidadesList.add(cachoeira);
        cidadesList.add(santaMaria);
        cidadesList.add(erechim);
        cidadesList.add(getulioVargas);
        cidadesList.add(colorado);

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

        List<Address> addressesList = new ArrayList<>();
        List<Address> addresses;

        for(Cidades cidades: cidadesList){
            try {

                addresses = geocoder.getFromLocationName(cidades.getCidade() +", "+ cidades.getEstado(), 1);

                if (addresses != null && !addresses.isEmpty()) {
                    addressesList.add(addresses.get(0));

                } else {
                    Log.d("GoogleMaps", cidades.getCidade());
                }

            } catch (Exception e) {
                Log.e("GoogleMaps", e.getMessage());
            }
        }

        ArrayList<LatLng> coordList = new ArrayList<LatLng>();
        if(addressesList.size() > 0){

            for(Address address: addressesList) {

                // latitude and longitude
                double latitude = address.getLatitude();
                double longitude = address.getLongitude();

                coordList.add(new LatLng(latitude, longitude));

                // create marker
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latitude, longitude)).title(address.getFeatureName());

                // Changing marker icon
                marker.icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

                // adding marker
                googleMap.addMarker(marker);
            }

            PolylineOptions polylineOptions = new PolylineOptions();

            // Create polyline options with existing LatLng ArrayList
            polylineOptions.addAll(coordList);
            polylineOptions
                    .width(5)
                    .color(Color.RED);

            // Adding multiple points in map using polyline and arraylist
            googleMap.addPolyline(polylineOptions);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(addressesList.get(0).getLatitude(), addressesList.get(0).getLongitude())).zoom(9).build();
            googleMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            googleMap.getUiSettings().setZoomControlsEnabled(true);

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    try {
                        googleMap.setMyLocationEnabled(true);
                    } catch (SecurityException e) {
                        Log.e("OneFragment", e.getMessage());
                    }
                }
            });
        }









        /* aqui esta ok
        // latitude and longitude
        double latitude = 17.385044;
        double longitude = 78.486671;

        // create marker
        MarkerOptions marker = new MarkerOptions().position(
                new LatLng(latitude, longitude)).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(17.385044, 78.486671)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                try {
                    googleMap.setMyLocationEnabled(true);
                } catch (SecurityException e) {
                    Log.e("OneFragment", e.getMessage());
                }
            }
        });
        */
    }



    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
