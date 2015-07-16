package com.dream.timetask.db;

/**
 * 数据库信息类，包括数据库库名、数据库版本号、数据库各表表名及各表操作语句
 * 
 * @author 温坤哲
 * 
 */
public class DBInfo {

	public static class DB {

		/**
		 * 数据库名称
		 */
		public static final String DB_NAME = "TimeTask.db";

		/**
		 * 数据库版本
		 */
		public static final int VERSION = 1;

	}

	public static class Table {

		/**
		 * 任务表名称
		 */
		public static final String TASK_TABLE = "TimeTask";

		/**
		 * 主键
		 */
		public static final String _ID = "_id";

		/**
		 * 任务开始时间小时
		 */
		public static final String START_HOUR = "startHour";

		/**
		 * 任务开始时间分钟
		 */
		public static final String START_MINUTE = "startMinute";

		/**
		 * 任务结束时间小时
		 */
		public static final String END_HOUR = "endHour";

		/**
		 * 任务结束时间分钟
		 */
		public static final String END_MINUTE = "endMinute";

		/**
		 * 重复时间
		 */
		public static final String REPET = "repet";

		/**
		 * 创建任务
		 */
		public static final String CREATE_USER_TABLE = "create table "
				+ TASK_TABLE + "(" + _ID
				+ " integer primary key autoincrement," + START_HOUR
				+ " integer," + START_MINUTE + " integer," + END_HOUR
				+ " integer," + END_MINUTE + " integer," + REPET + " integer"
				+ ");";

	}

}
