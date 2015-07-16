package com.dream.timetask.domin;

/**
 * 来电拦截任务类，包含开始时间(继承于父类)、结束时间、重复的星期数
 * 
 * @author 温坤哲
 * 
 */
public class InterceptTask extends TimeTask {

	private int endHour;
	private int endMinute;
	private int repet;

	public InterceptTask() {
		super();
	}

	public InterceptTask(int startHour, int startMinute, int endHour,
			int endMinute) {
		super(startHour, startMinute);
		this.endHour = endHour;
		this.endMinute = endMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public int getRepet() {
		return repet;
	}

	public void setRepet(int repet) {
		this.repet = repet;
	}

	@Override
	public String toString() {
		return "TimeTask [startHour=" + startHour + ", startMinute="
				+ startMinute + ", endHour=" + endHour + ", endMinute="
				+ endMinute + ", repet=" + repet + "]";
	}

}
