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
	setCursorPos(x, y);
}
JNIEXPORT void JNICALL Java_ter_terminal_setCursorVisibility(JNIEnv *env, jclass clazz, jboolean enable) {
	setCursorVisibility(enable);
}
JNIEXPORT void JNICALL Java_ter_terminal_setAltBuf(JNIEnv *env, jclass clazz, jboolean enable) {
	setAltBuf(enable);
}
