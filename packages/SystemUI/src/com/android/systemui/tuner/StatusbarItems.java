/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.android.systemui.tuner;

import android.content.ContentResolver;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v14.preference.SwitchPreference;
import android.support.v14.preference.PreferenceFragment;
import com.android.systemui.R;

public class StatusbarItems extends PreferenceFragment {
    
    private static final String KEY_STATUS_BAR_LOGO = "status_bar_logo";

	private SwitchPreference mShowAncientLogo;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContentResolver resolver = getActivity().getContentResolver();

        mShowAncientLogo = (SwitchPreference) findPreference(KEY_STATUS_BAR_LOGO);
        mShowAncientLogo.setChecked((Settings.System.getInt(resolver, Settings.System.STATUS_BAR_LOGO, 1) == 1));
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.statusbar_items);
    }
    
    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        if  (preference == mShowAncientLogo) {
            boolean checked = ((SwitchPreference)preference).isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_LOGO, checked ? 1:0);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }
}
