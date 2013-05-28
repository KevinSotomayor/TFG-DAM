package dam.project.wearevalencia;

import org.holoeverywhere.widget.Toast;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

public class LugaresDeInteres_Ficha_Item_Detallle extends SherlockFragmentActivity{
	private Typeface robotoThin, robotoBoldCondensed, robotoRegular;
	private ActionBar actionBar;
	private final int EMPTY = 0;
	private final String BUNDLE_OBJECT_ARRAYLIST = "objetoTotal";
	private TextView content, volver;
	private LinearLayout volverButtonLayout;
	private LugaresDeInteres_Item objeto; 

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugaresdeinteres_ficha_detalle);
		//obtener el objeto entero de tipo lugaresdeInteres_item
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			objeto = bundle.getParcelable(BUNDLE_OBJECT_ARRAYLIST);
			
			//personalizar actionbar
			actionBar = getSupportActionBar();
			changeActionBar();
			
	 
			if(objeto != null){
				//recogo el contenido del objeto y lo imprimo en pantalla
				content = (TextView)findViewById(R.id.textView_Content_lugaresdeinteres_OnlyText_Detalle);
				content.setTypeface(robotoRegular);
				content.setText(objeto.getContent());
	
				volver = (TextView)findViewById(R.id.textView_Button_lugaresdeinteres_Detalle_Volver);
				volver.setTypeface(robotoBoldCondensed);
				volver.setText(getString(R.string.volver).toUpperCase());
				
				volverButtonLayout = (LinearLayout)findViewById(R.id.button_ficha_detalle_volver);
				volverButtonLayout.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						LugaresDeInteres_Ficha_Item_Detallle.this.finish();
			            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			            //salir y sobreescribir la transicion de salida
	
					}
				});
			}
			
		}else{
			Toast.makeText(LugaresDeInteres_Ficha_Item_Detallle.this, "Lo sentimos, no se ha podido cargar la informacion", Toast.LENGTH_LONG).show();
		}
	
	}
	
	//menu que ayuda a centrar el titulo del actionbar.
	public boolean onCreateOptionsMenu(Menu menu){			
		super.onCreateOptionsMenu(menu);
		//item de menu
		menu.add(0, EMPTY, 0, getString(R.string.vacio)).setIcon(R.drawable.ic_empty)
		.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		return true;
		
	}
	
	
	public boolean onOptionsItemSelected (MenuItem item){
		switch(item.getItemId()){
    	case android.R.id.home:
    		LugaresDeInteres_Ficha_Item_Detallle.this.finish();
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
		        titulo.setText(objeto.getTitle().toUpperCase());
		        //obtener la categoría del unico objeto
		        
		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}	
	

}
