package com.simplemobiletools.notes.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.simplemobiletools.notes.BuildConfig;
import com.simplemobiletools.notes.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {
    @BindView(R.id.about_copyright) TextView mCopyright;
    @BindView(R.id.about_version) TextView mVersion;
    @BindView(R.id.about_email) TextView mEmailTV;

    private static Resources mRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        mRes = getResources();

        setupEmail();
        setupVersion();
        setupCopyright();
    }

    private void setupEmail() {
        final String email = mRes.getString(R.string.email);
        final String appName = mRes.getString(R.string.app_name);
        final String href = "<a href=\"mailto:" + email + "?subject=" + appName + "\">" + email + "</a>";
        mEmailTV.setText(Html.fromHtml(href));
        mEmailTV.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setupVersion() {
        final String versionName = BuildConfig.VERSION_NAME;
        final String versionText = String.format(mRes.getString(R.string.version), versionName);
        mVersion.setText(versionText);
    }

    private void setupCopyright() {
        final int year = Calendar.getInstance().get(Calendar.YEAR);
        final String copyrightText = String.format(mRes.getString(R.string.copyright), year);
        mCopyright.setText(copyrightText);
    }

    @OnClick(R.id.about_license)
    public void licenseClicked() {
        final Intent intent = new Intent(getApplicationContext(), LicenseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.about_facebook)
    public void facebookClicked() {
        String link = "https://www.facebook.com/simplemobiletools";
        try {
            getPackageManager().getPackageInfo("com.facebook.katana", 0);
            link = "fb://page/150270895341774";
        } catch (Exception ignored) {
        }
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    @OnClick(R.id.about_gplus)
    public void googlePlusClicked() {
        final String link = "https://plus.google.com/communities/104880861558693868382";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }
}