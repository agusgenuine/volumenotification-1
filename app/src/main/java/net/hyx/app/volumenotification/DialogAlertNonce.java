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

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DialogAlertNonce extends DialogFragment {

    private PrefSettings settings;
    private int pref_key;
    private String message;

    /*static DialogAlertNonce newInstance(String message) {
        return newInstance(0, message);
    }*/

    public static DialogAlertNonce newInstance(int pref_key, String message) {
        DialogAlertNonce frag = new DialogAlertNonce();
        Bundle args = new Bundle();
        args.putInt("pref_key", pref_key);
        args.putString("message", message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = new PrefSettings(getContext());
        pref_key = getArguments().getInt("pref_key");
        message = getArguments().getString("message");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_dialog_alert_nonce, null);
        TextView message_view = (TextView) view.findViewById(R.id.pref_dialog_alert_message);
        message_view.setText(message);

        if (pref_key != 0) {
            CheckBox nonce_checked = (CheckBox) view.findViewById(R.id.pref_dialog_alert_nonce_checked);
            nonce_checked.setChecked(settings.getDialogAlertNonceChecked(pref_key));
            nonce_checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    settings.edit().putBoolean("pref_dialog_alert_nonce_checked_" + pref_key, isChecked).apply();
                }
            });
        } else {
            LinearLayout nonce_view = (LinearLayout) view.findViewById(R.id.pref_dialog_alert_nonce);
            nonce_view.setVisibility(View.GONE);
        }

        return new AlertDialog.Builder(getContext())
                .setView(view)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }

}