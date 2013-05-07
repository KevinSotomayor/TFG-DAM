package dam.project.wearevalencia;


import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingFragmentActivity;
import dam.project.wearevalencia.fragments.Main_Content_Fragment;
import dam.project.wearevalencia.fragments.Sliding_Menu_Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class Main_FragmentActivity extends SlidingFragmentActivity{
	private Fragment mContent;
	static SlidingMenu slidingMenu;
	private final String BUNDLE_KEY = "mContent";
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Que se deslize el actionbar
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
	

		Button botonInicioSM = (Button)findViewById(R.id.buttonInicioSlidingMenu);
		botonInicioSM.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf"));
		//volver a la pantalla principal
		botonInicioSM.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switchContent(new Main_Content_Fragment());
			}
		});
		

	}
	
	//guarda el fragment al salir de la actividad
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		this.getSupportFragmentManager().putFragment(outState, BUNDLE_KEY, mContent);
	}
	
	//metodo que obtiene el bundle guardado en el onSaveInstanceState
		public void onRestoreInstanceState(Bundle inState){
			mContent = getSupportFragmentManager().getFragment(inState, BUNDLE_KEY);
			super.onRestoreInstanceState(inState);
		}

	private void SlidingMenuAction() {
		slidingMenu = getSlidingMenu();
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffset(80);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setFadeDegree(0.10f);
		slidingMenu.setBehindScrollScale(0);
		
	}
	
	//metodo que se llama para reemplazar la vista de la actividad principal, esta, por el fragment que se selecciona en la opción de menu
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
		transaction.replace(R.id.content_fragment, fragment);
		//transaction.addToBackStack(null);
		transaction.commit();

		//este handler hace que la transicion de cierre
		//del contenido de la derecha del menu sea posible de ver
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}

	
	
	//devuelve la referencia al objeto de esta clase, con lo cual en las otras clases de donde querramos abrir el menú
	//solo habrá que instanciar el objeto.
	public static SlidingMenu putReference(){
		return slidingMenu;
	}
}
