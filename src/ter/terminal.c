#include "terminal.h"
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

#if defined (__linux__)
#include <termios.h>
#include <unistd.h>
#elif defined (_win32)
#include <conio.h>
#endif

#if defined (__linux__)
static struct termios oldSettings;
static void setInstantInput(uint8_t enable);
#endif

static uint8_t instantMode = 0;

static void terminate(int s){
	setInstantInput(0);
	exit(s);
}
static void suspend(int s){
#if defined (__linux__)
	setInstantInput(0);
    signal(SIGTSTP, SIG_DFL);
    raise(SIGTSTP);
#endif
}
static void restore(int s){
#if defined (__linux__)
	setInstantInput(instantMode);
	signal(SIGTSTP, suspend);
#endif
}

#if defined (__linux__)
void init(){
	tcgetattr(STDIN_FILENO, &oldSettings);
	signal(SIGINT, terminate);
	signal(SIGTSTP, suspend);
	signal(SIGCONT, restore);
}
#elif defined (_WIN32)
void init(){
	signal(SIGINT, terminate);
	signal(SIGTSTP, suspend);
	signal(SIGCONT, restore);
}
#endif

#if defined (__linux__)
void setInstantInput(uint8_t enable){
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
	setInstantInput(enable);
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
