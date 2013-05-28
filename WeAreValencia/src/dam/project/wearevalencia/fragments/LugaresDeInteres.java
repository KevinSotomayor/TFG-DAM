package dam.project.wearevalencia.fragments;

import java.util.ArrayList;
import java.util.Random;
import org.holoeverywhere.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import dam.project.wearevalencia.LugaresDeInteres_Ficha_Item;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import dam.project.wearevalencia.maps.Map_Item;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class LugaresDeInteres extends SherlockFragment {
	
	private Typeface robotoBoldCondensed, robotoCondensed, robotoRegular;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	private final int MAPA = 1;
	private final String BUNDLE_OBJECT_ARRAYLIST = "objetoTotal";
	private ArrayList<LugaresDeInteres_Item> arrayLugaresDeInteres;
	
	
	//constructor cuando se le pasan datos, el arraylist de objetos
	public LugaresDeInteres(ArrayList<LugaresDeInteres_Item> arrayList) {
		this.arrayLugaresDeInteres = arrayList;
		setRetainInstance(true);
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.lugaresdeinteres_list, null);
	}


	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//debe ser llamado para crear el menú, de lo contrario no aparecerán los items en el actionbar
		setHasOptionsMenu(true);
		//personalizar el actionbar
		changeActionBar();
		//referencia del slidginmenu
		slidingMenu = Main_FragmentActivity.putReference();
		//tarea asíncrona que se encarga de gestionar la lista, el header y el inflado de los items
		new ListAsyncTask().execute();

	}
	
	//menu con el mapa de todos los lugares de interes que se muestren en ese moment
	public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){			
		super.onCreateOptionsMenu(menu, menuInflater);
		menu.add(0, MAPA, 0, getActivity().getString(R.string.irAlMapa))
			.setIcon(R.drawable.ic_action_go_map)
			.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

	}


   public boolean onOptionsItemSelected (MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			slidingMenu.toggle();
			return true;

		case 1:
			Toast.makeText(getActivity(), "Mapa de " + arrayLugaresDeInteres.get(MAPA).getCategory(), Toast.LENGTH_LONG).show();
			return true;
		}

		return super.onOptionsItemSelected(item);
    }



	private void changeActionBar() {
		//typeface personalizadas
        robotoBoldCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
        robotoCondensed = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Condensed.ttf");
        robotoRegular = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Regular.ttf");

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

	//clase asincrona para que mientras se cargan las listas se muestre un progressbar
	class ListAsyncTask extends AsyncTask<Void, Void, Void>{
		ListView lista;
		AdaptadorLugaresDeInteres adaptador;
		View HeaderListView = (View)getActivity().getLayoutInflater().inflate(R.layout.lugaresdeinteres_list_header, null);

		protected Void doInBackground(Void... params) {
			lista = (ListView)getView().findViewById(R.id.listView_lugaresDeInteres_Monumentos);
			
			//elegir una imagen al azar de cualquiera de los items de la lista para ponerla como fondo del header.
			ImageView imageView = (ImageView)HeaderListView.findViewById(R.id.imageViewLugaresDeInteres_Header);
			Random r = new Random();
			int max = arrayLugaresDeInteres.size() - 1;
			int randomNum = r.nextInt(max);
			imageView.setImageResource(arrayLugaresDeInteres.get(randomNum).getThumbailMax());
			
			lista.addHeaderView(HeaderListView);


			try{
				adaptador = new AdaptadorLugaresDeInteres(getActivity(), R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres );
				lista.setAdapter(adaptador);
				
				}catch (Exception e) {
					Log.e("Error en postExecute", getTag());
				}

			return null;	


		}
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			lista.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int position, long arg3) {
					LugaresDeInteres_Item object =  arrayLugaresDeInteres.get(position -1);
					Intent i = new Intent(getActivity(), LugaresDeInteres_Ficha_Item.class);
					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, object);
					i.putExtras(bundle);
					
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

				}
			});
		}

	}
	
	//clase interna que infla la vista con los empleados.
	class AdaptadorLugaresDeInteres extends ArrayAdapter<LugaresDeInteres_Item> {
    	Activity context;
    	int layoutResource;
    	ArrayList<LugaresDeInteres_Item> arrayLugares;

    	AdaptadorLugaresDeInteres(Activity context, int layoutResource, ArrayList<LugaresDeInteres_Item> arrayLugares) {
    		super(context, layoutResource, arrayLugares);
    		this.context = context;
    		this.layoutResource = layoutResource;
    		this.arrayLugares = arrayLugares;
    	}

    	public View getView(final int position, View convertView, ViewGroup parent) {
    		
    		View item = convertView;
    		ViewHolder holder;
    		
    		if ( item == null) {
    			LayoutInflater inflater = getActivity().getLayoutInflater();
    			item = inflater.inflate(R.layout.lugaresdeinteres_item_list, null);
    			
    			//guardando solo las referencias a los controles que utilizarré
    			holder = new ViewHolder();
    			holder.titulo =  (TextView)item.findViewById(R.id.textViewLugaresDeInteres_Title);
    			holder.contenido = (TextView)item.findViewById(R.id.textViewLugaresDeInteres_Content);holder.irAlMapa = (TextView)item.findViewById(R.id.button_lugaresDeInteres_irMapa);
    			holder.irAlMapa .setTypeface(robotoRegular);
    			holder.layoutIrAlMapa = (LinearLayout)item.findViewById(R.id.layoutLugaresDeInteres_IrAlMapa);
    			holder.thumbail = (ImageView)item.findViewById(R.id.imageViewLugaresDeInteres_Icon);
    			
    			item.setTag(holder);
    			
    		}else{
    			holder = (ViewHolder)item.getTag();
    		}
    		holder.titulo.setText(arrayLugares.get(position).getTitle());
			holder.titulo.setTypeface(robotoCondensed);

			holder.contenido .setTypeface(robotoRegular);
			String cadena = arrayLugares.get(position).getContent();
			String cadenaTotal = "";	
			for (int i = 0; i < cadena.length(); i++ ){
				
				if (i <= 50) {
					cadenaTotal += cadena.charAt(i);

				}

			}
			cadenaTotal += "...";
			holder.contenido .setText(cadenaTotal); 
			//solo dejo imrpimir 50 caracteres en cada item  que se presenta en la lista
			holder.irAlMapa .setText(arrayLugares.get(position).getAddres());


			holder.layoutIrAlMapa.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LugaresDeInteres_Item object =  arrayLugaresDeInteres.get(position);
					
					Intent i = new Intent(getActivity(), Map_Item.class);					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, object);
					i.putExtras(bundle);
					
					startActivity(i);
					getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

				}
			});
			
			holder.thumbail.setImageResource(arrayLugares.get(position).getThumbailMax());
    		return (item);

    	}

    }
	//obtención de la referencia a cada uno de los objetos a modificar mediante el método findViewById().
	//Para mejorar rendimiento aprovecho que estoy “guardando” un layout anterior para guardar
	//también la referencia a los controles que lo forman y defino la siguiente clase viewHolder 
    //con aquellos atributos con referencia a cada uno de los controles que tengo que manipular
	
	public static class ViewHolder{
		TextView titulo;
		TextView contenido;
		TextView irAlMapa;
		LinearLayout layoutIrAlMapa;
		ImageView thumbail;
	}
	

}
