#include "App.h"

#include <windows.h>

JNIEXPORT void JNICALL 
Java_App_msgbox(JNIEnv* env, jclass cls, jstring str)
{
    const char* msg = (const char*) env->GetStringUTFChars(str, NULL);
    
    MessageBox((HWND)0, msg, "From Java", MB_OK);
    
    env->ReleaseStringUTFChars(str, msg);
}
