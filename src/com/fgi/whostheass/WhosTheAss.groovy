package com.fgi.whostheass

import com.fgi.whostheass.game.Game
import com.fgi.whostheass.strategy.OddStrategy
import com.fgi.whostheass.strategy.SimpleStrategy
import com.fgi.whostheass.strategy.TerribleStrategy

class WhosTheAss {

	public static void main(String[] args) {

		def numGames = (args ? args[0] : 1) as Integer

		def strategies = [
			new SimpleStrategy(),
			new SimpleStrategy(),
			new SimpleStrategy(),
			new OddStrategy()
		]

		def stats = initStats(strategies)

		numGames.times{

			def game = new Game(strategies)
			def results = game.play()

			updateStats(stats, results)
		}

		processStats(stats, numGames)

		stats.each{
			println it
		}
	}

	static def initStats(strategies) {

		strategies.collect{
			[
				strategy: it,
				totalScore: 0,
				bestScore: Integer.MAX_VALUE,
				worstScore: Integer.MIN_VALUE
			]
		}
	}

	static def updateStats(stats, results) {

		results.eachWithIndex{
			player, i ->

			Integer thisScore = player.points
			def playerStats = stats[i]

			playerStats.totalScore += thisScore

			playerStats.bestScore = Math.min(playerStats.bestScore as Integer, thisScore)
			playerStats.worstScore = Math.max(playerStats.worstScore as Integer, thisScore)
		}
	}

	static def processStats(stats, numGames) {

		stats.each{

			it.averageScore = it.totalScore / numGames
		}
	}
}
