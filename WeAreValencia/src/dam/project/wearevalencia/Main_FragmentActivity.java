package dam.project.wearevalencia;

import java.util.ArrayList;

import org.holoeverywhere.widget.Toast;

import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import dam.project.wearevalencia.fragments.Main_Content_Fragment;
import dam.project.wearevalencia.fragments.Main_LugaresDeInteres;
import dam.project.wearevalencia.fragments.Sliding_Menu_Fragment;
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
}
