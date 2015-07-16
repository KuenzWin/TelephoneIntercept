package com.dream.timetask.domin;

/**
 * 短信发送任务类
 * 
 * @author 温坤哲
 * 
 */
public class SmsTask extends TimeTask {

	public String content;

	public SmsTask(int startHour, int startMinute, String content) {
		super(startHour, startMinute);
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
