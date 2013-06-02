package dam.project.wearevalencia;

import android.graphics.Typeface;
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
import dam.project.wearevalencia.objects.FiestasPopulares_Item;

public class FiestasPopulares_Ficha_Item extends SherlockFragmentActivity{
	private Typeface robotoThin, robotoBoldCondensed, robotoRegular;
	private ActionBar actionBar;
	private final int EMPTY = 0;
	private final String BUNDLE_FROM_FRAGMENT_FP ="bundleFromFragmentFP";
	private ImageView thumbail;
	private TextView title, content, calendarTitle, calendar;
	private FiestasPopulares_Item objeto; 

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fiestaspopulares_ficha_item);
		//obtener el objeto entero de tipo lugaresdeInteres_item
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			objeto = bundle.getParcelable(BUNDLE_FROM_FRAGMENT_FP);
		}

		actionBar = getSupportActionBar();
		changeActionBar();
		
		if(objeto != null){
			title = (TextView)findViewById(R.id.textView_Ficha_FP_titulo);
			title.setTypeface(robotoBoldCondensed);
			title.setText(objeto.getTitle());
			
			content = (TextView)findViewById(R.id.textView_Content_FP_OnlyText);
			content.setTypeface(robotoRegular);
			content.setText(objeto.getContent());
			
			calendarTitle = (TextView)findViewById(R.id.textView_ficha_FP_calendar);
			calendarTitle.setTypeface(robotoBoldCondensed);
			calendarTitle.setText(getString(R.string.calendario).toUpperCase());
			
			calendar = (TextView)findViewById(R.id.textView_ficha_FP_calendar_content);
			calendar.setTypeface(robotoRegular);
			calendar.setText(objeto.getCalendar());
			
			thumbail = (ImageView)findViewById(R.id.imageView_Ficha_FP);
			thumbail.setImageResource(objeto.getThumbail());
			
			TextView textoButton = (TextView)findViewById(R.id.textView_ficha_button_Layout_Text);
			textoButton.setTypeface(robotoBoldCondensed);
			textoButton.setText(getString(R.string.volver).toUpperCase());
			
			//boton volver
			LinearLayout volver = (LinearLayout)findViewById(R.id.button_fiestaspopulares_ficha_volver);
			volver.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					FiestasPopulares_Ficha_Item.this.finish();
		            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		            //salir y sobreescribir la transicion de salida

				}
			});
		}
			
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
    		FiestasPopulares_Ficha_Item.this.finish();
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
		        titulo.setText(getString(R.string.fiestas).toUpperCase());
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
