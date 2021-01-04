package KeyValueObjects;

public class Head extends KeyValueObject{
    public Head(String branch){
        this.key = "HEAD";
        this.content = new StringBuilder("refs/heads/" + branch);
    }
}
