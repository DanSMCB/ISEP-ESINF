package project2;

public class Flag {
    String flag;
    String description;

    public Flag(String flag, String description) {
        this.flag = flag;
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  "" + flag + '\'' +
                ", Description = '" + description ;
    }
}
