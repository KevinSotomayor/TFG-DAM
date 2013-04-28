package dam.project.wearevalencia.fragments;

import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.SlidingMenu.OnClosedListener;
import com.slidingmenu.lib.SlidingMenu.OnOpenedListener;

import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;

public class Main_Content_Fragment extends SherlockFragment{
	//constantes
	private final static int MAP = 1;

	private ActionBar actionBar;
	private Typeface robotoThin, robotoBoldCondensed;
	private SlidingMenu slidingMenu;
	
	private LinearLayout main_Layout;
	private AnimationDrawable animacion_backgrounds;
	private TransitionDrawable transitionDrawable;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.main_content_activity, null);	
	}

	
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		//debe ser llamado para crear el men�, de lo contrario no aparecer�n los items en el actionbar
		setHasOptionsMenu(true);
		changeActionBar();
		
		//hace referencia al objeto de la clase Main_fragment con todas las propiedades del slidingMenu
		slidingMenu = Main_FragmentActivity.putReference();
	
		
		/*codigo de animationDrawable*/
		//animacion asociada al linearLayout (tema 3 de Android - Dibujar en Android - pag 10)
		main_Layout = (LinearLayout)getActivity().findViewById(R.id.main_bg_layout);
		animacion_backgrounds = (AnimationDrawable)getActivity().getResources().getDrawable(R.anim.animacion_main_bg);
		
		//comprobacion si la version de android en la que se ejecuta es anterior a la honeycomb(target16)
		//ya que setBackgroundDrawable es deprecated.
		if(Build.VERSION.SDK_INT <= 16)
			main_Layout.setBackgroundDrawable(animacion_backgrounds);
		else
			main_Layout.setBackground(animacion_backgrounds);
		
		//inicar la animacion
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				animacion_backgrounds.start();
				
			}
		},2000);
		
		
		
		
		
		//ocultar iconos del actionbar o mostrarlos dependiendo de si esta abierto o no el sliding menu:
		slidingMenu.setOnOpenedListener(new OnOpenedListener() {

			public void onOpened() {
				actionBar.setHomeButtonEnabled(false);
    			actionBar.setDisplayShowHomeEnabled(false);
    			setHasOptionsMenu(false);
			}
		});
		
		slidingMenu.setOnClosedListener(new OnClosedListener() {
			
			public void onClosed() {
				actionBar.setHomeButtonEnabled(true);
    			actionBar.setDisplayShowHomeEnabled(true);
    			setHasOptionsMenu(true);
				
			}
		});

	}
	
   
	
	//Metodo del menu y el listener del men�	
		/*Menu del actionBarSherlock - boton de buscar */
		//a diferencia del menu comun este no infla un xml con los items, que tambien se puede.
			public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
				super.onCreateOptionsMenu(menu, menuInflater);
				
			 
				menu.add(0, MAP, 0, "Ir al mapa")
				.setIcon(R.drawable.location_place)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
				
				
			}
			
			
    public boolean onOptionsItemSelected (MenuItem item){
    	switch(item.getItemId()){
    	/*Listener del boton home actionBar */
    	case android.R.id.home:
    		slidingMenu.toggle();
    		//toggle(); -> Toggle the SlidingMenu. If it is open, it will be closed, and vice versa.
    		return true;
    		
    	case MAP:
    		//abrir mapa de la ciudad
    		Toast.makeText(getActivity(), "Mapa de Valencia", Toast.LENGTH_SHORT).show(); //de prueba
    		return true;
    		
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    	
    }
	
    
	//cambiar el actionbar con iconos y fondo personalizado
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void changeActionBar() {
			//typeface personalizadas
	        robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Thin.ttf");
	        robotoBoldCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
	        
					//boton de volver atras del boton home, e icono personalizado
					actionBar.setDisplayHomeAsUpEnabled(false);
	        		actionBar.setHomeButtonEnabled(true);
			        actionBar.setIcon(R.drawable.nav_menu);
			        
			        //cambiar el titulo por otro con subtitulo + layout
			        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
			        //permitir el customizado
			        actionBar.setDisplayShowCustomEnabled(true);
			       
					         
			        //inflar un view con el layout de los titulos
			        View customView = LayoutInflater.from(getActivity()).inflate(R.layout.actionbar_title,null);
			
			        //identificar las etiquetas y setTypeface otra letra
			        TextView titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
			        titulo.setTypeface(robotoThin);
			        
			        TextView subtitulo =(TextView)customView.findViewById(R.id.titulo2WeAreValencia);
			        subtitulo.setTypeface(robotoBoldCondensed);
	
  
			        /// center xml in actionbar
			        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
			        lp.gravity = Gravity.CENTER;
			        customView.setLayoutParams(lp);
			        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */
			
			        //set inflate view to actionBarSherlock
			        actionBar.setCustomView(customView);
					
		}	
	
}
