package profile;

public class Score implements Comparable<Score>
{
	private String player;
	private int level;
	private int scores;
	private int time;

	public Score()
	{
		setScores(0);
		setTime(0);
	}

	public Score(String player, int level, int scores, int time)
	{
		setPlayer(player);
		setLevel(level);
		setScores(scores);
		setTime(time);
	}

	public int getScores()
	{
		return scores;
	}

	public int getTime()
	{
		return time;
	}

	public void setScores(int scores)
	{
		this.scores = scores;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	public String getPlayer()
	{
		return player;
	}

	public int getLevel()
	{
		return level;
	}

	public void setPlayer(String player)
	{
		this.player = player;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public void printInfo()
	{
		System.out.println("Player: " + getPlayer() + ", Level: " + getLevel() + ", Scores: "
				+ getScores() + ", Time: " + getTimeToClock());
	}

	@Override
	public int compareTo(Score score)
	{
		if (this.scores > score.getScores())
			return -1;
		else if (this.scores < score.getScores())
			return 1;
		else
			return 0;
	}

	public String getTimeToClock()
	{
		int hh = time / 3600;
		int mm = (int) ((((float) time / 3600) - hh) * 60);
		int ss = (int) ((((((float) time / 3600) - hh) * 60) - mm) * 60);
		return "\"" + hh + ":" + mm + ":" + ss;
	}
}
