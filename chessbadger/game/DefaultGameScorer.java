package chessbadger.game;

public class DefaultGameScorer implements GameScorer
{

    public int getScore(GameModel gameModel)
    {
        String s;
        GameHeaderModel headerModel = gameModel.getHeaderModel();
        GameMoveModel moveModel = gameModel.getMoveModel();
        
        int score = 0;
        score += moveModel.getTotalNumOfPlies()  * 3;
        score += moveModel.getTotalCommentSize() * 1;
        
        s = headerModel.getWhite();  if (s != null) score += s.length() * 1;
        s = headerModel.getBlack();  if (s != null) score += s.length() * 1;
        if (headerModel.getDate()          != null) score += 8;
        if (headerModel.getResultStr()     != null) score += 8;
        s = headerModel.getEvent();  if (s != null) score += s.length() * 1;
        s = headerModel.getSite();   if (s != null) score += s.length() * 1;
        if (headerModel.getWhiteEloStr()   != null) score += 4;
        if (headerModel.getBlackEloStr()   != null) score += 4;
        if (headerModel.getECO()           != null) score += 1;
        
        return score;
    }
    
}