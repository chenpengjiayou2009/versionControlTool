package KeyValueObjects;

public class Branch extends KeyValueObject{
    public Branch(String branch,String commit){
        this.path += "refs/heads/";
        this.key =  branch;
        this.content = commit;
    }
}
