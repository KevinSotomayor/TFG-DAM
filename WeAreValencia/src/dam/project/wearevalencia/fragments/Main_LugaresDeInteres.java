package dam.project.wearevalencia.fragments;

import java.util.ArrayList;
import org.holoeverywhere.widget.Toast;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import dam.project.wearevalencia.LugaresDeInteres_List;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import dam.project.wearevalencia.objects.LugaresDeInteres_Data_Objects;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

public class Main_LugaresDeInteres extends SherlockFragment{
	private Typeface robotoBoldCondensed, robotoCondensed, robotoRegular, robotoThin;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	private TextView textViewMonumentos, textViewCentroCiudad, textViewParques, textViewCAC, textViewWeAre, textViewValencia;
	private LinearLayout layoutMonumentos, layoutCentroCiudad, layoutParques, layoutCAC;
	private final int MAPA = 1;

	ArrayList<LugaresDeInteres_Item> arrayListMonumentos;
	ArrayList<LugaresDeInteres_Item> arrayListCentroCiudad;
	ArrayList<LugaresDeInteres_Item> arrayListCAC;
	ArrayList<LugaresDeInteres_Item> arrayListParques;
		
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.main_lugaresdeinteres, null);
	}


	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//debe ser llamado para crear el menú, de lo contrario no aparecerán los items en el actionbar
		setHasOptionsMenu(true);
		//referencia del slidginmenu
		slidingMenu = Main_FragmentActivity.putReference();
		//personalizar el actionbar
		changeActionBar();
		
		//cambiar typeface a las etiquetas de texto que hay en la pantalla principal, además de los textview del footer
		changeTypefaceTextViews();
		
		
		arrayListMonumentos = LugaresDeInteres_Data_Objects.obtainMonuments(getActivity());
		//listeners de cada boton para que lleve a la siguiente pantalla pasando un objeto
		listenersLayoutsButtons();
		
		
		
	

	}
	
	//menu con el mapa de todos los lugares de interes que se muestren en ese moment
		public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){			
			super.onCreateOptionsMenu(menu, menuInflater);
			menu.add(0, MAPA, 0, getString(R.string.irAlMapa))
				.setIcon(R.drawable.ic_action_go_map)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		}


	   public boolean onOptionsItemSelected (MenuItem item){
			switch(item.getItemId()){
			case android.R.id.home:
				slidingMenu.toggle();
				return true;

			case 1:
				Toast.makeText(getActivity(), "Mapa", Toast.LENGTH_LONG).show();
				return true;
			}

			return super.onOptionsItemSelected(item);
	    }
		

	private void changeActionBar() {
		//typeface personalizadas
        robotoBoldCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
        robotoCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Condensed.ttf");
        robotoRegular = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Regular.ttf");
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Thin.ttf");

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
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
		        titulo.setTypeface(robotoBoldCondensed);
		        titulo.setText(getActivity().getString(R.string.lugaresDeInteres));


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}
	
	private void changeTypefaceTextViews(){
		textViewMonumentos = (TextView)getView().findViewById(R.id.textView_mainlugaresdeinteres_monumentos);
		textViewMonumentos.setTypeface(robotoCondensed);
		
		textViewCentroCiudad = (TextView)getView().findViewById(R.id.textView_mainlugaresdeinteres_centroCiudad);
		textViewCentroCiudad.setTypeface(robotoCondensed);
		
		textViewParques = (TextView)getView().findViewById(R.id.textView_mainlugaresdeinteres_parques);
		textViewParques.setTypeface(robotoCondensed);

		textViewCAC = (TextView)getView().findViewById(R.id.textView_mainlugaresdeinteres_cac);
		textViewCAC.setTypeface(robotoCondensed);
		
		textViewCAC = (TextView)getView().findViewById(R.id.textView_mainlugaresdeinteres_cac);
		textViewCAC.setTypeface(robotoCondensed);
		
		textViewWeAre = (TextView)getView().findViewById(R.id.textView_main_lugaresdeinteres_weare);
		textViewWeAre.setTypeface(robotoThin);
		
		textViewValencia = (TextView)getView().findViewById(R.id.textView_main_lugaresdeinteres_valencia);
		textViewValencia.setTypeface(robotoBoldCondensed);

	}
	
	private void listenersLayoutsButtons() {
		layoutMonumentos = (LinearLayout)getView().findViewById(R.id.layout_button_Monuments);
		layoutMonumentos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToIntent(arrayListMonumentos);
			}
		});
		
		layoutCentroCiudad = (LinearLayout)getView().findViewById(R.id.layout_button_CentroCiudad);
		layoutCentroCiudad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		layoutParques = (LinearLayout)getView().findViewById(R.id.layout_button_Parques);
		layoutParques.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		layoutCAC = (LinearLayout)getView().findViewById(R.id.layout_button_CAC);
		layoutCAC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
	}

	private void goToIntent(ArrayList<LugaresDeInteres_Item> arrayList){
		Main_FragmentActivity mFragmentActivity = (Main_FragmentActivity)getActivity();
		mFragmentActivity.onLugarDeInteresSelecconado(arrayListMonumentos);
	}

}
