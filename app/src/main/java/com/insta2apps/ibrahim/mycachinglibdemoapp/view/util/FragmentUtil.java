package com.insta2apps.ibrahim.mycachinglibdemoapp.view.util;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public class FragmentUtil {

    //===================================================================================//

    /**
     * Replacing fragment into specified layout with specified animation. Replaced fragment is
     * added to back stack if a valid fragment tag is passed. If fragment tag is NULL, fragment is
     * not added to back stack. <br />
     * If no animation specified, transaction is done with default animation of : <br />
     * <li>enter animation: slide in right to left</li>
     * <li>exit animation: slide out right to left</li>
     * <li>Back animation: reverse enter and exit animations listed above</li>
     *
     * @param FragmentManager   manager used to perform transaction
     * @param containerResId    resource id where fragment should be placed
     * @param fragmentToReplace fragment to replace/display
     * @param fragmentTag       tag used to add fragment to back stack. When NULL, fragment is NOT added to stack
     */
    public static void replaceFragment(FragmentManager FragmentManager, @IdRes int containerResId,
                                       Fragment fragmentToReplace, String fragmentTag) {

        FragmentTransaction transaction = FragmentManager.beginTransaction();

        //adding transaction with tag to facilitate removal and retrieval of specific fragment from transactions list
        transaction.replace(containerResId, fragmentToReplace, fragmentToReplace.getClass().getName());

        if (fragmentTag != null) {
            transaction.addToBackStack(fragmentTag);
        }

        transaction.commit();
    }//end replaceFragment

    /**
     * Adds fragment to a container layout. fragment is added to back stack if tag != null
     *
     * @param FragmentManager   manager used to perform transaction
     * @param containerId       resource id where fragment should be placed
     * @param fragmentToBeAdded fragment to add/display
     * @param tag               String added to back stack
     */
    public static void addFragment(FragmentManager FragmentManager, int containerId, Fragment fragmentToBeAdded, String tag) {
        FragmentTransaction FragmentTransaction = FragmentManager.beginTransaction();
        FragmentTransaction.add(containerId, fragmentToBeAdded, fragmentToBeAdded.getClass().getName());
        if (tag != null) {
            FragmentTransaction.addToBackStack(tag);
        }
        FragmentTransaction.commit();
    }

    public static void reAttachCurrentFragment(Fragment fragment) {
        FragmentManager currentFragmentManager;
        if (fragment.getParentFragment() != null) {
            currentFragmentManager = fragment.getParentFragment().getChildFragmentManager();
        } else {
            currentFragmentManager = fragment.getFragmentManager();
        }
        Fragment currentFragment = currentFragmentManager.findFragmentByTag(fragment.getClass().getName());
        FragmentTransaction fragTransaction = currentFragmentManager.beginTransaction();
        fragTransaction.detach(currentFragment);
        fragTransaction.attach(currentFragment);
        fragTransaction.commitAllowingStateLoss();
    }

    public static Fragment getCurrentFragment(FragmentManager fragmentManager, int containerId) {
        return fragmentManager.findFragmentById(containerId);
    }
}
