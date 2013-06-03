package dam.project.wearevalencia.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class CentrosComerciales_Item implements Parcelable {
	private int id;
	private String title;
	private String address;
	private String telephone;
	private String web;
	private LatLng latLng;
	private int thumbail;
	
	
	public CentrosComerciales_Item(int id, String title, String address, String telephone, String web, LatLng latLng, int thumbail) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.telephone = telephone;
		this.web = web;
		this.latLng = latLng;
		this.thumbail = thumbail;
	}
	
	//parcel part:
			//info sacada de: http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/ 
			//y http://www.sohailaziz.com/2012/04/passing-custom-objects-between-android.html
			//recuperar un objeto que implementa parcelable ya que de otra forma es imposible recuperar todos y cada uno
			//de los campos que tiene el objeto.
			
			public int getThumbail() {
		return thumbail;
	}

	public void setThumbail(int thumbail) {
		this.thumbail = thumbail;
	}

			public CentrosComerciales_Item(Parcel in){
				readFromParcel(in);
			}
			

			@Override
			public int describeContents() {
				// TODO Auto-generated method stub
				return 0;
			}


			@Override
			public void writeToParcel(Parcel dest, int flags) {
				dest.writeInt(id);
				dest.writeString(title);
				dest.writeString(address);
				dest.writeString(telephone);
				dest.writeString(web);
				dest.writeParcelable(latLng, flags);
				dest.writeInt(thumbail);
				
			}
			
			private void readFromParcel(Parcel in) {
				id = in.readInt();
				title = in.readString();
				address = in.readString();
				telephone = in.readString();
				web = in.readString();
				latLng = in.readParcelable(CentrosComerciales_Item.class.getClassLoader());
				thumbail = in.readInt();
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
		           public CentrosComerciales_Item createFromParcel(Parcel in) {
		               return new CentrosComerciales_Item(in);
		           }

		           public CentrosComerciales_Item[] newArray(int size) {
		               return new CentrosComerciales_Item[size];
		           }
		    };
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public LatLng getLatLng() {
		return latLng;
	}
	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}
	
}
