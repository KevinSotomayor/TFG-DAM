package dam.project.wearevalencia.fragments;

import java.util.ArrayList;
import org.holoeverywhere.widget.Toast;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import dam.project.wearevalencia.objects.LugaresDeInteres_Data_Objects;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

public class Main_LugaresDeInteres extends SherlockFragment{
	private Typeface robotoBoldCondensed, robotoCondensed;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	private TextView textViewMonumentos, textViewCentroCiudad, textViewParques, textViewCAC;
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
		//tarea asincrona para obtener los arraylists
		new TareaAsincrona().execute();
		//cambiar typeface a las etiquetas de texto que hay en la pantalla principal, además de los textview del footer
		changeTypefaceTextViews();
		
		
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
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
		    			slidingMenu.toggle();
						
					}
				}, 80);
				return true;

			case 1:
				Toast.makeText(getActivity(), "Mapa", Toast.LENGTH_LONG).show();
				return true;
			}

			return super.onOptionsItemSelected(item);
	    }
		
	public class TareaAsincrona extends AsyncTask<Void, Void, Void>{
		View layout, layout_pogressBar, progressBar;
		
		@Override
		protected void onPreExecute(){
			super.onPreExecute();
			layout = (LinearLayout)getView().findViewById(R.id.layout_main_lugaresdeinteres);
			layout.setVisibility(View.GONE);

			layout_pogressBar = (LinearLayout)getView().findViewById(R.id.layout_lugaresdeinteres_progressBar);
			layout_pogressBar.setVisibility(View.VISIBLE);
			
			progressBar = (ProgressBar)getView().findViewById(R.id.progressBar_LugaresDeInteres_Main);
			progressBar.setVisibility(View.VISIBLE);
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			arrayListMonumentos = LugaresDeInteres_Data_Objects.obtainMonuments(getActivity());
			arrayListCAC = LugaresDeInteres_Data_Objects.obtainCAC(getActivity());
			arrayListCentroCiudad = LugaresDeInteres_Data_Objects.obtainCentroCiudad(getActivity());
			arrayListParques = LugaresDeInteres_Data_Objects.obtainMonuments(getActivity());
			return null;
		}
		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
	
			
			new Handler().postDelayed(new Runnable() {

				@Override
				public void run() {
					layout_pogressBar.setVisibility(View.GONE);
					progressBar.setVisibility(View.GONE);
					layout.setVisibility(View.VISIBLE);

				}
			}, 1500); //esta espera simulará la descarga de datos, el arraylist de objetos
				
		}
	}
	   
	   
	private void changeActionBar() {
		//typeface personalizadas
        robotoBoldCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
        robotoCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Condensed.ttf");

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
	}
	
	private void listenersLayoutsButtons() {
		layoutMonumentos = (LinearLayout)getView().findViewById(R.id.layout_button_Monuments);
		layoutMonumentos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToIntent(arrayListMonumentos);
			}
		});
		
		layoutCentroCiudad = (LinearLayout)getView().findViewById(R.id.layout_button_CentroDeLaCiudad);
		layoutCentroCiudad.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToIntent(arrayListCentroCiudad);
			}
		});
		
		layoutParques = (LinearLayout)getView().findViewById(R.id.layout_button_Parques);
		layoutParques.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToIntent(arrayListParques);
			}
		});
		
		layoutCAC = (LinearLayout)getView().findViewById(R.id.layout_button_CiudadArtesCiencias);
		layoutCAC.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				goToIntent(arrayListCAC);
			}
		});
	}

	private void goToIntent(ArrayList<LugaresDeInteres_Item> arrayList){
		Main_FragmentActivity mFragmentActivity = (Main_FragmentActivity)getActivity();
		mFragmentActivity.onLugarDeInteresSelecconado(arrayList);
	}

}
