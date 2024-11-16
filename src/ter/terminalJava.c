#include <jni.h>
#include "terminal.h"

JNIEXPORT void JNICALL Java_ter_terminal_init(JNIEnv *env, jclass clazz) {
	init();
}
JNIEXPORT void JNICALL Java_ter_terminal_terminate(JNIEnv *env, jclass clazz) {
	terminate();
}
JNIEXPORT void JNICALL Java_ter_terminal_setInstantInputMode(JNIEnv *env, jclass clazz, jboolean enable) {
	setInstantInputMode(enable);
}
JNIEXPORT jint JNICALL Java_ter_terminal_instantGetChar(JNIEnv *env, jclass clazz) {
	return instantGetChar();
}
JNIEXPORT void JNICALL Java_ter_terminal_setCursorPos(JNIEnv *env, jclass clazz, jint x, jint y) {
	setCursorPos((Point){x, y});
}
JNIEXPORT void JNICALL Java_ter_terminal_setCursorVisibility(JNIEnv *env, jclass clazz, jboolean enable) {
	setCursorVisibility(enable);
}
JNIEXPORT void JNICALL Java_ter_terminal_setAltBuf(JNIEnv *env, jclass clazz, jboolean enable) {
	setAltBuf(enable);
}

JNIEXPORT jobject JNICALL Java_ter_terminal_getCursorPos(JNIEnv *env, jclass clazz) {
    Point point = getCursorPos();

    jclass pointClass = (*env)->FindClass(env, "ter/Point");
    if (pointClass == NULL) {
        return NULL;
    }

    jmethodID constructor = (*env)->GetMethodID(env, pointClass, "<init>", "(II)V");
    if (constructor == NULL) {
        return NULL;
    }

    jobject pointObj = (*env)->NewObject(env, pointClass, constructor, point.x, point.y);
    return pointObj;
}
