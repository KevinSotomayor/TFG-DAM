package dam.project.wearevalencia.maps;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import dam.project.wearevalencia.R;
import dam.project.wearevalencia.objects.Eventos_Item;

public class Eventos_Ficha_Map extends SherlockFragmentActivity {
	private Typeface robotoBoldCondensed,robotoRegular;
	private ActionBar actionBar;
	
	//constantes para identificar que opcion de menu se selecciona.
	private final int MAPA_NORMAL = 1;
	private final int MAPA_HYBRIDO = 2;
	private final int MAPA_SATELITE = 3;
	private final int MAPA_TERRANEO = 4;
	
	private final String BUNDLE_FROM_FRAGMENT_EVENTS_MAP ="bundleFromFragment";
	private Eventos_Item objeto; 

	//escuchar los cambios de posicion del usuario
	private GoogleMap mapa;
	private LatLng myDestine;
	
	//variables para recuperar informacion del objeto
	String tituloMarker ="";
	String direccion ="";
	
	private TextView title, content, address, horary;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eventos_ficha_mapas);
	    mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_events)).getMap();
		//typeface personalizadas
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");
        robotoRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
	
				objeto = bundle.getParcelable(BUNDLE_FROM_FRAGMENT_EVENTS_MAP);
				myDestine = objeto.getLatLng();
				tituloMarker = objeto.getTitle();
				direccion = objeto.getAddress();
				
				goToLugar();
				
				title = (TextView)findViewById(R.id.textView_Eventos_Ficha_Item_Title);
				content = (TextView)findViewById(R.id.textView_Eventos_Ficha_Item_Content);
				address = (TextView)findViewById(R.id.textView_Eventos_Ficha_Item_Address);
				horary = (TextView)findViewById(R.id.textView_Eventos_Ficha_Item_Horary);
				
				title.setTypeface(robotoBoldCondensed);
				title.setText(objeto.getTitle().toUpperCase());
				
				content.setTypeface(robotoRegular);
				content.setText(objeto.getContent());
				
				address.setTypeface(robotoRegular);
				address.setText(objeto.getAddress().toUpperCase());
				
				horary.setTypeface(robotoRegular);
				horary.setText(objeto.getHorary().toUpperCase());
			
	        
	        }

		//action bar + personalizaciones
		actionBar = getSupportActionBar();
        changeActionBar();
        
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
    		Eventos_Ficha_Map.this.finish();
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
    	
		return super.onOptionsItemSelected(item);

    }
	
	
	private void goToLugar(){
		//establecer la posicion y marker:		
		//dirige la posicion del mapa hacia esa latitud y esa longitud
		CameraPosition cameraPosition = new CameraPosition.Builder()
		.target(myDestine)
		.zoom(14)
		.build();

		
		CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
		mapa.animateCamera(cameraUpdate); //animacion para llegar a la lat y long indiciados que se le pasa al objeto cameraupdate
		mapa.addMarker(new MarkerOptions() //marker personalizado
		.position(myDestine)
		.title(tituloMarker)
		.snippet(direccion)
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_map_red)))
		.showInfoWindow();
	
	}
	
	
	
	
	private void changeActionBar() {
	
				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.ic_navigation_back);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);


		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
		        titulo.setTypeface(robotoBoldCondensed);
		        titulo.setText(getString(R.string.eventos).toUpperCase());
		        
		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}

}
