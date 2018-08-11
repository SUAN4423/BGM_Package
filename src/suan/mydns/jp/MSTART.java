package suan.mydns.jp;

public class MSTART
{
	public void audio(DATABASE[] data)
	{
		// TODO 自動生成されたメソッド・スタブ
		if(data[0].start)
		{
			boolean b = false;
			data[0].time = (long) (((System.currentTimeMillis() - data[0].starttime)/1000.0)*BGM.mm2.HzMu);
			for(int i = 0; i < 4; i++)
			{
				if(data[0].nowNotes[i] < data[i].Volume.size())
				{
					b = true;
					if(data[i].Time.get(data[0].nowNotes[i]) <= data[0].time)
					{
						BGM.mm2.ChStat(data[i].Freque.get(data[0].nowNotes[i]), data[i].Duty.get(data[0].nowNotes[i]), data[i].Volume.get(data[0].nowNotes[i]), data[i].Voldow.get(data[0].nowNotes[i]), data[i].Fredow.get(data[0].nowNotes[i]), true, data[0].nowNotes[i], i, data[i].VolDUM.get(data[0].nowNotes[i]));
						if(data[0].time >= data[i].Time.get(data[0].nowNotes[i])+data[i].SoundT.get(data[0].nowNotes[i]))
						{
							data[0].nowNotes[i]++;
						}
					}
					else
					{
					}
				}
				else
				{
				}
			}
			if(!b)
			{
				if(data[0].loop)
				{
					data[0].starttime = System.currentTimeMillis();
					for(int i = 0; i < 4; i++)
					{
						data[0].nowNotes[i] = 0;
					}
				}
				else
				{
					/*for(int i = 0; i < 4; i++)
					{
						BGM.mm2.ChStat(i == 3 ? 0 : -1, 0.5f, 16, 0.0f, 1.0f, true, 0, i, 16);
					}*/
					for(int i = 0; i < 4; i++)
					{
						data[0].nowNotes[i] = 0;
					}
					data[0].start = false;
				}
			}
		}
	}
}
