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
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
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
	
	//constructor cuando no se le pasan datos
	public LugaresDeInteres () {
		this.arrayLugaresDeInteres = obtainMonuments();
	}
	
	//constructor cuando se le pasan datos
	public LugaresDeInteres(ArrayList<String> arrayList) {
		this.arrayLugaresDeInteres = arrayList;
		setRetainInstance(true);
	}

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
		String lonjaDeLaSeda = "Lonja de la seda"; //getActivity().getString(R.string.lonjaDeLaSeda);
		String torresDeSerranos = "Torres de serranos"; //getActivity().getString(R.string.torresDeSerranos);
		String torresDeQuart = "Torres de quart";//getActivity().getString(R.string.torresDeQuart);
		String mercadoCentral = "Mercado Central";//getActivity().getString(R.string.mercadoCentral);
		String mercadoColon = "Mercado Colón"; //getActivity().getString(R.string.mercadoColon);
		String CAC = "Ciudad de las Artes y las Ciencias"; //getActivity().getString(R.string.cac);
		String plazaAyuntamiento = "Plaza del ayuntamiento"; //getActivity().getString(R.string.plazaAyuntamiento);
		String estacionNorte = "Estación del norte"; //getActivity().getString(R.string.estacionDelNorte);
		String plazaToros = "Plaza de toros"; //getActivity().getString(R.string.plazaDeToros);
		String plazaVirgen = "Plaza de la virgen"; //getActivity().getString(R.string.plazaDeLaVirgen);
		String elMicalet = "El micalet";//getActivity().getString(R.string.elMicalet);
		String palauMusica ="Palau de la musica"; // getActivity().getString(R.string.palauDeLaMusica);

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
		ListView listaMonumentos, listaCAC, listaCentroCiudad, ListaParques;
		AdaptadorLugaresDeInteres adaptador;
		View HeaderListView = (View)getActivity().getLayoutInflater().inflate(R.layout.lugaresdeinteres_list_header, null);

		protected Void doInBackground(Void... params) {

			listaMonumentos = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_Monumentos);
			listaMonumentos.addHeaderView(HeaderListView);


			try{
				//arrayLugaresDeInteres = obtainMonuments();
				adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
				listaMonumentos.setAdapter(adaptador);
				
				}catch (Exception e) {
					Log.e("Error en postExecute", getTag());
				}

			return null;	


		}
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

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
