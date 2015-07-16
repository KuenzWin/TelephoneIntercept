package com.dream.timetask.domin;

/**
 * 所有具体任务类的父类，成员变量包括id号，开始时间，主要用于继承
 * 
 * @author 温坤哲
 * 
 */
public class TimeTask {

	protected int _id;
	protected int startHour;
	protected int startMinute;

	public TimeTask() {
		super();
	}

	public TimeTask(int startHour, int startMinute) {
		this.startHour = startHour;
		this.startMinute = startMinute;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_id() {
		return _id;
	}

	public void setStartHour(int startHour) {
		this.startHour = startHour;
	}

	public int getStartHour() {
		return startHour;
	}

	public int getStartMinute() {
		return startMinute;
	}

	public void setStartMinute(int startMinute) {
		this.startMinute = startMinute;
	}

}
