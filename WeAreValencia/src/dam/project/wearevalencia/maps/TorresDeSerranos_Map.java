package dam.project.wearevalencia.maps;

import org.holoeverywhere.widget.Toast;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import dam.project.wearevalencia.R;
import dam.project.wearevalencia.gallery.TorresDeSerranos_Gallery;

public class TorresDeSerranos_Map extends SherlockFragmentActivity  implements LocationListener{
	private Typeface robotoThin, robotoBoldCondensed;
	private ActionBar actionBar;
	
	//constantes para identificar que opcion de menu se selecciona.
	private final int MAPA_NORMAL = 1;
	private final int MAPA_HYBRIDO = 2;
	private final int MAPA_SATELITE = 3;
	private final int MAPA_TERRANEO = 4;
	
	private final float latitud = (float) 39.47926986007646;
	private final float longitud = (float) -0.3760123212119959;

	//constantes para el minimo de tiempo en actualizar la posicion y la distancia en radio de la posicion actual
	private final int MIN_TIME = 2000;
	private final int MIN_DISTANCE = 50;
	
	//escuchar los cambios de posicion del usuario
	private LocationManager locationManager;
	GoogleMap mapa;
	Location location;
	String provider;
	
	private LatLng myPosition;
	private LatLng myDestine;
	
	
		
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_and_gallery);
		
		//action bar + personalizaciones
		actionBar = getSupportActionBar();
        changeActionBar();
        
      //Obteniendo el estado de google play services, ya que gracias a ello se muestra el mapa
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
    	
        // Mostrar mapa y demás si está disponible
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services no esta disponible
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            //http://developer.android.com/intl/es/google/play-services/setup.html
            dialog.show();
        }else{
  
	        mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

	        //mi posicion habilitada
	        
			goToTorresDeSerranos();
			
			
			 ImageButton myPosition = (ImageButton)findViewById(R.id.myLocationButton);
		        myPosition.setOnClickListener(new OnClickListener() {
					
		        	@Override
					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Obteniendo tu ubicación, espera...", Toast.LENGTH_LONG).show();

						new Handler().post(new Runnable() {
							
							@Override
							public void run() {
				    			mapa.setMyLocationEnabled(true);
				    			// obteniendo el objeto LocationManager desde el System Service -> LOCATION_SERVICE
				    	     	locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
				    			Criteria criteria = new Criteria(); //establecemos precision
				    			criteria.setAccuracy(Criteria.ACCURACY_FINE); //establecemos que se escuche al mejor proveedor de señal, ya sea el wifi, gps o red movil
				    			provider = locationManager.getBestProvider(criteria, true); //devuelve en un string el provider con los mejores criterios 
						        
								if(locationManager != null){
									boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
									boolean networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
									
									if(!gpsEnabled){
										Toast toast = Toast.makeText(getApplicationContext(), "GPS Deshabilitado, actívalo para mejor precisión", Toast.LENGTH_LONG);
										toast.setGravity(Gravity.CENTER,0,0);
										toast.show();
									}
									if(!networkEnabled){
										Toast toast = Toast.makeText(getApplicationContext(), "No hay conexión de datos, actívela para mostrar el mapa", Toast.LENGTH_LONG);
										toast.setGravity(Gravity.CENTER,0,0);
										toast.show();
									}
								}
											
								location = locationManager.getLastKnownLocation(provider);
								//Returns a Location indicating the data from the last known location fix obtained from the given provider. 
					
								if(location!=null){
									onLocationChanged(location);
								}
							}
						});
	        	}
			});
		        if(locationManager != null)
		        	locationManager.requestLocationUpdates(provider, MIN_TIME, MIN_DISTANCE, this); //obtener actualizaciones de posicionamiento

        }
        

        ImageButton galeria = (ImageButton)findViewById(R.id.torresdeserranos_galleryButton);
        galeria.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
        		goToIntent();
			}
		});
        

        ImageButton visitedSite = (ImageButton)findViewById(R.id.visitedSiteButton);
        visitedSite.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
        		
        		Toast.makeText(getApplicationContext(), "He visitado ya las Torres de Serranos", Toast.LENGTH_LONG).show();
    	           
    			}
		
		});
        
        //trazar ruta entre mi posicion y mi destino
        ImageButton makeRoute = (ImageButton)findViewById(R.id.makeRouteButton);
        makeRoute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Estableciendo ruta...", Toast.LENGTH_LONG).show();
				new Handler().post(new Runnable() {
					
					@Override
					public void run() {
						
					}
				});
			}
			
		});
        
        //cuando se pulse el boton, se posicionara nuevamente al usuario con la vista de las torres de serranos.
        ImageButton goLocation = (ImageButton)findViewById(R.id.goLocationMapButton);
        goLocation.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				goToTorresDeSerranos();
				
			}
		});
	}
	
	//Menu para ver el mapa con diferentes vistas
	public boolean onCreateOptionsMenu(Menu menu){			
		super.onCreateOptionsMenu(menu);
		SubMenu submenu = menu.addSubMenu(getString(R.string.optionsMaps));
		submenu.add(0, MAPA_NORMAL, 0, getString(R.string.mapaNormal));
		submenu.add(0, MAPA_HYBRIDO, 1, getString(R.string.mapaHibryd));
		submenu.add(0, MAPA_SATELITE, 2,getString(R.string.mapaSatelite));
		submenu.add(0, MAPA_TERRANEO, 3, getString(R.string.mapaTerraneo));
		submenu.getItem().setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		return true;
		
		}
	
	public boolean onOptionsItemSelected (MenuItem item){
    	
		switch(item.getItemId()){
    	
    	case android.R.id.home:
    		TorresDeSerranos_Map.this.finish();
			//sobreescribir la animacion para finalizar esta activity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    		return true;
    	
    	case 0:
    		break;
    		
    	case MAPA_NORMAL:
    		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    		break;
    		
    	case MAPA_HYBRIDO:
    		mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    		break;
    		
    	case MAPA_SATELITE:
    		mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    		break;
    		
    	case MAPA_TERRANEO:
    		mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    		break;	

    	}
    	
		Log.e("Variable item", "" + item.getItemId());

		return super.onOptionsItemSelected(item);

    }
	
	
	private void goToTorresDeSerranos(){
		//establecer la posicion y marker de las torres de serranos:		
		LatLng torresLatLng = new LatLng(latitud, longitud);
		myDestine = torresLatLng;
		//dirige la posicion del mapa hacia esa latitud y esa longitud
		CameraPosition cameraPosition = new CameraPosition.Builder()
		.target(torresLatLng)
		.zoom(17)
		.bearing(285)
		//.tilt(75)
		.build();

		
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		mapa.animateCamera(cameraUpdate); //animacion para llegar a la lat y long indiciados que se le pasa al objeto cameraupdate
		mapa.addMarker(new MarkerOptions() //marker personalizado
		.position(torresLatLng)
		.title("Las Torres de Serranos")
		.snippet("Valencia, Centro histórico")
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map_red)))
		.showInfoWindow();
	
		//listener del windows info:
		mapa.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			
			@Override
			public void onInfoWindowClick(Marker marker) {
				goToIntent();
			}
		});
	
	}

	private void goToIntent(){
		Intent galeria = new Intent(TorresDeSerranos_Map.this, TorresDeSerranos_Gallery.class);
		startActivity(galeria);
		//sobreescribir la animacion para dar entrada a la nueva pantalla
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
	
	
	private void changeActionBar() {
		//typeface personalizadas
        robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");

				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.ic_navigation_back);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);


		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_title_maps_with_gallery,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloOtherActivity);
		        titulo.setText(getString(R.string.torresDe));
		        titulo.setTypeface(robotoThin);

		        TextView otroTitulo =(TextView)customView.findViewById(R.id.titulo2OtherActivity);
		        otroTitulo.setText(getString(R.string.Serranos));
		        otroTitulo.setTypeface(robotoBoldCondensed);


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}
	
/*	public void onPause(){
		super.onPause();
		if(locationManager != null){
			locationManager.removeUpdates((LocationListener)this);
		}
	}
	
	public void onResume(){
		super.onResume();
		initMap();
		if(locationManager != null){
			mapa.setMyLocationEnabled(true);
		}
	}*/

	@Override
	public void onLocationChanged(Location location) {
		if(location != null){
			//obteniendo la latitud de mi posicion actual
			double latitude = location.getLatitude();
			
			//obteniendo la longitud de mi posicion actual
			double longitude = location.getLongitude();
			
			//direccion de mi posicion actual
			LatLng myCurrentPosition = new LatLng(latitude, longitude);
			myDestine = myCurrentPosition;
			//mover la posicion del mapa hacia donde estoy ubicado en ese momento
			CameraPosition myCameraPosition = new CameraPosition.Builder()
			.target(myCurrentPosition).zoom(15).build();
			CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(myCameraPosition);
			mapa.animateCamera(cameraUpdate);
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
