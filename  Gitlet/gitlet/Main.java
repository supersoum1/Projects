package gitlet;
import java.io.File;
import java.io.IOException;


/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) throws IOException {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                // TODO: handle the `init` command
                break;
            case "add":
                String textfilename=args[1];
                Repository.add(textfilename);
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            case "commit":
                // TODO: handle the `add [filename]` command
                break;

            // TODO: FILL THE REST IN
            case "restore":
                if(args[1]=="--"){
                    String filename=args[2];
                    Repository.restore("",filename);
                }
                else{
                    String commitID=args[1];
                    String filename=args[3];
                    Repository.restore(commitID,filename);
                }

            default:
                System.out.println("wrong command you whore ");

        }

    }




}
