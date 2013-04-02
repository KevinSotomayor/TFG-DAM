package dam.project.wearevalencia;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.slidingmenu.lib.SlidingMenu;
import com.slidingmenu.lib.app.SlidingActivityBase;

import dam.project.wearevalencia.fragments.Main_Content_Fragment;
import dam.project.wearevalencia.fragments.Sliding_Menu_Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class Main_FragmentActivity extends SherlockFragmentActivity implements SlidingActivityBase{
	
	//fragment que identifica si contiene una vista
	//para sustituirla, de lo contrario, mostrará la vista que se le indica debajo
	private Fragment mContent;
	SlidingMenu slidingMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//mostrar la primera vista de la aplicacion
		//muestra el estado del bundle en la primera vista o sino el fragmento reemplazado.
		if(savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if(mContent == null){
			mContent = new Main_Content_Fragment();
		
		//aqui es donde verdaderamente reemplazamos la vista
		setContentView(R.layout.main_fragment_activity);
		getSupportFragmentManager().beginTransaction()
		.replace(R.id.content_fragment, mContent).commit();
		
		//vista del menu
		setSlidingActionBarEnabled(true);
		slidingMenuAction();

		}
	}
	
	
	//muy util si queremos recuperar la vista que teniamos 
	//si es que por ejemplo giramos la pantalla...
	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
	
	

	private void slidingMenuAction() {
		//propiedades del menu deslizable
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffset(93);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setFadeDegree(0.70f);
		slidingMenu.attachToActivity(Main_FragmentActivity.this, SlidingMenu.SLIDING_WINDOW);
		slidingMenu.setMenu(R.layout.sliding_menu_fragment_list);
		
		//fragment que rellena el xml
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.sliding_menu_content, new Sliding_Menu_Fragment())
		.commit();
	}

	@Override
	public void onBackPressed(){
		
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
			//alert que pregunta antes de salir
		}
	}







	@Override
	public void setBehindContentView(View view, LayoutParams layoutParams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBehindContentView(View view) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBehindContentView(int layoutResID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SlidingMenu getSlidingMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void toggle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showContent() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showSecondaryMenu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSlidingActionBarEnabled(boolean slidingActionBarEnabled) {
		// TODO Auto-generated method stub
		
	}

	
}
