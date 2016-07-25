package com.example.ywb.mytreasure.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ActivityUtils {
    private WeakReference<Activity> activityWeakReference;
    private WeakReference<Fragment> fragmentWeakReference;
    private Toast toast;

    public ActivityUtils(Activity activity){
        activityWeakReference=new WeakReference<>(activity);
    }
    public ActivityUtils(Fragment fragment){
        fragmentWeakReference=new WeakReference<>(fragment);
    }

    @Nullable
    private Activity getActivity(){
        if (activityWeakReference!=null){
            return activityWeakReference.get();
        }
        if (fragmentWeakReference!=null){
            Fragment fragment=fragmentWeakReference.get();
            return  fragment==null?null:fragment.getActivity();
        }
        return null;
    }
    /**activity的跳转*/
    public  void startActivity(Class<? extends Activity> cls){
        Activity activity=getActivity();
        if (activity==null)
            return;
        Intent intent=new Intent(activity,cls);
        activity.startActivity(intent);
    }

    public void showToast(CharSequence msg){
        Activity activity=getActivity();
        if (activity!=null){
            if (toast==null){
                toast=Toast.makeText(activity,msg,Toast.LENGTH_SHORT);
                toast.setText(msg);
                toast.show();
            }
        }
    }

    public void hideSoftKeyboard(){
        Activity activity=getActivity();
        if (activity==null)
            return;
        View view=activity.getCurrentFocus();
        if (view!=null){
            InputMethodManager imm= (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
}
