package soccer;

public class TransferModel {
    
    String playerid,playername,ocid,ocname,ncid,ncname,transferfee;

    public TransferModel(String playerid, String playername, String ocid, String ocname, String ncid, String ncname, String transferfee) {
        this.playerid = playerid;
        this.playername = playername;
        this.ocid = ocid;
        this.ocname = ocname;
        this.ncid = ncid;
        this.ncname = ncname;
        this.transferfee = transferfee;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public String getPlayername() {
        return playername;
    }

    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public String getOcid() {
        return ocid;
    }

    public void setOcid(String ocid) {
        this.ocid = ocid;
    }

    public String getOcname() {
        return ocname;
    }

    public void setOcname(String ocname) {
        this.ocname = ocname;
    }

    public String getNcid() {
        return ncid;
    }

    public void setNcid(String ncid) {
        this.ncid = ncid;
    }

    public String getNcname() {
        return ncname;
    }

    public void setNcname(String ncname) {
        this.ncname = ncname;
    }

    public String getTransferfee() {
        return transferfee;
    }

    public void setTransferfee(String transferfee) {
        this.transferfee = transferfee;
    }
    
    
}
