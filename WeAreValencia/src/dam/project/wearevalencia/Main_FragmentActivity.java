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
	private Fragment mContent;
	SlidingMenu slidingMenu;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_content_activity);
		
		//set the above view
		if(savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "content");
		if(mContent == null){
			mContent = new Main_Content_Fragment();
					
		setContentView(R.layout.main_fragment_activity);
		
		
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_fragment, mContent).commit();
		}
		
		//vista del menu
		setSlidingActionBarEnabled(true);
		SlidingMenuAction();
	}
	
	
	
	
	private void SlidingMenuAction() {
		//configurar slidingMenu
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setShadowDrawable(R.drawable.shadow);
		slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		slidingMenu.setBehindOffset(89);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.setFadeDegree(0.70f);
		slidingMenu.attachToActivity(Main_FragmentActivity.this, SlidingMenu.SLIDING_WINDOW);
		slidingMenu.setMenu(R.layout.sliding_menu_fragment_list);
		
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.sliding_menu_fragment, new Sliding_Menu_Fragment())
		.commit();
	
		
	}
	
	@Override
	public void onBackPressed(){
		
		if (slidingMenu.isMenuShowing()) {
			slidingMenu.showContent();
		} else {
			super.onBackPressed();
		}
	}





















	/******* metodos implementados ****/
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
