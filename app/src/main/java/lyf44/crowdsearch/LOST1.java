package lyf44.crowdsearch;

/**
 * Created by lyf44 on 18/5/2015.
 */
public class LOST1 {

    //@com.google.gson.annotations.SerializedName("RecordIndexL")
    //protected int RecordIndexL;
    @com.google.gson.annotations.SerializedName("Award_Points")
    protected int Award_Points;
    @com.google.gson.annotations.SerializedName("id")
    protected int id;
    @com.google.gson.annotations.SerializedName("Item")
    protected String Item;
    @com.google.gson.annotations.SerializedName("Place")
    protected String Place;
    @com.google.gson.annotations.SerializedName("Colour")
    protected String Colour;
    @com.google.gson.annotations.SerializedName("UserID")
    protected int UserID;
    @com.google.gson.annotations.SerializedName("Brand")
    protected String Brand;
    @com.google.gson.annotations.SerializedName("Remark")
    protected String Remark;
    //@com.google.gson.annotations.SerializedName("Photo")
    //protected String Photo;
    @com.google.gson.annotations.SerializedName("Date")
    protected String Date;
    //@com.google.gson.annotations.SerializedName("__deleted")
   //protected boolean __deleted;

    public LOST1()
    {
    }

    public String getName()
    {
        return Item;
    }
    public String getPlace()
    {
        return Place;
    }

    public void setName(String name)
    {
        Item = name;
    }

    public void setPlace(String place)
    {
        Place = place;
    }


}
