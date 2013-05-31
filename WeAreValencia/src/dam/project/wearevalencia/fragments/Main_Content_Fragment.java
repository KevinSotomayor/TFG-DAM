package dam.project.wearevalencia.fragments;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import android.view.View.OnClickListener;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;

public class Main_Content_Fragment extends SherlockFragment{
	//constante que identifica que se presiona el boton del actionbar mapa
	private final static int MAP = 1;

	private ActionBar actionBar;
	private TextView titulo;
	private Typeface robotoBoldCondensed;
	private SlidingMenu slidingMenu;	
	private LinearLayout main_Layout;
	private AnimationDrawable animacion_backgrounds;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.main_content_activity, null);	
	}


	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		Button main_galeria, main_sitios, main_buscar, main_descubre;

		//debe ser llamado para crear el menú, de lo contrario no aparecerán los items en el actionbar
		setHasOptionsMenu(true);
		changeActionBar();
		
		setRetainInstance(true);
		//setRetainInstance(true) will tell the FragmentManager to keep the fragment around when the containing Activity is killed and rebuilt for some reason.
		// It doesn't guarantee that the Fragment instance will stick around after a transaction to add or replace.

		//hace referencia al objeto de la clase Main_fragment con todas las propiedades del slidingMenu
		slidingMenu = Main_FragmentActivity.putReference();
		
		/*codigo de animationDrawable, fondo que cambia en la pantalla principal*/
		//animacion asociada al linearLayout (tema 3 de Android - Dibujar en Android - pag 10)
		main_Layout = (LinearLayout)getActivity().findViewById(R.id.main_bg_layout);
		animacion_backgrounds = (AnimationDrawable)getActivity().getResources().getDrawable(R.anim.animacion_main_bg);

		//comprobacion si la version de android en la que se ejecuta es anterior a la honeycomb(target16)
		//ya que setBackgroundDrawable es deprecated.
		if(Build.VERSION.SDK_INT <= 16)
			main_Layout.setBackgroundDrawable(animacion_backgrounds);
		else
			main_Layout.setBackground(animacion_backgrounds);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {

				animacion_backgrounds.start();

			}
		},500);

		//cambiar typeface's a los botones de la pantalla principal
		main_galeria = (Button)getActivity().findViewById(R.id.main_galeria_button);
		main_galeria.setTypeface(robotoBoldCondensed);

		main_sitios = (Button)getActivity().findViewById(R.id.main_sitios_visitados_button);
		main_sitios.setTypeface(robotoBoldCondensed);

		main_buscar = (Button)getActivity().findViewById(R.id.main_buscar_sitio_button);
		main_buscar.setTypeface(robotoBoldCondensed);

		main_descubre = (Button)getActivity().findViewById(R.id.main_descubre_button);
		main_descubre.setTypeface(robotoBoldCondensed);
		main_descubre.setOnClickListener(new OnClickListener() {
			//abrir el menu con el boton descubre valencia
			@Override
			public void onClick(View v) {
				if(slidingMenu.isShown())
					new Handler().postDelayed(new Runnable() {
						
						@Override
						public void run() {
			    			slidingMenu.toggle();
							
						}
					}, 80);
				}
			});
		
	}

   

	//Metodo del menu y el listener del menú	/*Menu  actionBarSherlock*/
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
		super.onCreateOptionsMenu(menu, menuInflater);
		menu.add(0, MAP, 0, "Ir al mapa")
		.setIcon(R.drawable.ic_location_place)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);


	}


    public boolean onOptionsItemSelected (MenuItem item){
    	switch(item.getItemId()){
    	case android.R.id.home:
    		new Handler().postDelayed(new Runnable() {
				
				@Override
				public void run() {
	    			slidingMenu.toggle();
					
				}
			}, 80);
    		return true;
    		
    	case MAP:
    		return true;
    		
    	}
    	
		return super.onOptionsItemSelected(item);

    }

    
	//cambiar el actionbar con iconos y fondo personalizado
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void changeActionBar() {
			//typeface personalizadas
	        robotoBoldCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
	      
					//boton de volver atras del boton home, e icono personalizado
					actionBar.setDisplayHomeAsUpEnabled(false);
	        		actionBar.setHomeButtonEnabled(true);
			        actionBar.setIcon(R.drawable.ic_nav_menu);

			        //cambiar el titulo por otro con subtitulo + layout
			        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
			        //permitir el customizado
			        actionBar.setDisplayShowCustomEnabled(true);


			        //inflar un view con el layout de los titulos
			        View customView = LayoutInflater.from(getActivity()).inflate(R.layout.actionbar_title,null);

			        //identificar las etiquetas y setTypeface otra letra
			        titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
			        titulo.setTypeface(robotoBoldCondensed);

  
			        /// center xml in actionbar
			        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
			        lp.gravity = Gravity.CENTER;
			        customView.setLayoutParams(lp);
			        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

			        //set inflate view to actionBarSherlock
			        actionBar.setCustomView(customView);

		}	

}