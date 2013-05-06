package dam.project.wearevalencia.maps;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import dam.project.wearevalencia.R;
import dam.project.wearevalencia.gallery.TorresDeSerranos_Gallery;

public class TorresDeSerranos_Map extends SherlockFragmentActivity {
	private Typeface robotoThin, robotoBoldCondensed;
	private ActionBar actionBar;
	private final int MAPA_NORMAL = 0;
	private final int MAPA_HYBRIDO = 1;
	private final int MAPA_SATELITE = 2;
	private final int MAPA_TERRANEO = 3;
	
	//objeto google map con el que se interactua
	GoogleMap mapa;
	
	public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_and_gallery);
		
		//action bar + personalizaciones
		actionBar = getSupportActionBar();
        changeActionBar();
        
        mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		LatLng torresLatLng = new LatLng(39.47926986007646, -0.3760123212119959);
		
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
        
		mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				goToIntent();
				return false;
			}
		});
      		
      		
        
        Button galeria = (Button)findViewById(R.id.torresdeserranos_galleryButton);
        galeria.setTypeface(robotoThin);
        galeria.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
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
    		mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    		break;
    		
    	case 1:
    		mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    		break;
    		
    	case 2:
    		mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    		break;
    		
    	case 3:
    		mapa.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
    		break;	

    	}
    	
		return super.onOptionsItemSelected(item);

    }

	private void changeActionBar() {
		//typeface personalizadas
        robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");

				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.navigation_back);

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
	

}
