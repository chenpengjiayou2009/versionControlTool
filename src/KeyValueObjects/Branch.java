package KeyValueObjects;

public class Branch extends KeyValueObject{
    public Branch(String branch,String commit){
        this.path += "refs/heads/";  // prepare the path to write()
        this.key =  branch; // write() destination
        this.content = commit; // branch content is the commit hashcode
    }
}
