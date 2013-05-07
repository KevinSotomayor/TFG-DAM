package dam.project.wearevalencia.fragments;


import java.util.ArrayList;
import org.holoeverywhere.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LugaresDeInteres extends SherlockFragment {
	private Typeface robotoThin, robotoBoldCondensed;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	
	private final int BUSCAR = 1;
	
	private ArrayList<String> arrayLugaresDeInteres;
	
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.lugaresdeinteres_list, null);
	}
	

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//debe ser llamado para crear el menú, de lo contrario no aparecerán los items en el actionbar
		setHasOptionsMenu(true);
		
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		changeActionBar();
		
		slidingMenu = Main_FragmentActivity.putReference();
		
		new ListAsyncTask().execute();

	}

	//metodos para recuperar el arraylist con la lista de los lugares
	public ArrayList<String> obtainMonuments(){
		ArrayList<String> arrayList = new ArrayList<String>();
		
		//Strings de monumentos + array
		String lonjaDeLaSeda = getString(R.string.lonjaDeLaSeda);
		String torresDeSerranos = getString(R.string.torresDeSerranos);
		String torresDeQuart = getString(R.string.torresDeQuart);
		String mercadoCentral = getString(R.string.mercadoCentral);
		String mercadoColon = getString(R.string.mercadoColon);
		String CAC = getString(R.string.cac);
		String plazaAyuntamiento = getString(R.string.plazaAyuntamiento);
		String estacionNorte = getString(R.string.estacionDelNorte);
		String plazaToros = getString(R.string.plazaDeToros);
		String plazaVirgen = getString(R.string.plazaDeLaVirgen);
		String elMicalet = getString(R.string.elMicalet);
		String palauMusica = getString(R.string.palauDeLaMusica);
		
		arrayList.add(lonjaDeLaSeda);
		arrayList.add(torresDeSerranos);
		arrayList.add(torresDeQuart);
		arrayList.add(mercadoCentral);
		arrayList.add(mercadoColon);
		arrayList.add(plazaAyuntamiento);
		arrayList.add(estacionNorte);
		arrayList.add(plazaToros);
		arrayList.add(plazaVirgen);
		arrayList.add(elMicalet);
		arrayList.add(CAC);
		arrayList.add(palauMusica);

		return arrayList;
		
	}
	
	public ArrayList<String> obtainCAC(){
		ArrayList<String> arrayList = new ArrayList<String>();
		String hemisferic = getString(R.string.hemisferic);
		String museoDeLasCiencias = getString(R.string.museoDeLasCiencias);
		String oceanografic = getString(R.string.oceanografic);
		String palauArts = getString(R.string.palauDeLesArts);
		String umbracle = getString(R.string.umbracle);
		String agora = getString(R.string.agora);
		
		arrayList.add(hemisferic);
		arrayList.add(museoDeLasCiencias);
		arrayList.add(oceanografic);
		arrayList.add(palauArts);
		arrayList.add(umbracle);
		arrayList.add(agora);
	
		return arrayList;
		
	}
	
	public ArrayList<String> obtainCC(){
		ArrayList<String> arrayList = new ArrayList<String>();
		String carmen = getString(R.string.elCarmen);
		String ccbancaja = getString(R.string.ccbancaja);
		String lonja = getString(R.string.lonjaDeLaSeda);
		String plazaReina = getString(R.string.plazaDeLaVirgen);
		String plazaToros = getString(R.string.plazaDeToros);
		String ayuntamiento = getString(R.string.plazaAyuntamiento);
		String tserranos = getString(R.string.torresDeSerranos);
		String tquart = getString(R.string.torresDeQuart);
		String streetArt = getString(R.string.streetArt);
		
		arrayList.add(carmen);
		arrayList.add(ccbancaja);
		arrayList.add(lonja);
		arrayList.add(plazaReina);
		arrayList.add(plazaToros);
		arrayList.add(ayuntamiento);
		arrayList.add(tserranos);
		arrayList.add(tquart);
		arrayList.add(streetArt);
	
		return arrayList;
		
	}
	
	public ArrayList<String> obtainParques(){
		ArrayList<String> arrayList = new ArrayList<String>();
		String jbotanico = getString(R.string.jBotanico);
		String jViveros = getString(R.string.jViveros);
		String jturia = getString(R.string.jTuria);
		String parqueCabecera = getString(R.string.parqueCabecera);

		
		arrayList.add(jbotanico);
		arrayList.add(jViveros);
		arrayList.add(jturia);
		arrayList.add(parqueCabecera);
	
		return arrayList;
		
	}
	
	
	
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){			
		super.onCreateOptionsMenu(menu, menuInflater);

		menu.add(0, BUSCAR, 0, getActivity().getString(R.string.buscarLugaresDeInteres))
			.setIcon(R.drawable.ic_action_search)
			.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW).getItemId();
			
	}
	
	
   public boolean onOptionsItemSelected (MenuItem item){
		switch(item.getItemId()){
		
		case android.R.id.home:
			slidingMenu.toggle();
			return true;
	
		case 1:
			Toast.makeText(getActivity(), "Buscador", Toast.LENGTH_LONG).show();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
    }
	

	
	private void changeActionBar() {
		//typeface personalizadas
        robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Thin.ttf");
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
		        View customView = LayoutInflater.from(getActivity()).inflate(R.layout.actionbar_title_maps_with_gallery,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloOtherActivity);
		        titulo.setText(getString(R.string.lugaresDe));
		        titulo.setTypeface(robotoThin);

		        TextView otroTitulo =(TextView)customView.findViewById(R.id.titulo2OtherActivity);
		        otroTitulo.setText(getString(R.string.interes));
		        otroTitulo.setTypeface(robotoBoldCondensed);


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}
	
	//clase asincrona para que mientras se cargan las listas se muestre un progressbar
		class ListAsyncTask extends AsyncTask<Void, Void, Void>{
			TextView monumentos, cac, centroCiudad, parques;
			ListView listaMonumentos, listaCAC, listaCentroCiudad, ListaParques;
			AdaptadorLugaresDeInteres adaptador;
			
			View scrollView, progressBar;
			
			protected void onPreExecute() {
				super.onPreExecute();
				
				progressBar = (ProgressBar)getView().findViewById(R.id.progressBarLugaresDeInteres);
				progressBar.setVisibility(View.VISIBLE);
				
				scrollView = getView().findViewById(R.id.scrollView_LugaresDeInteres);
				scrollView.setVisibility(View.GONE);

			}
			
			protected Void doInBackground(Void... params) {
				monumentos = (TextView)getView().findViewById(R.id.textViewlugaresDeInteres_Monumentos);
				monumentos.setText(getString(R.string.Monumentos));
				monumentos.setTypeface(robotoThin);
			
				cac = (TextView)getView().findViewById(R.id.textViewlugaresDeInteres_CAC);
				cac.setText(getString(R.string.cac));
				cac.setTypeface(robotoThin);
				
				centroCiudad = (TextView)getView().findViewById(R.id.textViewlugaresDeInteres_centroCiudad);
				centroCiudad.setText(getString(R.string.centroCiudad));
				centroCiudad.setTypeface(robotoThin);
				
				parques = (TextView)getView().findViewById(R.id.textViewlugaresDeInteres_parques);
				parques.setText(getString(R.string.parques));
				parques.setTypeface(robotoThin);					

				listaMonumentos = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_Monumentos);
				listaCAC = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_CAC);
				listaCentroCiudad = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_centroCiudad);
				ListaParques = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_parques);
				//adaptadores con listas rellenas:
				
				try{
					arrayLugaresDeInteres = obtainMonuments();
					adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
					listaMonumentos.setAdapter(adaptador);
					HelperListView.getListViewSize(listaMonumentos);
					
					arrayLugaresDeInteres = obtainCAC();
					adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
					listaCAC.setAdapter(adaptador);
					HelperListView.getListViewSize(listaCAC);
					
					arrayLugaresDeInteres = obtainCC();
					adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
					listaCentroCiudad.setAdapter(adaptador);
					HelperListView.getListViewSize(listaCentroCiudad);
					
					arrayLugaresDeInteres = obtainParques();
					adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
					ListaParques.setAdapter(adaptador);
					HelperListView.getListViewSize(ListaParques);
					//http://www.androidhub4you.com/2012/12/listview-into-scrollview-in-android.html
					}catch (Exception e) {
						Log.e("Error en postExecute", getTag());
					}
				
				return null;	

				
			}
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				
				new Handler().postDelayed(new Runnable() {
					
					@Override
					public void run() {
						progressBar.setVisibility(View.GONE);
						scrollView.setVisibility(View.VISIBLE);

					}
				}, 2000);
		
			}

		}
		//clase interna que infla la vista con los empleados.
		class AdaptadorLugaresDeInteres extends ArrayAdapter<String> {
	    	
	    	Activity context;
	    	int layoutResource;
	    	ArrayList<String> arrayLugares;
	    	
	    	AdaptadorLugaresDeInteres(Activity context, int layoutResource, ArrayList<String> arrayLugares) {
	    		super(context, layoutResource, arrayLugares);
	    		this.context = context;
	    		this.layoutResource = layoutResource;
	    		this.arrayLugares = arrayLugares;
	    	}
	    	
	    	public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = getActivity().getLayoutInflater();
				View item = inflater.inflate(R.layout.lugaresdeinteres_item_list, null);
				
				TextView textoItemList = (TextView)item.findViewById(R.id.textViewLugaresDeInteres_ItemList);
				textoItemList.setText(arrayLugares.get(position));
				textoItemList.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Condensed.ttf"));
	    		
	    		return (item);
				
	    	}
	    	
	    }
	

}
