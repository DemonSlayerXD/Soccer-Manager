package soccer;

public class PlayerModel {
    
    String playerid,name,age,position,country,clubid;

    public PlayerModel(String playerid, String name, String age, String position, String country, String clubid) {
        this.playerid = playerid;
        this.name = name;
        this.age = age;
        this.position = position;
        this.country = country;
        this.clubid = clubid;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid) {
        this.clubid = clubid;
    }
    
    
}
