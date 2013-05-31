package dam.project.wearevalencia.objects;

import java.util.ArrayList;
import dam.project.wearevalencia.R;
import android.content.Context;

public class FiestasPopulares_Data_Objects {
	private static ArrayList<FiestasPopulares_Item> arrayList;
	private static final int FALLAS = 1;
	private static final int SAN_VICENTE_FERRER = 2;
	private static final int CORPUS = 3;
	private static final int SEMANA_SANTA = 4;
	private static final int VIRGEN_DESAMPARADOS= 5;
	private static final int NUEVE_OCTUBRE = 6;
	
	
	public static ArrayList<FiestasPopulares_Item> obtainFiestasPopulares(Context contexto){
		arrayList = new ArrayList<FiestasPopulares_Item>();
		
		FiestasPopulares_Item fallas = new FiestasPopulares_Item(
				FALLAS,
				contexto.getString(R.string.fallas), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_fallas, 
				contexto.getString(R.string.fallasCalendar));
		
		FiestasPopulares_Item sanVicenteFerrer = new FiestasPopulares_Item(
				SAN_VICENTE_FERRER,
				contexto.getString(R.string.sanVicenteFerrer), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_sanvicenteferrer, 
				contexto.getString(R.string.fallasCalendar));
		
		FiestasPopulares_Item corpus = new FiestasPopulares_Item(
				CORPUS,
				contexto.getString(R.string.corpus), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_corpus, 
				contexto.getString(R.string.fallasCalendar));
		
		FiestasPopulares_Item semanaSanta = new FiestasPopulares_Item(
				SEMANA_SANTA,
				contexto.getString(R.string.semanaSanta), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_semanasanta, 
				contexto.getString(R.string.fallasCalendar));
		
		FiestasPopulares_Item vigenDesamparados = new FiestasPopulares_Item(
				VIRGEN_DESAMPARADOS,
				contexto.getString(R.string.virgenDesamparados), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_virgendesamparados, 
				contexto.getString(R.string.fallasCalendar));
		
		FiestasPopulares_Item nueveOctubre = new FiestasPopulares_Item(
				NUEVE_OCTUBRE,
				contexto.getString(R.string.nueveOctubre), 
				contexto.getString(R.string.fallasContent), 
				R.drawable.fiestaspopulares_nuevedeoctubre, 
				contexto.getString(R.string.fallasCalendar));
		

		arrayList.add(fallas);
		arrayList.add(sanVicenteFerrer);
		arrayList.add(corpus);
		arrayList.add(semanaSanta);
		arrayList.add(vigenDesamparados);
		arrayList.add(nueveOctubre);
		
		return arrayList;
		
	}
}
