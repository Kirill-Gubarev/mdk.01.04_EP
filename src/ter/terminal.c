#include "terminal.h"
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

#if defined (__linux__)
#include <termios.h>
#include <unistd.h>
#elif defined (_win32)
#include <windows.h>
#include <conio.h>
#endif

#if defined (__linux__)
static struct termios oldSettings;
static void __setInstantInputMode(uint8_t enable);
static void __setCursorVisibility(uint8_t enable);
static void __setAltBuf(uint8_t enable);
#endif

static uint8_t instantMode = 0;
static uint8_t cursorMode = 0;
static uint8_t altBufMode = 0;

static void __terminate(int s){
	terminate();
	exit(s);
}
static void suspend(int s){
#if defined (__linux__)
	__setInstantInputMode(0);
	__setCursorVisibility(1);
	__setAltBuf(0);
    signal(SIGTSTP, SIG_DFL);
    raise(SIGTSTP);
#endif
}
static void restore(int s){
#if defined (__linux__)
	__setInstantInputMode(instantMode);
	__setCursorVisibility(cursorMode);
	__setAltBuf(altBufMode);
	signal(SIGTSTP, suspend);
#endif
}

void setCursorPos(int x, int y){
	printf("\033[%d;%dH", y, x);
	fflush(0);
}
void __setCursorVisibility(uint8_t enable){
	if(enable)
		printf("\033[?25h");
	else
		printf("\033[?25l");
	fflush(0);
}
void setCursorVisibility(uint8_t enable){
	cursorMode = enable;
	__setCursorVisibility(enable);
}
void __setAltBuf(uint8_t enable){
	if(enable)
		printf("\033[?1049h");
	else
		printf("\033[?1049l");
	fflush(0);
}
void setAltBuf(uint8_t enable){
	altBufMode = enable;
	__setAltBuf(enable);
}


#if defined (__linux__)
void init(){
	tcgetattr(STDIN_FILENO, &oldSettings);
	signal(SIGINT, __terminate);
	signal(SIGTSTP, suspend);
	signal(SIGCONT, restore);
}
#elif defined (_WIN32)
void init(){
	DWORD mode;
	GetConsoleMode(hConsole, &mode);
	SetConsoleMode(hConsole, mode | ENABLE_VIRTUAL_TERMINAL_PROCESSING);

	signal(SIGINT, __terminate);
	signal(SIGTSTP, suspend);
	signal(SIGCONT, restore);
}
#endif

#if defined (__linux__)
void terminate(){
	setInstantInputMode(0);
	setCursorVisibility(1);
	setAltBuf(0);
}
#elif defined (_WIN32)
void terminate(){
	setInstantInputMode(0);
	setCursorVisibility(1);
	setAltBuf(0);
}
#endif

#if defined (__linux__)
void __setInstantInputMode(uint8_t enable){
	if(enable){
		struct termios newSettings = oldSettings;
		newSettings.c_lflag &= ~(ICANON | ECHO);
		tcsetattr(STDIN_FILENO, TCSANOW, &newSettings);
	}
	else{
		tcsetattr(STDIN_FILENO, TCSANOW, &oldSettings);
	}
}
void setInstantInputMode(uint8_t enable){
	instantMode = enable;
	__setInstantInputMode(enable);
}
#elif defined (_WIN32)
void setInstantInputMode(uint8_t enable){

}
#endif

#if defined (__linux__)
int instantGetChar(){
	return getchar();
}
#elif defined (_WIN32)
int instantGetChar(){
	return _getch();
}
#endif
