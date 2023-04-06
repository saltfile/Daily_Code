//
// Created by saltfish on 23-4-5.
//
#include <jni.h>

#ifndef _Included_org_example_capi_Myfunc
#define _Included_org_example_capi_Myfunc
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     org_MyThread
 * Method:    start
 * Signature: ()V
 */
JNIEXPORT jint JNICALL Java_org_example_capi_Myfunc_add
        (JNIEnv * env, jobject jo,jint a,jint b);

#ifdef __cplusplus
}


#endif
#endif