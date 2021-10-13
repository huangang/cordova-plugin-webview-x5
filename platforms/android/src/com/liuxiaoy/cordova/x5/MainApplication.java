package com.liuxiaoy.cordova.x5;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import java.util.HashMap;

public class MainApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 使用独立 ClassLoader, 需要在内核初始化之前
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER, true);
		QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initTbsSettings(map);

        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                System.out.println(" tbs init ok");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                System.out.println(" tbs init view: " + b);
            }
        });
    }
}
