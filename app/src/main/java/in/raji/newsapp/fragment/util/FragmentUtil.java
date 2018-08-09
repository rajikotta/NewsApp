package in.raji.newsapp.fragment.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import javax.annotation.Nullable;

import in.raji.newsapp.MainActivity;

public class FragmentUtil {

    public static void replace(FragmentActivity activity, Fragment fragment, int id, boolean animate, @Nullable String backStackTag) {
        FragmentTransaction fragmentTransaction = activity.getSupportFragmentManager().beginTransaction();
        if(backStackTag!=null)
        fragmentTransaction.addToBackStack(backStackTag);
        if(animate)
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(id, fragment).commit();
    }
}
