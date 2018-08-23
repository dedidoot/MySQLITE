package com.lenovo.mysqlite;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Lenovo on 8/23/2018.
 */

public class ShowLoadingCustom {

    private Dialog dialogLoading;
    private Context context;
    private View view;

    public ShowLoadingCustom(Context mContext) {
        this.context = mContext;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        view = View.inflate(context, R.layout.dialog_loading_default, null);
        builder.setView(view);
        dialogLoading = builder.create();
        dialogLoading.setCancelable(true);
        dialogLoading.setCanceledOnTouchOutside(false);
        setLoadingSoco();
    }

    public void hideTextStatus() {
        view.findViewById(R.id.text_status_loading).setVisibility(View.GONE);
    }

    public void setLoadingSoco() {
        ProgressBar pgs = view.findViewById(R.id.custom_pgs);
    }

    public Dialog getDialogLoading() {
        return dialogLoading;
    }

    public void show() {
        dialogLoading.show();
    }

    public void hide() {
        dialogLoading.dismiss();
    }

}
