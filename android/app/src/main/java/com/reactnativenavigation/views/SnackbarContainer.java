package com.reactnativenavigation.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

import com.reactnativenavigation.events.ScreenChangeBroadcastReceiver;
import com.reactnativenavigation.params.SnackbarParams;

public class SnackbarContainer extends CoordinatorLayout implements Snakbar.OnDismissListener, ScreenChangeBroadcastReceiver.OnScreenChangeListener {

    private Snakbar snakbar;
    private ScreenChangeBroadcastReceiver screenChangeBroadcastReceiver;

    public SnackbarContainer(Context context) {
        super(context);
        registerTabSelectedReceiver();
    }

    private void registerTabSelectedReceiver() {
        screenChangeBroadcastReceiver = new ScreenChangeBroadcastReceiver(this);
        screenChangeBroadcastReceiver.register();
    }

    public void showSnackbar(final String navigatorEventId, final SnackbarParams params) {
        snakbar = new Snakbar(this, navigatorEventId, params);
        snakbar.show();
    }

    public void onScreenChange() {
        if (snakbar != null) {
            snakbar.dismiss();
            snakbar = null;
        }
    }

    @Override
    public void onDismiss() {
        snakbar = null;
    }

    public void destroy() {
        screenChangeBroadcastReceiver.unregister();
    }

    @Override
    public void onScreenChangeListener() {
        onScreenChange();
    }
}
