package gitlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Formatter;


import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author Soumya Agarwal and Salma Mufti
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    String DateFormat= "EEE, d MMM yyyy HH:mm:ss Z";
    static File BlobDir;
    static File CommitDir;
    static File StageDir;
    static File StageAdd;
    static HashMap<String, String> stageForAdd ;


    /* TODO: fill in the rest of this class. */
    public static void setUpPersistance(){
        BlobDir=Utils.join(GITLET_DIR,"BLobDir");
        CommitDir=Utils.join(GITLET_DIR,"BLobDir");
        StageDir=Utils.join(GITLET_DIR,"StageDir");
        BlobDir.mkdir();
        CommitDir.mkdir();
        StageDir.mkdir();
        stageForAdd = new HashMap<>();
        HashMap<String,String> stageForRemove = new HashMap<>();
        Utils.writeObject(StageAdd,stageForAdd);
    }
    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");//edge case
            System.exit(0);
        }
        setUpPersistance();
        commit("initial commit");
    }
        //I CHECK IF IT HASNT BEEN MADE PREVIOUSLY+ HOW DO I CHECK CHANGE IN CONTENTS + WHAT IF WE CHANGE FILENAME
        //want to put this up in persistance


        //Branches? HEre we need to initialize  a master branch
        // and have it point to the initial commit
        //what is UID?
    public static String add(String filename) throws IOException {

        File addFile=new File(filename);
        File BlobFile;

        if(!addFile.exists()){
            System.out.println("File does not exist");
            System.exit(0);
        }

        String contents= Utils.readContentsAsString(addFile);
        if(stageForAdd.containsValue(filename))
        {
            //stageforadd is a hashmap keeping track of the files we are staging for addition
            //keys are file name and values are blobids ofthe staged files here
            //checking if the specific file is in the stage for add
            // if file is staged for addition then do this steps
            //stagedblob id will have blobid of staged file
            //File stagedBlobFile = join(BlobDir, stagedBlobID); //if ifle is staged for addiyion code gets blobid
            // of filename from the map of stage for add


            String stagedBlobID = stageForAdd.get(filename); //gets blob id associated sith file name from map
            File stagedBlobFile= new File(stagedBlobID);
            String stagedContents = readContentsAsString(stagedBlobFile);

            if (contents.equals(stagedContents)) {
                stageForAdd.remove(filename);
                System.exit(0);
            }
            Blob BlobObj= new Blob(contents);
            String blobid=BlobObj.getBlobid();
            BlobFile=new File(blobid);
            writeObject(BlobFile,BlobObj);
            BlobFile=join(BlobDir,blobid);
            stageForAdd.remove(filename);
            stageForAdd.put(blobid,filename);
            System.exit(0);
            /** create a new blobobj with contents of the new file.
             * Make a new blob file and writeObject(blobobj, new file.
             * change stagefor add ka blob id to the new blob ka id
             */
        }

        Blob BlobObj= new Blob(contents);//creates an object blob and saves contents of the file
        String blobID=BlobObj.getBlobid();
        //BlobObj.SerialNumber(blobID);//give serial number to the blob obj
        BlobFile=new File(blobID);
        writeObject(BlobFile,BlobObj);
        BlobFile=join(BlobDir,blobID);
        addFile= join(StageDir,filename);
        stageForAdd.put(blobID,filename);//adding to add hash map
        return "";

    }

    public static void commit(String message){
        Commit commitObj=new Commit(message);
        File commitFile;
        commitFile=new File(commitObj.getOwnHash());
        writeObject(commitFile,commitObj);
        commitFile=Utils.join(CommitDir, commitObj.getOwnHash());//created a file with name of SHa1

        /**
        //clone parent commit
        //change meta data aka: message and timestamp
        //use staging area to modify the file we're tracking**/



        // save files in a list??
        //HOW DO WE ACCESS OLD PARENT FILES
        //HOW DO WE CLEAR THE STAGING AREA
    }
    public static void log(){

        for(String  i:Utils.plainFilenamesIn(CommitDir)){
            System.out.println("===");
            System.out.println("commit"+ i);
            File commitFile= new File(i);
            System.out.println(readContentsAsString(commitFile)); //since i is object name
            //System.out.println(i.getMessage());
        }
    }
    public static void restore(String Commitid, String filename) {
        File filetemp=new File(filename);
        if(!filetemp.exists()){
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }

        if(Commitid==""){
            //get old version of the file
            //replace old version of the file
            //read contents will return byte array
            Utils.writeContents(filetemp,(Object)Utils.readContents(filetemp));

            /**Takes the version of the file as it exists in the head commit and puts it in the working
            // directory, overwriting the version of the file that’s already there if there is one.
            // The new version of the file is not staged.**/
        }

        File commitFile=new File(Commitid);

        if(!commitFile.exists()){
            System.out.println("No commit with that id exists.");

        }
        Utils.writeContents(filetemp, (Object) Utils.readContents(commitFile));

        /**Takes the version of the file as it exists in
         * the head commit and puts it in the working directory, overwriting the version of the file
         * that’s already there if there is one. The new version of the file is not staged.**/
        //CHNAGE THE HEAD


    }



}
