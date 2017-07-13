package com.wotou.SharePay;

import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.platformtools.BackwardSupportUtil.BitmapFactory;
import com.unity3d.player.UnityPlayerActivity;

import android.graphics.Bitmap;
import android.os.Bundle;

public class WeChatShare extends UnityPlayerActivity
{

	private String callbackGameobject;
	private String WxAppID;
	private IWXAPI api;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	}
	
	public String initappid(String callbackGameObject,String appid, int platfrom)
	{
		if(platfrom == 1)
		{
			WxAppID = appid;
			api = WXAPIFactory.createWXAPI(this, WxAppID, true);
			api.registerApp(WxAppID);
		}
		callbackGameobject = callbackGameObject;
		
		return callbackGameobject + "_" + WxAppID;
	}
	
	public void shareToWeChat(String title, String url, String describe)
	{
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = "http://wotou.pw";
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = "sharePay";
		msg.description = "readme";
		
		//Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.send_music_thumb);
		//Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE, THUMB_SIZE, true);
		//bmp.recycle();
		//msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
		
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene = SendMessageToWX.Req.WXSceneSession;
		api.sendReq(req);
		
		finish();
	}
	
	private String buildTransaction(final String type) 
	{
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
}
