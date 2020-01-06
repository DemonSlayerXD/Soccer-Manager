package soccer;

public class ClubModel {
    
    String clubid,club,country,coach,home,year;

    public ClubModel(String clubid,String club, String country, String coach, String home, String year) {
        this.clubid = clubid;
        this.club = club;
        this.country = country;
        this.coach = coach;
        this.home = home;
        this.year = year;
    }

    public String getClubid() {
        return clubid;
    }

    public void setClubid(String clubid) {
        this.clubid = clubid;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    
}
