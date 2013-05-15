package dam.project.wearevalencia.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import dam.project.wearevalencia.Main_FragmentActivity;
import dam.project.wearevalencia.R;

public class Sliding_Menu_Fragment extends ListFragment{
	private final int INCIO = 0;
	private final int LUGARES_DE_INTERES = 1;
	private final int FIESTAS_POPULARES = 2;
	private final int CENTROS_COMERCIALES = 3;
	private final int MAS_SOBRE_VALENCIA = 4;
	private final int RECOMENDACIONES = 5;
	private final int MAPA = 6;
	
	SampleAdapterMenu adapter;
	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		return inflater.inflate(R.layout.sliding_menu_content_list, null);
	}
	

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		adapter = new SampleAdapterMenu(getActivity());
		
		//items de la lista:
		ItemList inicio = new ItemList(getActivity().getString(R.string.vacio), R.drawable.slidingmenu_logo_inicio);
		ItemList lugaresDeInteres = new ItemList(getActivity().getString(R.string.smLugares), R.drawable.slidingmenu_lugaresdeinteres);
		ItemList fiestasPopulares = new ItemList(getActivity().getString(R.string.smFiestas), R.drawable.slidingmenu_fiesta);
		ItemList cc = new ItemList(getActivity().getString(R.string.smCC), R.drawable.slidingmenu_cc);
		ItemList masSobreValencia = new ItemList(getActivity().getString(R.string.smMasSobreValencia), R.drawable.slidingmenu_valencia);
		ItemList recomendaciones = new ItemList(getString(R.string.smRecomendaciones), R.drawable.slidingmenu_recomendaciones);
		ItemList mapa = new ItemList(getActivity().getString(R.string.smMapaVLC), R.drawable.slidingmenu_mapa);
		
		adapter.add(inicio);
		adapter.add(lugaresDeInteres);
		adapter.add(fiestasPopulares);
		adapter.add(cc);
		adapter.add(masSobreValencia);
		adapter.add(recomendaciones);
		adapter.add(mapa);

		setListAdapter(adapter);
		
		
		
	}

	public void onListItemClick(ListView lista, View view, int position, long id){
		Fragment newContent = null;
		switch (position){
		case INCIO: //lugares de interes
			newContent = new Main_Content_Fragment();
			break;
		
		case LUGARES_DE_INTERES: //fiestas populares
			newContent = new LugaresDeInteres();
			break;
		
		case FIESTAS_POPULARES: //centros comerciales
			break;
			
		case CENTROS_COMERCIALES: //mas sobre valencia
			break;
			
		case MAS_SOBRE_VALENCIA: //recomendaciones
			break;
		
		case RECOMENDACIONES: //mapa
			break;
			
		case MAPA:
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
			Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "Roboto-Condensed.ttf");
			holder.hTexto.setTypeface(roboto);
			holder.hTexto.setText(getItem(position).textItem);
			
			return(mView);
			
		}
	}
	
	public class ViewHolder {
		TextView hTexto;
		ImageView hPhoto;
	}
	
}
