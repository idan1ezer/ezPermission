package com.example.ezpermission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionManager {
    private static final int REQUEST_CODE_PERMISSIONS = 100;
    private final Activity mActivity;
    private final PermissionResultListener mListener;
    private final String mRationaleMessage;

    public interface PermissionResultListener {
        void onPermissionGranted();
        void onPermissionDenied(List<String> deniedPermissions);
    }

    public PermissionManager(Activity activity, PermissionResultListener listener,
                             String rationaleMessage) {
        mActivity = activity;
        mListener = listener;
        mRationaleMessage = rationaleMessage;
    }

    public void requestPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> deniedPermissions = new ArrayList<>();
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(mActivity, permission)
                        != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission);
                }
            }

            if (deniedPermissions.isEmpty()) {
                mListener.onPermissionGranted();
            } else {
                boolean shouldShowRationale = false;
                for (String permission : deniedPermissions) {
                    shouldShowRationale = shouldShowRationale ||
                            ActivityCompat.shouldShowRequestPermissionRationale(mActivity, permission);
                }

                if (shouldShowRationale) {
                    showRationaleDialog(deniedPermissions);
                } else {
                    ActivityCompat.requestPermissions(mActivity,
                            deniedPermissions.toArray(new String[0]),
                            REQUEST_CODE_PERMISSIONS);
                }
            }
        } else {
            mListener.onPermissionGranted();
        }
    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            List<String> deniedPermissions = new ArrayList<>();
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.isEmpty()) {
                mListener.onPermissionGranted();
            } else {
                mListener.onPermissionDenied(deniedPermissions);
            }
        }
    }

    private void showRationaleDialog(final List<String> permissions) {
        new AlertDialog.Builder(mActivity)
                .setTitle(mRationaleMessage)
                .setMessage("This app needs these permissions to function properly.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(mActivity,
                                permissions.toArray(new String[0]),
                                REQUEST_CODE_PERMISSIONS);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListener.onPermissionDenied(permissions);
                    }
                })
                .setCancelable(false)
                .show();
    }
}

