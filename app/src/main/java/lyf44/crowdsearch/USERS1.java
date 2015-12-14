package lyf44.crowdsearch;

    /**
     * Created by lyf44 on 15/5/2015.
     */
    public class USERS1 {

        //@com.google.gson.annotations.SerializedName("UserID")
        //protected int UserID;
        //protected String userid;
        @com.google.gson.annotations.SerializedName("PassWord")
        protected String PassWord;
        @com.google.gson.annotations.SerializedName("Email")
        protected String Email;
        @com.google.gson.annotations.SerializedName("LastName")
        protected String LastName;
        @com.google.gson.annotations.SerializedName("FirstName")
        protected String FirstName;
        @com.google.gson.annotations.SerializedName("id")
        protected int id;
        //protected Integer point;
        //protected String achievement;

        public USERS1()
        {}
        public String getEmail()
        {
            return Email;
        }
        public String getPassWord()
        {
            return PassWord;
        }
        public String getFirstName(){return FirstName;}
        public String getLastName(){return LastName;}
        public void setEmail(String email)
        {
            Email = email;
        }
        public void setPassWord(String password)
        {
            PassWord = password;
        }
        public void setLastName(String lastname)
        {
            LastName = lastname;
        }
        public void setFirstName(String firstname)
        {
            FirstName = firstname;
        }




    }
