package com.wotou.SharePay.wxapi;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.ShowMessageFromWX;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unity3d.player.UnityPlayer;
import com.wotou.SharePay.R;
import com.wotou.SharePay.WeChatShare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

	private IWXAPI api;
	
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    //setContentView(R.layout.activity_main);
	    api = WXAPIFactory.createWXAPI(this, WeChatShare.WxAppID);
        api.handleIntent(getIntent(), this);
	}
	
	@Override  
    protected void onNewIntent(Intent intent) {  
        super.onNewIntent(intent);  
        setIntent(intent);  
        api.handleIntent(intent, this);
	}
	
	// 微信发送请求到第三方应用时，会回调到该方法
	@Override
	public void onReq(BaseReq req) 
	{
		// TODO Auto-generated method stub
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
			goToGetMsg();		
			break;
		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
			goToShowMsg((ShowMessageFromWX.Req) req);
			break;
		default:
			break;
		}
	}

	// 第三方应用发送到微信的请求处理后的响应结果，会回调到该方法
	@Override
	public void onResp(BaseResp resp) 
	{
		// TODO Auto-generated method stub
		int result = 0;
		
		//Toast.makeText(this, "openid = " + resp.openId, Toast.LENGTH_SHORT).show();
		
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			result = R.string.errcode_success;
			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
			result = R.string.errcode_cancel;
			break;
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			result = R.string.errcode_deny;
			break;
		case BaseResp.ErrCode.ERR_UNSUPPORT:
			result = R.string.errcode_unsupported;
			break;
		default:
			result = R.string.errcode_unknown;
			break;
		}
		
		//Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		Toast.makeText(this, R.string.share_ok, Toast.LENGTH_SHORT).show();
		UnityPlayer.UnitySendMessage(WeChatShare.callbackGameobject,"OnError",Integer.toString(resp.errCode));

		finish();
	}

	private void goToGetMsg() 
	{
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
	}
	
	private void goToShowMsg(ShowMessageFromWX.Req showReq) 
	{
//		WXMediaMessage wxMsg = showReq.message;		
//		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
//		
//		StringBuffer msg = new StringBuffer(); // 组织一个待显示的消息内容
//		msg.append("description: ");
//		msg.append(wxMsg.description);
//		msg.append("\n");
//		msg.append("extInfo: ");
//		msg.append(obj.extInfo);
//		msg.append("\n");
//		msg.append("filePath: ");
//		msg.append(obj.filePath);
//		
//		Intent intent = new Intent(this, SendToWXActivity.class);
//		intent.putExtra("标题", wxMsg.title);
//		intent.putExtra("消息", msg.toString());
//		intent.putExtra("thumb data", wxMsg.thumbData);
//		startActivity(intent);
//		finish();
	}
}
