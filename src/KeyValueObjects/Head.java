package KeyValueObjects;

public class Head extends KeyValueObject{
    public Head(String branch){
        this.key = "HEAD";
        this.content = "refs/heads/" + branch;
    }
}
