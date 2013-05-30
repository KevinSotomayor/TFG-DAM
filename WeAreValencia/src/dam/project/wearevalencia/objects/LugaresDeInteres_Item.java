package dam.project.wearevalencia.objects;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class LugaresDeInteres_Item implements Parcelable {
	private int id;
	private String title;
	private String content;
	private String category;
	private LatLng latLng; 
	private String addres;
	private String price;
	private String horary;
	private String telephone;
	private int thumbailMax;
	private int[] gallery;
	
	public LugaresDeInteres_Item(int id, String title, String content,
			String category, LatLng latLng, String addres,
			String price, String horary, String telephone, int thumbailMax,
			int[] gallery) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.category = category;
		this.latLng = latLng;
		this.addres = addres;
		this.price = price;
		this.horary = horary;
		this.telephone = telephone;
		this.thumbailMax = thumbailMax;
		this.gallery = gallery;
	}

	
	//parcel part:
	//info sacada de: http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/ 
	//y http://www.sohailaziz.com/2012/04/passing-custom-objects-between-android.html
	//recuperar un objeto que implementa parcelable ya que de otra forma es imposible recuperar todos y cada uno
	//de los campos que tiene el objeto.
	
	public LugaresDeInteres_Item(Parcel in){
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
		dest.writeString(content);
		dest.writeString(category);
		dest.writeParcelable(latLng, flags);
		dest.writeString(addres);
		dest.writeString(price);
		dest.writeString(horary);
		dest.writeString(telephone);
		dest.writeInt(thumbailMax);
		dest.writeValue(gallery);
		
	}
	
	private void readFromParcel(Parcel in) {
		id = in.readInt();
		title = in.readString();
		content = in.readString();
		category = in.readString();
		latLng = in.readParcelable(LugaresDeInteres_Item.class.getClassLoader());
		addres = in.readString();
		price = in.readString();
		horary = in.readString();
		telephone = in.readString();
		thumbailMax = in.readInt();
		gallery = (int[]) in.readValue(LugaresDeInteres_Item.class.getClassLoader());
		
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
           public LugaresDeInteres_Item createFromParcel(Parcel in) {
               return new LugaresDeInteres_Item(in);
           }

           public LugaresDeInteres_Item[] newArray(int size) {
               return new LugaresDeInteres_Item[size];
           }
       };
       
       
    //getters y setters autogenerados
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getHorary() {
		return horary;
	}

	public void setHorary(String horary) {
		this.horary = horary;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getThumbailMax() {
		return thumbailMax;
	}

	public void setThumbailMax(int thumbailMax) {
		this.thumbailMax = thumbailMax;
	}

	public int[] getGallery() {
		return gallery;
	}

	public void setGallery(int[] gallery) {
		this.gallery = gallery;
	}	

}
