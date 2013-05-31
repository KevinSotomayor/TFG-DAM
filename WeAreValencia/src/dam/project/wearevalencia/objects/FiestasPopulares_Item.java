package dam.project.wearevalencia.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class FiestasPopulares_Item implements Parcelable{
	private int id;
	private String title;
	private String content;
	private int thumbail;
	private String calendar;
	
	public FiestasPopulares_Item(int id, String title, String content, int thumbail, String calendar) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.thumbail = thumbail;
		this.calendar = calendar;
	}
	
	//parcel part:
		//info sacada de: http://shri.blog.kraya.co.uk/2010/04/26/android-parcel-data-to-pass-between-activities-using-parcelable-classes/ 
		//y http://www.sohailaziz.com/2012/04/passing-custom-objects-between-android.html
		//recuperar un objeto que implementa parcelable ya que de otra forma es imposible recuperar todos y cada uno
		//de los campos que tiene el objeto.
		
		public FiestasPopulares_Item(Parcel in){
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
			dest.writeString(calendar);
			dest.writeInt(thumbail);
			
		}
		
		private void readFromParcel(Parcel in) {
			id = in.readInt();
			title = in.readString();
			content = in.readString();
			calendar = in.readString();
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
	           public FiestasPopulares_Item createFromParcel(Parcel in) {
	               return new FiestasPopulares_Item(in);
	           }

	           public FiestasPopulares_Item[] newArray(int size) {
	               return new FiestasPopulares_Item[size];
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

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public int getThumbail() {
			return thumbail;
		}

		public void setThumbail(int thumbail) {
			this.thumbail = thumbail;
		}

		public String getCalendar() {
			return calendar;
		}

		public void setCalendar(String calendar) {
			this.calendar = calendar;
		}
	       
	       
	
	
	
	}
