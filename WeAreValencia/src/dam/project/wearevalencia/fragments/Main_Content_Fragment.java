package dam.project.wearevalencia.fragments;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.slidingmenu.lib.SlidingMenu;
import android.view.View.OnClickListener;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;

public class Main_Content_Fragment extends SherlockFragment{
	private ActionBar actionBar;
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

		actionBar.hide(); //sin actionbar la primera pantalla
		
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
			
			@Override
			public void onClick(View v) {
				if(slidingMenu.isShown()){
					slidingMenu.toggle();
				}else{
					slidingMenu.showMenu();
				}
				
			}
		});
		
	}
	
	
}
