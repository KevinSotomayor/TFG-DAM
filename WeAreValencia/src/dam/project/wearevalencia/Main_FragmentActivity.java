package dam.project.wearevalencia;

import java.util.ArrayList;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import dam.project.wearevalencia.fragments.Main_Content_Fragment;
import dam.project.wearevalencia.fragments.Sliding_Menu_Fragment;
import dam.project.wearevalencia.maps.Eventos_Ficha_Map;
import dam.project.wearevalencia.maps.Map_Item;
import dam.project.wearevalencia.objects.CentrosComerciales_Item;
import dam.project.wearevalencia.objects.Eventos_Item;
import dam.project.wearevalencia.objects.FiestasPopulares_Item;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;


public class Main_FragmentActivity extends SlidingFragmentActivity{
	private Fragment mContent;
	static SlidingMenu slidingMenu;
	private final String BUNDLE_KEY = "mContent";
	private final String BUNDLE_FROM_FRAGMENT = "bundleFromFragment";
	private final String BUNDLE_FROM_FRAGMENT_FP ="bundleFromFragmentFP";
	private final String BUNDLE_FROM_FRAGMENT_CC ="bundleFromFragmentCC";
	private final String BUNDLE_FROM_FRAGMENT_EVENTS_MAP ="bundleFromFragment";
	private final String BUNDLE_BUSCADOR ="bundleBuscador";

	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//identificar sliding menu:
		slidingMenu = getSlidingMenu();

		// Que no se deslize el actionbar
		setSlidingActionBarEnabled(true); 
		// es igual que: ->  //slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT); //deslizar todo menos el actionbar
		
		setBehindContentView(R.layout.sliding_menu_frame_list);
		//reemplazar la vista del sliding menu
		FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.sliding_menu_fragment, new Sliding_Menu_Fragment());
		fragmentTransaction.commit();
		
		//configurar slidingMenu
		SlidingMenuAction();
		

		//si esta guardado el fragment y sino establecer la vista de la pantalla principal
		if(savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, BUNDLE_KEY);
		
		if(mContent == null)
			mContent = new Main_Content_Fragment();
				
		setContentView(R.layout.main_frame_activity);
		//reemplazada la vista "contenedora(main_frame_activity)" por la vista "main_content_activity"
		fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.content_fragment, mContent).commit();
		
	}
	
	
	//guarda el fragment al salir de la actividad
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, BUNDLE_KEY, mContent);
	}
	
	
	//metodo que obtiene el bundle guardado en el onSaveInstanceState
		public void onRestoreInstanceState(Bundle inState){
			mContent = getSupportFragmentManager().getFragment(inState, BUNDLE_KEY);
			super.onRestoreInstanceState(inState);
		}

		
	private void SlidingMenuAction() {
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffset(155);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setFadeEnabled(true);
		slidingMenu.setFadeDegree(1.0f);
		slidingMenu.setBehindScrollScale(0.10f);
	}
	
	
	//metodo que se llama para reemplazar la vista de la actividad principal, esta, por el fragment que se selecciona en la opción de menu
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.content_fragment, fragment);
		transaction.commit();

		//este handler hace que la transicion de cierre
		//del contenido de la derecha del menu sea posible de ver
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				getSlidingMenu().showContent();
			}
		},40);
		
		
	}

	//devuelve la referencia al objeto de esta clase, con lo cual en las otras clases de donde querramos abrir el menú
	//solo habrá que instanciar el objeto.
	public static SlidingMenu putReference(){
		return slidingMenu;
	}

	
	//este metodo es el que se encargará de pasar a la siguiente pantalla, lo que hace es esperar que el fragment lo llame
	//y le pase el arraylist que tiene que enviar a la otra actividad.
	public void onLugarDeInteresSelecconado( ArrayList<LugaresDeInteres_Item> lugares) {
		Intent i = new Intent(Main_FragmentActivity.this, LugaresDeInteres_List.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(BUNDLE_FROM_FRAGMENT, lugares);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
		
	}
	
	//este metodo gestiona los intents de las fiestas populares, la lista.
	public void onFiestaPopularSelecconada(FiestasPopulares_Item fiesta) {
		Intent i = new Intent(Main_FragmentActivity.this, FiestasPopulares_Ficha_Item.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(BUNDLE_FROM_FRAGMENT_FP, fiesta);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
	//este metodo gestiona los intents de las fiestas populares, la lista.
	public void onCentroComercialSeleccionado(CentrosComerciales_Item cc) {
		Intent i = new Intent(Main_FragmentActivity.this, Map_Item.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(BUNDLE_FROM_FRAGMENT_CC, cc);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
	//metodo que envia el objeto seleccionado de los eventos a la siguiente pantalla
	//este metodo gestiona los intents de las fiestas populares, la lista.
	public void onEventoSeleccionado(Eventos_Item evento) {
		Intent i = new Intent(Main_FragmentActivity.this, Eventos_Ficha_Map.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(BUNDLE_FROM_FRAGMENT_EVENTS_MAP, evento);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
	public void onMapIconActionbarSeleccionado(ArrayList<LugaresDeInteres_Item> lugares) {
		Intent i = new Intent(Main_FragmentActivity.this, Map_Item.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(BUNDLE_FROM_FRAGMENT_EVENTS_MAP, lugares);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
	public void onBuscarSeleccionado(ArrayList<LugaresDeInteres_Item> lugares) {
		Intent i = new Intent(Main_FragmentActivity.this, Buscar.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(BUNDLE_BUSCADOR, lugares);
		i.putExtras(bundle);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
	}
	
}
