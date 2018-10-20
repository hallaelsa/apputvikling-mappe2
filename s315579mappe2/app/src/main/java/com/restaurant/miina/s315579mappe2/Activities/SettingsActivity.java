package com.restaurant.miina.s315579mappe2.Activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.restaurant.miina.s315579mappe2.NotificationService;
import com.restaurant.miina.s315579mappe2.PeriodicalService;
import com.restaurant.miina.s315579mappe2.R;

import java.time.Period;


public class SettingsActivity extends AppCompatActivity {
    ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().replace(R.id.preferenceFrameLayout,new
                PrefsFragment()).commit();


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(R.string.SettingsHeader);

    }

    public static class PrefsFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            configureSMScheckbox();
            configureSetEnable();
            configureSetTime();
            configureMessage();

        }

        private void configureSetEnable() {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            boolean smsCheck = prefs.getBoolean("SMScheckbox",false);
            if(smsCheck) {
                getPreferenceScreen().findPreference("setHour").setEnabled(true);
                getPreferenceScreen().findPreference("setMinute").setEnabled(true);
                getPreferenceScreen().findPreference("setSMSmessage").setEnabled(true);
            } else {
                getPreferenceScreen().findPreference("setHour").setEnabled(false);
                getPreferenceScreen().findPreference("setMinute").setEnabled(false);
                getPreferenceScreen().findPreference("setSMSmessage").setEnabled(false);
            }
        }

        private void configureMessage() {
            Preference messageSettings = (Preference) findPreference("setSMSmessage");
            messageSettings.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object object) {
                    // must restart service when message is updated
                    stopService();
                    startService();
                    return true;
                }
            });
        }

        private void configureSMScheckbox() {
            Preference notificationSettings = (Preference) findPreference("SMScheckbox");
            notificationSettings.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object object) {
                    boolean notificationON = ((Boolean) object).booleanValue();
                    if(notificationON) {
                        getPreferenceScreen().findPreference("setHour").setEnabled(true);
                        getPreferenceScreen().findPreference("setMinute").setEnabled(true);
                        getPreferenceScreen().findPreference("setSMSmessage").setEnabled(true);
                        startService();
                    } else {
                        getPreferenceScreen().findPreference("setHour").setEnabled(false);
                        getPreferenceScreen().findPreference("setMinute").setEnabled(false);
                        getPreferenceScreen().findPreference("setSMSmessage").setEnabled(false);
                        stopService();
                    }
                    return true;
                }
            });
        }

        private void configureSetTime() {
            Preference hourSettings = (Preference) findPreference("setHour");
            hourSettings.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object object) {
                    // must restart service when new hour is set
                    stopService();
                    startService();
                    return true;
                }
            });

            Preference minuteSettings = (Preference) findPreference("setMinute");
            minuteSettings.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object object) {
                    // must restart service when new minute is set
                    stopService();
                    startService();
                    return true;
                }
            });
        }

        public void startService() {
            Log.d("PrefsFragment","i startService()");
            Intent intent = new Intent();
            intent.setAction("com.restaurant.miina.custombroadcast");
            getActivity().getApplicationContext().sendBroadcast(intent);
            Intent i = new Intent(getActivity().getApplicationContext(), PeriodicalService.class);
            getActivity().getApplicationContext().startService(i);
        }

        public void stopService() {
            Log.d("PrefsFragment","i stopService()");

            Intent i = new Intent(getActivity().getApplicationContext(), NotificationService.class);
            PendingIntent pIntent = PendingIntent.getService(getActivity().getApplicationContext(),
                    0, i, 0);
            AlarmManager alarm =(AlarmManager) getActivity().getApplicationContext()
                    .getSystemService(Context.ALARM_SERVICE);
            if(alarm!= null) {
                alarm.cancel(pIntent);
            }
        }
    }
}
