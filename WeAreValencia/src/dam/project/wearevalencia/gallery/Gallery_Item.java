package dam.project.wearevalencia.gallery;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ViewSwitcher.ViewFactory;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;

import dam.project.wearevalencia.R;
import dam.project.wearevalencia.objects.LugaresDeInteres_Item;

public class Gallery_Item extends SherlockFragmentActivity implements ViewFactory {
	private final String BUNDLE_OBJECT_ARRAYLIST = "objetoTotal";
	private final int GUARDAR_IMAGEN = 1;
	private final int ESTABLECER_COMO = 2;
	private ActionBar actionBar;
	private Typeface robotoThin, robotoBoldCondensed;
	//Integer pics[] = {R.drawable.serranos_1, R.drawable.main_bg_10, R.drawable.main_bg_11, R.drawable.main_bg_12, R.drawable.main_bg_14};
	Integer[] pics;
	int middle;
	ImageSwitcher iSwitcher;
	String tituloLugarDeInteres;
	private LugaresDeInteres_Item objeto; 



	/** Called when the activity is first created. */
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.imageswitcher_gallery);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			objeto = bundle.getParcelable(BUNDLE_OBJECT_ARRAYLIST);
			tituloLugarDeInteres = objeto.getTitle();
		}
		
		actionBar = getSupportActionBar();
		changeActionBar();

		/*Drawables leidos desde la sd card, proxima implementación
		Bitmap unoBitmap = BitmapFactory.decodeFile("../sdcard/wearevalencia/torres_de_serranos/torres_serranos_1.png");
		Drawable unoDrawable = new BitmapDrawable(getResources(), unoBitmap);*/

		pics = new Integer[]{R.drawable.main_bg_1, R.drawable.main_bg_10, R.drawable.main_bg_11, R.drawable.main_bg_12, R.drawable.main_bg_14};
		middle = pics.length / 2;
		
		iSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcherSerranos);
		iSwitcher.setFactory(this);
		iSwitcher.setInAnimation(AnimationUtils.loadAnimation(this,	android.R.anim.fade_in));
		iSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));

		Gallery gallery = (Gallery) findViewById(R.id.gallerySerranos);
		gallery.setAdapter(new ImageAdapter(this));
		gallery.setSelection(middle);
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				iSwitcher.setImageResource(pics[position]);
			}
		});
	}



	public class ImageAdapter extends BaseAdapter {

		private Context ctx;
		private int itemBackground;


		public ImageAdapter(Context c) {
			ctx = c; 
			TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
			itemBackground = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
		}

		@Override
		public int getCount() {

			return pics.length;
		}

		@Override
		public Object getItem(int arg0) {

			return arg0;
		}

		@Override
		public long getItemId(int arg0) {

			return arg0;
		}

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {

			ImageView iView = new ImageView(ctx);
			iView.setImageResource(pics[position]);
			iView.setScaleType(ImageView.ScaleType.FIT_XY);
			iView.setLayoutParams(new Gallery.LayoutParams(150, 150));
			iView.setBackgroundResource(itemBackground);
			return iView;
		}


	}

	@Override
	public View makeView() {
		ImageView iView = new ImageView(this);
		iView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		iView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
		iView.setImageResource(pics[middle]);
		return iView;
	}
	
	//Menu para ver el mapa con diferentes vistas
	public boolean onCreateOptionsMenu(Menu menu){			
		super.onCreateOptionsMenu(menu);
		SubMenu submenu = menu.addSubMenu(getString(R.string.optionsMaps));
		submenu.add(0, GUARDAR_IMAGEN, 0, getString(R.string.guardarImagen));
		submenu.add(0, ESTABLECER_COMO, 1, getString(R.string.establecerFondo));
		submenu.getItem().setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_dark)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		
		return true;
		
		}

	//cuando el usuario pulsa el boton de volver del actionbar
	public boolean onOptionsItemSelected (MenuItem item){
    	switch(item.getItemId()){
    	case android.R.id.home:
    		Gallery_Item.this.finish();
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
		        actionBar.setIcon(R.drawable.ic_navigation_back);

		        //cambiar el titulo por otro con subtitulo + layout
		        actionBar.setDisplayShowTitleEnabled(false);//ocultar titulo normal
		        //permitir el customizado
		        actionBar.setDisplayShowCustomEnabled(true);


		        //inflar un view con el layout de los titulos
		        View customView = LayoutInflater.from(this).inflate(R.layout.actionbar_title,null);

		        //identificar las etiquetas y setTypeface otra letra
		        TextView titulo = (TextView)customView.findViewById(R.id.tituloWeAreValencia);
		        titulo.setText(tituloLugarDeInteres.toUpperCase());
		        titulo.setTypeface(robotoBoldCondensed);

		        /// center xml in actionbar
		        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
		        lp.gravity = Gravity.CENTER;
		        customView.setLayoutParams(lp);
		        /* http://stackoverflow.com/questions/11327210/setting-a-custom-text-in-the-center-actionbarsherlock */

		        //set inflate view to actionBarSherlock
		        actionBar.setCustomView(customView);

	}	

}