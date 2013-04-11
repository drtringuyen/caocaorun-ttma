package test.touch;

class TimeSystem {
	private long min;
	private long sec;
	private int base;
	
	public TimeSystem(){
		min =0;
		sec =0;
		base=59000;
	}
	
	public void setTime(long min, long sec){
		if(sec*1000>base){
			this.min = min+1;
			this.sec = sec*1000 - base-1000;
		}
		else{
			this.min=min;
			this.sec=sec*1000;
		}
	}
	public void increasingTime(long delayTime){
		if(sec<base){
		sec=sec+delayTime;
		}
		else{
			min++;
			sec=0;
		}
	}
	
	public void decreasingTime(long delayTime){
		if(sec>0){
			sec-=delayTime;
		}
		else{
			sec=base;
			min--;
		}
	}
	
	public long showSec(){
		return sec/1000;
	}
	
	public long showMin(){
		return min;
	}
}
