package dam.project.wearevalencia.maps;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import dam.project.wearevalencia.R;

public class TorresDeSerranosMap extends FragmentActivity {

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.torresdeserrano_map_fragment);
		//objeto google map con el que se interactua
		
		GoogleMap mapa = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		
		LatLng torresLatLng = new LatLng(39.47926986007646, -0.3760123212119959);
		
		//dirige la posicion del mapa hacia esa latitud y esa longitud
		CameraPosition cameraPosition = new CameraPosition.Builder()
		.target(torresLatLng)
		.zoom(15).build();
		
		
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		mapa.animateCamera(cameraUpdate); //animacion para llegar a la lat y long indiciados que se le pasa al objeto cameraupdate
		mapa.addMarker(new MarkerOptions() //marker personalizado
		.position(torresLatLng)
		.title("Las Torres de Serranos")
		.snippet("Valencia, Centro histórico")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map_red)))
		.showInfoWindow();
	}
}

