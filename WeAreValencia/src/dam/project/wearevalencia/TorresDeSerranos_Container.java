package dam.project.wearevalencia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


import dam.project.wearevalencia.R;
import dam.project.wearevalencia.gallery.TorresDeSerranosGallery;

public class TorresDeSerranos_Container extends SherlockFragmentActivity {
	private Typeface robotoThin, robotoBoldCondensed;
	private ActionBar actionBar;
	
	public void onCreate(Bundle savedInstanceState) {
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.torresdeserrano_container);
		
		//action bar + personalizaciones
		actionBar = getSupportActionBar();
        changeActionBar();
        Button galeria = (Button)findViewById(R.id.torresdeserranos_galleryButton);
        galeria.setTypeface(robotoThin);
        galeria.setOnClickListener(new OnClickListener() {
			
        	@Override
			public void onClick(View v) {
				Intent galeria = new Intent(TorresDeSerranos_Container.this, TorresDeSerranosGallery.class);
				startActivity(galeria);
				//sobreescribir la animacion para dar entrada a la nueva pantalla
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
	
	}
	
	public boolean onOptionsItemSelected (MenuItem item){
    	switch(item.getItemId()){
    	case android.R.id.home:
    		TorresDeSerranos_Container.this.finish();
			//sobreescribir la animacion para finalizar esta activity
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    		return true;
    	}
		return super.onOptionsItemSelected(item);

    }

	private void changeActionBar() {
		//typeface personalizadas
        robotoThin = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
        robotoBoldCondensed = Typeface.createFromAsset(getAssets(), "Roboto-BoldCondensed.ttf");

				//boton de volver atras del boton home, e icono personalizado
				actionBar.setDisplayHomeAsUpEnabled(false);
        		actionBar.setHomeButtonEnabled(true);
		        actionBar.setIcon(R.drawable.navigation_back);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);


		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_title_other_activity,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloOtherActivity);
		        titulo.setText(getString(R.string.torresDe));
		        titulo.setTypeface(robotoThin);

		        TextView otroTitulo =(TextView)customView.findViewById(R.id.titulo2OtherActivity);
		        otroTitulo.setText(getString(R.string.Serranos));
		        otroTitulo.setTypeface(robotoBoldCondensed);


		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}	
	

}
