package dam.project.wearevalencia.objects;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import dam.project.wearevalencia.R;

public class CentrosComerciales_Data_Objects {
	private static ArrayList<CentrosComerciales_Item> arrayList;
	private static LatLng latLng;
	private static final int CCAQUA = 1;
	private static final int CCADEMUZ = 2;
	private static final int CCARENA = 3;
	private static final int CCSALER = 4;
	private static final int CCGRAN_TURIA= 5;
	private static final int CCNUEVO_CENTRO = 6;
	
	public static ArrayList<CentrosComerciales_Item> obtainCentrosComerciales(Context contexto){
		arrayList = new ArrayList<CentrosComerciales_Item>();
	
		latLng = new LatLng(39.456675990112494, -0.3456900500000226);
		CentrosComerciales_Item aqua = new CentrosComerciales_Item(
				CCAQUA,
				contexto.getString(R.string.aqua), 
				contexto.getString(R.string.aquaAddress), 
				contexto.getString(R.string.aquaTelephone), 
				contexto.getString(R.string.aquaWeb), 
				latLng,
				R.drawable.centroscomerciales_aqua);

		
		latLng = new LatLng(39.485064263083764, -0.3959247000000232);
		CentrosComerciales_Item ademuz = new CentrosComerciales_Item(
				CCADEMUZ,
				contexto.getString(R.string.ademuz), 
				contexto.getString(R.string.ademuzAddress), 
				contexto.getString(R.string.ademuzTelephone), 
				contexto.getString(R.string.ademuzWeb), 
				latLng,
				R.drawable.centroscomerciales_ademuz);

		
		latLng = new LatLng(39.49438610662652, -0.3607265899353185);
		CentrosComerciales_Item arena = new CentrosComerciales_Item(
				CCARENA,
				contexto.getString(R.string.arena), 
				contexto.getString(R.string.arenaAddress), 
				contexto.getString(R.string.arenaTelephone), 
				contexto.getString(R.string.arenaWeb), 
				latLng,
				R.drawable.centroscomerciales_arena);

		
		latLng = new LatLng(39.45381142596046, -0.3536204325408776);
		CentrosComerciales_Item saler = new CentrosComerciales_Item(
				CCSALER,
				contexto.getString(R.string.saler), 
				contexto.getString(R.string.salerAddress), 
				contexto.getString(R.string.salerTelephone), 
				contexto.getString(R.string.salerWeb), 
				latLng,
				R.drawable.centroscomerciales_saler);

		
		latLng = new LatLng(39.46574071307477, -0.4265221000000565);
		CentrosComerciales_Item granTuria = new CentrosComerciales_Item(
				CCGRAN_TURIA,
				contexto.getString(R.string.granTuria), 
				contexto.getString(R.string.granTuriaAddress), 
				contexto.getString(R.string.granTuriaTelephone), 
				contexto.getString(R.string.granTuriaWeb), 
				latLng,
				R.drawable.centroscomerciales_turia);

		
		latLng = new LatLng(39.48005703207343, -0.3910780907378997);
		CentrosComerciales_Item nuevoCentro = new CentrosComerciales_Item(
				CCNUEVO_CENTRO,
				contexto.getString(R.string.nuevoCentro), 
				contexto.getString(R.string.nuevoCentroAddress), 
				contexto.getString(R.string.nuevoCentroTelephone), 
				contexto.getString(R.string.nuevoCentroWeb), 
				latLng,
				R.drawable.centroscomerciales_nuevocentro);

		arrayList.add(aqua);
		arrayList.add(ademuz);
		arrayList.add(arena);
		arrayList.add(saler);
		arrayList.add(granTuria);
		arrayList.add(nuevoCentro);
		
		return arrayList;
		
	}

}
