import java.util.ArrayList;

public class UserService {

    void createSquad(String username,String squadName)
    {
        Squad squad = new Squad();
        squad.setSquadName(squadName);
        squad.setSquadID(SquadRepo.squadMap.size()+1);
        UserRepo.usersMap.get(username).setSquadID(squad.getSquadID());
        SquadRepo.squadMap.put(squad.getSquadID(),squad);
    }
    boolean addPlayerToSquad(String username , Integer playerId)
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
        int numOfPlayers = SquadRepo.squadMap.get(squadID).getNumOfPlayers();
        if(!budgetChecker(username,playerId)) return false;
        if(!PlayerIdentityChecker(username,playerId)) return false;
        if(!playersCounterChecker(username,playerId)) return false;
        if(!playersPositionChecker(username,playerId)) return false;

        SquadRepo.squadMap.get(squadID).addToListOfPlayerID(playerId);
        SquadRepo.squadMap.get(squadID).setNumOfPlayers(++numOfPlayers);
        return true;
    }
    private boolean budgetChecker(String username , Integer playerId)
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
        int playerValue = PlayerRepo.playersMap.get(playerId).getPlayerValue();
        int sum = SquadRepo.squadMap.get(squadID).getSquadValue();
        int userBudget = UserRepo.usersMap.get(username).getUserBudget();
        sum = sum + playerValue;
        if(sum>100)
        {
            System.out.println("budget exceeded");
            return false;
        }
        SquadRepo.squadMap.get(squadID).setSquadValue(sum);
        UserRepo.usersMap.get(username).setUserBudget(userBudget-playerValue);
        return true;
    }
    private boolean PlayerIdentityChecker(String username , Integer playerId)
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
        int arraySize = SquadRepo.squadMap.get(squadID).getListOfPlayerID().size();
        for(int i=0; i < arraySize;i++) {
            if (playerId == SquadRepo.squadMap.get(squadID).getListOfPlayerID().get(i))
                return false;
        }
        return true;
    }
    private boolean playersPositionChecker(String username , int playerId)
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
        int GK = SquadRepo.squadMap.get(squadID).getCurrentNoGK();
        int DF = SquadRepo.squadMap.get(squadID).getCurrentNoDF();
        int MF = SquadRepo.squadMap.get(squadID).getCurrentNoMF();
        int FD = SquadRepo.squadMap.get(squadID).getCurrentNoFD();
        String position = PlayerRepo.playersMap.get(playerId).getPlayerPosition();
        String playerName = PlayerRepo.playersMap.get(playerId).getPlayerName();
        if(position.compareToIgnoreCase("GK")==0)
        {
            if(GK<2)
            {
                SquadRepo.squadMap.get(squadID).setCurrentNoGK(++GK);
                System.out.println("added "+playerName + " position " + position );
                System.out.println( (2 - GK) + " GK left");
            } else return false;
        }
        else if(position.compareToIgnoreCase("DF")==0)
        {
            if(DF<5) {
                SquadRepo.squadMap.get(squadID).setCurrentNoDF(++DF);
                System.out.println("added "+playerName + " position " + position );
                System.out.println( (5 - DF) + " DF left");
            } else return false;
        }
        else if(position.compareToIgnoreCase("MF")==0){

            if(MF<5) {
                SquadRepo.squadMap.get(squadID).setCurrentNoMF(++MF);
                System.out.println("added "+playerName + " position " + position );
                System.out.println( (5 - MF) + " MF left");
            } else return false;
        }
        else if(position.compareToIgnoreCase("FD")==0)
        {
            if(FD<3){
                SquadRepo.squadMap.get(squadID).setCurrentNoFD(++FD);
                System.out.println("added "+playerName + " position " + position );
                System.out.println( (3 - FD) + " FD left");
            } else return false;
        }
        return true;
    }
    private boolean playersCounterChecker(String username , int playerId)
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
        String playerTeam = PlayerRepo.playersMap.get(playerId).getPlayerTeam();
        int arraySize = SquadRepo.squadMap.get(squadID).getListOfPlayerID().size();
        ArrayList<Integer> listOfPlayers = new ArrayList<>(SquadRepo.squadMap.get(squadID).getListOfPlayerID());
        int counter = 0;
        for(int i=0; i < arraySize;i++) {
            if (playerTeam.compareTo(PlayerRepo.playersMap.get(listOfPlayers.get(i)).getPlayerTeam())==0)
            {
                counter++;
            }
        }
        return counter < 3;
    }
    void addPlayerToMainSquad(String username , Integer playerId) //constraints: at least 1 goalkeeper, 3 defenders and 1 forward
    {
        int squadID = UserRepo.usersMap.get(username).getSquadID();
       // SquadRepo.squadMap.get(squadID).addToMainSquad(playerId);
    }
    boolean replacePlayer(String username , int playerId)
    {
        return true;
    }
    void setCaptain(int playerID)
    {

    }
    void setViceCaptain(int playerID)
    {

    }
    void setSquadScore(int playerID)
    {

    }
}
