package dam.project.wearevalencia.objects;

import java.util.ArrayList;
import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import dam.project.wearevalencia.R;

public class Eventos_Data_Objects {
	private static ArrayList<Eventos_Item> arrayList;
	private static LatLng latLng;

	public static ArrayList<Eventos_Item> obtainEvents(Context contexto){
		arrayList = new ArrayList<Eventos_Item>();
	
		latLng = new LatLng(39.45806142505684, -0.3559175473212495);
		Eventos_Item festivalMediterrani = new Eventos_Item(
				contexto.getString(R.string.festivalDelMediterrani), 
				contexto.getString(R.string.festivalDelMediterraniContent), 
				contexto.getString(R.string.festivalDelMediterraniHorary), 
				contexto.getString(R.string.festivalDelMediterraniAddress), 
				latLng);

		latLng = new LatLng(39.47361150314051, -0.3759308556640284);
		Eventos_Item vlcCuinaOberta = new Eventos_Item(
				contexto.getString(R.string.valenciaCuinaOberta), 
				contexto.getString(R.string.valenciaCuinaObertaContent), 
				contexto.getString(R.string.valenciaCuinaObertaHorary), 
				contexto.getString(R.string.valenciaCuinaObertaAddress), 
				latLng);
		
		latLng = new LatLng(39.467317068775415, -0.32260854044796394);
		Eventos_Item sanJuan = new Eventos_Item(
				contexto.getString(R.string.sanJuan), 
				contexto.getString(R.string.sanJuanContent), 
				contexto.getString(R.string.sanJuanCalendar), 
				contexto.getString(R.string.sanJuanAddress), 
				latLng);
		
		latLng = new LatLng(39.471938564305404, -0.37692863741756844);
		Eventos_Item corpus = new Eventos_Item(
				contexto.getString(R.string.corpus), 
				contexto.getString(R.string.corpusContent), 
				contexto.getString(R.string.corpusCalendar), 
				contexto.getString(R.string.corpusAddress), 
				latLng);
		
		latLng = new LatLng(39.458615732210895, -0.33798430362583565);
		Eventos_Item fromVlcWithDesign = new Eventos_Item(
				contexto.getString(R.string.fromVLC), 
				contexto.getString(R.string.fromVLCContent), 
				contexto.getString(R.string.fromVLCHorary), 
				contexto.getString(R.string.fromVLCAddress), 
				latLng);
		
		latLng = new LatLng(39.45670220339141, -0.353893826397671);
		Eventos_Item hemisferic5aniversario = new Eventos_Item(
				contexto.getString(R.string.hemisfericCincoAniversario), 
				contexto.getString(R.string.hemisfericCincoAniversarioContent), 
				contexto.getString(R.string.hemisfericCincoAniversarioHorary), 
				contexto.getString(R.string.hemisfericCincoAniversarioAddress), 
				latLng);
		
		latLng = new LatLng(39.4622816433038, -0.3145327072631998);
		Eventos_Item conciertoAS = new Eventos_Item(
				contexto.getString(R.string.alejandroSanz), 
				contexto.getString(R.string.alejandroSanzContent), 
				contexto.getString(R.string.alejandroSanzHorary), 
				contexto.getString(R.string.alejandroSanzAddress), 
				latLng);
		
		arrayList.add(festivalMediterrani);
		arrayList.add(vlcCuinaOberta);
		arrayList.add(sanJuan);
		arrayList.add(corpus);
		arrayList.add(fromVlcWithDesign);
		arrayList.add(hemisferic5aniversario);
		arrayList.add(conciertoAS);
		
		return arrayList;
		
	}
}
