package edu.udistrital.plantae.logicadominio.datosespecimen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Sosa G., Mateus A.
 * @version 1.0
 * @created 26-Jun-2013 12:09:14 AM
 */
public class ColorMunsell implements Parcelable {

    private Long id;
    private Integer hue;
    private Integer value;
    private Integer chroma;

	public void finalize() throws Throwable {
	}
	public ColorEspecimen getRGB(){
		return null;
	}

    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHue() {
		return hue;
	}

    public void setHue(Integer hue) {
        this.hue = hue;
	}

    public Integer getValue() {
		return value;
	}

    public void setValue(Integer value) {
        this.value = value;
	}

    public Integer getChroma() {
		return chroma;
	}

	/**
	 *
	 * @param chroma
	 */
	public void setChroma(Integer chroma){
		this.chroma = chroma;
	}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeInt(this.hue);
        dest.writeInt(this.value);
        dest.writeInt(this.chroma);
    }

    public ColorMunsell() {
    }

    public ColorMunsell(int hue, int value, int chroma){
        this.hue = hue;
        this.value = value;
        this.chroma = chroma;
    }

    public String aString() {
        String string = "";
        if (hue != null) {
            string = string + (string.equals("") ? "Hue: ":", Hue: ") + hue;
        }
        if (value != null) {
            string = string + (string.equals("") ? "Value: ":", Value: ") + value;
        }
        if (chroma != null) {
            string = string + (string.equals("") ? "Chroma: ":", Chroma: ") + chroma;
        }
        return string;
    }

    @Override
    public String toString() {
        return "ColorMunsell{" +
                "id=" + id +
                ", hue=" + hue +
                ", value=" + value +
                ", chroma=" + chroma +
                '}';
    }

    private ColorMunsell(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.hue = in.readInt();
        this.value = in.readInt();
        this.chroma = in.readInt();
    }

    public static final Parcelable.Creator<ColorMunsell> CREATOR = new Parcelable.Creator<ColorMunsell>() {
        public ColorMunsell createFromParcel(Parcel source) {
            return new ColorMunsell(source);
        }

        public ColorMunsell[] newArray(int size) {
            return new ColorMunsell[size];
        }
    };
}