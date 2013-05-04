package dam.project.wearevalencia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/* Clase que se inicia y muestra una pantalla de espera mientras se carga la aplicacion
 * basada en la aplicación de ODEC (FCT) */


public class SplashScreen_Inicio extends Activity {
	private final int DELAY_MILIS = 1000;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen_inicio);
		
		Typeface robotoSplash = Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf");
		TextView cargando = (TextView)findViewById(R.id.splashScreen_textView);
		cargando.setTypeface(robotoSplash);

			new Handler().postDelayed(new Runnable() {
							
				@Override
				public void run() {
					Intent mainScreen = new Intent(SplashScreen_Inicio.this, Main_FragmentActivity.class);
					mainScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(mainScreen);
					overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

					//el nuevo intent elimina las actividades que está creadas por encima de esta.
					/*Si se establece, y la actividad que se puso en marcha está en ejecución en la tarea actual, en lugar de lanzar una nueva instancia de la actividad,
					 *  todas las demás actividades de la parte superior de la misma se cerrará y la intención será 
					 *  entregado a la (ahora en superior) actividad antigua como nueva Intención.

					* Por ejemplo, considere una tarea que consiste en las actividades: A, B, C, D. 
					* Si D llama startActivity () con la intención que 
					* tiene el componente de la actividad B, C y D se terminará y B reciben la intención dada ,
					* lo que resulta en la pila ahora siendo: a, B.
					* 
					* http://developer.android.com/intl/es/reference/android/content/Intent.html#FLAG_ACTIVITY_CLEAR_TOP
					 */
					
					SplashScreen_Inicio.this.finish();
				}
			}, DELAY_MILIS);
			
		}
	
}
