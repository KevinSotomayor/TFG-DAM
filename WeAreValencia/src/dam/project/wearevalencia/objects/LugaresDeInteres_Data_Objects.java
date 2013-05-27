package dam.project.wearevalencia.objects;

/*
 * 
 * Clase para formar objetos y devolver arraylist con los objetos cargados.
 * 
 * Estructura de datos de cada objeto:
 * 
 * -id
 * -titulo
 * -contenido
 * -categoria
 * -latitud y longitud en el mapa
 * -miniatura de la lista de lugares de interes que es la misma que la de la ficha del lugar de interes
 * -direccion
 * -precio
 * -horario
 * -telefono
 * -miniatura de fondo item ficha
 * -array de las fotos que se presentan en la galería
 * 
 * 
 * 
 */


import java.util.ArrayList;
import android.content.Context;
import com.google.android.gms.maps.model.LatLng;
import dam.project.wearevalencia.R;

public class LugaresDeInteres_Data_Objects {
	
	private static ArrayList<LugaresDeInteres_Item> arrayList;
	private static LatLng latlng = null;
	private static int[] arrayGallery;
	
	/*constantes identificadoras de cada lugar*/
	private final static int LONJA_SEDA = 1;
	private final static int TORRES_DE_SERRANOS = 2;
	private final static int TORRES_DE_QUART = 3;
	private final static int MERCADO_CENTRAL = 4;
	private final static int MERCADO_COLON = 5;
	private final static int CAC = 6;
	private final static int PLAZA_AYUNTAMIENTO = 7;
	private final static int ESTACION_NORTE =8;
	private final static int PLAZA_TOROS = 9;
	private final static int PLAZA_VIRGEN = 10;
	private final static int EL_MICALET = 11;
	private final static int PALAU_MUSICA = 12;
	
	public static ArrayList<LugaresDeInteres_Item> obtainMonuments(Context contexto){
			arrayList = new ArrayList<LugaresDeInteres_Item>();
			latlng = new LatLng(39.47434857729124, -0.3785929481113093);
			arrayGallery = new int[]{R.drawable.main_bg_1, R.drawable.main_bg_2, R.drawable.main_bg_3, R.drawable.main_bg_4, R.drawable.main_bg_5};
		
			LugaresDeInteres_Item lonjaDeLaSeda =  new LugaresDeInteres_Item(
					LONJA_SEDA,
					contexto.getString(R.string.lonjaDeLaSeda), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_lonjadelaseda, 
					arrayGallery
					);
			latlng = new LatLng(39.4792574384201,-0.37599086353987676);
			LugaresDeInteres_Item torresDeSerranos =  new LugaresDeInteres_Item(
					TORRES_DE_SERRANOS,
					contexto.getString(R.string.torresDeSerranos), 
					contexto.getString(R.string.torresDeSerranosContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_torresdeserranos, 
					arrayGallery
					);
			
			LugaresDeInteres_Item torresDeQuart =  new LugaresDeInteres_Item(
					TORRES_DE_QUART,
					contexto.getString(R.string.torresDeQuart), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_torresquart, 
					arrayGallery
					);
			
			LugaresDeInteres_Item mercadoCentral =  new LugaresDeInteres_Item(
					MERCADO_CENTRAL,
					contexto.getString(R.string.mercadoCentral), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_mercadocentral, 
					arrayGallery
					);
			
			LugaresDeInteres_Item mercadoColon =  new LugaresDeInteres_Item(
					MERCADO_COLON,
					contexto.getString(R.string.mercadoColon), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_mercadocolon, 
					arrayGallery
					);
			
			LugaresDeInteres_Item ciudadartesyciencias =  new LugaresDeInteres_Item(
					CAC,
					contexto.getString(R.string.cac), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_cac, 
					arrayGallery
					);
			
			
			LugaresDeInteres_Item plazaAyuntamiento =  new LugaresDeInteres_Item(
					PLAZA_AYUNTAMIENTO,
					contexto.getString(R.string.plazaAyuntamiento), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_plazaayuntamiento, 
					arrayGallery
					);
			
			LugaresDeInteres_Item estacionNorte =  new LugaresDeInteres_Item(
					ESTACION_NORTE,
					contexto.getString(R.string.estacionDelNorte), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_estacionnorte, 
					arrayGallery
					);
			LugaresDeInteres_Item plazaToros =  new LugaresDeInteres_Item(
					PLAZA_TOROS,
					contexto.getString(R.string.plazaDeToros), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_plazatoros, 
					arrayGallery
					);
			LugaresDeInteres_Item plazaVirgen =  new LugaresDeInteres_Item(
					PLAZA_VIRGEN,
					contexto.getString(R.string.plazaDeLaVirgen), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_plazavirgen, 
					arrayGallery
					);
			LugaresDeInteres_Item elMicalet =  new LugaresDeInteres_Item(
					EL_MICALET,
					contexto.getString(R.string.elMicalet), 
					contexto.getString(R.string.LonjaDeLaSedaContent), 
					contexto.getString(R.string.Monumentos),
					latlng, 
					contexto.getString(R.string.lonjaDeLaSedaAddres),
					contexto.getString(R.string.lonjaDeLaSedaPrice),
					contexto.getString(R.string.lonjaDeLaSedaHorary),
					contexto.getString(R.string.lonjaDeLaSedaTelephone),
					R.drawable.lugaresdeinteres_ficha_elmicalet, 
					arrayGallery
					);
			
			
			arrayList.add(lonjaDeLaSeda);
			arrayList.add(torresDeSerranos);
			arrayList.add(torresDeQuart);
			arrayList.add(mercadoCentral);
			arrayList.add(mercadoColon);
			arrayList.add(ciudadartesyciencias);
			arrayList.add(plazaAyuntamiento);
			arrayList.add(estacionNorte);
			arrayList.add(plazaToros);
			arrayList.add(plazaVirgen);
			arrayList.add(elMicalet);

		return arrayList;
		
	}
	

}
