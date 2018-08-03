package com.jsmy.pingshan.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 信息工具类
 * 
 * @ClassName: MesssageUtil
 * @Description: TODO
 * @author xiaoxiao
 * @date 2015-5-12 下午1:35:40
 *
 */
public class MesssageUtil {

	/**
	 * 打电话
	 * 
	 * @param context
	 * @param phone
	 */
	public static void call(Context context, String phone) {
		Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + phone));// 自定义的Intent
		context.startActivity(intent);
	}

	/**
	 * 发短信
	 * 
	 * @param phone_number
	 * @param phone_number
	 */
	public static void sendSMS(Context context, String phone_number) {
		Uri smsToUri = Uri.parse("smsto:" + phone_number);
		Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
		context.startActivity(mIntent);
	}
}
