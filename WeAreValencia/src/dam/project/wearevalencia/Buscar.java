package dam.project.wearevalencia;

import java.util.ArrayList;
import java.util.Random;
import org.holoeverywhere.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import dam.project.wearevalencia.maps.Map_Item;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

public class Buscar extends SherlockFragmentActivity {
	
	private Typeface robotoBoldCondensed, robotoCondensed, robotoRegular;
	private ActionBar actionBar;
	private final String BUNDLE_BUSCADOR ="bundleBuscador";
	private final String BUNDLE_OBJECT_ARRAYLIST = "objetoTotal";
	private ArrayList<LugaresDeInteres_Item> arrayLugaresDeInteres;
	private TextView textoNoEcontrado, textoLista;
	private EditText buscador;
	private ListView lista;
	private View layout_lista, Layout_icono_search, Layout_noencontrado; //layouts que se ocultarán o no dependiendo de que se filtre
	private AdaptadorLugaresDeInteres adaptador;

	@SuppressWarnings("unchecked")
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buscador_list);
		
		actionBar = getSupportActionBar();	

		//personalizar el actionbar
		changeActionBar();
				
		Bundle bundle = getIntent().getExtras();
		if(bundle != null){
			arrayLugaresDeInteres = (ArrayList<LugaresDeInteres_Item>)bundle.getSerializable(BUNDLE_BUSCADOR);

		new BuscadorAsyncTask().execute();
		
		}else{
			Toast.makeText(this, "No se han podido cargar los items", Toast.LENGTH_SHORT).show();
		}
			

	}
	
	//menu que ayuda a centrar el texto del actionbar //truco
	public boolean onCreateOptionsMenu(Menu menu){			
		super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0,  getString(R.string.vacio))
		.setIcon(R.drawable.ic_empty)
		.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		return true;
		
	}


   public boolean onOptionsItemSelected (MenuItem item){
		switch(item.getItemId()){
		case android.R.id.home:
			Buscar.this.finish();
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			return true;

		}
		return super.onOptionsItemSelected(item);
    }
   
   class BuscadorAsyncTask extends AsyncTask<Void, Void, Void>{
	   @Override
	   protected void onPreExecute(){
			super.onPreExecute();

			//identificar los layouts y controles
			buscador = (EditText)findViewById(R.id.editText_buscadorMainContent);
			layout_lista = (LinearLayout)findViewById(R.id.layout_buscador_list_listView);
			Layout_noencontrado = (LinearLayout)findViewById(R.id.layout_buscador_list_icono_noencontrado);
			Layout_icono_search = (LinearLayout)findViewById(R.id.layout_buscador_list_icono_search);
			textoNoEcontrado = (TextView)findViewById(R.id.textView_buscador_noencontrado);
			textoLista = (TextView)findViewById(R.id.textView_buscador_Lista_Coincidencias);
			lista = (ListView)findViewById(R.id.listView_BuscadorMainContent);
			adaptador = new AdaptadorLugaresDeInteres(Buscar.this, R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres);
		}
	   
		@Override
		protected Void doInBackground(Void... params) {

			textoNoEcontrado.setTypeface(robotoBoldCondensed);
			textoLista.setTypeface(robotoBoldCondensed);
			
			buscador.addTextChangedListener(filterText); //listener cuando se escribe un caracter en el edittext
			buscador.clearFocus();
			
			Layout_icono_search.setVisibility(View.VISIBLE);
			layout_lista.setVisibility(View.GONE);
			Layout_noencontrado.setVisibility(View.GONE);
			
			try{
				lista.setAdapter(adaptador);	
			}catch (Exception e) {
				
			}
			
			return null;
		}
		
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			lista.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					
					LugaresDeInteres_Item object = (LugaresDeInteres_Item)adaptador.getItem(position);
					Intent i = new Intent(Buscar.this, LugaresDeInteres_Ficha_Item.class);
					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, object);
					i.putExtras(bundle);
					
					startActivity(i);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

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

     	public View getView(int position, View convertView, ViewGroup parent) {
     		
     		View item = convertView;
     		ViewHolder holder;
     		
     		if ( item == null) {
     			LayoutInflater inflater = getLayoutInflater();
     			item = inflater.inflate(layoutResource, null);
     			
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

 			final int auxposition = position;
 			holder.layoutIrAlMapa.setOnClickListener(new OnClickListener() {
 				@Override
 				public void onClick(View v) {
 					LugaresDeInteres_Item object =  (LugaresDeInteres_Item)adaptador.getItem(auxposition);
 					
 					Intent i = new Intent(Buscar.this, Map_Item.class);					
 					Bundle bundle = new Bundle();
 					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, object);
 					i.putExtras(bundle);
 					
 					startActivity(i);
 					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

 				}
 			});
 			
 			holder.thumbail.setImageResource(arrayLugares.get(position).getThumbailMax());
     		return (item);

     	}

     }

 	public static class ViewHolder{
 		TextView titulo;
 		TextView contenido;
 		TextView irAlMapa;
 		LinearLayout layoutIrAlMapa;
 		ImageView thumbail;
 	}
   
   
 	private TextWatcher filterText = new TextWatcher() {
 	   	int total;
 	   	@Override
 	   	//Se visualizan o no dependiendo si el usuario al escribir un caracter en el editText coincide con algun objeto de la lista
 	   	//y si no coinciden, oculta los demas layouts para mostrar el layout personalizado para que se muestre cuando no existe ese objeto en el arraylist que guarda todos los sitios
 	   	
 			public void onTextChanged(final CharSequence s, int start, int before, int count) {
 	   		if(arrayLugaresDeInteres != null){
 				
 				//hacer el trabajo dificil en un handler, ya que el buscador, por cada busqueda añade y vuelve a poner el adaptador con los objetos
 				//que coinciden, por eso un nuevo handler, para que al escribir cada palabra no sea lento, ya que en cada caracter hace la busqueda
 				new Handler().post(new Runnable() {
 					
 					/*
 					 * Explicacion del buscador:
 					 * coger el titulo de cada objeto y compararlo ignorando si esta en mayuscula o minuscula
 					 *si contiene el caracter introducido en el edittext se guarda en el nuevo arraylist
 					 *este nuevo arraylist se muestra en pantalla, porque ya ha filtrado los objetos del arraylist que contienen esa letra
 					 *
 					 */
 					@Override
 					public void run() {
 						if(!s.toString().toLowerCase().equalsIgnoreCase("")){
 							
 							ArrayList<LugaresDeInteres_Item> arrayBusqueda = new ArrayList<LugaresDeInteres_Item>();
 			    			
 			    			for(int i = 0; i < arrayLugaresDeInteres.size(); i++){
 			    				if(arrayLugaresDeInteres.get(i).getTitle().toString().toLowerCase().contains(s.toString().toLowerCase())){
 			    					arrayBusqueda.add(arrayLugaresDeInteres.get(i));
 			    				}
 			    			}
 			    			Layout_icono_search.setVisibility(View.GONE);
	 			   			layout_lista.setVisibility(View.VISIBLE);
	 			   			Layout_noencontrado.setVisibility(View.GONE);
	 			   			
	 			   			adaptador = new AdaptadorLugaresDeInteres(Buscar.this, R.layout.lugaresdeinteres_item_list, arrayBusqueda);
 			    			lista.setAdapter(adaptador);
 			    			
 			    			try{
 			    				total = arrayBusqueda.size();
 			    			if (total == 0){
 			    				
 			    				//ademas que la lista desaparece, desaparecera el layout, mostrando eel que no encuentra coincidencias
 			    				layout_lista.setVisibility(View.GONE);
 			    				Layout_icono_search.setVisibility(View.GONE);
 			    				
 			    				Layout_noencontrado.setVisibility(View.VISIBLE);
 			    				textoNoEcontrado.setText("No coincide ningún lugar :(");
 			    			
 			    				ArrayList<LugaresDeInteres_Item> vacio = new ArrayList<LugaresDeInteres_Item>();
 			    				adaptador = new AdaptadorLugaresDeInteres(Buscar.this, R.layout.lugaresdeinteres_item_list, vacio);
 				    			lista.setAdapter(adaptador);
 				    			
 			    			}else if(total == 1){
 			    				layout_lista.setVisibility(View.VISIBLE);
 			    				Layout_icono_search.setVisibility(View.GONE);
			    				Layout_noencontrado.setVisibility(View.GONE);
			    				

 			    				textoLista.setText("Encontrado "+ total+ " lugar con " +"'"+s.toString()+"'");
 			    			}else{
 			    				textoLista.setText("Encontrados "+ total + " lugares con " +"'"+s.toString()+"'");
 			    			}
 			    			}catch (Exception e) {
 								// TODO: handle exception
 							}
 			    			
 			    		}else{
 			    			layout_lista.setVisibility(View.GONE);
 			    			Layout_icono_search.setVisibility(View.GONE);
		    				Layout_noencontrado.setVisibility(View.VISIBLE);
		    				
 			    			adaptador = new AdaptadorLugaresDeInteres(Buscar.this, R.layout.lugaresdeinteres_item_list, arrayLugaresDeInteres);
 			    			lista.setAdapter(adaptador);
 			    			
 			    			try{
 			    			
 			    			Random r = new Random();
 							int max = arrayLugaresDeInteres.size() - 1;
 							final int randomNum = r.nextInt(max);
 							
 							textoNoEcontrado.setText("Prueba a buscar " + "'"+ arrayLugaresDeInteres.get(randomNum).getTitle()+ "'");
 			    			}catch (Exception e) {
 								// TODO: handle exception
 							}
 			    		}
 					}
 				});
 		    		
 	   		}
 	   		//ayudas, NO solución:
 	   		//stackoverflow.com/questions/14119442/aftertextchanged-event-on-edittext-in-custom-listview-in-android
 	   		//stackoverflow.com/questions/15042860/filtering-a-listview-using-an-arrayadapter-without-overriding-getfilter-method
 			}
 	   	
 			
 			@Override
 			public void beforeTextChanged(CharSequence s, int start, int count,
 					int after) {
 				
 				
 			}
 			
 			@Override
 			public void afterTextChanged(Editable s) {
 				
 			}
 		};
 		//código mejorado de aplicación de ODEC (FCT)
 	
 	
   
	
	private void changeActionBar() {
		//typeface personalizadas
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");
        robotoCondensed = Typeface.createFromAsset(getAssets(), "Roboto-Condensed.ttf");
        robotoRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");

				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.ic_navigation_back);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);


		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(Buscar.this).inflate(R.layout.actionbar_title,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
		        titulo.setTypeface(robotoBoldCondensed);
		        titulo.setText(getString(R.string.mainButton_Buscar));


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}


}
