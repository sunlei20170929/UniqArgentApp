package com.store.pacific.stage.utils;

import static android.content.Context.MODE_PRIVATE;
import static android.os.Build.VERSION.SDK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import android.os.Build;
import android.os.Looper;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationUtil {
    private final static String LOCAL_GOOGLE = "local_location";
    private static final int REQUEST_CHECK_SETTINGS = 0x1;
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    private Activity context;
    private LocationCallBack locationCallBack;
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;

    public LocationUtil(Activity context) {
        this.context = context;
        init();
    }

//    public static GoogleLocation getLocalLocation(Context context) {
//        GoogleLocation googleLocation = new GoogleLocation();
//
//////        定位取值获取优先级
//////        1.LocalLocationBySDK
//////        2.LocalLocationByService
//////        3.LocalLocationByLast
//////
//////        例如，优选取值1的结果、1为空取值2、2为空取值3
////
////        googleLocation=getLocalLocationBySDK()?saveLocalLocationByService()?saveLocalLocationByLast();
//
//        return googleLocation;
//    }

//    public static void saveLocalLocationBySDK(Context context, GoogleLocation googleLocation) {
////        SharedPreferences setting_info = context.getSharedPreferences(SDK, MODE_PRIVATE);
////        SharedPreferences.Editor edit = setting_info.edit();
////        edit.putString("googleLatitude", googleLocation.getGoogleLatitude());
////        edit.putString("googleLongitude", googleLocation.getGoogleLongitude());
////        edit.commit();
//    }

//    public static void saveLocalLocationByService(Context context, GoogleLocation googleLocation) {
////        SharedPreferences setting_info = context.getSharedPreferences(Service, MODE_PRIVATE);
////        SharedPreferences.Editor edit = setting_info.edit();
////        edit.putString("googleLatitude", googleLocation.getGoogleLatitude());
////        edit.putString("googleLongitude", googleLocation.getGoogleLongitude());
////        edit.commit();
//    }

//    public static void saveLocalLocationByLast(Context context, GoogleLocation googleLocation) {
////        SharedPreferences setting_info = context.getSharedPreferences(Last, MODE_PRIVATE);
////        SharedPreferences.Editor edit = setting_info.edit();
////        edit.putString("googleLatitude", googleLocation.getGoogleLatitude());
////        edit.putString("googleLongitude", googleLocation.getGoogleLongitude());
////        edit.commit();
//    }

    public static void getSystemLocation(final Context context) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        String serviceName = Context.LOCATION_SERVICE;
                        LocationManager locationManager = (LocationManager) context.getSystemService(serviceName);
                        String provider = LocationManager.GPS_PROVIDER;
                        Location location = locationManager.getLastKnownLocation(provider);
                        double lat = location.getLatitude();
                        double lng = location.getLongitude();
                        if (String.valueOf(lng).length() > 6) {
//                            GoogleLocation googleLocation = new GoogleLocation();
//                            googleLocation.setGoogleLatitude(location.getLatitude() + "");
//                            googleLocation.setGoogleLongitude(location.getLongitude() + "");
//                            saveLocalLocationByService(context, googleLocation);
                        }


                    } else {
                    }
                } catch (Exception e) {

                }
            }
        }.start();


    }


    public void setLocationCallBack(LocationCallBack locationCallBack) {
        this.locationCallBack = locationCallBack;
    }

    private void init() {
        mRequestingLocationUpdates = false;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mSettingsClient = LocationServices.getSettingsClient(context);
        createLocationCallback();
        createLocationRequest();
        buildLocationSettingsRequest();
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(context, new OnSuccessListener<Location>() {

                        @Override
                        public void onSuccess(Location location) {
                            try {
                                if (location != null) {
//                                    GoogleLocation googleLocation = new GoogleLocation();
//                                    googleLocation.setGoogleLatitude(location.getLatitude() + "");
//                                    googleLocation.setGoogleLongitude(location.getLongitude() + "");
//                                    saveLocalLocationByLast(context, googleLocation);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void startLocation() {
        startLocationUpdates();
    }

    private void createLocationCallback() {
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                mCurrentLocation = locationResult.getLastLocation();
                stopLocationUpdates();
//                GoogleLocation googleLocation = new GoogleLocation();
//                googleLocation.setGoogleLatitude(mCurrentLocation.getLatitude() + "");
//                googleLocation.setGoogleLongitude(mCurrentLocation.getLongitude() + "");
//                saveLocalLocationBySDK(context, googleLocation);
//                if (locationCallBack != null) {
//                    locationCallBack.onLocationResult(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());
//                }

            }
        };
    }

    private void createLocationRequest() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        }

    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void startLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
        } else {
            return;
        }

        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(context, new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                    }
                })
                .addOnFailureListener(context, new OnFailureListener() {
                    @Override
                    public void onFailure(@androidx.annotation.NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        if (locationCallBack != null) {
                            locationCallBack.onGoogleapisFail("Location fail");
                        }
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(context, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                                mRequestingLocationUpdates = false;
                        }

                    }
                });
    }

    public void stopLocationUpdates() {
        if (!mRequestingLocationUpdates) {
            return;
        }
        mFusedLocationClient.removeLocationUpdates(mLocationCallback)
                .addOnCompleteListener(context, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                        mRequestingLocationUpdates = false;

                    }
                });
    }

    public abstract static class LocationCallBack {
        public void onLocationResult(double latitude, double longitude) {

        }

        public void onGoogleapisFail(String error) {

        }

        public void onCompleted() {

        }
    }
}