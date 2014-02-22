package com.fgi.whostheass

import com.fgi.whostheass.game.Game
import com.fgi.whostheass.strategy.OddStrategy
import com.fgi.whostheass.strategy.SimpleStrategy

class WhosTheAss {

	public static void main(String[] args) {

		def numGames = (args ? args[0] : 1) as Integer

		def strategies = [
			new SimpleStrategy(),
			new SimpleStrategy(),
			new SimpleStrategy(),
			new OddStrategy()
		]

		new WhosTheAss(numGames, strategies)
	}

	def stats

	WhosTheAss(numGames, strategies) {

		initStats(strategies)

		numGames.times{

			def game = new Game(strategies)
			def results = game.play()

			updateStats(results)
		}

		processStats(numGames)

		stats.each{
			println it
		}
	}

	void initStats(strategies) {

		stats = strategies.collect{
			[
				strategy: it,
				totalScore: 0,
				bestScore: Integer.MAX_VALUE,
				worstScore: Integer.MIN_VALUE
			]
		}
	}

	void updateStats(results) {

		results.eachWithIndex{
			player, i ->

			Integer thisScore = player.points
			def playerStats = stats[i]

			playerStats.totalScore += thisScore

			playerStats.bestScore = Math.min(playerStats.bestScore as Integer, thisScore)
			playerStats.worstScore = Math.max(playerStats.worstScore as Integer, thisScore)
		}
	}

	void processStats(numGames) {

		stats.each{

			it.averageScore = it.totalScore / numGames
		}
	}
}
