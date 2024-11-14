#include <jni.h>
#include "terminal.h"

JNIEXPORT void JNICALL Java_ter_terminal_init(JNIEnv *env, jclass clazz) {
	init();
}
JNIEXPORT void JNICALL Java_ter_terminal_setInstantInputMode(JNIEnv *env, jclass clazz, jboolean enable) {
	setInstantInputMode(enable);
}
JNIEXPORT jint JNICALL Java_ter_terminal_instantGetChar(JNIEnv *env, jclass clazz) {
	return instantGetChar();
}
