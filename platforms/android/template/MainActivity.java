/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package <packageName>;

import android.os.Bundle;
import org.apache.cordova.*;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import java.util.HashMap;

public class MainActivity extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", true)) {
            // moveTaskToBack(true);
        }
        if(!QbSdk.canLoadX5(this)) {
            HashMap map = new HashMap();
            map.put(TbsCoreSettings.TBS_SETTINGS_USE_PRIVATE_CLASSLOADER, true);
            QbSdk.setDownloadWithoutWifi(true);
            QbSdk.initTbsSettings(map);
            QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
                @Override
                public void onCoreInitFinished() {
                    System.out.println(" tbs init ok1");
                }

                @Override
                public void onViewInitFinished(boolean b) {
                    System.out.println(" tbs init view1: " + b);
                }
            });
            System.out.println("no can load x5.");
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("can load x5.");
        }
        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }
}
