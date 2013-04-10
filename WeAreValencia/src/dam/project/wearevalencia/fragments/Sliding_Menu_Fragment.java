package dam.project.wearevalencia.fragments;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		ItemList valencia = new ItemList("VALENCIA", R.drawable.valencia);
		ItemList lugaresDeInteres = new ItemList("LUGARES DE INTERÉS", R.drawable.lugaresdeinteres);
		ItemList fallas = new ItemList("FALLAS", R.drawable.fallas);
		ItemList mapa = new ItemList("MAPA DE VALENCIA", R.drawable.mapa);
		ItemList consejo = new ItemList("CONSEJOS", R.drawable.consejo);
		
		adapter.add(valencia);
		adapter.add(lugaresDeInteres);
		adapter.add(fallas);
		adapter.add(mapa);
		adapter.add(consejo);
		
		setListAdapter(adapter);
		
		
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
