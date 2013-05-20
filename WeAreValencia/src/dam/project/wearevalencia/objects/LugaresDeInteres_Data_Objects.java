package dam.project.wearevalencia.objects;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.maps.model.LatLng;
import dam.project.wearevalencia.R;

public class LugaresDeInteres_Data_Objects {
	private static ArrayList<LugaresDeInteres_Item> arrayList;
	private static LatLng latlng = null;
	
	public static ArrayList<LugaresDeInteres_Item> obtainMonuments(Context contexto){
		arrayList = new ArrayList<LugaresDeInteres_Item>();
			latlng = new LatLng(39.47434857729124, -0.3785929481113093);
			LugaresDeInteres_Item lonjaDeLaSeda =  new LugaresDeInteres_Item(contexto.getString(R.string.lonjaDeLaSeda), 
					contexto.getString(R.string.LonjaDeLaSedaContent), latlng, R.drawable.lugaresdeinteres_content_lonja);
			
			LugaresDeInteres_Item torresDeSerranos = new LugaresDeInteres_Item("Torres de Serranos", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_torresdeserranos);
			
			LugaresDeInteres_Item torresDeQuart = new LugaresDeInteres_Item("Torres de quart", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_torresdequart);
			
			LugaresDeInteres_Item mercadoCentral = new LugaresDeInteres_Item("Mercado Central", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_mercadocentral);
			
			LugaresDeInteres_Item mercadoColon = new LugaresDeInteres_Item("Mercado Colón", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_mercadocolon);
			
			LugaresDeInteres_Item CAC = new LugaresDeInteres_Item("Ciudad de las Artes y las Ciencias", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_cac);
			
			LugaresDeInteres_Item plazaAyuntamiento = new LugaresDeInteres_Item("Plaza del Ayuntamiento", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_plazaayuntamiento);
			
			LugaresDeInteres_Item estacionNorte = new LugaresDeInteres_Item("Estación del Norte", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_estacionnorte);
			
			LugaresDeInteres_Item plazaToros = new LugaresDeInteres_Item("Plaza de Toros", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_plazatoros);
			
			LugaresDeInteres_Item plazaVirgen = new LugaresDeInteres_Item("Plaza de la Virgen", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_plazavrigen);
			
			LugaresDeInteres_Item elMicalet = new LugaresDeInteres_Item("El Micalet", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_elmicalet);
			
			LugaresDeInteres_Item palauMusica = new LugaresDeInteres_Item("Palau de la Música", 
					"La lonja de la seda es declarada Patrimonio de la humanidad", latlng, R.drawable.lugaresdeinteres_content_palaumusica);
			
			arrayList.add(lonjaDeLaSeda);
			arrayList.add(torresDeSerranos);
			arrayList.add(torresDeQuart);
			arrayList.add(mercadoCentral);
			arrayList.add(mercadoColon);
			arrayList.add(plazaAyuntamiento);
			arrayList.add(estacionNorte);
			arrayList.add(plazaToros);
			arrayList.add(plazaVirgen);
			arrayList.add(elMicalet);
			arrayList.add(CAC);
			arrayList.add(palauMusica);

		return arrayList;
		
	}
	

}
