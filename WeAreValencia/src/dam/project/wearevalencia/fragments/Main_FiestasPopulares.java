package dam.project.wearevalencia.fragments;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import dam.project.wearevalencia.objects.FiestasPopulares_Item;
import dam.project.wearevalencia.objects.FiestasPopulares_Data_Objects;

public class Main_FiestasPopulares extends SherlockFragment{
	private Typeface robotoBoldCondensed, robotoCondensed, robotoRegular;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	private ListView listaFiestas;
	private AdaptadorFiestasPopulares adaptador;
	ArrayList<FiestasPopulares_Item> arrayListFiestas;
		
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.fiestaspopulares_list, null);
	}


	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		//debe ser llamado para crear el menú, de lo contrario no aparecerán los items en el actionbar
		setHasOptionsMenu(true);
		
		//referencia del slidginmenu
		slidingMenu = Main_FragmentActivity.putReference();
		//personalizar el actionbar
		changeActionBar();
		
		//obtener el arrayList con los objetos en el arraylist
		arrayListFiestas = FiestasPopulares_Data_Objects.obtainFiestasPopulares(getActivity());
		
		listaFiestas = (ListView)getView().findViewById(R.id.listView_fiestasPopulares);
		adaptador = new AdaptadorFiestasPopulares(getActivity(), R.layout.fiestaspopulares_item_list, arrayListFiestas );
		listaFiestas.setAdapter(adaptador);
		listaFiestas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
				
			FiestasPopulares_Item object = adaptador.getItem(position);
			Main_FragmentActivity mFragmentActivity = (Main_FragmentActivity)getActivity();
			mFragmentActivity.onFiestaPopularSelecconada(object);

			}
		});

	}
	
	//menu con el mapa de todos los lugares de interes que se muestren en ese moment
		public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){			
			super.onCreateOptionsMenu(menu, menuInflater);
			menu.add(0, 0, 0, getActivity().getString(R.string.vacio))
				.setIcon(R.drawable.ic_empty)
				.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		}


	   public boolean onOptionsItemSelected (MenuItem item){
			switch(item.getItemId()){
			case android.R.id.home:
				slidingMenu.toggle();
				return true;
			}
			return super.onOptionsItemSelected(item);
	    }
				
	//clase interna que infla la vista con los empleados.
	class AdaptadorFiestasPopulares extends ArrayAdapter<FiestasPopulares_Item> {
    	Activity context;
    	int layoutResource;
    	ArrayList<FiestasPopulares_Item> arrayAdapter;

    	AdaptadorFiestasPopulares(Activity context, int layoutResource, ArrayList<FiestasPopulares_Item> array) {
    		super(context, layoutResource, array);
    		this.context = context;
    		this.layoutResource = layoutResource;
    		this.arrayAdapter = array;
    	}

    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View item = convertView;
    		ViewHolder holder;
    		
    		if ( item == null) {
    			LayoutInflater inflater = LayoutInflater.from(getActivity());
    			item = inflater.inflate(R.layout.fiestaspopulares_item_list, null);
    			
    			//guardando solo las referencias a los controles que utilizarré
    			holder = new ViewHolder();
    			holder.textViewTitle =  (TextView)item.findViewById(R.id.textView_fiestaspopulares_item_list);
    			holder.textViewContent = (TextView)item.findViewById(R.id.textViewfiestaspopulares_description);
    			holder.textViewCalendar = (TextView)item.findViewById(R.id.textViewfiestaspopulares_description_day);
    			holder.thumbail = (ImageView)item.findViewById(R.id.imageView_fiestaspopulares_item_list);
    			
    			item.setTag(holder);
    			
    		}else{
    			holder = (ViewHolder)item.getTag();
    		}
    		holder.textViewTitle.setText(arrayAdapter.get(position).getTitle());
    		holder.textViewTitle.setTypeface(robotoCondensed);
    		
    		holder.textViewContent.setText(obtainsCharactersFromString(arrayAdapter.get(position).getContent(), 75));
    		holder.textViewContent.setTypeface(robotoRegular);
    		
    		
    		holder.textViewCalendar.setText(obtainsCharactersFromString(arrayAdapter.get(position).getCalendar(),45));
			holder.textViewCalendar.setTypeface(robotoRegular);
			holder.thumbail.setImageResource(arrayAdapter.get(position).getThumbail());
		
    		return (item);

    	}
    	
    	private String obtainsCharactersFromString(String cadena, int caracteres){
	
			String cadenaTotal = "";	
			for (int i = 0; i < cadena.length(); i++ ){
				
				if (i <= caracteres) {
					cadenaTotal += cadena.charAt(i);

				}

			}
			return cadenaTotal += "...";
    	}

    }
	//obtención de la referencia a cada uno de los objetos a modificar mediante el método findViewById().
	//Para mejorar rendimiento aprovecho que estoy “guardando” un layout anterior para guardar
	//también la referencia a los controles que lo forman y defino la siguiente clase viewHolder 
    //con aquellos atributos con referencia a cada uno de los controles que tengo que manipular
	public static class ViewHolder{
		TextView textViewTitle, textViewContent, textViewCalendar;
		ImageView thumbail;
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
		        titulo.setText(getActivity().getString(R.string.fiestas).toUpperCase());


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}
	
}
