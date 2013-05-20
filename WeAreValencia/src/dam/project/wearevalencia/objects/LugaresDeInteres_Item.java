package dam.project.wearevalencia.objects;

import com.google.android.gms.maps.model.LatLng;

public class LugaresDeInteres_Item {
	private String title;
	private String Content;
	private LatLng latLng;
	private int icon;
	
	public LugaresDeInteres_Item(String title, String content, LatLng latLng, int icon) {
		this.title = title;
		this.Content = content;
		this.latLng = latLng;
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}
	

}
