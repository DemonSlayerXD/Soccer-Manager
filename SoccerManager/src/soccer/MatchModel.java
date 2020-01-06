package soccer;

public class MatchModel {
    
    String c1id,c1name,c2id,c2name,date,stadium;

    public MatchModel(String c1id, String c1name, String c2id, String c2name, String date, String stadium) {
        this.c1id = c1id;
        this.c1name = c1name;
        this.c2id = c2id;
        this.c2name = c2name;
        this.date = date;
        this.stadium = stadium;
    }

    public String getC1id() {
        return c1id;
    }

    public void setC1id(String c1id) {
        this.c1id = c1id;
    }

    public String getC1name() {
        return c1name;
    }

    public void setC1name(String c1name) {
        this.c1name = c1name;
    }

    public String getC2id() {
        return c2id;
    }

    public void setC2id(String c2id) {
        this.c2id = c2id;
    }

    public String getC2name() {
        return c2name;
    }

    public void setC2name(String c2name) {
        this.c2name = c2name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }
    
    
}
