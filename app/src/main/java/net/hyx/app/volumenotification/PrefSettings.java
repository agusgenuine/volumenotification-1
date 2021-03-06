/*
 * Copyright (C) 2017 Seht (R) Hyx Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.hyx.app.volumenotification;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.preference.PreferenceManager;

class PrefSettings {

    private Resources resources;
    private SharedPreferences preferences;

    PrefSettings(Context context) {
        resources = context.getResources();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    SharedPreferences.Editor edit() {
        return preferences.edit();
    }

    int getAppTheme() {
        if (getAppThemeDark()) {
            return R.style.style_app_theme_dark;
        }
        return R.style.style_app_theme_light;
    }

    boolean getDialogAlertNonceChecked(int pref_key) {
        return preferences.getBoolean("pref_dialog_alert_nonce_checked_" + pref_key, false);
    }

    boolean getAppThemeDark() {
        boolean default_value = resources.getBoolean(R.bool.pref_dark_app_theme_default);
        return preferences.getBoolean("pref_dark_app_theme", default_value);
    }

    boolean getEnabled() {
        boolean default_value = resources.getBoolean(R.bool.pref_enabled_default);
        return preferences.getBoolean("pref_enabled", default_value);
    }

    boolean getTransparent() {
        boolean default_value = resources.getBoolean(R.bool.pref_translucent_default);
        return preferences.getBoolean("pref_translucent", default_value);
    }

    boolean getHideStatus() {
        boolean default_value = resources.getBoolean(R.bool.pref_hide_status_default);
        return preferences.getBoolean("pref_hide_status", default_value);
    }

    boolean getToggleMute() {
        boolean default_value = resources.getBoolean(R.bool.pref_toggle_mute_default);
        return preferences.getBoolean("pref_toggle_mute", default_value);
    }

    boolean getToggleSilent() {
        boolean default_value = resources.getBoolean(R.bool.pref_toggle_silent_default);
        return preferences.getBoolean("pref_toggle_silent", default_value);
    }

    boolean getTopPriority() {
        boolean default_value = resources.getBoolean(R.bool.pref_top_priority_default);
        return preferences.getBoolean("pref_top_priority", default_value);
    }

    boolean getHideLocked() {
        boolean default_value = resources.getBoolean(R.bool.pref_hide_locked_default);
        return preferences.getBoolean("pref_hide_locked", default_value);
    }

    boolean getButtonChecked(int pos) {
        String pref_key = "pref_buttons_checked_btn_" + pos;
        int default_res = resources.getIdentifier(pref_key + "_default", "bool", getClass().getPackage().getName());
        boolean default_value = resources.getBoolean(default_res);
        return preferences.getBoolean(pref_key, default_value);
    }

    int getButtonSelection(int pos) {
        int default_value = pos - 1;
        return preferences.getInt("pref_buttons_selection_btn_" + pos, default_value);
    }

    int getButtonIcon(int pos) {
        int default_value = pos - 1;
        return preferences.getInt("pref_buttons_icon_btn_" + pos, default_value);
    }

    String getButtonLabel(int pos) {
        int btn_sel = getButtonSelection(pos) + 1;
        int resource = resources.getIdentifier("btn_sel_" + btn_sel + "_label", "string", getClass().getPackage().getName());
        return resources.getString(resource);
    }

    String getTheme() {
        String default_value = resources.getString(R.string.pref_theme_default);
        return preferences.getString("pref_theme", default_value);
    }

    String getCustomThemeBackgroundColor() {
        String default_value = resources.getString(R.string.pref_custom_theme_background_color_default);
        return preferences.getString("pref_custom_theme_background_color", default_value);
    }

    String getCustomThemeIconColor() {
        String default_value = resources.getString(R.string.pref_custom_theme_icon_color_default);
        return preferences.getString("pref_custom_theme_icon_color", default_value);
    }

    int getColor(String pref_value) {
        int color = 0;
        try {
            if (!pref_value.isEmpty()) {
                color = Color.parseColor(pref_value);
            }
        } catch (IllegalArgumentException e) {
            //
        }
        return color;
    }

    int getDrawable(Context context, int resource, int pos) {
        int drawable;
        TypedArray drawables = context.getResources().obtainTypedArray(resource);
        drawable = drawables.getResourceId(pos, 0);
        drawables.recycle();
        return drawable;
    }

}
