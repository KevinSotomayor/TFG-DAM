package dam.project.wearevalencia.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;

public class Sliding_Menu_Fragment extends ListFragment{
	
	SampleAdapterMenu adapter;
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.sliding_menu_content_list, null);
	}
	

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		adapter = new SampleAdapterMenu(getActivity());
		
		//items de la lista:
		ItemList valencia = new ItemList(getActivity().getString(R.string.smValencia), R.drawable.slidingmenu_valencia);
		ItemList lugaresDeInteres = new ItemList(getActivity().getString(R.string.smLugares), R.drawable.slidingmenu_lugaresdeinteres);
		ItemList fallas = new ItemList(getActivity().getString(R.string.smFallas), R.drawable.slidingmenu_fallas);
		ItemList cc = new ItemList(getActivity().getString(R.string.smCC), R.drawable.slidingmenu_cc);
		ItemList mapa = new ItemList(getActivity().getString(R.string.smMapaVLC), R.drawable.slidingmenu_mapa);

		
		adapter.add(valencia);
		adapter.add(lugaresDeInteres);
		adapter.add(fallas);
		adapter.add(cc);
		adapter.add(mapa);

		setListAdapter(adapter);
		
		
		
	}

	public void onListItemClick(ListView lista, View view, int position, long id){
		Fragment newContent = null;
		switch (position){
		case 0:
			break;
		
		case 1: 
			newContent = new LugaresDeInteres();
			break;
		
		case 2:
			break;
		
		case 4: 
			break;
		
		case 5:
			break;
			
		
		default:
			break;
		}
		
		if(newContent != null){
			switchFragment(newContent);
		}
	}
	
	private void switchFragment(Fragment newContent) {
		// getActivity() // -> Devuelva la actividad que está asociada con este fragmento.
		if(getActivity() == null)
			return;
		
		//comprueba si getActivity es una instancia de Main_FragmentActivity, la clase que va a contener este fragment
		//llama al metodo de swtich para hacer el replace().
		if(getActivity() instanceof Main_FragmentActivity){
			Main_FragmentActivity mFragmentActivity = (Main_FragmentActivity)getActivity();
			mFragmentActivity.switchContent(newContent);
		}
		
	}

	//similar a clase persona
	private class ItemList{
		public String textItem;
		public int photoItem;
		
		public ItemList (String textItem, int photoItem){
			this.textItem = textItem;
			this.photoItem = photoItem;
		}
		
	}
	
	//clase interna que infla la lista con los items del menu, con la foto y el texto
	private class SampleAdapterMenu extends ArrayAdapter<ItemList>{

		public SampleAdapterMenu(Context context) {
			super(context, 0);
			// TODO Auto-generated constructor stub
		}
		
		public View getView(int position, View view, ViewGroup parent){
			//recliclar el layout para no volver a cargar cada vez que el listView sube o baja.
			View mView = view;
			//reciclara tambien los objetos que inflaran la lista, optimizando el deslizamiento.
			ViewHolder holder;
			
			if(mView == null){
			
			mView = LayoutInflater.from(getContext()).inflate(R.layout.sliding_menu_item_list, null);

			holder = new ViewHolder();
			holder.hPhoto  = (ImageView)mView.findViewById(R.id.imageViewItem);
			holder.hTexto = (TextView)mView.findViewById(R.id.textViewItem);
			
			mView.setTag(holder);
			
			}else{
				holder = (ViewHolder)mView.getTag();
			}
			
			holder.hPhoto .setImageResource(getItem(position).photoItem);
			Typeface robotoThin = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-BoldCondensed.ttf");
			holder.hTexto.setTypeface(robotoThin);
			holder.hTexto.setText(getItem(position).textItem);
			
			return(mView);
			
		}
	}
	
	public class ViewHolder {
		TextView hTexto;
		ImageView hPhoto;
	}
	
}
