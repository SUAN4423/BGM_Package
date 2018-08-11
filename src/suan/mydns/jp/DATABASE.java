package suan.mydns.jp;

import java.util.ArrayList;

public class DATABASE
{
	public ArrayList<Byte	> Volume = new ArrayList<>();
	public ArrayList<Double	> Freque = new ArrayList<>();
	public ArrayList<Integer> FrequI = new ArrayList<>();
	public ArrayList<Long	> Time	 = new ArrayList<>();
	public ArrayList<Integer> SoundT = new ArrayList<>();
	public ArrayList<Float	> Duty	 = new ArrayList<>();
	public ArrayList<Double	> Voldow = new ArrayList<>();
	public ArrayList<Double	> Fredow = new ArrayList<>();
	public ArrayList<Integer> VolDUM = new ArrayList<>();
	public boolean loop = false;
	public boolean start = false;
	public long starttime = 0;
	public long time = 0;
	public int[] nowNotes = {0, 0, 0, 0};
}
