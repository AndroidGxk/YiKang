package com.yikangcheng.admin.yikang.activity.copy;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.sobot.chat.utils.ToastUtil;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by elha on 3/19/2018.
 * 这是一键复制的Demo---------------
 */
public class CopyButtonLibrary {

    private ClipboardManager myClipboard;
    private ClipData myClip;
    private Context context;
    private TextView textView;

    public CopyButtonLibrary(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    public void init() {
        myClipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        String text;
        text = textView.getText().toString();

        myClip = ClipData.newPlainText("text", text);
        myClipboard.setPrimaryClip(myClip);
        ToastUtils.show(text + " 已复制");
    }

}
