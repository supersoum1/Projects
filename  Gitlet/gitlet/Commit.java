package gitlet;


        import org.apache.commons.collections.map.HashedMap;
        import java.io.Serializable;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Map;
public class Commit implements Serializable {
    private String message;
    private String ownHash; //stores hash of the commit
    private String parentHash;
    private String timestamp;
    private DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
    private Map<String, String> blobIDs;
    public Commit(String message ) //or parent? as in previous commit
    {
        this.message = message;
        this.parentHash = null ;
        this.timestamp = getTimestamp();
        this.blobIDs = new HashMap<>();
        this.blobIDs=Utils.readObject(Repository.StageAdd,HashMap.class);
        Utils.writeObject(Repository.StageAdd,new HashMap<>());
        // sha1 of the file: sha1 of the blob; file ID: blobID, fileID
        byte[] commitObjbyte = Utils.serialize(this.timestamp+this.message);
        this.ownHash = Utils.sha1(commitObjbyte);
        parentHash = this.ownHash;
        if ("initial commit".equals(this.message)) {
            this.timestamp = "00:00:00 UTC, Thursday, 1 January 1970";
        }
    }

    // generate a unique hash for the commit using its contents
    // and Commit commitObj = new Commit(message);
    //get serial number and returns it //
    //should return sha1 //return sha1(commitObj)
    public String getMessage(){
        return message;
    }
    public String getTimestamp(){
        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        Date date = new Date();
        return (dateFormat.format(date));
        //return timestamp;//add google timestamp
    }
    public String getParentHash() {
        return parentHash;
    }

    public String getOwnHash() {
        return ownHash;
    }
    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }
    public void addFile(String filename, String blobID) {
        blobIDs.put(filename, blobID);
    }
}