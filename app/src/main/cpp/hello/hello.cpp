#include <jni.h>
#include <String>
#include "include/hello.h"

extern "C"{
jstring
Java_com_ljy_mutjnidemo_jni_Hello_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = sayHello();
    return env->NewStringUTF(hello.c_str());
}
}