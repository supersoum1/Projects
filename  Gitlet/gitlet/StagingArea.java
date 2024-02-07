package gitlet;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;

public class StagingArea implements Serializable {
    private Map<String, String> stagedFiles; //mao contains file anmes and blob ids of saved files


    public StagingArea() {
        stagedFiles = new HashMap<>();
    }

    public void addFile(String filename, String blobID) {
        stagedFiles.put(filename, blobID);
    }
    public void removeFile(String filename) {
        stagedFiles.remove(filename);
    }
    public Map<String, String> getStagedFiles() {
        return stagedFiles;
    }
}
