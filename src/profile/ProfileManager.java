package profile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ProfileManager
{
	private List<Profile> profiles;

	public ProfileManager() throws IOException
	{
		profiles = new ArrayList<Profile>();
		final BufferedReader base = new BufferedReader(new FileReader("profiles/profili.txt"));

		String tmpString = null;
		while ((tmpString = base.readLine()) != null)
		{
			Profile nextProfile;
			nextProfile = new Profile("profiles/" + tmpString + ".txt");
			profiles.add(nextProfile);
		}
	}

	public void close() throws FileNotFoundException
	{
		Iterator<Profile> iterator = profiles.iterator();
		while (iterator.hasNext())
		{
			Profile tmp = iterator.next();
			tmp.writeProfileToFile();
		}
	}

	public List<Score> getRankingLevels()
	{
		ArrayList<Score> ranking = new ArrayList<Score>();
		Iterator<Profile> iterator = profiles.iterator();
		while (iterator.hasNext())
		{
			Profile tmp = iterator.next();
			ranking.addAll(tmp.getKakariko());
			ranking.addAll(tmp.getDesert());
			ranking.addAll(tmp.getDungeon());
			ranking.addAll(tmp.getForest());
			ranking.addAll(tmp.getLake());
			ranking.addAll(tmp.getVolcano());
			ranking.addAll(tmp.getSky());
		}

		return ranking;
	}

	public List<Score> getSortedRanking()
	{
		List<Score> sortedRanking = getRankingLevels();
		Collections.sort(sortedRanking);
		return sortedRanking;
	}

	public void printBestRanking()
	{
		List<Score> sortedRanking = getSortedRanking();
		Iterator<Score> iterator = sortedRanking.iterator();
		while (iterator.hasNext())
		{
			Score tmp = iterator.next();
			tmp.printInfo();
		}
	}

	public void deleteProfile(String nameProfile) throws FileNotFoundException
	{
		boolean deleted = false;
		Iterator<Profile> iterator = profiles.iterator();
		while (iterator.hasNext() && !deleted)
		{
			Profile tmp = iterator.next();
			if (tmp.getName().equals(nameProfile))
			{
				profiles.remove(tmp);
				deleted = true;
			}
		}
		PrintWriter printWriter = new PrintWriter("profiles/profili.txt");
		iterator = profiles.iterator();
		while (iterator.hasNext())
		{
			printWriter.println(iterator.next().getName());
		}
		printWriter.close();
	}

	public List<Profile> getProfiles()
	{
		return profiles;
	}

	public void setProfiles(List<Profile> profiles)
	{
		this.profiles = profiles;
	}

	public static void main(String[] args)
	{
		// Profile p = new Profile();
		// p.setName("Aloisius");
		// try
		// {
		// p.writeProfileToFile();
		// }
		// catch (FileNotFoundException exception)
		// {
		// // TODO Auto-generated catch block
		// exception.printStackTrace();
		// }

		try
		{
			ProfileManager profileManager = new ProfileManager();
			profileManager.printBestRanking();
		}
		catch (IOException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

		// Profile p;
		// try
		// {
		// p = new Profile("profiles/Link.txt");
		// p.printProfile();
		// }
		// catch (IOException exception)
		// {
		// // TODO Auto-generated catch block
		// exception.printStackTrace();
		// }

	}
}
