package controller;

import model.Chessboard;
import model.PlayerColor;
import model.Step;

import java.util.List;

public class AiPlayer {
    private GameMode gameMode;
    private Chessboard model;

    public AiPlayer(GameMode gameMode, Chessboard model){
        this.gameMode = gameMode;
        this.model = model;
    }
    public Step generateMove(PlayerColor color) {
        // 生成一个合适的AI走棋步骤
        // 简化起见，这里仅选择一个合法的随机移动
        if (gameMode == GameMode.AI_1) {
            return AiMove1(color);
        }
        return null;
    }

    private Step AiMove1(PlayerColor color) {
        List<Step> steps = model.getValidSteps(color);
        if (steps.size() > 0) {
            return steps.get((int) (Math.random() * steps.size()));
        }
        return null;
    }
}
