package suan.mydns.jp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BGM
{
	private ArrayList<DATABASE[]> bgm = new ArrayList<>();
	private ArrayList<DATABASE[]> se = new ArrayList<>();
	private MSTART ms;
	public static MM2 mm2;
	public BGM()
	{
		mm2 = new MM2();
		ms = new MSTART();
		Timer T2 = new Timer();
		T2.scheduleAtFixedRate(new AudioTask(), 0, 1000 / (mm2.HzMu / mm2.onecool));
	}

	class AudioTask extends TimerTask
	{
		@Override
		public void run()
		{
			for(int i = 0; i < 4; i++)
			{
				mm2.ChStat(i == 3 ? 0 : -1, 0.5f, 16, 0.0f, 1.0f, true, 0, i, 16);
			}
			for(int i = 0; i < bgm.size(); i++)
			{
				ms.audio(bgm.get(i));
			}
			for(int i = 0; i < se.size(); i++)
			{
				ms.audio(se.get(i));
			}
			for(int j = 0; j < 4; j++)
			{
				mm2.Set(j);
			}
		}
	}

	public boolean isStartBGM(int number)
	{
		return bgm.get(number)[0].start;
	}

	public boolean isStartSE(int number)
	{
		return se.get(number)[0].start;
	}

	public void BGMSTART(int number)
	{
		if(!bgm.get(number)[0].start)
		{
			bgm.get(number)[0].loop = true;
			bgm.get(number)[0].starttime = System.currentTimeMillis();
			for(int i = 0; i < 4; i++)
			{
				bgm.get(number)[0].nowNotes[i] = 0;
			}
			bgm.get(number)[0].start = true;
		}
	}

	public void SESTART(int number)
	{
		if(!se.get(number)[0].start)
		{
			se.get(number)[0].loop = false;
			se.get(number)[0].starttime = System.currentTimeMillis();
			for(int i = 0; i < 4; i++)
			{
				se.get(number)[0].nowNotes[i] = 0;
			}
			se.get(number)[0].start = true;
		}
	}

	public void BGMSTOP(int number)
	{
		bgm.get(number)[0].start = false;
	}

	public void SESTOP(int number)
	{
		se.get(number)[0].start = false;
	}

	public void BGMCLEAR()
	{
		this.bgm.clear();
	}

	public void SECLEAR()
	{
		this.se.clear();
	}

	public void BGMLOAD(String[] filePath)
	{
		this.LOAD(filePath, true);
	}

	public void SELOAD(String[] filePath)
	{
		this.LOAD(filePath, false);
	}

	public void LOAD(String[] filePath, boolean BGMSE)
	{
		for(int l = 0; l < filePath.length; l++)
		{
			DATABASE[] temp = new DATABASE[4];

			for(int k = 0; k < 4; k++)
			{
				temp[k] = new DATABASE();
			}

			BufferedReader br = null;
			String str = null;
			try
			{
				File file = new File(filePath[l]);
				br = new BufferedReader(new FileReader(file));
				str = br.readLine();
				for(int i = 0; i < 4; i++)
				{
					str = br.readLine();
				}

				while(str != null)
				{
					int a = Integer.parseInt(str.substring(0, str.indexOf(",")));
					int b = str.indexOf(",");
					int c = Integer.parseInt(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					double d = Double.parseDouble(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					int e = Integer.parseInt(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					long f = Long.parseLong(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					int g = Integer.parseInt(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					double h = Double.parseDouble(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					double i = Double.parseDouble(str.substring(b+1, str.indexOf(",", b+1)));
					b = str.indexOf(",", b+1);
					double j;
					int k;
					try
					{
						j = Double.parseDouble(str.substring(b+1, str.indexOf(",", b+1)));
						b = str.indexOf(",", b+1);
						k = Integer.parseInt(str.substring(b+1));
					}
					catch(Exception es)
					{
						j = Double.parseDouble(str.substring(b+1));
						if(i < 0) k = 0;
						else k = 16;
					}

					temp[a].Volume.add((byte)c);
					temp[a].Freque.add(d);
					temp[a].FrequI.add(e);
					temp[a].Time.add(f);
					temp[a].SoundT.add(g);
					temp[a].Duty.add((float)h);
					temp[a].Voldow.add(i);
					temp[a].Fredow.add(j);
					temp[a].VolDUM.add(k);

					str = br.readLine();
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			if(BGMSE) bgm.add(temp);
			else se.add(temp);
		}
	}
}
