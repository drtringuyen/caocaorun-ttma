package test.touch;

class TimeSystem {
	private int min;
	private int sec;
	private int base;
	
	public TimeSystem(){
		min =0;
		sec =0;
		base=59000;
	}
	
	public void setTime(int min, int sec){
		this.min=min;
		this.sec=sec*1000;
	}
	public void increasingTime(int delayTime){
		if(sec<base){
		sec=sec+delayTime;
		}
		else{
			min++;
			sec=0;
		}
	}
	
	public void decreasingTime(int delayTime){
		if(sec>0){
			sec-=delayTime;
		}
		else{
			sec=base;
			min--;
		}
	}
	
	public int showSec(){
		return sec/1000;
	}
	
	public int showMin(){
		return min;
	}
}
