package dam.project.wearevalencia;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.google.android.gms.maps.GoogleMap;

import dam.project.wearevalencia.maps.Map_Item;

public class LugaresDeInteres_Ficha_Item extends SherlockFragmentActivity{
	private Typeface robotoThin, robotoBoldCondensed, robotoCondensed, robotoRegular;
	private ActionBar actionBar;
	private final int CAMBIAR_MONEDA = 1;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugaresdeinteres_ficha_item);
		actionBar = getSupportActionBar();
		changeActionBar();
	}
	//Menu para ver el mapa con diferentes vistas
		public boolean onCreateOptionsMenu(Menu menu){			
			super.onCreateOptionsMenu(menu);
			
			menu.add(0, CAMBIAR_MONEDA, 0, getString(R.string.cambioDivisas)).setIcon(R.drawable.ic_cambiar_divisas)
			.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
			
			return true;
			
			}
		
		public boolean onOptionsItemSelected (MenuItem item){
	    	
			switch(item.getItemId()){
	    	
	    	case android.R.id.home:
	    		LugaresDeInteres_Ficha_Item.this.finish();
				//sobreescribir la animacion para finalizar esta activity
	            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

	    		return true;

			}
			return super.onOptionsItemSelected(item);

		}
		
	
	private void changeActionBar() {
		//typeface personalizadas
		robotoCondensed = Typeface.createFromAsset(getAssets(), "Roboto-Condensed.ttf");
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
		        titulo.setText("LONJA DE LA SEDA".toUpperCase());
		        //no hay texto en este actionbar ya que se sobrepone al contenido al scrollear
		        
		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}	

}
