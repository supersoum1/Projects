package gitlet;

import java.io.Serializable;

import static gitlet.Utils.*;

public class Blob implements Serializable {
    private String contents;
    private String BlobID="";


    public Blob(String filecontents){
        this.contents=filecontents;
        byte[] BlobByte= serialize(this);//get serialnumber
        this.BlobID= sha1(BlobByte);//get serialnumber to string


        //this.SerialNumber(blobID);//give serial number to the blob obj
        //File BlobFile=new File(BlobID);
        //writeObject(BlobFile,this);
        //BlobFile=join(BlobDir,blobID);


    }
    public String getBlobid(){
        return BlobID;
    }

}
