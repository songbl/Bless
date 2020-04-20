package com.example.parttime.utils;

import android.util.Log;

import com.example.parttime.BuildConfig;

public class LogUtil {
	private static final String DEFULT_TAG = "===log===";

	public static void v(String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.v(DEFULT_TAG, msg);
		}
	}

	public static void d(String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.d(DEFULT_TAG, msg);
		}
	}

	public static void i(String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.i(DEFULT_TAG, msg);
		}
	}

	public static void w(String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.w(DEFULT_TAG, msg);
		}
	}

	public static void e(String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.e(DEFULT_TAG, msg);
		}
	}


	public static void i(String tag, String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.i(tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.e(tag, msg);
		}
	}
	public static void v(String tag, String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.v(tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (BuildConfig.NEED_PRINT_LOG) {
			Log.d(tag, msg);
		}
	}
}
