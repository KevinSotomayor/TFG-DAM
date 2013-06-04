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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.slidingmenu.lib.SlidingMenu;
import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;
import dam.project.wearevalencia.objects.CentrosComerciales_Data_Objects;
import dam.project.wearevalencia.objects.CentrosComerciales_Item;

public class Main_CentrosComerciales extends SherlockFragment{
	private Typeface robotoBoldCondensed, robotoCondensed, robotoRegular;
	private ActionBar actionBar;
	private SlidingMenu slidingMenu;
	private ListView listaCC;
	private AdaptadorFiestasPopulares adaptador;
	ArrayList<CentrosComerciales_Item> arrayListCC;
		
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		actionBar = ((SherlockFragmentActivity) getActivity()).getSupportActionBar();	
		return inflater.inflate(R.layout.fiestaspopulares_centroscomerciales_eventos_list, null);
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
		arrayListCC = CentrosComerciales_Data_Objects.obtainCentrosComerciales(getActivity());
		
		listaCC = (ListView)getView().findViewById(R.id.listView_fiestasPopulares);
		adaptador = new AdaptadorFiestasPopulares(getActivity(), R.layout.centroscomerciales_item_list, arrayListCC );
		listaCC.setAdapter(adaptador);
		listaCC.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
				
			CentrosComerciales_Item object = adaptador.getItem(position);
			Main_FragmentActivity mFragmentActivity = (Main_FragmentActivity)getActivity();
			mFragmentActivity.onCentroComercialSeleccionado(object);

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
	class AdaptadorFiestasPopulares extends ArrayAdapter<CentrosComerciales_Item> {
    	Activity context;
    	int layoutResource;
    	ArrayList<CentrosComerciales_Item> arrayAdapter;

    	AdaptadorFiestasPopulares(Activity context, int layoutResource, ArrayList<CentrosComerciales_Item> array) {
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
    			item = inflater.inflate(layoutResource, null);
    			
    			//guardando solo las referencias a los controles que utilizarré
    			holder = new ViewHolder();
    			holder.textViewTitle =  (TextView)item.findViewById(R.id.textView_CentrosComerciales_Item_Title);
    			holder.textViewAddress = (TextView)item.findViewById(R.id.textView_CentrosComerciales_Item_Address);
    			holder.textViewTelephone = (TextView)item.findViewById(R.id.textView_CentrosComerciales_Item_Telephone);
    			holder.textViewWeb = (TextView)item.findViewById(R.id.textView_CentrosComerciales_Item_web);
    			holder.thumbail = (ImageView)item.findViewById(R.id.imageView_CentrosComerciales_Item);
    			
    			item.setTag(holder);
    			
    		}else{
    			holder = (ViewHolder)item.getTag();
    		}
    		holder.textViewTitle.setText(arrayAdapter.get(position).getTitle());
    		holder.textViewTitle.setTypeface(robotoCondensed);
    		
    		holder.textViewAddress.setText(arrayAdapter.get(position).getAddress());
    		holder.textViewAddress.setTypeface(robotoRegular);
    		
    		
    		holder.textViewTelephone.setText(arrayAdapter.get(position).getTelephone());
			holder.textViewTelephone.setTypeface(robotoRegular);
			
			holder.textViewWeb.setText(arrayAdapter.get(position).getWeb());
			holder.textViewWeb.setTypeface(robotoRegular);
			
			holder.thumbail.setImageResource(arrayAdapter.get(position).getThumbail());
		
    		return (item);

    	}
    	
    }
	//obtención de la referencia a cada uno de los objetos a modificar mediante el método findViewById().
	//Para mejorar rendimiento aprovecho que estoy “guardando” un layout anterior para guardar
	//también la referencia a los controles que lo forman y defino la siguiente clase viewHolder 
    //con aquellos atributos con referencia a cada uno de los controles que tengo que manipular
	public static class ViewHolder{
		TextView textViewTitle, textViewAddress, textViewTelephone, textViewWeb;
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
		        titulo.setText(getActivity().getString(R.string.centrosComerciales).toUpperCase());


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}
}
