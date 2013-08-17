package com.fgi.whostheass

import com.fgi.whostheass.game.Game
import com.fgi.whostheass.strategy.TerribleStrategy

class WhosTheAss {

    public static void main(String[] args) {

        new Game(
			[
				new TerribleStrategy(),
				new TerribleStrategy(),
				new TerribleStrategy(),
				new TerribleStrategy()
			]
        ).play()
    }
}
