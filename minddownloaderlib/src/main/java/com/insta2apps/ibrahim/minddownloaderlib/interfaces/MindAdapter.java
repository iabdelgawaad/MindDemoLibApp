package com.insta2apps.ibrahim.minddownloaderlib.interfaces;

/**
 * Created by Ibrahim AbdelGawad on 4/8/2018.
 */

public interface MindAdapter<S, D> {
    D adapt(S source) throws Exception;
}
