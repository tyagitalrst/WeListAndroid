//
// Created by Tyagita Larasati on 2019-12-14.
//

#include <jni.h>
#include <string>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

extern "C" JNIEXPORT jint JNICALL
Java_id_ac_ui_cs_mobileprogramming_tyagitalarasati_welist_view_fragment_DashboardFragment_randomNumber(
        JNIEnv* env,
        jobject jobj) {

    int random;

    random = rand() % 4 + 1;

    return random;
}

