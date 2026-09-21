package lw01.unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int Cost;
        int day = getDays();
        if (day <= 3) {
            Cost = day*60000;
        } else {
            Cost = (3*60000) + ((day - 3)* 45000);
        }
        
        return Cost + 20000;
    }

    @Override
    public String label() {
        return "Projector";
    }
}
