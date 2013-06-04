package dam.project.wearevalencia.objects;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.maps.model.LatLng;

public class Eventos_Item implements Parcelable{
	private String title;
	private String content;
	private String horary;
	private String address;
	private LatLng latLng;
	
	
	public Eventos_Item(String title, String content, String horary, String address, LatLng latLng) {
		super();
		this.title = title;
		this.content = content;
		this.horary = horary;
		this.address = address;
		this.latLng = latLng;
	}
	
	//parcel part:
			//info sacada de: http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/ 
			//y http://www.sohailaziz.com/2012/04/passing-custom-objects-between-android.html
			//recuperar un objeto que implementa parcelable ya que de otra forma es imposible recuperar todos y cada uno
			//de los campos que tiene el objeto.
			
			public Eventos_Item(Parcel in){
				readFromParcel(in);
			}
			

			@Override
			public int describeContents() {
				// TODO Auto-generated method stub
				return 0;
			}


			@Override
			public void writeToParcel(Parcel dest, int flags) {
				dest.writeString(title);
				dest.writeString(content);
				dest.writeString(horary);
				dest.writeString(address);
				dest.writeParcelable(latLng, flags);
				
			}
			
			private void readFromParcel(Parcel in) {
				title = in.readString();
				content = in.readString();
				horary = in.readString();
				address = in.readString();
				latLng = in.readParcelable(Eventos_Item.class.getClassLoader());
				
			}
			
			/**
		    *
		    * This field is needed for Android to be able to
		    * create new objects, individually or as arrays.
		    *
		    * This also means that you can use use the default
		    * constructor to create the object and use another
		    * method to hyrdate it as necessary.
		    *
		    *
		    */
		   @SuppressWarnings("rawtypes")
		   public static final Parcelable.Creator CREATOR =
		   	new Parcelable.Creator() {
		           public Eventos_Item createFromParcel(Parcel in) {
		               return new Eventos_Item(in);
		           }

		           public Eventos_Item[] newArray(int size) {
		               return new Eventos_Item[size];
		           }
		    };
		    
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getHorary() {
		return horary;
	}
	public void setHorary(String horary) {
		this.horary = horary;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public LatLng getLatLng() {
		return latLng;
	}
	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}
}
