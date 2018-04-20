import java.util.Random;

public class AI extends Joueur {

    public AI() {
        super("I-401");
    }

    @Override
    public void initialize(){

    }

    public int sendMissile(Joueur adversary){
        String missileCoord=randomCoord();
        return super.sendMissile(missileCoord, adversary);
    }

    public String randomCoord(){
        Random r = new Random();
        String line="ABCDEFGHIJ";
        char randLine=line.charAt(r.nextInt(line.length()));
        int randColumn=r.nextInt(10)+1;
        String randCoord = randLine+Integer.toString(randColumn);
        return randCoord;
    }

}
