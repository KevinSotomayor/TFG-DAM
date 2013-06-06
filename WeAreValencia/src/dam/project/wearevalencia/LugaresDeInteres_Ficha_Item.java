package dam.project.wearevalencia;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.holoeverywhere.widget.Toast;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import dam.project.wearevalencia.gallery.Gallery_Item;
import dam.project.wearevalencia.maps.Map_Item;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

@SuppressLint("SimpleDateFormat")
public class LugaresDeInteres_Ficha_Item extends SherlockFragmentActivity{
	private Typeface robotoThin, robotoBoldCondensed, robotoRegular;
	private ActionBar actionBar;
	private final int EMPTY = 0;
	private final String BUNDLE_OBJECT_ARRAYLIST = "objetoTotal";
	private String latlongString, urlImage;
	private ImageView thumbailMax, map_static;
	private TextView title, content, leermas, address, addressContent, price, priceContent, horary, horaryContent, telephone, telephoneContent,
	textViewHeVisitado, textViewIrAlMapa, textViewIrGaleria, textViewClock, textViewDate;
	private LinearLayout heVisitado, irAlMapa, irGaleria, direccion, leermasLayoutButton, telephoneLayoutButton;
	private LugaresDeInteres_Item objeto; 
	private Bitmap bitmap;

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugaresdeinteres_ficha_item);
		//obtener el objeto entero de tipo lugaresdeInteres_item
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			objeto = bundle.getParcelable(BUNDLE_OBJECT_ARRAYLIST);
		}

		actionBar = getSupportActionBar();
		changeActionBar();
		
		if(objeto != null){
			//cambiar typeface a los "botones"
			textViewHeVisitado = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_heVisitado);
			textViewHeVisitado.setTypeface(robotoRegular);
			textViewIrAlMapa = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_irAlMapa);
			textViewIrAlMapa.setTypeface(robotoRegular);
			textViewIrGaleria = (TextView)findViewById(R.id.textView_irGaleria);
			textViewIrGaleria.setTypeface(robotoRegular);
			
			
			thumbailMax = (ImageView)findViewById(R.id.imageView_Ficha_LugaresDeInteres);
			thumbailMax.setImageResource(objeto.getThumbailMax());
			
			latlongString = getLatLng(objeto.getLatLng().toString()); //obtener la longitud y latitud separada de los ()
			urlImage = "http://maps.google.com/maps/api/staticmap?center="+latlongString+"&zoom=17&markers=color:red|40.365827,-3.758285|"+latlongString+"&size=780x300&sensor=false";
			//url de la imagen generada automaticamente
			//sacada de: http://gmaps-samples.googlecode.com/svn/trunk/simplewizard/makestaticmap.html y el api de maps v2
			map_static = (ImageView)findViewById(R.id.map_static_ficha_lugaresDeInteres);
			new LoadImageAsyncTask().execute();
			//bajar la imagen en un asynctask, en otro hilo que no sea el principal, e aquí el error que me daba:
			//android.os.NetworkOnMainThreadException
			//solucion: http://stackoverflow.com/questions/7408046/android-os-networkonmainthreadexception-in-asynctask
		    
			
			title = (TextView)findViewById(R.id.textView_Ficha_LugaresDeInteres_titulo);
			title.setTypeface(robotoBoldCondensed);
			title.setText(objeto.getTitle());
			
			content = (TextView)findViewById(R.id.textView_Content_lugaresdeinteres_OnlyText);
			content.setTypeface(robotoRegular);
			String cadena = objeto.getContent();
			String cadenaTotal = "";	
			for (int i = 0; i < cadena.length(); i++ ){
				
				if (i <= 280) {
					cadenaTotal += cadena.charAt(i);
	
				}
	
			}
			cadenaTotal += "...";
			content.setText(cadenaTotal); 
			
			leermas = (TextView)findViewById(R.id.textView_Button_lugaresdeinteres_LeerMas);
			leermas.setTypeface(robotoBoldCondensed);
			leermas.setText(getString(R.string.leerMas).toUpperCase());
			leermasLayoutButton = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_content);
			leermasLayoutButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(LugaresDeInteres_Ficha_Item.this, LugaresDeInteres_Ficha_Item_Detallle.class);					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, objeto);
					i.putExtras(bundle);
					
					startActivity(i);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				}
			});
		
			address = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_address);
			address.setTypeface(robotoBoldCondensed);
			address.setText(getString(R.string.addresFichaLugaresDeInteres).toUpperCase());
			addressContent = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_address_content);
			addressContent.setTypeface(robotoRegular);
			addressContent.setText(objeto.getAddres());
			
			price = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_price);
			price.setTypeface(robotoBoldCondensed);
			price.setText(getString(R.string.priceFichaLugaresDeInteres).toUpperCase());
			priceContent = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_price_content);
			priceContent.setTypeface(robotoRegular);
			priceContent.setText(objeto.getPrice());
			
			horary = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_horary);
			horary.setTypeface(robotoBoldCondensed);
			horary.setText(getString(R.string.horaryFichaLugaresDeInteres).toUpperCase());
			horaryContent = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_horary_content);
			horaryContent.setTypeface(robotoRegular);
			horaryContent.setText(objeto.getHorary());
			
			telephone = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_telephone);
			telephone.setTypeface(robotoBoldCondensed);
			telephone.setText(getString(R.string.telephoneFichaLugaresDeInteres).toUpperCase());
			telephoneContent = (TextView)findViewById(R.id.textView_ficha_lugaresdeinteres_telephone_content);
			telephoneContent.setTypeface(robotoRegular);
			telephoneContent.setText(objeto.getTelephone());
			
			//establecer la hora en ese momento del sistema:
			//http://www.mkyong.com/android/android-time-picker-example/
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);
			textViewClock = (TextView)findViewById(R.id.textView_ficha_lugaresDeInteres_Clock);
			textViewClock.setTypeface(robotoThin);
			textViewClock.setText(
                    new StringBuilder().append(pad(hour))
                    .append(":").append(pad(minute)));

			//obtener la fecha
			//ayuda, api de android + stackoverflow
			Date now = new Date();
			SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE");
			textViewDate = (TextView)findViewById(R.id.textView_ficha_lugaresDeInteres_Date);
			textViewDate.setTypeface(robotoThin);
			textViewDate.setText(dateFormatter.format(now));
			
			
			heVisitado = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_heVisitado);
			heVisitado.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(LugaresDeInteres_Ficha_Item.this, "Añadido a sitios visitados", Toast.LENGTH_LONG).show();
				}
			});
			
			irAlMapa = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_irAlMapa);
			irAlMapa.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Intent i = new Intent(LugaresDeInteres_Ficha_Item.this, Map_Item.class);					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, objeto);
					i.putExtras(bundle);
					
					startActivity(i);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	
				}
			});
			
			irGaleria = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_irGaleria);
			irGaleria.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Intent i = new Intent(LugaresDeInteres_Ficha_Item.this, Gallery_Item.class);					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, objeto);
					i.putExtras(bundle);
					
					startActivity(i);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				}
			});
			
			direccion = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_address);
			direccion.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent i = new Intent(LugaresDeInteres_Ficha_Item.this, Map_Item.class);					
					Bundle bundle = new Bundle();
					bundle.putParcelable(BUNDLE_OBJECT_ARRAYLIST, objeto);
					i.putExtras(bundle);
					
					startActivity(i);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					
				}
			});
			
			telephoneLayoutButton = (LinearLayout)findViewById(R.id.button_ficha_lugaresdeinteres_telephone);
			telephoneLayoutButton.setOnClickListener(new OnClickListener() {
								
				@Override
				public void onClick(View v) {
					
					Intent dial = new Intent(Intent.ACTION_DIAL);
					dial.setData(Uri.parse("tel:"+objeto.getTelephone()));
					startActivity(dial);
					
				}
			});
		}else{
			Toast.makeText(LugaresDeInteres_Ficha_Item.this, "Lo sentimos, no se han podido cargar los datos", Toast.LENGTH_LONG).show();
		}
	}
	
	//extraigo la latitud y la longitud que se pasa a tostring y queda algo como  lat/lng(3.156116, 0.462623) y con este metodo solo queda 3.156116, 0.462623
	private String getLatLng(String cadena){
		String cadenaTotal="";
		int start = 10;
		int end = cadena.length() -1;
		cadenaTotal = cadena.substring(start, end);
		return cadenaTotal;
	}
	
	private static String pad(int c) {
		if (c >= 10)
		   return String.valueOf(c);
		else
		   return "0" + String.valueOf(c);
	}
	
	//Menu que tiene un item vacio para ayudar a centrar el texto del actionbar
	public boolean onCreateOptionsMenu(Menu menu){			
		super.onCreateOptionsMenu(menu);
		menu.add(0, EMPTY, 0, getString(R.string.vacio)).setIcon(R.drawable.ic_empty)
		.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		return true;
		
	}
	
	
	public boolean onOptionsItemSelected (MenuItem item){
    	
		switch(item.getItemId()){
    	
    	case android.R.id.home:
    		//ahorro trabajo al garbage collector
    		if (bitmap != null) {
    			bitmap.recycle(); //Free the native object associated with this bitmap, and clear the reference to the pixel data.
    			bitmap = null;
    		}
    		LugaresDeInteres_Ficha_Item.this.finish();
			//sobreescribir la animacion para finalizar esta activity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    		return true;

		}
		return super.onOptionsItemSelected(item);

	}
		
	
	private void changeActionBar() {
		//typeface personalizadas
		robotoRegular = Typeface.createFromAsset(getAssets(), "Roboto-Regular.ttf");
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");
        robotoThin  = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");

				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.ic_navigation_back_black);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);

		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_title_ficha_lugaresdeinteres,null);
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloFichaActionBar);
		        titulo.setTypeface(robotoThin);
		        titulo.setText(objeto.getCategory().toUpperCase());
		        //obtener la categoría del unico objeto
		        
		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}	
	
	private class LoadImageAsyncTask extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected Void doInBackground(Void... params) {
			
			try {
				  bitmap = BitmapFactory.decodeStream((InputStream)new URL(urlImage).getContent());
				} catch (MalformedURLException e) {
				  e.printStackTrace();
				} catch (IOException e) {
				  e.printStackTrace();
				}
			
			return null;
		}
		
		protected void onPostExecute(Void result){
			super.onPostExecute(result);
			if (bitmap != null) 
				map_static.setImageBitmap(bitmap); 
			else 
				Toast.makeText(LugaresDeInteres_Ficha_Item.this, "No se ha podido descargar la ubicación, verifica tu conexión a internet", Toast.LENGTH_LONG).show();
		}//asi el fondo predeterminado no se cambia y avisa al usuario que hace falta internet para poder cambiarla.
		
	}
	

}
