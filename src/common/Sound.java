package common;

import java.io.File;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound
{

	private final File sound;
	private AudioFileFormat aff;
	private AudioInputStream ais;
	private AudioFormat af;
	private Clip ol;
	private boolean soundActive;

	/**
	 * 
	 * 
	 * @param fileName
	 * @param soundActive
	 *            LA CLASSE "Sound" RICEVE IN INPUT IL NOME DEL FILE .wav DA
	 *            ESEGUIRE E CONTROLLA SE NELL'IMPOSTAZIONI E' ABILITATO L'AUDIO
	 */
	public Sound(String fileName, boolean soundActive)
	{

		this.soundActive = soundActive;

		sound = new File(fileName);

		if (!sound.canRead())
		{
			soundActive = false;
			this.soundActive = soundActive;
		}

		if (soundActive)
		{

			aff = null;
			ais = null;
			ol = null;

			try
			{
				this.aff = AudioSystem.getAudioFileFormat(sound);
			}
			catch (UnsupportedAudioFileException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try
			{
				this.ais = AudioSystem.getAudioInputStream(sound);
			}
			catch (UnsupportedAudioFileException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.af = this.aff.getFormat();

			DataLine.Info info = new DataLine.Info(Clip.class, this.ais.getFormat(),
					((int) this.ais.getFrameLength() * this.af.getFrameSize()));
			try
			{
				this.ol = (Clip) AudioSystem.getLine(info);
			}
			catch (LineUnavailableException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	/**
	 * LA FUNZIONE "play" RICEVE IN INPUT IL NUMERO DI VOLTE CHE DEVE ESSERE
	 * ESEGUITA (0 per eserguirla all'infinito)
	 */
	public void play(int numLoop)
	{

		if (soundActive)
		{
			try
			{
				this.ol.open(this.ais);
				if (numLoop == 0)
				{
					this.ol.loop(Clip.LOOP_CONTINUOUSLY);
				}
				this.ol.loop(numLoop - 1);
			}
			catch (LineUnavailableException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void stop()
	{
		this.ol.stop();
		this.ol.close();
	}
}
